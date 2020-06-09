/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  DelayQueueProcessor is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.service.delayqueue;

import com.b3tuning.b3console.service.files.monitor.service.utils.ServiceUtils;
import lombok.extern.slf4j.XSlf4j;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

@XSlf4j
public class DelayQueueProcessor {

	private final      BlockingQueue<QueuedFileEvent> delayed = new DelayQueue<>();
	@Autowired private ServiceUtils                   utils;

	@PostConstruct
	public void init() {
		log.info("Starting DelayQueueProcessor");
		new Thread(this::processQueue).start();
	}

	/**
	 * Add Queued event (file/directory) to the queue.
	 * First, remove event in case it's already in the queue...
	 * Then offer the event to the queue so the queue always has only the latest event.
	 * (Events are placed into the queue based on their delay. The queue is sorted based on remaining delay)
	 *
	 * @param event - the event
	 */
	public void addQueuedFile(QueuedFileEvent event) {
		log.trace("ADDING TO QUEUE = {}", event.getFile());
		delayed.remove(event);
		delayed.offer(event);
	}

	/**
	 * Given a delete event, check for a create event for same file and remove it, returning true so we know not to queue the delete event!
	 *
	 * @param file - the delete file
	 * @return - true if a create event for same file was removed
	 */
	public boolean removeCreateEvent(File file) {
		return delayed.removeIf(queued -> queued.getFile() == file && ENTRY_CREATE.equals(queued.getType()));
	}

	/**
	 * Given a directory to delete, remove any events that have the directory in their path
	 *
	 * @param directory - the directory
	 */
	public void removeChildFilesOf(Path directory) {
		delayed.removeIf(queued -> queued.getFile().toPath().startsWith(directory));
	}

	/**
	 * Process the events in the queue.
	 * When the delay timer expires for an event it will be taken from the queue
	 * and processed according to the event type
	 */
	private void processQueue() {
		log.entry();
		while(!Thread.interrupted()) {
			QueuedFileEvent event;
			try {
				event = delayed.take();
			} catch (InterruptedException e) {
				log.error("processQueue(): Interrupted, {}", e.getMessage(), e);
				break;
			}
			WatchEvent.Kind<?> kind = event.getType();
			log.trace("Processing Queued {} file: {}, for event type: {}", kind.name(), event.getFile().getName());

			FileIndexResource.Mode mode = ENTRY_DELETE.equals(kind)
			                              ? FileIndexResource.Mode.DELETE : FileIndexResource.Mode.UPSERT;

			utils.processQueuedIndexedFile(event.getMountPath(), event.getFile(), mode);
		}
		log.exit();
	}
}

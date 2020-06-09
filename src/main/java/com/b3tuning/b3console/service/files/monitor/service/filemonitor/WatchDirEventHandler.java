/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  WatchDirEventHandler is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.service.filemonitor;

import com.b3tuning.b3console.service.files.monitor.service.delayqueue.DelayQueueProcessor;
import com.b3tuning.b3console.service.files.monitor.service.delayqueue.QueuedFileEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.Value;
import lombok.extern.slf4j.XSlf4j;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

@XSlf4j
public class WatchDirEventHandler {

	@Value("${file.monitor.queue.delay}") private long delay;

	@Autowired private DelayQueueProcessor queueProcessor;

	private static final String LINE = "**************************";

	/**
	 * A Map of file extensions from recursively walking the watched directories during visitFile()
	 * <p>
	 * This Map only file extensions and whether it is supported or not, and is updated during CREATE/UPDATE events
	 * This is mainly used for processing delete events, we check the DELETE event file to see if it is contained
	 * in this Map. If it is, we know we can send a delete request to files-api!
	 */
	private Map<String, Boolean> fileTypes = Maps.newHashMap();

	/**
	 * A Set of `monitored` directories (by Path).
	 *
	 * This Set of Path's is updated during CREATE/UPDATE events.
	 * This is used for processing delete events, by checking if the event path is in this monitored Set.
	 * If it is, we can send out the delete directory call to files-api, and remove the path from the Set.
	 */
	private Set<Path> monitored = new HashSet<>();

	@PostConstruct
	public void init() {
		Preconditions.checkNotNull(queueProcessor);
	}

	void addMonitoredDirectory(Path dir) {
		monitored.add(dir);
	}

	void handleOverflowEvent() {
		// TODO: do we need to re-register the paths? OVERFLOW handling needs investigation
		log.error(LINE);
		log.error("**** Hit OVERFLOW!!!! ****");
		log.error(LINE);
	}

	/**
	 * EVENT HANDLING STARTS HERE: Fork on event type
	 *
	 * @param file - the file/directory to process
	 * @param kind - the type of event (ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)
	 * @return - true if event was handled, otherwise false
	 */
	<K> boolean handleEvent(Path mountPath, File file, WatchEvent.Kind<K> kind) {
		log.trace("{} : processing file: {}", kind, file.getName());
		if (ENTRY_DELETE.equals(kind)) {
			return handleDeleteEvent(mountPath, file);
		}
		if (ENTRY_CREATE.equals(kind) || ENTRY_MODIFY.equals(kind)) {
			return handleCreateModifyEvent(mountPath, file, kind);
		}
		return false;
	}

	/**
	 * DELETE EVENTS START HERE: Fork on file/directory
	 *
	 * @param file - the file/directory to process
	 * @return - true if event was handled, otherwise false
	 */
	boolean handleDeleteEvent(Path mountPath, File file) {
		if (monitored.contains(file.toPath())) {
			return handleDeleteDirectory(mountPath, file);
		} else {
			return handleDeleteFile(mountPath, file);
		}
	}

	/**
	 * Delete a directory.
	 * Remove any child files from queue, send to queue, and remove from Set `monitored`
	 *
	 * @param file - the directory to process (files not allowed)
	 * @return - true if event was handled, otherwise false
	 */
	boolean handleDeleteDirectory(Path mountPath, File file) {
		if (!monitored.contains(file.toPath())) {
			log.trace("handleDeleteDirectory(): Not a monitored directory! '{}'", file.getName());
			return false;
		}
		queueProcessor.removeChildFilesOf(file.toPath());
		queueEvent(mountPath, file, ENTRY_DELETE);
		monitored.remove(file.toPath());
		return true;
	}

	/**
	 * Delete a file.
	 * If file is supported, send to queue.
	 *
	 * @param file - the file to process (if supported)
	 * @return - true if event was handled, otherwise false
	 */
	private boolean handleDeleteFile(Path mountPath, File file) {
		// check that there isn't a pending create event for the same file...
		boolean removedCreate = queueProcessor.removeCreateEvent(file);
		// if there was, and we removed it we don't process the delete as it doesn't exist in files-api anyway
		if (!removedCreate) {
			queueEvent(mountPath, file, ENTRY_DELETE);
		}
		return true;
	}

	/**
	 * CREATE/MODIFY EVENTS START HERE: Fork on file/directory (we no longer process new directories!)
	 *
	 * @param file - the file to process
	 * @param kind - the type of event (only ENTRY_CREATE, ENTRY_MODIFY accepted)
	 * @return - true if event was handled, otherwise false
	 */
	<K> boolean handleCreateModifyEvent(Path mountPath, File file, WatchEvent.Kind<K> kind) {
		if (!Files.isDirectory(file.toPath(), NOFOLLOW_LINKS)) {
			try {
				return handleFile(mountPath, file, kind);
			} catch (IOException e) {
				log.warn("processEvents(): IOException! event: {}, file: {}, message: {}", kind, file.getName(), e.getMessage());
				return false;
			}
		}
		log.trace("Got a directory, nothing to do");
		return false;
	}

	/**
	 * Create/Update a file. (We aren't concerned with new directories, as files-api creates directories from file path)
	 * If supported, send to queue.
	 *
	 * @param file - the file to process
	 * @param kind - the type of event (ENTRY_CREATE, ENTRY_MODIFY)
	 * @return - true if event was handled, otherwise false
	 */
	<K> boolean handleFile(Path mountPath, File file, WatchEvent.Kind<K> kind) throws IOException {
		// If file is one we should inspect....
		if (isFileAndNotHidden(file.toPath())) {
			queueEvent(mountPath, file, kind);
			return true;
		}
		log.trace("{} : handledFile(): Unsupported file: {}", kind, file.getName());
		return false;
	}

	private <K> QueuedFileEvent createQueueEvent(Path mountPath, File file, WatchEvent.Kind<K> kind) {
		return new QueuedFileEvent(mountPath, file, kind, delay);
	}

	private <K> void queueEvent(Path mountPath, File file, WatchEvent.Kind<K> kind) {
		queueProcessor.addQueuedFile(createQueueEvent(mountPath, file, kind));
		log.trace("{} : Queued file: {}", kind, file.getName());
	}

	boolean isFileAndNotHidden(Path file) throws IOException {
		return Files.isRegularFile(file) && !Files.isHidden(file);
	}
}

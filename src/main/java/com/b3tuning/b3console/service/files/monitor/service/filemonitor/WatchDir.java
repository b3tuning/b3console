/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  WatchDir is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.service.filemonitor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.sun.nio.file.SensitivityWatchEventModifier;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

@XSlf4j
public class WatchDir {

	private final Path                 mountPath;
	private final WatchDirEventHandler eventHandler;
	private final WatchService         watcher;
	private final Map<WatchKey, Path>  keys    = Maps.newHashMap();
	private       boolean              started = false;
	private       Thread               watchThread;

	private void validateDependencies() {
		Preconditions.checkNotNull(eventHandler);
		Preconditions.checkNotNull(watcher);
	}

	/**
	 * Creates a WatchService and registers the given directory
	 */
	public void start() {
		validateDependencies();
		if (started) {
			throw new IllegalStateException("Already started");
		}
		registerAll(mountPath);
		watchThread = new Thread(this::processEvents);
		watchThread.start();
		log.info("Started new watch thread: {}", watchThread.getName());

		log.trace("Registered {}.", mountPath);
		started = true;
	}

	public void stop() {
		if (!started) {
			throw new IllegalStateException("Not started");
		}
		started = false;
		watchThread.interrupt();
		try {
			watchThread.join();
		} catch (InterruptedException e) {
			log.error("Failed to join watch thread: {}", watchThread.getName());
		}
		watchThread = null;
		log.trace("Stopped watching {}", mountPath);
	}

	/**
	 * Register the given directory with the WatchService
	 * Add the directory to the WatchKey keys
	 * Add the directory to the monitored directory Set
	 */
	private void register(Path directory) throws IOException {
		WatchKey key = RegisterHelper.register(directory, watcher, new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE}, SensitivityWatchEventModifier.HIGH);
		keys.put(key, directory);
		eventHandler.addMonitoredDirectory(directory);
	}

	/**
	 * Register the given directory, and all its sub-directories, with the
	 * WatchService.
	 * visit all the files and if started send create events
	 */
	private void registerAll(Path directory) {
		try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					register(dir);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (started) {
						eventHandler.handleFile(mountPath, file.toFile(), ENTRY_CREATE);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			log.error("Failed to walk directory: " + directory.toString(), e);
		}
	}

	/**
	 * Process all events for keys queued to the watcher
	 */
	private void processEvents() {
		while(!Thread.interrupted()) {
			if (!started) {
				break;
			}

			// wait for key to be signaled
			WatchKey key;
			try {
				key = watcher.take();
				log.trace("Got key: {}", key.watchable());
			} catch (InterruptedException e) {
				log.error("WatchDir processEvents(): Interrupted, {}", e.getMessage());
				break;
			}

			if (!keys.containsKey(key)) {
				log.warn("WatchKey not recognized!!");
				continue;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (OVERFLOW.equals(kind)) {
					eventHandler.handleOverflowEvent();
					continue;
				}

				Path item = (Path) event.context();
				File file = new File(((Path) key.watchable()).toAbsolutePath() + File.separator + item.getFileName());

				// Pre-Handle Create directory events here, since we need to registerAll()
				if (ENTRY_CREATE.equals(kind) && Files.isDirectory(file.toPath(), NOFOLLOW_LINKS)) {
					log.trace("Registering new directory: {}", file.toPath());
					registerAll(file.toPath());
				}

				// Handle all other CREATE/MODIFY/DELETE events
				if (eventHandler.handleEvent(mountPath, file, kind)) {
					log.trace("{} : Handled file: {}", kind, file.getName());
				}

				log.trace("processEvents: Finished handling event, Resetting key: {}", ((Path) key.watchable()).getFileName());
				boolean valid = key.reset();
				if (!valid) {
					log.trace("processEvents: Key Invalid, Removing key: {}", ((Path) key.watchable()).getFileName());
					keys.remove(key);

					if (keys.isEmpty()) {
						log.info("WatchDir(): No more keys to process!");
						break;
					}
				}
			}
		}
	}
}
/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  FileMonitor is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor;

import com.b3tuning.b3console.service.files.monitor.service.filemonitor.WatchDir;
import com.b3tuning.b3console.service.files.monitor.util.MountPathResolver;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@XSlf4j
public class FileMonitor {

	@Autowired private MountPathResolver pathResolver;
	@Autowired private WatchDirFactory   watchDirFactory;

	private Set<WatchDir> watchers = Sets.newHashSet();

	void run(String... args) throws Exception {
		log.trace("args: {}", args.length);
		// register mount and process its events
		pathResolver.paths().forEach(mount -> {
			Path rootPath = Paths.get(mount);
			log.debug("NEW WatchDir! {}", rootPath);
			try {
				WatchDir watchDir = watchDirFactory.newWatchDir(rootPath);
				watchers.add(watchDir);
				watchDir.start();
			} catch (IOException e) {
				log.error("Caught IO Exception starting WatchDirFactory: {}", e.getMessage());
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(() -> watchers.forEach(WatchDir::stop)));

	}

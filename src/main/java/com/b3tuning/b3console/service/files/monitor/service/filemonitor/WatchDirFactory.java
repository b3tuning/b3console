/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  WatchDirFactory is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.service.filemonitor;

import dagger.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class WatchDirFactory {

	@Autowired private WatchDirEventHandler eventHandler;
	@Autowired private WatchServiceFactory  watchServiceFactory;

	public WatchDir newWatchDir(Path path) throws IOException {
		return new WatchDir(path, eventHandler, watchServiceFactory.newPollingWatchService());
	}
}

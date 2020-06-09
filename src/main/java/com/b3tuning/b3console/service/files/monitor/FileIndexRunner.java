/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  FileIndexRunner is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor;

import lombok.extern.slf4j.XSlf4j;

@XSlf4j
public class FileIndexRunner implements CommandLineRunner {

	@Autowired FileIndexService fileIndexService;
	@Autowired FileMonitor fileMonitorApp;

	@Override
	public void run(String... args) throws Exception {
		log.trace("args: {}", args.length);

		fileIndexService.indexFileSystem();

		// Now that the catalog has been generated, lets run the monitor!
		fileMonitorApp.run(args);
	}

}

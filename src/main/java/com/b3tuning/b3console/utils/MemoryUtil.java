package com.b3tuning.b3console.utils;

import lombok.extern.slf4j.XSlf4j;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class MemoryUtil {

	public static void reportMemoryUsage() {
		if (!log.isTraceEnabled()) {
			return;
		}
		Runtime runtime = Runtime.getRuntime();
		long beforeGC = runtime.totalMemory() - runtime.freeMemory();

		System.gc();
		long afterGC = runtime.totalMemory() - runtime.freeMemory();

		double mb = 1024 * 1024;
		log.trace("Used Memory before GC: {} MB, after GC: {} MB", beforeGC / mb, afterGC / mb);
//        log.trace("Memory GC: {} MB", beforeGC / mb);
	}
}

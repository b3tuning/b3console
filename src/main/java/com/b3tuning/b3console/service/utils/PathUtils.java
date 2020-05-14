package com.b3tuning.b3console.service.utils;

import java.io.File;

/*
 *  Created on:  Apr 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class PathUtils {

	public static String sanitizePath(String path) {
		return sanitizePath(path, File.separator);
	}

	private static String sanitizePath(String path, String separator) {
		if (path == null || separator == null) {
			return path;
		}
		if (!separator.equals("/")) {
			return path.replace(separator, "/");
		}
		return path;
	}

}

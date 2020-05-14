package com.b3tuning.b3console.utils;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class RegexUtils {
	public static String regexFromGlob(String glob) {
		return glob
				.replace(".", "\\.")
				.replace("?", ".")
				.replace("*", ".*");
	}
}

/*
 *  Created on:  Jun 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ConversionUtil is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.test;

import java.text.DecimalFormat;

public class ConversionUtil {
	public static String readableFileSize(Long size) {
		if (size <= 0) {
			return "0";
		}
		final String[] units       = new String[]{"B", "kB", "MB", "GB", "TB"};
		int            digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
}

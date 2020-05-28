package com.b3tuning.b3console.service.files;

import com.b3tuning.b3console.service.FileType;
import com.google.common.collect.Sets;

import java.util.Set;

import static com.b3tuning.b3console.service.FileType.DIRECTORY;
import static com.b3tuning.b3console.service.FileType.B3CONFIG;


/*
 *  Created on:  Apr 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class SupportedFileUtils {

	public static Set<FileType> getSupportedFileTypes() {
		Set<FileType> supported = Sets.newHashSet();
		for (FileType fileType : FileType.values()) {
			if (supportsFileType(fileType)) {
				supported.add(fileType);
			}
		}
		return supported;
	}

	public static boolean supportsFileType(FileType fileType) {
		if (DIRECTORY == fileType) {
			return true;
		}

//		if (PRESET_COPY_ALL_FILES.equals(deliverySetting.getPresetUid())) {
//			return true;
//		}
//		if (PRESET_COPY_VIDEO_FILES.equals(deliverySetting.getPresetUid())) {
//			return FileType.VIDEO == fileType;
//		}
//		if (PRESET_COPY_NON_VIDEO_FILES.equals(deliverySetting.getPresetUid())) {
//			return FileType.VIDEO != fileType;
//		}
//
//		if (CATEGORY_AUDIO_ONLY.equals(deliverySetting.getCategoryUid())) {
//			return FileType.AUDIO == fileType;
//		}

		// if not in the original source files or audio only category then
		// the delivery setting must be in a video category
		return B3CONFIG == fileType;
	}
}

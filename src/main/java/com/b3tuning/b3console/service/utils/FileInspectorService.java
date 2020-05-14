package com.b3tuning.b3console.service.utils;

import com.b3tuning.b3console.service.FileType;

import java.util.Collection;
import java.util.Map;

/*
 *  Created on:  Apr 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public interface FileInspectorService {

	String detectMimeType(String path);

	FileType classify(String path);

	Map<String, FileType> classify(Collection<String> paths);

}

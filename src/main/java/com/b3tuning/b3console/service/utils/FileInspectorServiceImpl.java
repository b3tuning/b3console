package com.b3tuning.b3console.service.utils;

import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.FileType;
import com.b3tuning.b3console.utils.RegexUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.XSlf4j;
import org.apache.tika.Tika;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.b3tuning.b3console.service.FileType.DIRECTORY;
import static com.b3tuning.b3console.service.FileType.OTHER;

/*
 *  Created on:  Apr 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class FileInspectorServiceImpl implements FileInspectorService {

	private AppProperties properties;
	private Tika tika;

	@Inject
	public FileInspectorServiceImpl(AppProperties properties, Tika tika) {
		this.properties = properties;
		this.tika       = tika;
	}

	public String detectMimeType(String path) {
		return tika.detect(path);
	}

	public FileType classify(String path) {
		if (path.endsWith("/")) {
			log.debug("classify: {}: {}", DIRECTORY, path);
			return DIRECTORY;
		}
		String mimeType = detectMimeType(path);
		FileType fileType = properties.getFileTypes().entrySet().stream()
		                              .filter(e -> anyMatch(e.getValue(), mimeType))
		                              .map(Map.Entry::getKey)
		                              .findFirst()
		                              .orElse(OTHER);
		log.debug("classify: {}: {}", fileType, path);
		return fileType;
	}

	public Map<String, FileType> classify(Collection<String> paths) {
		Map<String, FileType> map = Maps.newLinkedHashMap();
		paths.forEach(p -> map.put(p, classify(p)));
		return map;
	}

	static boolean anyMatch(List<String> allowed, String mimeType) {
		return allowed.stream().anyMatch(a -> mimeType.matches(RegexUtils.regexFromGlob(a)));
	}

}

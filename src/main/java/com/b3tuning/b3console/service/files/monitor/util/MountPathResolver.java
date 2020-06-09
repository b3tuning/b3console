/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MountPathResolver is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.util;

import com.google.common.collect.Lists;
import lombok.Value;
import lombok.extern.slf4j.XSlf4j;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@XSlf4j
public class MountPathResolver {

	private static final String LIST_DELIM = ",";
	private static final String DELIM = ":";

	@Value("${mount}") private String mount;

	private List<String> basePaths = Lists.newArrayList();

	@PostConstruct
	public void init() {
		for (String m : mount.split(LIST_DELIM)) {
			String[] hostLocal = m.split(DELIM);
			if (hostLocal.length == 2) {
				String pathString = hostLocal[1].trim();
				if (checkValidPathFormat(pathString)) {
					basePaths.add(pathString);
					log.trace("added root path '{}' to walk for files", pathString);
				} else{
					log.error("invalid path format {}, file root directory is expected", pathString);
					throw new IllegalArgumentException(String.format("Illegal mount: '%s'", m));
				}
			}
			else {
				throw new IllegalArgumentException(String.format("Illegal mount: '%s'", m));
			}
		}
	}

	private static boolean checkValidPathFormat(String pathString) {
		try {
			Path    path        = Paths.get(pathString);
			boolean isDirectory = Files.isDirectory(path);
			log.trace("Files.isDirectory(path) = {}", isDirectory);
			return isDirectory;
		}catch (InvalidPathException ex) {
			log.error("invalid path provided {}", pathString);
		}

		return false;
	}

	public List<String> paths() {
		return basePaths;
	}
}

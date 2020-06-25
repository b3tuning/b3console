package com.b3tuning.b3console.prefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class UserPreferences {

	private static final String USER_HOME = System.getProperty("user.home");

	private static final String DOWNLOAD_PATH         = "DOWNLOAD_PATH";
	private static final String DOWNLOAD_PATH_DEFAULT = USER_HOME + File.separator + "Desktop";

	private static final String BROWSE_LOCAL_PATH         = "BROWSE_LOCAL_PATH";
	private static final String BROWSE_LOCAL_PATH_DEFAULT = USER_HOME + File.separator + "Documents";

	private static final String RECENT_FILES        = "RECENT_FILES";
	public static final  String RECENT_FILE_DEFAULT = "NO RECENT FILES";

	private final Preferences  preferences;
	private final ObjectMapper mapper;

	public UserPreferences(ObjectMapper mapper) {
		this.preferences = Preferences.userRoot().node(this.getClass().getName());
		this.mapper      = mapper;
	}

	private void set(String property, String value) {
		if (value == null) {
			preferences.remove(property);
		} else {
			preferences.put(property, value);
		}
	}

	public String getDownloadPath() {
		return preferences.get(DOWNLOAD_PATH, DOWNLOAD_PATH_DEFAULT);
	}

	public void setDownloadPath(String value) {
		set(DOWNLOAD_PATH, value);
	}

	public String getBrowseLocalPath() {
		return preferences.get(BROWSE_LOCAL_PATH, BROWSE_LOCAL_PATH_DEFAULT);
	}

	public void setBrowseLocalPath(String value) {
		set(BROWSE_LOCAL_PATH, value);
	}

	public List<String> getRecentFiles() {
		try {
			String json = preferences.get(RECENT_FILES, "");
			if (StringUtils.isNotBlank(json)) {
				return mapper.readValue(json, new TypeReference<>() {
				});
			}
		}
		catch (IOException e) {
			log.error("ERROR in getting recent files from preferences : {}", e.getMessage(), e);
		}
		return Lists.newArrayList(RECENT_FILE_DEFAULT);
	}

	public void setRecentFiles(List<String> recentFiles) {
		log.entry(recentFiles);
		try {
			set(RECENT_FILES, mapper.writeValueAsString(recentFiles));
		}
		catch (JsonProcessingException e) {
			log.error("ERROR in saving recent file to preferences : {}", e.getMessage(), e);
		}
	}
}

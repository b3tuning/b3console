package com.b3tuning.b3console.prefs;

import com.b3tuning.b3console.control.menubar.recent.RecentFile;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
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

	private static final String DOWNLOAD_PATH     = "DOWNLOAD_PATH";
	private static final String BROWSE_LOCAL_PATH = "BROWSE_LOCAL_PATH";

	private static final String DOWNLOAD_PATH_DEFAULT     =
			System.getProperty("user.home") + File.separator + "Desktop";
	private static final String BROWSE_LOCAL_PATH_DEFAULT =
			System.getProperty("user.home") + File.separator + "Documents";

	private static final String MODULE_TYPE    = "MODULE_TYPE";
	private static final String MODULE_DEFAULT = "DOOR";

	private static final String RECENT_ITEM = "RECENT_ITEM";

	private final Preferences preferences;

	public UserPreferences() {
		preferences = Preferences.userRoot().node(this.getClass().getName());
	}

	private void set(String property, String value) {
		if (value == null) {
			preferences.remove(property);
		} else {
			preferences.put(property, value);
		}
	}

	public void setRecent() {
		RecentFile item = new RecentFile("TestFile", "/some/dir", ModuleType.DOOR, System.currentTimeMillis());
		try {
			ObjectMapper mapper = new ObjectMapper();
			preferences.put(RECENT_ITEM, mapper.writeValueAsString(item));
		}
		catch (JsonProcessingException e) {
			log.error("ERROR in saving recent file to preferences {}", e.getMessage(), e);
		}
	}

	public RecentFile getRecent() {
		RecentFile item = new RecentFile(null, null, null, null);
		try {
			String json = preferences.get(RECENT_ITEM, null);
			if (!StringUtils.isEmpty(json)) {
				ObjectMapper mapper = new ObjectMapper();
				item = mapper.readValue(json, RecentFile.class);
			}
		}
		catch (IOException e) {
			log.error("ERROR in getting recent file from preferences {}", e.getMessage(), e);
		}
		log.debug("FFFFFFFFFFFFFFFFFFFFF recent.name = {}", item.getName());
		log.debug("FFFFFFFFFFFFFFFFFFFFF recent.path = {}", item.getPath());
		log.debug("FFFFFFFFFFFFFFFFFFFFF recent.type = {}", item.getType());
		log.debug("FFFFFFFFFFFFFFFFFFFFF recent.LastAccessed = {}", item.getLastAccessed());
		return item;
	}

	public void setDownloadPath(String value) {
		set(DOWNLOAD_PATH, value);
	}

	public String getDownloadPath() {
		return preferences.get(DOWNLOAD_PATH, DOWNLOAD_PATH_DEFAULT);
	}

	public void setBrowseLocalPath(String value) {
		set(BROWSE_LOCAL_PATH, value);
	}

	public String getBrowseLocalPath() {
		return preferences.get(BROWSE_LOCAL_PATH, BROWSE_LOCAL_PATH_DEFAULT);
	}

	public void setModule(ModuleType module) {
		set(MODULE_TYPE, module.toString());
	}

	public String getModule() {
		return preferences.get(MODULE_TYPE, MODULE_DEFAULT);
	}
}

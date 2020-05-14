package com.b3tuning.b3console.service.settings;

import com.b3tuning.b3console.prefs.UserPreferences;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class SettingsService {

	/**
	 * The stored preferences
	 */
	private final UserPreferences preferences;

	/**
	 * @param preferences UserPreferences
	 */
	public SettingsService(UserPreferences preferences) {
		this.preferences = preferences;
	}
}

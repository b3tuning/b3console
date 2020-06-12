package com.b3tuning.b3console.view.live;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class LiveMenuViewModel extends BaseViewModel {

	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;

//	private ObjectProperty<ModuleType> module = new SimpleObjectProperty<>();

	@Inject
	public LiveMenuViewModel(UserPreferences preferences, NotificationCenter notifications) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notifications;
//		module.set(ModuleType.valueOf(preferences.getModule()));
//		globalNotifications.subscribe(MODULE_EVENT, (key, payload) -> module.set((ModuleType) (payload[0])));
	}
}

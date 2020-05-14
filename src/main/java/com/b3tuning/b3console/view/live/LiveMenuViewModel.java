package com.b3tuning.b3console.view.live;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.MODULE_EVENT;

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

	private UserPreferences    preferences;
	private NotificationCenter globalNotifications;

	private ObjectProperty<ModuleAction> module = new SimpleObjectProperty<>();

	@Inject
	public LiveMenuViewModel(UserPreferences preferences, NotificationCenter notifications) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notifications;
		module.set(ModuleAction.valueOf(preferences.getModule()));
		globalNotifications.subscribe(MODULE_EVENT, (key, payload) -> module.set((ModuleAction) (payload[0])));
	}
}

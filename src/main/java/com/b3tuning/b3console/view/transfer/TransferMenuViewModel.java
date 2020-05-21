package com.b3tuning.b3console.view.transfer;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleType;
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
public class TransferMenuViewModel extends BaseViewModel {

	private UserPreferences    preferences;
	private NotificationCenter globalNotifications;

	private ObjectProperty<ModuleType> module = new SimpleObjectProperty<>();

	@Inject
	public TransferMenuViewModel(UserPreferences prefs, NotificationCenter notifications) {
		log.entry();
		this.preferences         = prefs;
		this.globalNotifications = notifications;
		module.set(ModuleType.valueOf(preferences.getModule()));
		globalNotifications.subscribe(MODULE_EVENT, (key, payload) -> module.set((ModuleType) (payload[0])));
	}
}

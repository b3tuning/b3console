package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.config.door.DoorConfigView;
import com.b3tuning.b3console.view.config.door.DoorConfigViewModel;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigView;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigViewModel;
import com.b3tuning.b3console.view.config.trans.TransConfigView;
import com.b3tuning.b3console.view.config.trans.TransConfigViewModel;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction;
import de.saxsys.mvvmfx.ViewTuple;
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
public class ConfigMenuViewModel extends BaseViewModel {

	private UserPreferences    preferences;
	private NotificationCenter globalNotifications;

	private ObjectProperty<ModuleAction> module = new SimpleObjectProperty<>();

	private ViewTuple<DoorConfigView, DoorConfigViewModel>       doorConfigViewTuple;
	private ViewTuple<ShifterConfigView, ShifterConfigViewModel> shifterConfigViewTuple;
	private ViewTuple<TransConfigView, TransConfigViewModel>     transConfigViewTuple;

	@Inject
	public ConfigMenuViewModel(UserPreferences prefs, NotificationCenter notifications) {
		log.entry();
		this.preferences         = prefs;
		this.globalNotifications = notifications;
		module.set(ModuleAction.valueOf(preferences.getModule()));
		globalNotifications.subscribe(MODULE_EVENT, (key, payload) -> module.set((ModuleAction) (payload[0])));
	}

	public ObjectProperty<ModuleAction> getModule() {
		return module;
	}
}

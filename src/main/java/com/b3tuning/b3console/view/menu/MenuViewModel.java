/*
 *  Created on:  Jun 10, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MenuViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu;

import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.b3tuning.b3console.view.root.RootViewModel.CONFIG_LOADED;

@XSlf4j
public class MenuViewModel extends BaseViewModel {

	private final BooleanProperty    configLoaded = new SimpleBooleanProperty(false);
	private final NotificationCenter globalNotifications;

	@Inject
	public MenuViewModel(NotificationCenter notifications) {
		log.entry();

		this.globalNotifications = notifications;

		globalNotifications.subscribe(CONFIG_LOADED, ((key, payload) -> {
			log.entry(payload[0]);
			configLoaded.setValue((Boolean) payload[0]);
			log.warn("configLoaded = {}", configLoaded);
		}));
	}

	public BooleanProperty configLoadedProperty() {
		return configLoaded;
	}
}

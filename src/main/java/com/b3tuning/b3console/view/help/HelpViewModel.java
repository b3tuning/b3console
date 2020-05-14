package com.b3tuning.b3console.view.help;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.notifications.PageContextChangedNotification;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.net.URL;

import static com.b3tuning.b3console.view.root.RootViewModel.HELP_DETACHED_EVENT;
import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.MODULE_EVENT;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class HelpViewModel extends BaseViewModel {

	private static final String HELP_LOCATION_TEMPLATE = "/help/%s.html";
	private static final String NO_HELP_AVAILABLE = "/help/404.html";

	private UserPreferences preferences;
	private NotificationCenter globalNotifications;

	private StringProperty helpFile = new SimpleStringProperty();
	private BooleanProperty detached = new SimpleBooleanProperty(false);
	private ObjectProperty<ModuleAction> module = new SimpleObjectProperty<>();

	@Inject
	public HelpViewModel(UserPreferences prefs, NotificationCenter notificationCenter) {
		log.entry();
		this.preferences         = prefs;
		this.globalNotifications = notificationCenter;

		module.set(ModuleAction.valueOf(preferences.getModule()));
		globalNotifications.subscribe(MODULE_EVENT, (key, payload) -> module.set((ModuleAction) (payload[0])));

		globalNotifications.subscribe(PageContextChangedNotification.class.getName(),
		                              (key, payload) -> {
			                              log.entry(key, payload);
			                              PageContextChangedNotification notification = (PageContextChangedNotification) payload[0];
//			                              URL resource = HelpViewModel.class.getResource(String.format(HELP_LOCATION_TEMPLATE, notification.getView()));
//			                              if (resource == null) {
//				                              resource = HelpViewModel.class.getResource(NO_HELP_AVAILABLE);
//			                              }
			                              URL resource = HelpViewModel.class.getResource(NO_HELP_AVAILABLE);
			                              helpFile.set(resource.toExternalForm());
		                              });
	}

	public void detachHelp() {
		globalNotifications.publish(HELP_DETACHED_EVENT, new Object() {
		});
	}

	public StringProperty helpFileProperty() {
		return helpFile;
	}

	/***
	 * Properties
	 */
	public boolean getDetached() {
		return detached.get();
	}

	public void setDetached(boolean value) {
		detached.set(value);
	}

	public BooleanProperty detachedProperty() {
		return detached;
	}

}

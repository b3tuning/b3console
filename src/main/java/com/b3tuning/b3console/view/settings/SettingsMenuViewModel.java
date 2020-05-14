package com.b3tuning.b3console.view.settings;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.settings.SettingsService;
import com.b3tuning.b3console.view.BaseViewModel;
import com.fazecast.jSerialComm.SerialPort;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.b3tuning.b3console.service.comms.SerialComms.B3_USB_PORT_DESCRIPTION;
import static org.reactfx.EventStreams.changesOf;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class SettingsMenuViewModel extends BaseViewModel {

	public static final String             MODULE_EVENT = "module";
	// injected dependencies
	@SuppressWarnings("unused")
	private             SettingsService    configService;
	private             UserPreferences    preferences;
	private             NotificationCenter globalNotifications;

	// exposed properties
	private StringProperty downloadPath = new SimpleStringProperty();
	private StringProperty versionString = new SimpleStringProperty();
	private ObjectProperty<ModuleAction> module = new SimpleObjectProperty<>();

	public enum ModuleAction {
		DOOR, SHIFTER, TRANS
	}

	@Inject
	public SettingsMenuViewModel(SettingsService configService, UserPreferences prefs,
	                             AppProperties appProperties, NotificationCenter notificationCenter) {
		log.entry();
		this.configService       = configService;
		this.preferences         = prefs;
		this.globalNotifications = notificationCenter;

		SerialPort[] ports = SerialPort.getCommPorts();
		if (ports.length == 0) {
			log.error("NO SERIAL PORTS");
		} else {
			for (SerialPort s : ports) {
				checkPort(s);
			}
		}

		// populate initial values
		downloadPath.set(preferences.getDownloadPath());
		versionStringProperty().set(appProperties.getVersion());
		module.set(ModuleAction.valueOf(preferences.getModule()));

		// wire up updates
		manage(changesOf(downloadPath).subscribe(v -> preferences.setDownloadPath(v.getNewValue())));

		manage(changesOf(module).subscribe(m -> {
			preferences.setModule(m.getNewValue());
			globalNotifications.publish(MODULE_EVENT, module.get());
		}));
	}

	void onButtonAction(ModuleAction action) {
		log.entry(action);
		module.set(action);
	}

	public void checkPort(SerialPort port) {
		log.entry(port);
		log.debug("SystemPortName = {}", port.getSystemPortName());
		log.debug("DescriptivePortName = {}", port.getDescriptivePortName());
		log.debug("PortDescription = {}", port.getPortDescription());
		if (port.getDescriptivePortName().contains(B3_USB_PORT_DESCRIPTION)
		&& port.getDescriptivePortName().contains("cu.")) {
			log.debug("YAY FOUND SERIAL PORT B3");
		}
	}
	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////
	public StringProperty downloadPathProperty() {
		return downloadPath;
	}

	public ObjectProperty<ModuleAction> moduleProperty() {
		return module;
	}

	public StringProperty versionStringProperty() {
		return versionString;
	}

}
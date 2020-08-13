package com.b3tuning.b3console.view.root;

import com.b3tuning.b3console.service.comms.SerialComms;
import com.b3tuning.b3console.service.filemanager.FileManager;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.config.door.DoorConfigView;
import com.b3tuning.b3console.view.config.door.DoorConfigViewModel;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigView;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigViewModel;
import com.b3tuning.b3console.view.config.trans.TransConfigView;
import com.b3tuning.b3console.view.config.trans.TransConfigViewModel;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.notifications.PopViewNotification;
import com.b3tuning.b3console.view.notifications.PushViewNotification;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static org.reactfx.EventStreams.changesOf;
import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@XSlf4j
public class RootViewModel extends BaseViewModel {

	public static final String HELP_DETACHED_EVENT = "help_detached";

	// injected
	@Setter
	@SuppressWarnings("unused")
	private       Application        application;
	@SuppressWarnings("unused")
	private final NotificationCenter globalNotifications;
	private final ViewManager        viewManager;
	private final FileManager        fileManager;
	private final SerialComms        comms;

	// exposed properties
	private final ObjectProperty<StackPane> childViewPane = new SimpleObjectProperty<>();

	private final BooleanProperty            initialized = new SimpleBooleanProperty(false);
	private final ObjectProperty<ConfigBase> config      = new SimpleObjectProperty<>(null);

	@Inject
	public RootViewModel(NotificationCenter globalNotifications, ViewManager viewManager, FileManager manager, SerialComms comms) {
		log.entry();

		this.globalNotifications = globalNotifications;
		this.viewManager         = viewManager;
		this.fileManager         = manager;
		this.comms               = comms;

		config.bindBidirectional(fileManager.configProperty());

		initNotifications();
	}

	private void initNotifications() {
		manage(nonNullValuesOf(childViewPane).subscribe(c -> {
			log.entry();

			comms.getPort();

			// dispose of any loaded views that we may have
			viewManager.destroyAll(c);
			initialized.set(true);

			// any child views that need access to their parent are handled via
			// global notifications
			// to eliminate dependencies and possible memory leaks
			globalNotifications.subscribe(PushViewNotification.class.getName(), (key, payload) -> {
				log.entry(key, payload);
				PushViewNotification notification = (PushViewNotification) payload[0];
				viewManager.push(notification.getKey(), notification.getViewTuple(), childViewPane.get(),
				                 notification.isCloseOnLostFocus(), notification.isIgnoreDirty());
			});

			globalNotifications.subscribe(PopViewNotification.class.getName(), (key, payload) -> {
				log.entry(key, payload);
				viewManager.pop(childViewPane.get(), ((PopViewNotification) payload[0]).isReloadPage());
			});
		}));

		manage(changesOf(config).subscribe(configBaseChange -> {
			log.entry(configBaseChange);
			if (null == configBaseChange.getNewValue()) {
				viewManager.destroyAll(childViewPane.get());
			} else {
				log.error("CONFIG CHANGE");
				log.error(configBaseChange.getNewValue().getClass().descriptorString());
				log.error(configBaseChange.getNewValue().getClass().toString());
				switch (configBaseChange.getNewValue().getType()) {
					case DOOR -> showDoorView();
					case SHIFTER -> showShifterView();
					case TRANS -> showTransView();
				}
			}
		}));
	}

	private void showDoorView() {
		log.entry();
		if (viewManager.contains(DoorConfigView.class.getName())) {
			viewManager.toFront(DoorConfigView.class.getName());
		} else {
			ViewTuple<DoorConfigView, DoorConfigViewModel> tuple = FluentViewLoader
					.fxmlView(DoorConfigView.class).load();
			viewManager.push(DoorConfigView.class.getName(), tuple, childViewPane.get());
			tuple.getViewModel().configProperty().set((DoorConfig) config.get());
		}
	}

	private void showShifterView() {
		log.entry();
		if (viewManager.contains(ShifterConfigView.class.getName())) {
			viewManager.toFront(ShifterConfigView.class.getName());
		} else {
			ViewTuple<ShifterConfigView, ShifterConfigViewModel> tuple = FluentViewLoader
					.fxmlView(ShifterConfigView.class).load();
			viewManager.push(ShifterConfigView.class.getName(), tuple, childViewPane.get());
			tuple.getViewModel().configProperty().set((ShifterConfig) config.get());

		}
	}

	private void showTransView() {
		log.entry();
		if (viewManager.contains(TransConfigView.class.getName())) {
			viewManager.toFront(TransConfigView.class.getName());
		} else {
			ViewTuple<TransConfigView, TransConfigViewModel> tuple = FluentViewLoader
					.fxmlView(TransConfigView.class).load();
			viewManager.push(TransConfigView.class.getName(), tuple, childViewPane.get());
		}
	}

	/**
	 * JAVAFX PROPERTIES
	 */
	public ObjectProperty<ConfigBase> configProperty() {
		return config;
	}

	public BooleanProperty initializedProperty() {
		return initialized;
	}

	public void setChildViewPane(StackPane value) {
		childViewPane.set(value);
	}
}

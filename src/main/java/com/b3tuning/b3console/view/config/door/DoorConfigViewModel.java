package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.door.DoorConfig;
import com.b3tuning.b3console.service.module.door.DoorModuleService;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.EditableViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import com.b3tuning.b3console.view.utils.AlertUtils;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class DoorConfigViewModel extends BaseViewModel implements EditableViewModel, Refreshable {

	// injected dependencies
	private UserPreferences    preferences;
	private NotificationCenter globalNotifications;
	private DoorModuleService  service;

	private ObjectProperty<DoorConfig> config = new SimpleObjectProperty<>();
	private DoorConfig                 originalConfig;

	private BooleanProperty dirty  = new SimpleBooleanProperty(false);
	private BooleanProperty saving = new SimpleBooleanProperty(false);

	// validation properties
	private ObservableRuleBasedValidator mirrorActionDownMaxValidator  = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionDownMinValidator  = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionLeftMaxValidator  = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionLeftMinValidator  = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionRightMaxValidator = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionRightMinValidator = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionUpMaxValidator    = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorActionUpMinValidator    = new ObservableRuleBasedValidator();

	private ObservableRuleBasedValidator mirrorSelectDriverMaxValidator    = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorSelectDriverMinValidator    = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorSelectFoldMaxValidator      = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorSelectFoldMinValidator      = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorSelectPassengerMaxValidator = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator mirrorSelectPassengerMinValidator = new ObservableRuleBasedValidator();

	private ObservableRuleBasedValidator windowActionAutoDownMaxValidator = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionAutoDownMinValidator = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionAutoUpMaxValidator   = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionAutoUpMinValidator   = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionDownMaxValidator     = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionDownMinValidator     = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionUpMaxValidator       = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator windowActionUpMinValidator       = new ObservableRuleBasedValidator();

	private ObservableRuleBasedValidator driverWindowMaxCurrentValidator    = new ObservableRuleBasedValidator();
	private ObservableRuleBasedValidator passengerWindowMaxCurrentValidator = new ObservableRuleBasedValidator();

	private CompositeValidator formValidator = new CompositeValidator();

	@Inject
	public DoorConfigViewModel(UserPreferences preferences, NotificationCenter notificationCenter,
	                           DoorModuleService service) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notificationCenter;
		this.service             = service;
	}

	private void initializeValidation() {
		log.entry();

	}

	@Override
	public BooleanProperty dirtyProperty() {
		return dirty;
	}

	@Override
	public boolean isDirty() {
		return dirty.get();
	}

	@Override
	public String saveChangesMessage() {
		return "Door Configuration has unsaved changes";
	}

	@Override
	public void navigationCancelledAction() {
		log.entry();
		ClickButtonNotification.fire(globalNotifications, MainMenuItemAction.CONFIG);
	}

	@Override
	public void refresh() {
		log.entry();
		if (dirty.get()) {
			AlertUtils.warn(saveChangesMessage());
		} else {
//			config.set(moduleService.getDoorConfig());
			originalConfig = config.get().copy();
			config.get().resetTrackingChanges();
			dirty.set(false);
		}
	}
}

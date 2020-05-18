package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.door.DoorConfig;
import com.b3tuning.b3console.service.module.door.DoorModuleService;
import com.b3tuning.b3console.validation.ValidationUtil;
import com.b3tuning.b3console.view.DetailMode;
import com.b3tuning.b3console.view.config.SpecializedConfigViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import static org.reactfx.EventStreams.combine;
import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class DoorConfigViewModel extends SpecializedConfigViewModel {

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

//	private ObjectProperty<CompositeValidator> formValidator = new SimpleObjectProperty<>();

	@Inject
	public DoorConfigViewModel(UserPreferences preferences, NotificationCenter notificationCenter,
	                           DoorModuleService service) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notificationCenter;
		this.service             = service;

		manage(combine(nonNullValuesOf(super.modeProperty()), nonNullValuesOf(config), nonNullValuesOf(super.formValidatorProperty())).subscribe((t) -> {
			log.entry(t._1, t._2, t._3);
			if (t._1 == null || t._2 == null || t._3 == null) {
				return;
			}
			super.readOnlyProperty().set(DetailMode.VIEW.equals(t._1));

			if (!DetailMode.VIEW.equals(t._1)) {
				initializeValidation();
			}
		}));
	}

	@Override
	protected void initializeValidation() {
		log.entry();

		ValidationUtil.isNotNull(mirrorActionDownMaxValidator, config.get().mirrorActionProperty().get().downMaxProperty(), "DownMax");

		getFormValidator().addValidators(mirrorActionDownMaxValidator);
	}

	public ObjectProperty<DoorConfig> configProperty() {
		return config;
	}
}

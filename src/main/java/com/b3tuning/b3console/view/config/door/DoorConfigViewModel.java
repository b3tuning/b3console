package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.door.DoorModuleService;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorActionConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorSelectConfig;
import com.b3tuning.b3console.service.module.door.config.WindowActionConfig;
import com.b3tuning.b3console.validation.ValidationUtil;
import com.b3tuning.b3console.view.DetailMode;
import com.b3tuning.b3console.view.config.SpecializedConfigViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
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
	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;
	private final DoorModuleService  service;

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

		manage(combine(nonNullValuesOf(super.modeProperty()), nonNullValuesOf(config),
		               nonNullValuesOf(super.formValidatorProperty())).subscribe((t) -> {
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

		ValidationUtil.isNotNull(mirrorActionDownMaxValidator, getMirrorAction().getDownMax(), "MirDownMax");
		ValidationUtil.isNotNull(mirrorActionDownMinValidator, getMirrorAction().getDownMin(), "MirDownMin");
		ValidationUtil.isNotNull(mirrorActionLeftMaxValidator, getMirrorAction().getLeftMax(), "MirLeftMax");
		ValidationUtil.isNotNull(mirrorActionLeftMinValidator, getMirrorAction().getLeftMin(), "MirLeftMin");
		ValidationUtil.isNotNull(mirrorActionRightMaxValidator, getMirrorAction().getRightMax(), "MirRightMax");
		ValidationUtil.isNotNull(mirrorActionRightMinValidator, getMirrorAction().getRightMin(), "MirRightMin");
		ValidationUtil.isNotNull(mirrorActionUpMaxValidator, getMirrorAction().getUpMax(), "MirUpMax");
		ValidationUtil.isNotNull(mirrorActionUpMinValidator, getMirrorAction().getUpMin(), "MirUpMin");

		ValidationUtil.isNotNull(mirrorSelectDriverMaxValidator, getMirrorSelect().getDriverMax(), "DriverMax");
		ValidationUtil.isNotNull(mirrorSelectDriverMinValidator, getMirrorSelect().getDriverMin(), "DriverMin");
		ValidationUtil.isNotNull(mirrorSelectFoldMaxValidator, getMirrorSelect().getFoldMax(), "FoldMax");
		ValidationUtil.isNotNull(mirrorSelectFoldMinValidator, getMirrorSelect().getFoldMin(), "FoldMin");
		ValidationUtil
				.isNotNull(mirrorSelectPassengerMaxValidator, getMirrorSelect().getPassengerMax(), "PassMax");
		ValidationUtil
				.isNotNull(mirrorSelectPassengerMinValidator, getMirrorSelect().getPassengerMin(), "PassMin");

		ValidationUtil
				.isNotNull(windowActionAutoDownMaxValidator, getWindowAction().getAutoDownMax(), "WinAutoDownMax");
		ValidationUtil
				.isNotNull(windowActionAutoDownMinValidator, getWindowAction().getAutoDownMin(), "WinAutoDownMin");
		ValidationUtil.isNotNull(windowActionAutoUpMaxValidator, getWindowAction().getAutoUpMax(), "WinAutoUpMax");
		ValidationUtil.isNotNull(windowActionAutoUpMinValidator, getWindowAction().getAutoUpMin(), "WinAutoUpMin");
		ValidationUtil.isNotNull(windowActionDownMaxValidator, getWindowAction().getDownMax(), "WinDownMax");
		ValidationUtil.isNotNull(windowActionDownMinValidator, getWindowAction().getDownMin(), "WinDownMin");
		ValidationUtil.isNotNull(windowActionUpMaxValidator, getWindowAction().getUpMax(), "WinUpMax");
		ValidationUtil.isNotNull(windowActionUpMinValidator, getWindowAction().getUpMin(), "WinUpMin");

		ValidationUtil.isNotNull(driverWindowMaxCurrentValidator, config.get().getDriverWindowMaxCurrent(),
		                         "DriverCurrentMax");
		ValidationUtil.isNotNull(passengerWindowMaxCurrentValidator, config.get().getPassengerWindowMaxCurrent(),
		                         "PassCurrentMax");

		getFormValidator()
				.addValidators(mirrorActionDownMaxValidator, mirrorActionDownMinValidator, mirrorActionLeftMaxValidator,
				               mirrorActionLeftMinValidator, mirrorActionRightMaxValidator,
				               mirrorActionRightMinValidator, mirrorActionUpMaxValidator, mirrorActionUpMinValidator,
				               mirrorSelectDriverMaxValidator, mirrorSelectDriverMinValidator,
				               mirrorSelectFoldMaxValidator, mirrorSelectFoldMinValidator,
				               mirrorSelectPassengerMaxValidator, mirrorSelectPassengerMinValidator,
				               windowActionAutoDownMaxValidator, windowActionAutoDownMinValidator,
				               windowActionAutoUpMaxValidator, windowActionAutoUpMinValidator,
				               windowActionDownMaxValidator, windowActionDownMinValidator, windowActionUpMaxValidator,
				               windowActionUpMinValidator, driverWindowMaxCurrentValidator,
				               passengerWindowMaxCurrentValidator);
	}

	public ObjectProperty<DoorConfig> configProperty() {
		return config;
	}

	public MirrorActionConfig getMirrorAction() {
		return config.get().getMirrorAction().get();
	}

	public MirrorSelectConfig getMirrorSelect() {
		return config.get().getMirrorSelect().get();
	}

	public WindowActionConfig getWindowAction() {
		return config.get().getWindowAction().get();
	}
}

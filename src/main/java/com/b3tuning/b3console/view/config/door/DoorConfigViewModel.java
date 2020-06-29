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
import javafx.beans.property.SimpleBooleanProperty;
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

	private DoorConfig originalConfig;

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

		manage(combine(nonNullValuesOf(super.modeProperty()), nonNullValuesOf(configProperty()),
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

		ValidationUtil.isNotNull(mirrorActionDownMaxValidator, getMirrorAction().getDownMax().asObject(), "MirDownMax");
		ValidationUtil.isNotNull(mirrorActionDownMinValidator, getMirrorAction().getDownMin().asObject(), "MirDownMin");
		ValidationUtil.isNotNull(mirrorActionLeftMaxValidator, getMirrorAction().getLeftMax().asObject(), "MirLeftMax");
		ValidationUtil.isNotNull(mirrorActionLeftMinValidator, getMirrorAction().getLeftMin().asObject(), "MirLeftMin");
		ValidationUtil
				.isNotNull(mirrorActionRightMaxValidator, getMirrorAction().getRightMax().asObject(), "MirRightMax");
		ValidationUtil
				.isNotNull(mirrorActionRightMinValidator, getMirrorAction().getRightMin().asObject(), "MirRightMin");
		ValidationUtil.isNotNull(mirrorActionUpMaxValidator, getMirrorAction().getUpMax().asObject(), "MirUpMax");
		ValidationUtil.isNotNull(mirrorActionUpMinValidator, getMirrorAction().getUpMin().asObject(), "MirUpMin");

		ValidationUtil
				.isNotNull(mirrorSelectDriverMaxValidator, getMirrorSelect().getDriverMax().asObject(), "DriverMax");
		ValidationUtil
				.isNotNull(mirrorSelectDriverMinValidator, getMirrorSelect().getDriverMin().asObject(), "DriverMin");
		ValidationUtil.isNotNull(mirrorSelectFoldMaxValidator, getMirrorSelect().getFoldMax().asObject(), "FoldMax");
		ValidationUtil.isNotNull(mirrorSelectFoldMinValidator, getMirrorSelect().getFoldMin().asObject(), "FoldMin");
		ValidationUtil
				.isNotNull(mirrorSelectPassengerMaxValidator, getMirrorSelect().getPassengerMax().asObject(),
				           "PassMax");
		ValidationUtil
				.isNotNull(mirrorSelectPassengerMinValidator, getMirrorSelect().getPassengerMin().asObject(),
				           "PassMin");

		ValidationUtil
				.isNotNull(windowActionAutoDownMaxValidator, getWindowAction().getAutoDownMax().asObject(),
				           "WinAutoDownMax");
		ValidationUtil
				.isNotNull(windowActionAutoDownMinValidator, getWindowAction().getAutoDownMin().asObject(),
				           "WinAutoDownMin");
		ValidationUtil
				.isNotNull(windowActionAutoUpMaxValidator, getWindowAction().getAutoUpMax().asObject(), "WinAutoUpMax");
		ValidationUtil
				.isNotNull(windowActionAutoUpMinValidator, getWindowAction().getAutoUpMin().asObject(), "WinAutoUpMin");
		ValidationUtil.isNotNull(windowActionDownMaxValidator, getWindowAction().getDownMax().asObject(), "WinDownMax");
		ValidationUtil.isNotNull(windowActionDownMinValidator, getWindowAction().getDownMin().asObject(), "WinDownMin");
		ValidationUtil.isNotNull(windowActionUpMaxValidator, getWindowAction().getUpMax().asObject(), "WinUpMax");
		ValidationUtil.isNotNull(windowActionUpMinValidator, getWindowAction().getUpMin().asObject(), "WinUpMin");

		ValidationUtil.isNotNull(driverWindowMaxCurrentValidator,
		                         getDoorConfigFromBase().getDriverWindowMaxCurrent().asObject(),
		                         "DriverCurrentMax");
		ValidationUtil
				.isNotNull(passengerWindowMaxCurrentValidator,
				           getDoorConfigFromBase().getPassengerWindowMaxCurrent().asObject(),
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

	private DoorConfig getDoorConfigFromBase() {
		return (DoorConfig) configProperty().get();
	}

	public MirrorActionConfig getMirrorAction() {
		return getDoorConfigFromBase().getMirrorAction().get();
	}

	public MirrorSelectConfig getMirrorSelect() {
		return getDoorConfigFromBase().getMirrorSelect().get();
	}

	public WindowActionConfig getWindowAction() {
		return getDoorConfigFromBase().getWindowAction().get();
	}
}

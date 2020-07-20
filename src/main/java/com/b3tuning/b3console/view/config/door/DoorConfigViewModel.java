package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorActionConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorSelectConfig;
import com.b3tuning.b3console.service.module.door.config.WindowActionConfig;
import com.b3tuning.b3console.validation.ValidationUtil;
import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

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
public class DoorConfigViewModel extends BaseViewModel {

	private final ObjectProperty<DoorConfig> config = new SimpleObjectProperty<>();

//	private final BooleanProperty dirty  = new SimpleBooleanProperty(false);
//	private final BooleanProperty saving = new SimpleBooleanProperty(false);

	// validation properties
	private final ObservableRuleBasedValidator mirrorActionDownMaxValidator  = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionDownMinValidator  = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionLeftMaxValidator  = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionLeftMinValidator  = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionRightMaxValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionRightMinValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionUpMaxValidator    = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorActionUpMinValidator    = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator mirrorSelectDriverMaxValidator    = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorSelectDriverMinValidator    = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorSelectFoldMaxValidator      = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorSelectFoldMinValidator      = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorSelectPassengerMaxValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mirrorSelectPassengerMinValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator windowActionAutoDownMaxValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionAutoDownMinValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionAutoUpMaxValidator   = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionAutoUpMinValidator   = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionDownMaxValidator     = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionDownMinValidator     = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionUpMaxValidator       = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator windowActionUpMinValidator       = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator driverWindowMaxCurrentValidator    = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator passengerWindowMaxCurrentValidator = new ObservableRuleBasedValidator();

	private final CompositeValidator validator = new CompositeValidator();

	@Inject
	public DoorConfigViewModel() {
		log.entry();
		// injected dependencies

		manage(nonNullValuesOf(config).subscribe(c -> {
			log.entry(c);
			initializeValidation();
		}));
	}

	private void initializeValidation() {
		log.entry();
		getMirrorAction();

		ValidationUtil.isNotNull(mirrorActionDownMaxValidator, getMirrorAction().downMaxProperty(), "MirDownMax");
		ValidationUtil.isNotNull(mirrorActionDownMinValidator, getMirrorAction().downMinProperty(), "MirDownMin");
		ValidationUtil.isNotNull(mirrorActionLeftMaxValidator, getMirrorAction().leftMaxProperty(), "MirLeftMax");
		ValidationUtil.isNotNull(mirrorActionLeftMinValidator, getMirrorAction().leftMinProperty(), "MirLeftMin");
		ValidationUtil.isNotNull(mirrorActionRightMaxValidator, getMirrorAction().rightMaxProperty(), "MirRightMax");
		ValidationUtil.isNotNull(mirrorActionRightMinValidator, getMirrorAction().rightMinProperty(), "MirRightMin");
		ValidationUtil.isNotNull(mirrorActionUpMaxValidator, getMirrorAction().upMaxProperty(), "MirUpMax");
		ValidationUtil.isNotNull(mirrorActionUpMinValidator, getMirrorAction().upMinProperty(), "MirUpMin");

		ValidationUtil.isNotNull(mirrorSelectDriverMaxValidator, getMirrorSelect().driverMaxProperty(), "DriverMax");
		ValidationUtil.isNotNull(mirrorSelectDriverMinValidator, getMirrorSelect().driverMinProperty(), "DriverMin");
		ValidationUtil.isNotNull(mirrorSelectFoldMaxValidator, getMirrorSelect().foldMaxProperty(), "FoldMax");
		ValidationUtil.isNotNull(mirrorSelectFoldMinValidator, getMirrorSelect().foldMinProperty(), "FoldMin");
		ValidationUtil
				.isNotNull(mirrorSelectPassengerMaxValidator, getMirrorSelect().passengerMaxProperty(), "PassMax");
		ValidationUtil
				.isNotNull(mirrorSelectPassengerMinValidator, getMirrorSelect().passengerMinProperty(), "PassMin");

		ValidationUtil
				.isNotNull(windowActionAutoDownMaxValidator, getWindowAction().autoDownMaxProperty(), "WinAutoDownMax");
		ValidationUtil
				.isNotNull(windowActionAutoDownMinValidator, getWindowAction().autoDownMinProperty(), "WinAutoDownMin");
		ValidationUtil.isNotNull(windowActionAutoUpMaxValidator, getWindowAction().autoUpMaxProperty(), "WinAutoUpMax");
		ValidationUtil.isNotNull(windowActionAutoUpMinValidator, getWindowAction().autoUpMinProperty(), "WinAutoUpMin");
		ValidationUtil.isNotNull(windowActionDownMaxValidator, getWindowAction().downMaxProperty(), "WinDownMax");
		ValidationUtil.isNotNull(windowActionDownMinValidator, getWindowAction().downMinProperty(), "WinDownMin");
		ValidationUtil.isNotNull(windowActionUpMaxValidator, getWindowAction().upMaxProperty(), "WinUpMax");
		ValidationUtil.isNotNull(windowActionUpMinValidator, getWindowAction().upMinProperty(), "WinUpMin");

		ValidationUtil.isNotNull(driverWindowMaxCurrentValidator, config.get().driverWindowMaxCurrentProperty(),
		                         "DriverCurrentMax");
		ValidationUtil
				.isNotNull(passengerWindowMaxCurrentValidator, config.get().passengerWindowMaxCurrentProperty(),
				           "PassCurrentMax");

		validator
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
		return config.get().getMirrorAction();
	}

	public MirrorSelectConfig getMirrorSelect() {
		return config.get().getMirrorSelect();
	}

	public WindowActionConfig getWindowAction() {
		return config.get().getWindowAction();
	}

	public ValidationStatus mirrorActionDownMaxValidation() {
		return mirrorActionDownMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionDownMinValidation() {
		return mirrorActionDownMinValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionLeftMaxValidation() {
		return mirrorActionLeftMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionLeftMinValidation() {
		return mirrorActionLeftMinValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionRightMaxValidation() {
		return mirrorActionRightMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionRightMinValidation() {
		return mirrorActionRightMinValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionUpMaxValidation() {
		return mirrorActionUpMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorActionUpMinValidation() {
		return mirrorActionUpMinValidator.getValidationStatus();
	}

	public ValidationStatus mirrorSelectDriverMaxValidation() {
		return mirrorSelectDriverMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorSelectDriverMinValidation() {
		return mirrorSelectDriverMinValidator.getValidationStatus();
	}

	public ValidationStatus mirrorSelectFoldMaxValidation() {
		return mirrorSelectFoldMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorSelectFoldMinValidation() {
		return mirrorSelectFoldMinValidator.getValidationStatus();
	}

	public ValidationStatus mirrorSelectPassengerMaxValidation() {
		return mirrorSelectPassengerMaxValidator.getValidationStatus();
	}

	public ValidationStatus mirrorSelectPassengerMinValidation() {
		return mirrorSelectPassengerMinValidator.getValidationStatus();
	}

	public ValidationStatus windowActionAutoDownMaxValidation() {
		return windowActionAutoDownMaxValidator.getValidationStatus();
	}

	public ValidationStatus windowActionAutoDownMinValidation() {
		return windowActionAutoDownMinValidator.getValidationStatus();
	}

	public ValidationStatus windowActionAutoUpMaxValidation() {
		return windowActionAutoUpMaxValidator.getValidationStatus();
	}

	public ValidationStatus windowActionAutoUpMinValidation() {
		return windowActionAutoUpMinValidator.getValidationStatus();
	}

	public ValidationStatus windowActionDownMaxValidation() {
		return windowActionDownMaxValidator.getValidationStatus();
	}

	public ValidationStatus windowActionDownMinValidation() {
		return windowActionDownMinValidator.getValidationStatus();
	}

	public ValidationStatus windowActionUpMaxValidation() {
		return windowActionUpMaxValidator.getValidationStatus();
	}

	public ValidationStatus windowActionUpMinValidation() {
		return windowActionUpMinValidator.getValidationStatus();
	}

	public ValidationStatus driverWindowMaxCurrentValidation() {
		return driverWindowMaxCurrentValidator.getValidationStatus();
	}

	public ValidationStatus passengerWindowMaxCurrentValidation() {
		return passengerWindowMaxCurrentValidator.getValidationStatus();
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		config.unbind();
	}
}

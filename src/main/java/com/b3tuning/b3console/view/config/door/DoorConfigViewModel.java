package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.view.config.SpecializedConfigViewModel;
import javafx.beans.property.ObjectProperty;
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
public class DoorConfigViewModel extends SpecializedConfigViewModel {

	private final ObjectProperty<DoorConfig> doorConfig = new SimpleObjectProperty<>();

//	private final BooleanProperty dirty  = new SimpleBooleanProperty(false);
//	private final BooleanProperty saving = new SimpleBooleanProperty(false);

	// validation properties
//	private ObservableRuleBasedValidator mirrorActionDownMaxValidator  = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionDownMinValidator  = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionLeftMaxValidator  = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionLeftMinValidator  = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionRightMaxValidator = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionRightMinValidator = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionUpMaxValidator    = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorActionUpMinValidator    = new ObservableRuleBasedValidator();
//
//	private ObservableRuleBasedValidator mirrorSelectDriverMaxValidator    = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorSelectDriverMinValidator    = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorSelectFoldMaxValidator      = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorSelectFoldMinValidator      = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorSelectPassengerMaxValidator = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator mirrorSelectPassengerMinValidator = new ObservableRuleBasedValidator();
//
//	private ObservableRuleBasedValidator windowActionAutoDownMaxValidator = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionAutoDownMinValidator = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionAutoUpMaxValidator   = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionAutoUpMinValidator   = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionDownMaxValidator     = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionDownMinValidator     = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionUpMaxValidator       = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator windowActionUpMinValidator       = new ObservableRuleBasedValidator();
//
//	private ObservableRuleBasedValidator driverWindowMaxCurrentValidator    = new ObservableRuleBasedValidator();
//	private ObservableRuleBasedValidator passengerWindowMaxCurrentValidator = new ObservableRuleBasedValidator();

//	private ObjectProperty<CompositeValidator> formValidator = new SimpleObjectProperty<>();

	@Inject
	public DoorConfigViewModel() {
		log.entry();
		// injected dependencies

//		manage(combine(nonNullValuesOf(super.modeProperty()), nonNullValuesOf(configProperty()),
//		               nonNullValuesOf(super.formValidatorProperty())).subscribe((t) -> {
//			log.entry(t._1, t._2, t._3);
//			if (t._1 == null || t._2 == null || t._3 == null) {
//				return;
//			}
//			super.readOnlyProperty().set(DetailMode.VIEW.equals(t._1));
//
//			if (!DetailMode.VIEW.equals(t._1)) {
//				initializeValidation();
//			}
//		}));
	}

	@Override
	protected void initializeValidation() {
		log.entry();

//		ValidationUtil.isNotNull(mirrorActionDownMaxValidator, getMirrorAction().downMaxProperty(), "MirDownMax");
//		ValidationUtil.isNotNull(mirrorActionDownMinValidator, getMirrorAction().downMinProperty(), "MirDownMin");
//		ValidationUtil.isNotNull(mirrorActionLeftMaxValidator, getMirrorAction().leftMaxProperty(), "MirLeftMax");
//		ValidationUtil.isNotNull(mirrorActionLeftMinValidator, getMirrorAction().leftMinProperty(), "MirLeftMin");
//		ValidationUtil
//				.isNotNull(mirrorActionRightMaxValidator, getMirrorAction().rightMaxProperty(), "MirRightMax");
//		ValidationUtil
//				.isNotNull(mirrorActionRightMinValidator, getMirrorAction().rightMinProperty(), "MirRightMin");
//		ValidationUtil.isNotNull(mirrorActionUpMaxValidator, getMirrorAction().upMaxProperty(), "MirUpMax");
//		ValidationUtil.isNotNull(mirrorActionUpMinValidator, getMirrorAction().upMinProperty(), "MirUpMin");
//
//		ValidationUtil
//				.isNotNull(mirrorSelectDriverMaxValidator, getMirrorSelect().driverMaxProperty(), "DriverMax");
//		ValidationUtil
//				.isNotNull(mirrorSelectDriverMinValidator, getMirrorSelect().driverMinProperty(), "DriverMin");
//		ValidationUtil.isNotNull(mirrorSelectFoldMaxValidator, getMirrorSelect().foldMaxProperty(), "FoldMax");
//		ValidationUtil.isNotNull(mirrorSelectFoldMinValidator, getMirrorSelect().foldMinProperty(), "FoldMin");
//		ValidationUtil
//				.isNotNull(mirrorSelectPassengerMaxValidator, getMirrorSelect().passengerMaxProperty(),
//				           "PassMax");
//		ValidationUtil
//				.isNotNull(mirrorSelectPassengerMinValidator, getMirrorSelect().passengerMinProperty(),
//				           "PassMin");
//
//		ValidationUtil
//				.isNotNull(windowActionAutoDownMaxValidator, getWindowAction().autoDownMaxProperty(),
//				           "WinAutoDownMax");
//		ValidationUtil
//				.isNotNull(windowActionAutoDownMinValidator, getWindowAction().autoDownMinProperty(),
//				           "WinAutoDownMin");
//		ValidationUtil
//				.isNotNull(windowActionAutoUpMaxValidator, getWindowAction().autoUpMaxProperty(), "WinAutoUpMax");
//		ValidationUtil
//				.isNotNull(windowActionAutoUpMinValidator, getWindowAction().autoUpMinProperty(), "WinAutoUpMin");
//		ValidationUtil.isNotNull(windowActionDownMaxValidator, getWindowAction().downMaxProperty(), "WinDownMax");
//		ValidationUtil.isNotNull(windowActionDownMinValidator, getWindowAction().downMinProperty(), "WinDownMin");
//		ValidationUtil.isNotNull(windowActionUpMaxValidator, getWindowAction().upMaxProperty(), "WinUpMax");
//		ValidationUtil.isNotNull(windowActionUpMinValidator, getWindowAction().upMinProperty(), "WinUpMin");
//
//		ValidationUtil.isNotNull(driverWindowMaxCurrentValidator,
//		                         config.get().driverWindowMaxCurrentProperty(),
//		                         "DriverCurrentMax");
//		ValidationUtil
//				.isNotNull(passengerWindowMaxCurrentValidator,
//				           config.get().passengerWindowMaxCurrentProperty(),
//				           "PassCurrentMax");
//
//		getFormValidator()
//				.addValidators(mirrorActionDownMaxValidator, mirrorActionDownMinValidator, mirrorActionLeftMaxValidator,
//				               mirrorActionLeftMinValidator, mirrorActionRightMaxValidator,
//				               mirrorActionRightMinValidator, mirrorActionUpMaxValidator, mirrorActionUpMinValidator,
//				               mirrorSelectDriverMaxValidator, mirrorSelectDriverMinValidator,
//				               mirrorSelectFoldMaxValidator, mirrorSelectFoldMinValidator,
//				               mirrorSelectPassengerMaxValidator, mirrorSelectPassengerMinValidator,
//				               windowActionAutoDownMaxValidator, windowActionAutoDownMinValidator,
//				               windowActionAutoUpMaxValidator, windowActionAutoUpMinValidator,
//				               windowActionDownMaxValidator, windowActionDownMinValidator, windowActionUpMaxValidator,
//				               windowActionUpMinValidator, driverWindowMaxCurrentValidator,
//				               passengerWindowMaxCurrentValidator);
	}

	public ObjectProperty<DoorConfig> doorConfigProperty() {
		return doorConfig;
	}
//	public MirrorActionConfig getMirrorAction() {
//		return config.get().getMirrorAction();
//	}
//
//	public MirrorSelectConfig getMirrorSelect() {
//		return config.get().getMirrorSelect();
//	}
//
//	public WindowActionConfig getWindowAction() {
//		return config.get().getWindowAction();
//	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		doorConfig.unbind();
	}
}

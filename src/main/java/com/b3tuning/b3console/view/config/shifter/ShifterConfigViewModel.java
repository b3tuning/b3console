package com.b3tuning.b3console.view.config.shifter;

import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
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
public class ShifterConfigViewModel extends BaseViewModel {

	private final ObjectProperty<ShifterConfig> config = new SimpleObjectProperty<>();

	// validation properties
	private final ObservableRuleBasedValidator brightnessValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator colorValidator      = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator delayValidator      = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator rangeValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1ParkValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1ParkValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2ParkValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2ParkValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1ReverseValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1ReverseValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2ReverseValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2ReverseValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1NeutralValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1NeutralValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2NeutralValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2NeutralValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1DriveValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1DriveValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2DriveValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2DriveValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1ManualValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1ManualValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2ManualValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2ManualValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1UpValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1UpValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2UpValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2UpValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator x1DownValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y1DownValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator x2DownValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator y2DownValidator = new ObservableRuleBasedValidator();

	private final CompositeValidator validator = new CompositeValidator();

	@Inject
	public ShifterConfigViewModel() {
		log.entry();
		manage(nonNullValuesOf(config).subscribe(c -> {
			log.entry(c);
			initializeValidation();
		}));
	}

	private void initializeValidation() {
		log.entry();

		ValidationUtil.isNotNull(brightnessValidator, config.get().indicatorProperty().get().brightnessProperty(),
		                         "Brightness");
		ValidationUtil.isNotNull(colorValidator, config.get().indicatorProperty().get().colorProperty(), "Color");
		ValidationUtil.isNotNull(delayValidator, config.get().indicatorProperty().get().waitProperty(), "Delay");

		ValidationUtil.isNotNull(rangeValidator, config.get().melexisProperty().get().rangeProperty(), "Range");

		ValidationUtil.isNotNull(x1ParkValidator, config.get().parkProperty().get().x1Property(), "Park X1");
		ValidationUtil.isNotNull(y1ParkValidator, config.get().parkProperty().get().y1Property(), "Park Y1");
		ValidationUtil.isNotNull(x2ParkValidator, config.get().parkProperty().get().x2Property(), "Park X2");
		ValidationUtil.isNotNull(y2ParkValidator, config.get().parkProperty().get().y2Property(), "Park Y2");

		ValidationUtil.isNotNull(x1ReverseValidator, config.get().reverseProperty().get().x1Property(), "Reverse X1");
		ValidationUtil.isNotNull(y1ReverseValidator, config.get().reverseProperty().get().y1Property(), "Reverse Y1");
		ValidationUtil.isNotNull(x2ReverseValidator, config.get().reverseProperty().get().x2Property(), "Reverse X2");
		ValidationUtil.isNotNull(y2ReverseValidator, config.get().reverseProperty().get().y2Property(), "Reverse Y2");

		ValidationUtil.isNotNull(x1NeutralValidator, config.get().neutralProperty().get().x1Property(), "Neutral X1");
		ValidationUtil.isNotNull(y1NeutralValidator, config.get().neutralProperty().get().y1Property(), "Neutral Y1");
		ValidationUtil.isNotNull(x2NeutralValidator, config.get().neutralProperty().get().x2Property(), "Neutral X2");
		ValidationUtil.isNotNull(y2NeutralValidator, config.get().neutralProperty().get().y2Property(), "Neutral Y2");

		ValidationUtil.isNotNull(x1DriveValidator, config.get().driveProperty().get().x1Property(), "Drive X1");
		ValidationUtil.isNotNull(y1DriveValidator, config.get().driveProperty().get().y1Property(), "Drive Y1");
		ValidationUtil.isNotNull(x2DriveValidator, config.get().driveProperty().get().x2Property(), "Drive X2");
		ValidationUtil.isNotNull(y2DriveValidator, config.get().driveProperty().get().y2Property(), "Drive Y2");

		ValidationUtil.isNotNull(x1ManualValidator, config.get().manualProperty().get().x1Property(), "Manual X1");
		ValidationUtil.isNotNull(y1ManualValidator, config.get().manualProperty().get().y1Property(), "Manual Y1");
		ValidationUtil.isNotNull(x2ManualValidator, config.get().manualProperty().get().x2Property(), "Manual X2");
		ValidationUtil.isNotNull(y2ManualValidator, config.get().manualProperty().get().y2Property(), "Manual Y2");

		ValidationUtil.isNotNull(x1UpValidator, config.get().upProperty().get().x1Property(), "Up X1");
		ValidationUtil.isNotNull(y1UpValidator, config.get().upProperty().get().y1Property(), "Up Y1");
		ValidationUtil.isNotNull(x2UpValidator, config.get().upProperty().get().x2Property(), "Up X2");
		ValidationUtil.isNotNull(y2UpValidator, config.get().upProperty().get().y2Property(), "Up Y2");

		ValidationUtil.isNotNull(x1DownValidator, config.get().downProperty().get().x1Property(), "Down X1");
		ValidationUtil.isNotNull(y1DownValidator, config.get().downProperty().get().y1Property(), "Down Y1");
		ValidationUtil.isNotNull(x2DownValidator, config.get().downProperty().get().x2Property(), "Down X2");
		ValidationUtil.isNotNull(y2DownValidator, config.get().downProperty().get().y2Property(), "Down Y2");

		validator.addValidators(brightnessValidator, colorValidator, delayValidator,
		                        rangeValidator,
		                        x1ParkValidator, y1ParkValidator, x2ParkValidator, y2ParkValidator,
		                        x1ReverseValidator, y1ReverseValidator, x2ReverseValidator, y2ReverseValidator,
		                        x1NeutralValidator, y1NeutralValidator, x2NeutralValidator, y2NeutralValidator,
		                        x1DriveValidator, y1DriveValidator, x2DriveValidator, y2DriveValidator,
		                        x1ManualValidator, y1ManualValidator, x2ManualValidator, y2ManualValidator,
		                        x1UpValidator, y1UpValidator, x2UpValidator, y2UpValidator,
		                        x1DownValidator, y1DownValidator, x2DownValidator, y2DownValidator);
	}

	public ObjectProperty<ShifterConfig> configProperty() {
		return config;
	}

	public ValidationStatus brightnessValidation() {
		return brightnessValidator.getValidationStatus();
	}

	public ValidationStatus colorValidation() {
		return colorValidator.getValidationStatus();
	}

	public ValidationStatus delayValidation() {
		return delayValidator.getValidationStatus();
	}

	public ValidationStatus rangeValidation() {
		return rangeValidator.getValidationStatus();
	}

	public ValidationStatus x1ParkValidation() {
		return x1ParkValidator.getValidationStatus();
	}

	public ValidationStatus y1ParkValidation() {
		return y1ParkValidator.getValidationStatus();
	}

	public ValidationStatus x2ParkValidation() {
		return x2ParkValidator.getValidationStatus();
	}

	public ValidationStatus y2ParkValidation() {
		return y2ParkValidator.getValidationStatus();
	}

	public ValidationStatus x1ReverseValidation() {
		return x1ReverseValidator.getValidationStatus();
	}

	public ValidationStatus y1ReverseValidation() {
		return y1ReverseValidator.getValidationStatus();
	}

	public ValidationStatus x2ReverseValidation() {
		return x2ReverseValidator.getValidationStatus();
	}

	public ValidationStatus y2ReverseValidation() {
		return y2ReverseValidator.getValidationStatus();
	}

	public ValidationStatus x1NeutralValidation() {
		return x1NeutralValidator.getValidationStatus();
	}

	public ValidationStatus y1NeutralValidation() {
		return y1NeutralValidator.getValidationStatus();
	}

	public ValidationStatus x2NeutralValidation() {
		return x2NeutralValidator.getValidationStatus();
	}

	public ValidationStatus y2NeutralValidation() {
		return y2NeutralValidator.getValidationStatus();
	}

	public ValidationStatus x1DriveValidation() {
		return x1DriveValidator.getValidationStatus();
	}

	public ValidationStatus y1DriveValidation() {
		return y1DriveValidator.getValidationStatus();
	}

	public ValidationStatus x2DriveValidation() {
		return x2DriveValidator.getValidationStatus();
	}

	public ValidationStatus y2DriveValidation() {
		return y2DriveValidator.getValidationStatus();
	}

	public ValidationStatus x1ManualValidation() {
		return x1ManualValidator.getValidationStatus();
	}

	public ValidationStatus y1ManualValidation() {
		return y1ManualValidator.getValidationStatus();
	}

	public ValidationStatus x2ManualValidation() {
		return x2ManualValidator.getValidationStatus();
	}

	public ValidationStatus y2ManualValidation() {
		return y2ManualValidator.getValidationStatus();
	}

	public ValidationStatus x1UpValidation() {
		return x1UpValidator.getValidationStatus();
	}

	public ValidationStatus y1UpValidation() {
		return y1UpValidator.getValidationStatus();
	}

	public ValidationStatus x2UpValidation() {
		return x2UpValidator.getValidationStatus();
	}

	public ValidationStatus y2UpValidation() {
		return y2UpValidator.getValidationStatus();
	}

	public ValidationStatus x1DownValidation() {
		return x1DownValidator.getValidationStatus();
	}

	public ValidationStatus y1DownValidation() {
		return y1DownValidator.getValidationStatus();
	}

	public ValidationStatus x2DownValidation() {
		return x2DownValidator.getValidationStatus();
	}

	public ValidationStatus y2DownValidation() {
		return y2DownValidator.getValidationStatus();
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		config.unbind();
	}
}

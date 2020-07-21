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
	private final ObservableRuleBasedValidator colorValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator delayValidator = new ObservableRuleBasedValidator();

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

		ValidationUtil.isNotNull(brightnessValidator, config.get().indicatorProperty().get().brightnessProperty(), "Brightness");
		ValidationUtil.isNotNull(colorValidator, config.get().indicatorProperty().get().colorProperty(), "Color");
		ValidationUtil.isNotNull(delayValidator, config.get().indicatorProperty().get().waitProperty(), "Delay");

		validator.addValidators(brightnessValidator, colorValidator, delayValidator);
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

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		config.unbind();
	}
}

package com.b3tuning.b3console.view.config.trans;

import com.b3tuning.b3console.service.module.trans.config.TransConfig;
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
public class TransConfigViewModel extends BaseViewModel {

	private final ObjectProperty<TransConfig> config = new SimpleObjectProperty<>();

	// validation properties
	private final ObservableRuleBasedValidator encMaxValidator     = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator encMinValidator     = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator currentMaxValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator aggKpValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator aggKiValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator aggKdValidator = new ObservableRuleBasedValidator();

	private final ObservableRuleBasedValidator consKpValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator consKiValidator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator consKdValidator = new ObservableRuleBasedValidator();

	private final CompositeValidator validator = new CompositeValidator();

	@Inject
	public TransConfigViewModel() {
		log.entry();
		manage(nonNullValuesOf(config).subscribe(c -> {
			log.entry(c);
			initializeValidation();
		}));
	}

	private void initializeValidation() {
		log.entry();

		ValidationUtil.isNotNull(encMaxValidator, config.get().ems22AProperty().get().encMaxProperty(), "Encoder Max");
		ValidationUtil.isNotNull(encMinValidator, config.get().ems22AProperty().get().encMinProperty(), "Encoder Min");

		ValidationUtil.isNotNull(currentMaxValidator, config.get().vnh5019Property().get().maxCurrentProperty(),
		                         "Current Max");

		ValidationUtil.isNotNull(aggKpValidator, config.get().pidProperty().get().aggKpProperty(),
								 "Aggressive Kp");
		ValidationUtil.isNotNull(aggKiValidator, config.get().pidProperty().get().aggKiProperty(),
								 "Aggressive Ki");
		ValidationUtil.isNotNull(aggKdValidator, config.get().pidProperty().get().aggKdProperty(),
								 "Aggressive Kd");

		ValidationUtil.isNotNull(consKpValidator, config.get().pidProperty().get().consKpProperty(),
								 "Conservative Kp");
		ValidationUtil.isNotNull(consKiValidator, config.get().pidProperty().get().consKiProperty(),
								 "Conservative Ki");
		ValidationUtil.isNotNull(consKdValidator, config.get().pidProperty().get().consKdProperty(),
								 "Conservative Kd");

		validator.addValidators(encMaxValidator, encMinValidator,
								currentMaxValidator,
								aggKpValidator, aggKiValidator, aggKdValidator,
								consKpValidator, consKiValidator, consKdValidator);
	}

	public ObjectProperty<TransConfig> configProperty() {
		return config;
	}

	public ValidationStatus encMaxValidation() {
		return encMaxValidator.getValidationStatus();
	}

	public ValidationStatus encMinValidation() {
		return encMinValidator.getValidationStatus();
	}

	public ValidationStatus currentMaxValidation() {
		return currentMaxValidator.getValidationStatus();
	}

	public ValidationStatus aggKpValidation() {
		return aggKpValidator.getValidationStatus();
	}

	public ValidationStatus aggKiValidation() {
		return aggKiValidator.getValidationStatus();
	}

	public ValidationStatus aggKdValidation() {
		return aggKdValidator.getValidationStatus();
	}

	public ValidationStatus consKpValidation() {
		return consKpValidator.getValidationStatus();
	}

	public ValidationStatus consKiValidation() {
		return consKiValidator.getValidationStatus();
	}

	public ValidationStatus consKdValidation() {
		return consKdValidator.getValidationStatus();
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		config.unbind();
	}
}

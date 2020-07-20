/*
 *  Created on:  Jul 20, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  CanBusViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.service.module.CanBusConfig;
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

@XSlf4j
public class CanBusConfigViewModel extends BaseViewModel {

	private final ObjectProperty<CanBusConfig> config = new SimpleObjectProperty<>();

	private final ObservableRuleBasedValidator addrTXValidator   = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator bootRXValidator   = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator bootTXValidator   = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator intvlTXValidator  = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mask0Validator    = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator filter00Validator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator filter01Validator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator mask1Validator    = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator filter10Validator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator filter11Validator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator filter12Validator = new ObservableRuleBasedValidator();
	private final ObservableRuleBasedValidator filter13Validator = new ObservableRuleBasedValidator();

	private final CompositeValidator validator = new CompositeValidator();

	@Inject
	public CanBusConfigViewModel() {
		log.entry();

		manage(nonNullValuesOf(config).subscribe(c -> {
			log.entry(c);
			initializeValidation();
		}));
	}

	private void initializeValidation() {
		log.entry();

		ValidationUtil.isNotNull(addrTXValidator, config.get().addrTXProperty(), "addrTX");
		ValidationUtil.isNotNull(bootRXValidator, config.get().bootRXProperty(), "bootRX");
		ValidationUtil.isNotNull(bootTXValidator, config.get().bootTXProperty(), "bootTX");
		ValidationUtil.isNotNull(intvlTXValidator, config.get().intvlTXProperty(), "intvlTX");
		ValidationUtil.isNotNull(mask0Validator, config.get().mask0Property(), "mask0");
		ValidationUtil.isNotNull(filter00Validator, config.get().filter0_0Property(), "filter0_0");
		ValidationUtil.isNotNull(filter01Validator, config.get().filter0_1Property(), "filter0_1");
		ValidationUtil.isNotNull(mask1Validator, config.get().mask1Property(), "mask1");
		ValidationUtil.isNotNull(filter10Validator, config.get().filter1_0Property(), "filter1_0");
		ValidationUtil.isNotNull(filter11Validator, config.get().filter1_1Property(), "filter1_1");
		ValidationUtil.isNotNull(filter12Validator, config.get().filter1_2Property(), "filter1_2");
		ValidationUtil.isNotNull(filter13Validator, config.get().filter1_3Property(), "filter1_3");

		validator.addValidators(addrTXValidator, bootRXValidator, bootTXValidator, intvlTXValidator,
		                        mask0Validator, filter00Validator, filter01Validator,
		                        mask1Validator, filter10Validator, filter11Validator,
		                        filter12Validator, filter13Validator);
	}

	public ObjectProperty<CanBusConfig> configProperty() {
		return config;
	}

	public ValidationStatus addrTXValidation() {
		return addrTXValidator.getValidationStatus();
	}

	public ValidationStatus bootRXValidation() {
		return bootRXValidator.getValidationStatus();
	}

	public ValidationStatus bootTXValidation() {
		return bootTXValidator.getValidationStatus();
	}

	public ValidationStatus intvlTXValidation() {
		return intvlTXValidator.getValidationStatus();
	}

	public ValidationStatus mask0Validation() {
		return mask0Validator.getValidationStatus();
	}

	public ValidationStatus filter00Validation() {
		return filter00Validator.getValidationStatus();
	}

	public ValidationStatus filter01Validation() {
		return filter01Validator.getValidationStatus();
	}

	public ValidationStatus mask1Validation() {
		return mask1Validator.getValidationStatus();
	}

	public ValidationStatus filter10Validation() {
		return filter10Validator.getValidationStatus();
	}

	public ValidationStatus filter11Validation() {
		return filter11Validator.getValidationStatus();
	}

	public ValidationStatus filter12Validation() {
		return filter12Validator.getValidationStatus();
	}

	public ValidationStatus filter13Validation() {
		return filter13Validator.getValidationStatus();
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		config.unbind();
	}
}
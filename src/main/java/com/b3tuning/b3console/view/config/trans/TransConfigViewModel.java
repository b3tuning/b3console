package com.b3tuning.b3console.view.config.trans;

import com.b3tuning.b3console.service.module.trans.config.TransConfig;
import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
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
	private final ObservableRuleBasedValidator mirrorActionDownMaxValidator = new ObservableRuleBasedValidator();

	private final CompositeValidator formValidator = new CompositeValidator();

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
	}

	public ObjectProperty<TransConfig> configProperty() {
		return config;
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		config.unbind();
	}
}

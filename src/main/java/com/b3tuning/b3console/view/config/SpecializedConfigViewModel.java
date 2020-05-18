package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.DetailMode;
import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationStatus;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/*
 *  Created on:  May 18, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public abstract class SpecializedConfigViewModel extends BaseViewModel {

	private ObjectProperty<DetailMode>         mode          = new SimpleObjectProperty<>();
	private ObjectProperty<CompositeValidator> formValidator = new SimpleObjectProperty<>();
	private BooleanProperty                    readonly      = new SimpleBooleanProperty();

	protected SpecializedConfigViewModel() {
		super();

	}
	protected abstract void initializeValidation();

	public ObjectProperty<DetailMode> modeProperty() {
		return mode;
	}
	public BooleanProperty readOnlyProperty() {
		return readonly;
	}

	public ValidationStatus formValidation() {
		return formValidator.get().getValidationStatus();
	}

	protected CompositeValidator getFormValidator() {
		return formValidator.get();
	}

	protected ObjectProperty<CompositeValidator> formValidatorProperty() {
		return formValidator;
	}
}

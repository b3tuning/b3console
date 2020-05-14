package com.b3tuning.b3console.validation;

import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ObservableRuleBasedValidator;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.ObjectExpression;
import javafx.beans.value.ObservableStringValue;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import static de.saxsys.mvvmfx.utils.validation.ValidationMessage.error;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class ValidationUtil {

	private static final String NO_STYLE = "no_styling_applied";

	public static <T extends ObservableStringValue> void isNotEmpty(ObservableRuleBasedValidator validator, T property,
	                                                                String name) {

		validator.addRule(Bindings.isNotEmpty(property), error(String.format("%s is required.", name)));
	}

	public static void isTrue(ObservableRuleBasedValidator validator, BooleanBinding binding, String message) {

		validator.addRule(binding, error(message));

	}

	public static <T extends ObjectExpression<?>> void isNotNull(ObservableRuleBasedValidator validator, T property,
	                                                             String name) {

		validator.addRule(Bindings.isNotNull(property), error(String.format("%s is required.", name)));
	}

	public static void clearValidationValidators(CompositeValidator formValidator,
	                                             ObservableRuleBasedValidator... validators) {
		formValidator.removeValidators(validators);
	}

	public static ControlsFxVisualizer clearValidationVisualizer(ControlsFxVisualizer validationVisualizer) {
		if (validationVisualizer != null) {
			// force updating display
			validationVisualizer.setDecoration(new StyleClassValidationDecoration(NO_STYLE, NO_STYLE));
		}

		// now clear it out completely
		validationVisualizer = new ControlsFxVisualizer();
		validationVisualizer.setDecoration(new StyleClassValidationDecoration());
		return validationVisualizer;
	}
}

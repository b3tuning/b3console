package com.b3tuning.b3console.control.textfields;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class FloatTextFormatter extends TextFormatter<Float> {

	private static final Float DEFAULT_VALUE = null;

	public FloatTextFormatter() {

		super(
				// string converter converts between a string and a value property.
				new StringConverter<>() {
					@Override
					public String toString(Float value) {
						if (value == null) {
							return "";
						}
						return value.toString();
					}

					@Override
					public Float fromString(String value) {
						try {
							if (value == null) {
								return DEFAULT_VALUE;
							}
							return Float.parseFloat(value);
						}
						catch (NumberFormatException e) {
							return DEFAULT_VALUE;
						}
					}
				},

				DEFAULT_VALUE,

				// change filter rejects text input if it cannot be parsed.
				change -> {
					try {
						if (change.getControlNewText() == null || change.getControlNewText().isEmpty()) {
							return change;
						}
						Float.parseFloat(change.getControlNewText());
						return change;
					}
					catch (NumberFormatException e) {
						return null;
					}
				}
		     );
	}
}
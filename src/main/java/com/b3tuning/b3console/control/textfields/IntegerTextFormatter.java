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
public class IntegerTextFormatter extends TextFormatter<Integer> {

	private static final Integer DEFAULT_VALUE = null;

	public IntegerTextFormatter() {

		super(
				// string converter converts between a string and a value property.
				new StringConverter<>() {
					@Override
					public String toString(Integer value) {
						if (value == null) {
							return "";
						}
						return value.toString();
					}

					@Override
					public Integer fromString(String value) {
						try {
							if (value == null) {
								return DEFAULT_VALUE;
							}
							return Integer.parseInt(value);
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
						Integer.parseInt(change.getControlNewText());
						return change;
					}
					catch (NumberFormatException e) {
						return null;
					}
				}
		     );
	}
}
/*
 *  Created on:  Jul 01, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  IntegerTextFormatter is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.utils;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class IntegerTextFormatter extends TextFormatter<Integer> {

	public IntegerTextFormatter() {
		super(
				new IntegerStringConverter() {
					@Override
					public Integer fromString(String s) {
						if (s.isEmpty()) {
							return 0;
						}
						return super.fromString(s);
					}
				},
				0,
				change -> {
					String newText = change.getControlNewText();
					if (newText.matches("-?([1-9][0-9]*)?")) {
						return change;
					} else if ("-".equals(change.getText())) {
						if (change.getControlText().startsWith("-")) {
							change.setText("");
							change.setRange(0, 1);
							change.setCaretPosition(change.getCaretPosition() - 2);
							change.setAnchor(change.getAnchor() - 2);
						} else {
							change.setRange(0, 0);
						}
						return change;
					}
					return null;
				}
		     );
	}
}
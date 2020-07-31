/*
 *  Created on:  Jul 30, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  IntegerButtonInputField is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.utils.buttonInputField;

import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import javafx.beans.NamedArg;

public class IntegerButtonInputField extends ButtonInputField {

	public IntegerButtonInputField(@NamedArg("buttonText") String buttonText) {
		super(buttonText);
		this.setFormatter(new IntegerTextFormatter());
	}
}

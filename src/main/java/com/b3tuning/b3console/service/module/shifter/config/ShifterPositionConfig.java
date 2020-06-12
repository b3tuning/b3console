/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ShifterPositionConfig is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.shifter.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Shifter Position Config
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ShifterPositionConfig extends EditableEntity implements Serializable {
	private static final long serialVersionUID = -3677812416764695651L;

	private IntegerProperty x1; // int16_t - needs mask?
	private IntegerProperty x2; // int16_t - needs mask?
	private IntegerProperty y1; // int16_t - needs mask?
	private IntegerProperty y2; // int16_t - needs mask?
	private IntegerProperty enumVal;

	public ShifterPositionConfig() {
		this.x1      = new SimpleIntegerProperty();
		this.y1      = new SimpleIntegerProperty();
		this.x2      = new SimpleIntegerProperty();
		this.y2      = new SimpleIntegerProperty();
		this.enumVal = new SimpleIntegerProperty();
		super.trackProperties(x1, y1, x2, y2, enumVal);
	}

	public ShifterPositionConfig(int x1Value, int y1Value, int x2Value, int y2Value, int enumValue) {
		this();
		this.x1.set(x1Value);
		this.y1.set(y1Value);
		this.x2.set(x2Value);
		this.y2.set(y2Value);
		this.enumVal.set(enumValue);
	}
}

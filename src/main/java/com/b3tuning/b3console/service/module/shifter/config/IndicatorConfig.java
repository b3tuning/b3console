/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  IndicatorConfig is part of b3console
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
 * IndicatorConfig
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class IndicatorConfig extends EditableEntity implements Serializable {
	private static final long serialVersionUID = -7837278550103560074L;

	private IntegerProperty brightness; // uint_8t - needs mask 0x0FF?
	private IntegerProperty color;      // uint_8t - needs mask 0xFF?
	private IntegerProperty wait;       // uint_8t - needs mask 0xFF?

	public IndicatorConfig() {
		this.brightness = new SimpleIntegerProperty();
		this.color      = new SimpleIntegerProperty();
		this.wait       = new SimpleIntegerProperty();
		super.trackProperties(brightness, color, wait);
	}

	public IndicatorConfig(int brightnessValue, int colorValue, int waitValue) {
		this();
		this.brightness.set(brightnessValue);
		this.color.set(colorValue);
		this.wait.set(waitValue);
	}
}

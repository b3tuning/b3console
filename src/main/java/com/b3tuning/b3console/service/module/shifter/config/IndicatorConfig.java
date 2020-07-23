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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * IndicatorConfig
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class IndicatorConfig extends EditableEntity {

	private final ObjectProperty<Integer> brightness; // uint_8t - needs mask 0x0FF?
	private final ObjectProperty<Integer> color;      // uint_8t - needs mask 0xFF?
	private final ObjectProperty<Integer> wait;       // uint_8t - needs mask 0xFF?

	public IndicatorConfig() {
		this.brightness = new SimpleObjectProperty<>();
		this.color      = new SimpleObjectProperty<>();
		this.wait       = new SimpleObjectProperty<>();
		super.trackProperties(brightness, color, wait);
	}

	public IndicatorConfig(Integer brightnessValue, Integer colorValue, Integer waitValue) {
		this();
		setBrightness(brightnessValue);
		setColor(colorValue);
		setWait(waitValue);
	}

//	public IndicatorConfig clone() {
//		return new IndicatorConfig(this.getBrightness(),
//		                           this.getColor(),
//		                           this.getWait());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getBrightness() {
		return this.brightness.get();
	}

	public Integer getColor() {
		return this.color.get();
	}

	public Integer getWait() {
		return this.wait.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setBrightness(Integer value) {
		this.brightness.set(value);
	}

	public void setColor(Integer value) {
		this.color.set(value);
	}

	public void setWait(Integer value) {
		this.wait.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> brightnessProperty() {
		return this.brightness;
	}

	public ObjectProperty<Integer> colorProperty() {
		return this.color;
	}

	public ObjectProperty<Integer> waitProperty() {
		return this.wait;
	}
}

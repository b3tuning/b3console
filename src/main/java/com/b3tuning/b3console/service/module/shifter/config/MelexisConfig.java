/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MelexisConfig is part of b3console
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
 * MelexisConfig
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MelexisConfig extends EditableEntity {

	private final ObjectProperty<Integer> range;

	public MelexisConfig() {
		this.range = new SimpleObjectProperty<>();
		super.trackProperties(range);
	}

	public MelexisConfig(Integer rangeValue) {
		this();
		this.range.set(rangeValue);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getRange() {
		return this.range.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setRange(Integer value) {
		this.range.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> rangeProperty() {
		return this.range;
	}
}

/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  Vnh5019Config is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Vnh5019Config extends EditableEntity {

	private final ObjectProperty<Integer> maxCurrent;

	public Vnh5019Config() {
		this.maxCurrent = new SimpleObjectProperty<>();
		super.trackProperties(maxCurrent);
	}

	public Vnh5019Config(Integer max) {
		this();
		setMaxCurrent(max);
	}
//	public Vnh5019Config clone() {
//		return new Vnh5019Config(this.maxCurrent.get());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getMaxCurrent() {
		return this.maxCurrent.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setMaxCurrent(Integer value) {
		this.maxCurrent.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> maxCurrentProperty() {
		return this.maxCurrent;
	}

}

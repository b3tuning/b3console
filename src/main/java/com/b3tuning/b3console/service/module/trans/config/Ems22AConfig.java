/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  Ems22AConfig is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class Ems22AConfig extends EditableEntity {

	private final ObjectProperty<Integer> encMax;
	private final ObjectProperty<Integer> encMin;

	public Ems22AConfig() {
		this.encMax = new SimpleObjectProperty<>();
		this.encMin = new SimpleObjectProperty<>();
		super.trackProperties(this.encMax, this.encMin);
	}

	public Ems22AConfig(Integer max, Integer min) {
		this();
		setEncMax(max);
		setEncMin(min);
	}

//	public Ems22AConfig clone() {
//		return new Ems22AConfig(this.getEncMax(), this.getEncMin());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getEncMax() {
		return this.encMax.get();
	}

	public Integer getEncMin() {
		return this.encMin.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setEncMax(Integer value) {
		this.encMax.set(value);
	}

	public void setEncMin(Integer value) {
		this.encMin.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> encMaxProperty() {
		return this.encMax;
	}

	public ObjectProperty<Integer> encMinProperty() {
		return this.encMin;
	}

}

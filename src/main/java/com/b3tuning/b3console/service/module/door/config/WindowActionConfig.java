package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class WindowActionConfig extends EditableEntity {

	private final ObjectProperty<Integer> autoDownMax;
	private final ObjectProperty<Integer> autoDownMin;
	private final ObjectProperty<Integer> autoUpMax;
	private final ObjectProperty<Integer> autoUpMin;
	private final ObjectProperty<Integer> downMax;
	private final ObjectProperty<Integer> downMin;
	private final ObjectProperty<Integer> upMax;
	private final ObjectProperty<Integer> upMin;

	public WindowActionConfig() {
		this.autoDownMax = new SimpleObjectProperty<>();
		this.autoDownMin = new SimpleObjectProperty<>();
		this.autoUpMax   = new SimpleObjectProperty<>();
		this.autoUpMin   = new SimpleObjectProperty<>();
		this.downMax     = new SimpleObjectProperty<>();
		this.downMin     = new SimpleObjectProperty<>();
		this.upMax       = new SimpleObjectProperty<>();
		this.upMin       = new SimpleObjectProperty<>();
		super.trackProperties(this.autoDownMax,
		                      this.autoDownMin,
		                      this.autoUpMax,
		                      this.autoUpMin,
		                      this.downMax,
		                      this.downMin,
		                      this.upMax,
		                      this.upMin);
	}

	public WindowActionConfig(Integer autoDownMax, Integer autoDownMin, Integer autoUpMax, Integer autoUpMin,
	                          Integer downMax, Integer downMin, Integer upMax, Integer upMin) {
		this();
		setAutoDownMax(autoDownMax);
		setAutoDownMin(autoDownMin);
		setAutoUpMax(autoUpMax);
		setAutoUpMin(autoUpMin);
		setDownMax(downMax);
		setDownMin(downMin);
		setUpMax(upMax);
		setUpMin(upMin);
	}

//	public WindowActionConfig clone() {
//		return new WindowActionConfig(this.getAutoDownMax(),
//		                              this.getAutoDownMin(),
//		                              this.getAutoUpMax(),
//		                              this.getAutoUpMin(),
//		                              this.getDownMax(),
//		                              this.getDownMin(),
//		                              this.getUpMax(),
//		                              this.getUpMin());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getAutoDownMax() {
		return this.autoDownMax.get();
	}

	public Integer getAutoDownMin() {
		return this.autoDownMin.get();
	}

	public Integer getAutoUpMax() {
		return this.autoUpMax.get();
	}

	public Integer getAutoUpMin() {
		return this.autoUpMin.get();
	}

	public Integer getDownMax() {
		return this.downMax.get();
	}

	public Integer getDownMin() {
		return this.downMin.get();
	}

	public Integer getUpMax() {
		return this.upMax.get();
	}

	public Integer getUpMin() {
		return this.upMin.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setAutoDownMax(Integer value) {
		this.autoDownMax.set(value);
	}

	public void setAutoDownMin(Integer value) {
		this.autoDownMin.set(value);
	}

	public void setAutoUpMax(Integer value) {
		this.autoUpMax.set(value);
	}

	public void setAutoUpMin(Integer value) {
		this.autoUpMin.set(value);
	}

	public void setDownMax(Integer value) {
		this.downMax.set(value);
	}

	public void setDownMin(Integer value) {
		this.downMin.set(value);
	}

	public void setUpMax(Integer value) {
		this.upMax.set(value);
	}

	public void setUpMin(Integer value) {
		this.upMin.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> autoDownMaxProperty() {
		return this.autoDownMax;
	}

	public ObjectProperty<Integer> autoDownMinProperty() {
		return this.autoDownMin;
	}

	public ObjectProperty<Integer> autoUpMaxProperty() {
		return this.autoUpMax;
	}

	public ObjectProperty<Integer> autoUpMinProperty() {
		return this.autoUpMin;
	}

	public ObjectProperty<Integer> downMaxProperty() {
		return this.downMax;
	}

	public ObjectProperty<Integer> downMinProperty() {
		return this.downMin;
	}

	public ObjectProperty<Integer> upMaxProperty() {
		return this.upMax;
	}

	public ObjectProperty<Integer> upMinProperty() {
		return this.upMin;
	}

}

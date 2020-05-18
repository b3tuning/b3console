package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class WindowActionConfig extends EditableEntity implements Serializable {

	private static final long serialVersionUID = -7074999213839249453L;

	private ObjectProperty<Integer> autoDownMax;
	private ObjectProperty<Integer> autoDownMin;
	private ObjectProperty<Integer> autoUpMax;
	private ObjectProperty<Integer> autoUpMin;
	private ObjectProperty<Integer> downMax;
	private ObjectProperty<Integer> downMin;
	private ObjectProperty<Integer> upMax;
	private ObjectProperty<Integer> upMin;

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

	public WindowActionConfig(int autoDownMax, int downMin, int autoUpMax, int autoUpMin,
	                          int downMax, int rightMin, int upMax, int upMin) {
		this();
		this.autoDownMax.set(autoDownMax);
		this.autoDownMin.set(downMin);
		this.autoUpMax.set(autoUpMax);
		this.autoUpMin.set(autoUpMin);
		this.downMax.set(downMax);
		this.downMin.set(rightMin);
		this.upMax.set(upMax);
		this.upMin.set(upMin);
	}

	public WindowActionConfig copy() {
		return new WindowActionConfig(this.getAutoDownMax(),
		                              this.getAutoDownMin(),
		                              this.getAutoUpMax(),
		                              this.getAutoUpMin(),
		                              this.getDownMax(),
		                              this.getDownMin(),
		                              this.getUpMax(),
		                              this.getUpMin());
	}

	public int getAutoDownMax() {
		return autoDownMax.get();
	}

	public int getAutoDownMin() {
		return autoDownMin.get();
	}

	public int getAutoUpMax() {
		return autoUpMax.get();
	}

	public int getAutoUpMin() {
		return autoUpMin.get();
	}

	public int getDownMax() {
		return downMax.get();
	}

	public int getDownMin() {
		return downMin.get();
	}

	public int getUpMax() {
		return upMax.get();
	}

	public int getUpMin() {
		return upMin.get();
	}

	public void setAutoDownMax(int value) {
		this.autoDownMax.set(value);
	}

	public void setAutoDownMin(int value) {
		this.autoDownMin.set(value);
	}

	public void setAutoUpMax(int value) {
		this.autoUpMax.set(value);
	}

	public void setAutoUpMin(int value) {
		this.autoUpMin.set(value);
	}

	public void setDownMax(int value) {
		this.downMax.set(value);
	}

	public void setDownMin(int value) {
		this.downMin.set(value);
	}

	public void setUpMax(int value) {
		this.upMax.set(value);
	}

	public void setUpMin(int value) {
		this.upMin.set(value);
	}

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

package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

	private IntegerProperty autoDownMax;
	private IntegerProperty autoDownMin;
	private IntegerProperty autoUpMax;
	private IntegerProperty autoUpMin;
	private IntegerProperty downMax;
	private IntegerProperty downMin;
	private IntegerProperty upMax;
	private IntegerProperty upMin;

	public WindowActionConfig() {
		this.autoDownMax = new SimpleIntegerProperty();
		this.autoDownMin = new SimpleIntegerProperty();
		this.autoUpMax   = new SimpleIntegerProperty();
		this.autoUpMin   = new SimpleIntegerProperty();
		this.downMax     = new SimpleIntegerProperty();
		this.downMin     = new SimpleIntegerProperty();
		this.upMax       = new SimpleIntegerProperty();
		this.upMin       = new SimpleIntegerProperty();
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

	public IntegerProperty autoDownMaxProperty() {
		return this.autoDownMax;
	}

	public IntegerProperty autoDownMinProperty() {
		return this.autoDownMin;
	}

	public IntegerProperty autoUpMaxProperty() {
		return this.autoUpMax;
	}

	public IntegerProperty autoUpMinProperty() {
		return this.autoUpMin;
	}

	public IntegerProperty downMaxProperty() {
		return this.downMax;
	}

	public IntegerProperty downMinProperty() {
		return this.downMin;
	}

	public IntegerProperty upMaxProperty() {
		return this.upMax;
	}

	public IntegerProperty upMinProperty() {
		return this.upMin;
	}
}

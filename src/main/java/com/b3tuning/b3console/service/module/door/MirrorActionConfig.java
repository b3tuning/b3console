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
public class MirrorActionConfig extends EditableEntity implements Serializable {

	private static final long serialVersionUID = 5181372062732497629L;

	private IntegerProperty downMax;
	private IntegerProperty downMin;
	private IntegerProperty leftMax;
	private IntegerProperty leftMin;
	private IntegerProperty rightMax;
	private IntegerProperty rightMin;
	private IntegerProperty upMax;
	private IntegerProperty upMin;

	public MirrorActionConfig() {
		this.downMax  = new SimpleIntegerProperty();
		this.downMin  = new SimpleIntegerProperty();
		this.leftMax  = new SimpleIntegerProperty();
		this.leftMin  = new SimpleIntegerProperty();
		this.rightMax = new SimpleIntegerProperty();
		this.rightMin = new SimpleIntegerProperty();
		this.upMax    = new SimpleIntegerProperty();
		this.upMin    = new SimpleIntegerProperty();
		super.trackProperties(this.downMax,
		                      this.downMin,
		                      this.leftMax,
		                      this.leftMin,
		                      this.rightMax,
		                      this.rightMin,
		                      this.upMax,
		                      this.upMin);
	}

	public MirrorActionConfig(int downMax, int downMin, int leftMax, int leftMin,
	                          int rightMax, int rightMin, int upMax, int upMin) {
		this();
		this.downMax.set(downMax);
		this.downMin.set(downMin);
		this.leftMax.set(leftMax);
		this.leftMin.set(leftMin);
		this.rightMax.set(rightMax);
		this.rightMin.set(rightMin);
		this.upMax.set(upMax);
		this.upMin.set(upMin);
	}

	public MirrorActionConfig copy() {
		return new MirrorActionConfig(this.getDownMax(),
		                              this.getDownMin(),
		                              this.getLeftMax(),
		                              this.getLeftMin(),
		                              this.getRightMax(),
		                              this.getRightMin(),
		                              this.getUpMax(),
		                              this.getUpMin());
	}

	public int getDownMax() {
		return downMax.get();
	}

	public int getDownMin() {
		return downMin.get();
	}

	public int getLeftMax() {
		return leftMax.get();
	}

	public int getLeftMin() {
		return leftMin.get();
	}

	public int getRightMax() {
		return rightMax.get();
	}

	public int getRightMin() {
		return rightMin.get();
	}

	public int getUpMax() {
		return upMax.get();
	}

	public int getUpMin() {
		return upMin.get();
	}

	public void setDownMax(int value) {
		this.downMax.set(value);
	}

	public void setDownMin(int value) {
		this.downMin.set(value);
	}

	public void setLeftMax(int value) {
		this.leftMax.set(value);
	}

	public void setLeftMin(int value) {
		this.leftMin.set(value);
	}

	public void setRightMax(int value) {
		this.rightMax.set(value);
	}

	public void setRightMin(int value) {
		this.rightMin.set(value);
	}

	public void setUpMax(int value) {
		this.upMax.set(value);
	}

	public void setUpMin(int value) {
		this.upMin.set(value);
	}

	public IntegerProperty downMaxProperty() {
		return this.downMax;
	}

	public IntegerProperty downMinProperty() {
		return this.downMin;
	}

	public IntegerProperty leftMaxProperty() {
		return this.leftMax;
	}

	public IntegerProperty leftMinProperty() {
		return this.leftMin;
	}

	public IntegerProperty rightMaxProperty() {
		return this.rightMax;
	}

	public IntegerProperty rightMinProperty() {
		return this.rightMin;
	}

	public IntegerProperty upMaxProperty() {
		return this.upMax;
	}

	public IntegerProperty upMinProperty() {
		return this.upMin;
	}
}

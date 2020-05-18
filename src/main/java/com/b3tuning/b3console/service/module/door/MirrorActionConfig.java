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
public class MirrorActionConfig extends EditableEntity implements Serializable {

	private static final long serialVersionUID = -4651332825883884318L;

	private ObjectProperty<Integer> downMax;
	private ObjectProperty<Integer> downMin;
	private ObjectProperty<Integer> leftMax;
	private ObjectProperty<Integer> leftMin;
	private ObjectProperty<Integer> rightMax;
	private ObjectProperty<Integer> rightMin;
	private ObjectProperty<Integer> upMax;
	private ObjectProperty<Integer> upMin;

	public MirrorActionConfig() {
		this.downMax  = new SimpleObjectProperty<>();
		this.downMin  = new SimpleObjectProperty<>();
		this.leftMax  = new SimpleObjectProperty<>();
		this.leftMin  = new SimpleObjectProperty<>();
		this.rightMax = new SimpleObjectProperty<>();
		this.rightMin = new SimpleObjectProperty<>();
		this.upMax    = new SimpleObjectProperty<>();
		this.upMin    = new SimpleObjectProperty<>();
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

	public ObjectProperty<Integer> downMaxProperty() {
		return this.downMax;
	}

	public ObjectProperty<Integer> downMinProperty() {
		return this.downMin;
	}

	public ObjectProperty<Integer> leftMaxProperty() {
		return this.leftMax;
	}

	public ObjectProperty<Integer> leftMinProperty() {
		return this.leftMin;
	}

	public ObjectProperty<Integer> rightMaxProperty() {
		return this.rightMax;
	}

	public ObjectProperty<Integer> rightMinProperty() {
		return this.rightMin;
	}

	public ObjectProperty<Integer> upMaxProperty() {
		return this.upMax;
	}

	public ObjectProperty<Integer> upMinProperty() {
		return this.upMin;
	}
}

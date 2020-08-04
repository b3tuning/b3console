package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
public class MirrorActionConfig extends EditableEntity {

	private final ObjectProperty<Integer> downMax;
	private final ObjectProperty<Integer> downMin;
	private final ObjectProperty<Integer> leftMax;
	private final ObjectProperty<Integer> leftMin;
	private final ObjectProperty<Integer> rightMax;
	private final ObjectProperty<Integer> rightMin;
	private final ObjectProperty<Integer> upMax;
	private final ObjectProperty<Integer> upMin;

	public MirrorActionConfig() {
		this.downMax  = new SimpleObjectProperty<>();
		this.downMin  = new SimpleObjectProperty<>();
		this.leftMax  = new SimpleObjectProperty<>();
		this.leftMin  = new SimpleObjectProperty<>();
		this.rightMax = new SimpleObjectProperty<>();
		this.rightMin = new SimpleObjectProperty<>();
		this.upMax    = new SimpleObjectProperty<>();
		this.upMin    = new SimpleObjectProperty<>();
	}

	public MirrorActionConfig(Integer downMax, Integer downMin, Integer leftMax, Integer leftMin,
	                          Integer rightMax, Integer rightMin, Integer upMax, Integer upMin) {
		this();
		setDownMax(downMax);
		setDownMin(downMin);
		setLeftMax(leftMax);
		setLeftMin(leftMin);
		setRightMax(rightMax);
		setRightMin(rightMin);
		setUpMax(upMax);
		setUpMin(upMin);
		super.trackProperties(this.downMax,
		                      this.downMin,
		                      this.leftMax,
		                      this.leftMin,
		                      this.rightMax,
		                      this.rightMin,
		                      this.upMax,
		                      this.upMin);
	}

//	public MirrorActionConfig clone() {
//		return new MirrorActionConfig(this.getDownMax(),
//		                              this.getDownMin(),
//		                              this.getLeftMax(),
//		                              this.getLeftMin(),
//		                              this.getRightMax(),
//		                              this.getRightMin(),
//		                              this.getUpMax(),
//		                              this.getUpMin());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getDownMax() {
		return this.downMax.get();
	}

	public Integer getDownMin() {
		return this.downMin.get();
	}

	public Integer getLeftMax() {
		return this.leftMax.get();
	}

	public Integer getLeftMin() {
		return this.leftMin.get();
	}

	public Integer getRightMax() {
		return this.rightMax.get();
	}

	public Integer getRightMin() {
		return this.rightMin.get();
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

	public void setDownMax(Integer value) {
		this.downMax.set(value);
	}

	public void setDownMin(Integer value) {
		this.downMin.set(value);
	}

	public void setLeftMax(Integer value) {
		this.leftMax.set(value);
	}

	public void setLeftMin(Integer value) {
		this.leftMin.set(value);
	}

	public void setRightMax(Integer value) {
		this.rightMax.set(value);
	}

	public void setRightMin(Integer value) {
		this.rightMin.set(value);
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

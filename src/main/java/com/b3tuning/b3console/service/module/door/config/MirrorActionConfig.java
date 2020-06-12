package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
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
		return new MirrorActionConfig(this.getDownMax().get(),
		                              this.getDownMin().get(),
		                              this.getLeftMax().get(),
		                              this.getLeftMin().get(),
		                              this.getRightMax().get(),
		                              this.getRightMin().get(),
		                              this.getUpMax().get(),
		                              this.getUpMin().get());
	}
}

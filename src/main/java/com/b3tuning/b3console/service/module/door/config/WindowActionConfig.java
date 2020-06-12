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
		return new WindowActionConfig(this.getAutoDownMax().get(),
		                              this.getAutoDownMin().get(),
		                              this.getAutoUpMax().get(),
		                              this.getAutoUpMin().get(),
		                              this.getDownMax().get(),
		                              this.getDownMin().get(),
		                              this.getUpMax().get(),
		                              this.getUpMin().get());
	}
}

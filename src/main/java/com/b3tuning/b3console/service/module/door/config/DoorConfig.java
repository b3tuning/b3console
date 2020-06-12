package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.module.ConfigBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *  Created on:  Apr 27, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DoorConfig extends ConfigBase implements Serializable {
	private static final long serialVersionUID = 4780456121009969723L;

	private ObjectProperty<MirrorActionConfig> mirrorAction;
	private ObjectProperty<MirrorSelectConfig> mirrorSelect;
	private ObjectProperty<WindowActionConfig> windowAction;
	private ObjectProperty<Integer>            driverWindowMaxCurrent;
	private ObjectProperty<Integer>            passengerWindowMaxCurrent;

	public DoorConfig() {
		this.mirrorAction              = new SimpleObjectProperty<>();
		this.mirrorSelect              = new SimpleObjectProperty<>();
		this.windowAction              = new SimpleObjectProperty<>();
		this.driverWindowMaxCurrent    = new SimpleObjectProperty<>();
		this.passengerWindowMaxCurrent = new SimpleObjectProperty<>();
		super.trackProperties(this.mirrorAction,
		                      this.mirrorSelect,
		                      this.windowAction,
		                      this.driverWindowMaxCurrent,
		                      this.passengerWindowMaxCurrent);
	}

	public DoorConfig(MirrorActionConfig mirrorAction, MirrorSelectConfig mirrorSelect, WindowActionConfig windowAction,
	                  int driverMax, int passMax) {
		this();
		this.mirrorAction.set(mirrorAction);
		this.mirrorSelect.set(mirrorSelect);
		this.windowAction.set(windowAction);
		this.driverWindowMaxCurrent.set(driverMax);
		this.passengerWindowMaxCurrent.set(passMax);
	}

	public DoorConfig copy() {
		return new DoorConfig(this.getMirrorAction().get(),
		                      this.getMirrorSelect().get(),
		                      this.getWindowAction().get(),
		                      this.getDriverWindowMaxCurrent().get(),
		                      this.getPassengerWindowMaxCurrent().get());
	}
}
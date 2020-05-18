package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.ConfigBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

/*
 *  Created on:  Apr 27, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
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
		return new DoorConfig(this.getMirrorAction(),
		                      this.getMirrorSelect(),
		                      this.getWindowAction(),
		                      this.getDriverWindowMaxCurrent(),
		                      this.getPassengerWindowMaxCurrent());
	}

	public MirrorActionConfig getMirrorAction() {
		return mirrorAction.get();
	}

	public MirrorSelectConfig getMirrorSelect() {
		return mirrorSelect.get();
	}

	public WindowActionConfig getWindowAction() {
		return windowAction.get();
	}

	public int getDriverWindowMaxCurrent() {
		return driverWindowMaxCurrent.get();
	}

	public int getPassengerWindowMaxCurrent() {
		return passengerWindowMaxCurrent.get();
	}

	public void setMirrorAction(MirrorActionConfig value) {
		mirrorAction.set(value);
	}

	public void setMirrorSelect(MirrorSelectConfig value) {
		mirrorSelect.set(value);
	}

	public void setDriverWindowAction(WindowActionConfig value) {
		windowAction.set(value);
	}

	public void setDriverWindowMaxCurrent(int value) {
		driverWindowMaxCurrent.set(value);
	}

	public void setPassengerWindowMaxCurrent(int value) {
		passengerWindowMaxCurrent.set(value);
	}

	public ObjectProperty<MirrorActionConfig> mirrorActionProperty() {
		return mirrorAction;
	}

	public ObjectProperty<MirrorSelectConfig> mirrorSelectProperty() {
		return mirrorSelect;
	}

	public ObjectProperty<WindowActionConfig> windowActionProperty() {
		return windowAction;
	}

	public ObjectProperty<Integer> driverWindowMaxCurrentProperty() {
		return driverWindowMaxCurrent;
	}

	public ObjectProperty<Integer> passengerWindowMaxCurrentProperty() {
		return passengerWindowMaxCurrent;
	}

}
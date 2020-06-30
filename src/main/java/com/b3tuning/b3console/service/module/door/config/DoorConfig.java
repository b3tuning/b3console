package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ModuleType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.XSlf4j;

import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  Apr 27, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@XSlf4j
public class DoorConfig extends EditableEntity implements ConfigBase {

	private final ObjectProperty<ModuleType>         type;
	private final ObjectProperty<MirrorActionConfig> mirrorAction;
	private final ObjectProperty<MirrorSelectConfig> mirrorSelect;
	private final ObjectProperty<WindowActionConfig> windowAction;
	private final ObjectProperty<Integer>            driverWindowMaxCurrent;
	private final ObjectProperty<Integer>            passengerWindowMaxCurrent;

	public DoorConfig() {
		this.type                      = new SimpleObjectProperty<>();
		this.mirrorAction              = new SimpleObjectProperty<>(new MirrorActionConfig());
		this.mirrorSelect              = new SimpleObjectProperty<>(new MirrorSelectConfig());
		this.windowAction              = new SimpleObjectProperty<>(new WindowActionConfig());
		this.driverWindowMaxCurrent    = new SimpleObjectProperty<>();
		this.passengerWindowMaxCurrent = new SimpleObjectProperty<>();
		super.trackProperties(this.mirrorAction,
		                      this.mirrorSelect,
		                      this.windowAction,
		                      this.driverWindowMaxCurrent,
		                      this.passengerWindowMaxCurrent);

		track(mirrorAction);
		track(mirrorSelect);
		track(windowAction);

	}

	private void track(ObjectProperty<? extends EditableEntity> prop) {
		manage(nonNullValuesOf(prop).subscribe(p -> {
			log.entry("Tracking new {}", p);
			super.trackProperties(p.dirtyBinding());
			if (isTrackingChanges()) {
				p.startTrackingChanges();
			}
		}));
	}

	public DoorConfig(ModuleType type) {
		this();
		this.type.set(type);
	}

	public DoorConfig(ModuleType type, MirrorActionConfig mirrorAction, MirrorSelectConfig mirrorSelect,
	                  WindowActionConfig windowAction, Integer driverMax, Integer passMax) {
		this(type);
		this.mirrorAction.set(mirrorAction);
		this.mirrorSelect.set(mirrorSelect);
		this.windowAction.set(windowAction);
		this.driverWindowMaxCurrent.set(driverMax);
		this.passengerWindowMaxCurrent.set(passMax);
	}

	public DoorConfig clone() {
		return new DoorConfig(this.getType(),
		                      this.getMirrorAction(),
		                      this.getMirrorSelect(),
		                      this.getWindowAction(),
		                      this.getDriverWindowMaxCurrent(),
		                      this.getPassengerWindowMaxCurrent());
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public ModuleType getType() {
		return this.type.get();
	}

	public MirrorActionConfig getMirrorAction() {
		return this.mirrorAction.get();
	}

	public MirrorSelectConfig getMirrorSelect() {
		return this.mirrorSelect.get();
	}

	public WindowActionConfig getWindowAction() {
		return this.windowAction.get();
	}

	public Integer getDriverWindowMaxCurrent() {
		return this.driverWindowMaxCurrent.get();
	}

	public Integer getPassengerWindowMaxCurrent() {
		return this.passengerWindowMaxCurrent.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setType(ModuleType type) {
		this.type.set(type);
	}

	public void setMirrorAction(MirrorActionConfig config) {
		this.mirrorAction.set(config);
	}

	public void setMirrorSelect(MirrorSelectConfig config) {
		this.mirrorSelect.set(config);
	}

	public void setWindowAction(WindowActionConfig config) {
		this.windowAction.set(config);
	}

	public void setDriverWindowMaxCurrent(Integer value) {
		this.driverWindowMaxCurrent.set(value);
	}

	public void setPassengerWindowMaxCurrent(Integer value) {
		this.passengerWindowMaxCurrent.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<ModuleType> typeProperty() {
		return this.type;
	}

	public ObjectProperty<MirrorActionConfig> mirrorActionProperty() {
		return this.mirrorAction;
	}

	public ObjectProperty<MirrorSelectConfig> mirrorSelectProperty() {
		return this.mirrorSelect;
	}

	public ObjectProperty<WindowActionConfig> windowActionProperty() {
		return this.windowAction;
	}

	public ObjectProperty<Integer> driverWindowMaxCurrentProperty() {
		return this.driverWindowMaxCurrent;
	}

	public ObjectProperty<Integer> passengerWindowMaxCurrentProperty() {
		return this.passengerWindowMaxCurrent;
	}
}
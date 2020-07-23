package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = false)
public class MirrorSelectConfig extends EditableEntity {

	private final ObjectProperty<Integer> driverMax;
	private final ObjectProperty<Integer> driverMin;
	private final ObjectProperty<Integer> foldMax;
	private final ObjectProperty<Integer> foldMin;
	private final ObjectProperty<Integer> passengerMax;
	private final ObjectProperty<Integer> passengerMin;

	public MirrorSelectConfig() {
		this.driverMax    = new SimpleObjectProperty<>();
		this.driverMin    = new SimpleObjectProperty<>();
		this.foldMax      = new SimpleObjectProperty<>();
		this.foldMin      = new SimpleObjectProperty<>();
		this.passengerMax = new SimpleObjectProperty<>();
		this.passengerMin = new SimpleObjectProperty<>();
		super.trackProperties(this.driverMax,
		                      this.driverMin,
		                      this.foldMax,
		                      this.foldMin,
		                      this.passengerMax,
		                      this.passengerMin);
	}

	public MirrorSelectConfig(Integer driverMax, Integer driverMin, Integer foldMax, Integer foldMin,
	                          Integer passengerMax, Integer passengerMin) {
		this();
		setDriverMax(driverMax);
		setDriverMin(driverMin);
		setFoldMax(foldMax);
		setFoldMin(foldMin);
		setPassengerMax(passengerMax);
		setPassengerMin(passengerMin);
	}

//	public MirrorSelectConfig clone() {
//		return new MirrorSelectConfig(this.getDriverMax(),
//		                              this.getDriverMin(),
//		                              this.getFoldMax(),
//		                              this.getFoldMin(),
//		                              this.getPassengerMax(),
//		                              this.getPassengerMin());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getDriverMax() {
		return this.driverMax.get();
	}

	public Integer getDriverMin() {
		return this.driverMin.get();
	}

	public Integer getFoldMax() {
		return this.foldMax.get();
	}

	public Integer getFoldMin() {
		return this.foldMin.get();
	}

	public Integer getPassengerMax() {
		return this.passengerMax.get();
	}

	public Integer getPassengerMin() {
		return this.passengerMin.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setDriverMax(Integer value) {
		this.driverMax.set(value);
	}

	public void setDriverMin(Integer value) {
		this.driverMin.set(value);
	}

	public void setFoldMax(Integer value) {
		this.foldMax.set(value);
	}

	public void setFoldMin(Integer value) {
		this.foldMin.set(value);
	}

	public void setPassengerMax(Integer value) {
		this.passengerMax.set(value);
	}

	public void setPassengerMin(Integer value) {
		this.passengerMin.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> driverMaxProperty() {
		return driverMax;
	}

	public ObjectProperty<Integer> driverMinProperty() {
		return driverMin;
	}

	public ObjectProperty<Integer> foldMaxProperty() {
		return foldMax;
	}

	public ObjectProperty<Integer> foldMinProperty() {
		return foldMin;
	}

	public ObjectProperty<Integer> passengerMaxProperty() {
		return passengerMax;
	}

	public ObjectProperty<Integer> passengerMinProperty() {
		return passengerMin;
	}
}

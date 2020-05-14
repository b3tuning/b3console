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
public class MirrorSelectConfig extends EditableEntity implements Serializable {

	private static final long serialVersionUID = -3893787304279072139L;

	private IntegerProperty driverMax;
	private IntegerProperty driverMin;
	private IntegerProperty foldMax;
	private IntegerProperty foldMin;
	private IntegerProperty passengerMax;
	private IntegerProperty passengerMin;

	public MirrorSelectConfig() {
		this.driverMax    = new SimpleIntegerProperty();
		this.driverMin    = new SimpleIntegerProperty();
		this.foldMax      = new SimpleIntegerProperty();
		this.foldMin      = new SimpleIntegerProperty();
		this.passengerMax = new SimpleIntegerProperty();
		this.passengerMin = new SimpleIntegerProperty();
		super.trackProperties(this.driverMax,
		                      this.driverMin,
		                      this.foldMax,
		                      this.foldMin,
		                      this.passengerMax,
		                      this.passengerMin);
	}

	public MirrorSelectConfig(int driverMax, int driverMin, int foldMax, int foldMin,
	                          int passengerMax, int passengerMin) {
		this();
		this.driverMax.set(driverMax);
		this.driverMin.set(driverMin);
		this.foldMax.set(foldMax);
		this.foldMin.set(foldMin);
		this.passengerMax.set(passengerMax);
		this.passengerMin.set(passengerMin);
	}

	public MirrorSelectConfig copy() {
		return new MirrorSelectConfig(this.getDriverMax(),
		                              this.getDriverMin(),
		                              this.getFoldMax(),
		                              this.getFoldMin(),
		                              this.getPassengerMax(),
		                              this.getPassengerMin());
	}

	public int getDriverMax() {
		return driverMax.get();
	}

	public int getDriverMin() {
		return driverMin.get();
	}

	public int getFoldMax() {
		return foldMax.get();
	}

	public int getFoldMin() {
		return foldMin.get();
	}

	public int getPassengerMax() {
		return passengerMax.get();
	}

	public int getPassengerMin() {
		return passengerMin.get();
	}

	public void setDriverMax(int value) {
		this.driverMax.set(value);
	}

	public void setDriverMin(int value) {
		this.driverMin.set(value);
	}

	public void setFoldMax(int value) {
		this.foldMax.set(value);
	}

	public void setFoldMin(int value) {
		this.foldMin.set(value);
	}

	public void setPassengerMax(int value) {
		this.passengerMax.set(value);
	}

	public void setPassengerMin(int value) {
		this.passengerMin.set(value);
	}

	public IntegerProperty driverMaxProperty() {
		return this.driverMax;
	}

	public IntegerProperty driverMinProperty() {
		return this.driverMin;
	}

	public IntegerProperty foldMaxProperty() {
		return this.foldMax;
	}

	public IntegerProperty foldMinProperty() {
		return this.foldMin;
	}

	public IntegerProperty passengerMaxProperty() {
		return this.passengerMax;
	}

	public IntegerProperty passengerMinProperty() {
		return this.passengerMin;
	}
}

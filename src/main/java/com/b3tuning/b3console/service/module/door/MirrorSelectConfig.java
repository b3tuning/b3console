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
public class MirrorSelectConfig extends EditableEntity implements Serializable {

	private static final long serialVersionUID = -2218249870810467004L;

	private ObjectProperty<Integer> driverMax;
	private ObjectProperty<Integer> driverMin;
	private ObjectProperty<Integer> foldMax;
	private ObjectProperty<Integer> foldMin;
	private ObjectProperty<Integer> passengerMax;
	private ObjectProperty<Integer> passengerMin;

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

	public ObjectProperty<Integer> driverMaxProperty() {
		return this.driverMax;
	}

	public ObjectProperty<Integer> driverMinProperty() {
		return this.driverMin;
	}

	public ObjectProperty<Integer> foldMaxProperty() {
		return this.foldMax;
	}

	public ObjectProperty<Integer> foldMinProperty() {
		return this.foldMin;
	}

	public ObjectProperty<Integer> passengerMaxProperty() {
		return this.passengerMax;
	}

	public ObjectProperty<Integer> passengerMinProperty() {
		return this.passengerMin;
	}
}

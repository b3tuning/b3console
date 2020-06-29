package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
public class MirrorSelectConfig extends EditableEntity implements Serializable {
	private static final long serialVersionUID = -2218249870810467004L;

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
		return new MirrorSelectConfig(this.getDriverMax().get(),
		                              this.getDriverMin().get(),
		                              this.getFoldMax().get(),
		                              this.getFoldMin().get(),
		                              this.getPassengerMax().get(),
		                              this.getPassengerMin().get());
	}
}

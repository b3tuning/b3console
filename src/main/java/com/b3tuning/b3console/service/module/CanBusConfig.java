package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
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
public class CanBusConfig extends EditableEntity implements Serializable {
	private static final long serialVersionUID = -7443557657981767820L;

	private LongProperty    addrTX;    // unsigned long
	private LongProperty    bootRX;    // unsigned long
	private LongProperty    bootTX;    // unsigned long
	private IntegerProperty intvlTX;   // uint_8t - needs mask 0xFF?
	private LongProperty    mask0;     // unsigned long
	private LongProperty    filter0_0; // unsigned long
	private LongProperty    filter0_1; // unsigned long
	private LongProperty    mask1;     // unsigned long
	private LongProperty    filter1_0; // unsigned long
	private LongProperty    filter1_1; // unsigned long
	private LongProperty    filter1_2; // unsigned long
	private LongProperty    filter1_3; // unsigned long

	public CanBusConfig() {
		this.addrTX    = new SimpleLongProperty();
		this.bootRX    = new SimpleLongProperty();
		this.bootTX    = new SimpleLongProperty();
		this.intvlTX   = new SimpleIntegerProperty();
		this.mask0     = new SimpleLongProperty();
		this.filter0_0 = new SimpleLongProperty();
		this.filter0_1 = new SimpleLongProperty();
		this.mask1     = new SimpleLongProperty();
		this.filter1_0 = new SimpleLongProperty();
		this.filter1_1 = new SimpleLongProperty();
		this.filter1_2 = new SimpleLongProperty();
		this.filter1_3 = new SimpleLongProperty();
		super.trackProperties(addrTX, bootRX, bootTX, intvlTX, mask0, filter0_0, filter0_1, mask1, filter1_0,
		                      filter1_1, filter1_2, filter1_3);
	}

	public CanBusConfig(long addrTX, long bootRX, long bootTX, int intvlTX, long mask0, long filter0_0,
	                    long filter0_1, long mask1, long filter1_0, long filter1_1, long filter1_2,
	                    long filter1_3) {
		this();
		this.addrTX.set(addrTX);
		this.bootRX.set(bootRX);
		this.bootTX.set(bootTX);
		this.intvlTX.set(intvlTX);
		this.mask0.set(mask0);
		this.filter0_0.set(filter0_0);
		this.filter0_1.set(filter0_1);
		this.mask1.set(mask1);
		this.filter1_0.set(filter1_0);
		this.filter1_1.set(filter1_1);
		this.filter1_2.set(filter1_2);
		this.filter1_3.set(filter1_3);
	}
}

package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

import java.io.Serializable;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
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

	public long getAddrTX() {
		return addrTX.get();
	}

	public long getBootRX() {
		return bootRX.get();
	}

	public long getBootTX() {
		return bootTX.get();
	}

	public int getIntvlTX() {
		return intvlTX.get();
	}

	public long getMask0() {
		return mask0.get();
	}

	public long getFilter0_0() {
		return filter0_0.get();
	}

	public long getFilter0_1() {
		return filter0_1.get();
	}

	public long getMask1() {
		return mask1.get();
	}

	public long getFilter1_0() {
		return filter1_0.get();
	}

	public long getFilter1_1() {
		return filter1_1.get();
	}

	public long getFilter1_2() {
		return filter1_2.get();
	}

	public long getFilter1_3() {
		return filter1_3.get();
	}

	public void setAddrTX(long value) {
		addrTX.set(value);
	}

	public void setBootRX(long value) {
		bootRX.set(value);
	}

	public void setBootTX(long value) {
		bootTX.set(value);
	}

	public void setIntvlTX(int value) {
		intvlTX.set(value);
	}

	public void setMask0(long value) {
		mask0.set(value);
	}

	public void setFilter0_0(long value) {
		filter0_0.set(value);
	}

	public void setFilter0_1(long value) {
		filter0_1.set(value);
	}

	public void setMask1(long value) {
		mask1.set(value);
	}

	public void setFilter1_0(long value) {
		filter1_0.set(value);
	}

	public void setFilter1_1(long value) {
		filter1_1.set(value);
	}

	public void setFilter1_2(long value) {
		filter1_2.set(value);
	}

	public void setFilter1_3(long value) {
		filter1_3.set(value);
	}

	public LongProperty addrTXProperty() {
		return addrTX;
	}

	public LongProperty bootRXProperty() {
		return bootRX;
	}

	public LongProperty bootTXProperty() {
		return bootTX;
	}

	public IntegerProperty intvlTXProperty() {
		return intvlTX;
	}

	public LongProperty mask0Property() {
		return mask0;
	}

	public LongProperty filter0_0Property() {
		return filter0_0;
	}

	public LongProperty filter0_1Property() {
		return filter0_1;
	}

	public LongProperty mask1Property() {
		return mask1;
	}

	public LongProperty filter1_0Property() {
		return filter1_0;
	}

	public LongProperty filter1_1Property() {
		return filter1_1;
	}

	public LongProperty filter1_2Property() {
		return filter1_2;
	}

	public LongProperty filter1_3Property() {
		return filter1_3;
	}
}

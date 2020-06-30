package com.b3tuning.b3console.service.module;

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
public class CanBusConfig extends EditableEntity {

	private final ObjectProperty<Long>    addrTX;    // unsigned long
	private final ObjectProperty<Long>    bootRX;    // unsigned long
	private final ObjectProperty<Long>    bootTX;    // unsigned long
	private final ObjectProperty<Integer> intvlTX;   // uint_8t - needs mask 0xFF?
	private final ObjectProperty<Long>    mask0;     // unsigned long
	private final ObjectProperty<Long>    filter0_0; // unsigned long
	private final ObjectProperty<Long>    filter0_1; // unsigned long
	private final ObjectProperty<Long>    mask1;     // unsigned long
	private final ObjectProperty<Long>    filter1_0; // unsigned long
	private final ObjectProperty<Long>    filter1_1; // unsigned long
	private final ObjectProperty<Long>    filter1_2; // unsigned long
	private final ObjectProperty<Long>    filter1_3; // unsigned long

	public CanBusConfig() {
		this.addrTX    = new SimpleObjectProperty<>();
		this.bootRX    = new SimpleObjectProperty<>();
		this.bootTX    = new SimpleObjectProperty<>();
		this.intvlTX   = new SimpleObjectProperty<>();
		this.mask0     = new SimpleObjectProperty<>();
		this.filter0_0 = new SimpleObjectProperty<>();
		this.filter0_1 = new SimpleObjectProperty<>();
		this.mask1     = new SimpleObjectProperty<>();
		this.filter1_0 = new SimpleObjectProperty<>();
		this.filter1_1 = new SimpleObjectProperty<>();
		this.filter1_2 = new SimpleObjectProperty<>();
		this.filter1_3 = new SimpleObjectProperty<>();
		super.trackProperties(addrTX,
		                      bootRX,
		                      bootTX,
		                      intvlTX,
		                      mask0,
		                      filter0_0,
		                      filter0_1,
		                      mask1,
		                      filter1_0,
		                      filter1_1,
		                      filter1_2,
		                      filter1_3);
	}

	public CanBusConfig(Long addrTX, Long bootRX, Long bootTX, Integer intvlTX, Long mask0, Long filter0_0,
	                    Long filter0_1, Long mask1, Long filter1_0, Long filter1_1, Long filter1_2,
	                    Long filter1_3) {
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

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Long getAddrTX() {
		return addrTX.get();
	}

	public Long getBootRX() {
		return bootRX.get();
	}

	public Long getBootTX() {
		return bootTX.get();
	}

	public Integer getIntvlTX() {
		return intvlTX.get();
	}

	public Long getMask0() {
		return mask0.get();
	}

	public Long getFilter0_0() {
		return filter0_0.get();
	}

	public Long getFilter0_1() {
		return filter0_1.get();
	}

	public Long getMask1() {
		return mask1.get();
	}

	public Long getFilter1_0() {
		return filter1_0.get();
	}

	public Long getFilter1_1() {
		return filter1_1.get();
	}

	public Long getFilter1_2() {
		return filter1_2.get();
	}

	public Long getFilter1_3() {
		return filter1_3.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setAddrTX(Long value) {
		this.addrTX.set(value);
	}

	public void setBootRX(Long value) {
		this.bootRX.set(value);
	}

	public void setBootTX(Long value) {
		this.bootTX.set(value);
	}

	public void setIntvlTX(Integer value) {
		this.intvlTX.set(value);
	}

	public void setMask0(Long value) {
		this.mask0.set(value);
	}

	public void setFilter0_0(Long value) {
		this.filter0_0.set(value);
	}

	public void setFilter0_1(Long value) {
		this.filter0_1.set(value);
	}

	public void setMask1(Long value) {
		this.mask1.set(value);
	}

	public void setFilter1_0(Long value) {
		this.filter1_0.set(value);
	}

	public void setFilter1_1(Long value) {
		this.filter1_1.set(value);
	}

	public void setFilter1_2(Long value) {
		this.filter1_2.set(value);
	}

	public void setFilter1_3(Long value) {
		this.filter1_3.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Long> addrTXProperty() {
		return this.addrTX;
	}

	public ObjectProperty<Long> bootRXProperty() {
		return this.bootRX;
	}

	public ObjectProperty<Long> bootTXProperty() {
		return this.bootTX;
	}

	public ObjectProperty<Integer> intvlTXProperty() {
		return this.intvlTX;
	}

	public ObjectProperty<Long> mask0Property() {
		return this.mask0;
	}

	public ObjectProperty<Long> filter0_0Property() {
		return this.filter0_0;
	}

	public ObjectProperty<Long> filter0_1Property() {
		return this.filter0_1;
	}

	public ObjectProperty<Long> mask1Property() {
		return this.mask1;
	}

	public ObjectProperty<Long> filter1_0Property() {
		return this.filter1_0;
	}

	public ObjectProperty<Long> filter1_1Property() {
		return this.filter1_1;
	}

	public ObjectProperty<Long> filter1_2Property() {
		return this.filter1_2;
	}

	public ObjectProperty<Long> filter1_3Property() {
		return this.filter1_3;
	}
}

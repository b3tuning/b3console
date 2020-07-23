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

	private final ObjectProperty<Integer> addrTX;
	private final ObjectProperty<Integer> bootRX;
	private final ObjectProperty<Integer> bootTX;
	private final ObjectProperty<Integer> intvlTX;
	private final ObjectProperty<Integer> mask0;
	private final ObjectProperty<Integer> filter0_0;
	private final ObjectProperty<Integer> filter0_1;
	private final ObjectProperty<Integer> mask1;
	private final ObjectProperty<Integer> filter1_0;
	private final ObjectProperty<Integer> filter1_1;
	private final ObjectProperty<Integer> filter1_2;
	private final ObjectProperty<Integer> filter1_3;

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

	public CanBusConfig(Integer addrTX, Integer bootRX, Integer bootTX, Integer intvlTX, Integer mask0,
	                    Integer filter0_0,
	                    Integer filter0_1, Integer mask1, Integer filter1_0, Integer filter1_1, Integer filter1_2,
	                    Integer filter1_3) {
		this();
		setAddrTX(addrTX);
		setBootRX(bootRX);
		setBootTX(bootTX);
		setIntvlTX(intvlTX);
		setMask0(mask0);
		setFilter0_0(filter0_0);
		setFilter0_1(filter0_1);
		setMask1(mask1);
		setFilter1_0(filter1_0);
		setFilter1_1(filter1_1);
		setFilter1_2(filter1_2);
		setFilter1_3(filter1_3);
	}

//	public CanBusConfig clone() {
//		return new CanBusConfig(this.addrTX.get(),
//		                        this.bootRX.get(),
//		                        this.bootTX.get(),
//		                        this.intvlTX.get(),
//		                        this.mask0.get(),
//		                        this.filter0_0.get(),
//		                        this.filter0_1.get(),
//		                        this.mask1.get(),
//		                        this.filter1_0.get(),
//		                        this.filter1_1.get(),
//		                        this.filter1_2.get(),
//		                        this.filter1_3.get());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getAddrTX() {
		return addrTX.get();
	}

	public Integer getBootRX() {
		return bootRX.get();
	}

	public Integer getBootTX() {
		return bootTX.get();
	}

	public Integer getIntvlTX() {
		return intvlTX.get();
	}

	public Integer getMask0() {
		return mask0.get();
	}

	public Integer getFilter0_0() {
		return filter0_0.get();
	}

	public Integer getFilter0_1() {
		return filter0_1.get();
	}

	public Integer getMask1() {
		return mask1.get();
	}

	public Integer getFilter1_0() {
		return filter1_0.get();
	}

	public Integer getFilter1_1() {
		return filter1_1.get();
	}

	public Integer getFilter1_2() {
		return filter1_2.get();
	}

	public Integer getFilter1_3() {
		return filter1_3.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setAddrTX(Integer value) {
		this.addrTX.set(value);
	}

	public void setBootRX(Integer value) {
		this.bootRX.set(value);
	}

	public void setBootTX(Integer value) {
		this.bootTX.set(value);
	}

	public void setIntvlTX(Integer value) {
		this.intvlTX.set(value);
	}

	public void setMask0(Integer value) {
		this.mask0.set(value);
	}

	public void setFilter0_0(Integer value) {
		this.filter0_0.set(value);
	}

	public void setFilter0_1(Integer value) {
		this.filter0_1.set(value);
	}

	public void setMask1(Integer value) {
		this.mask1.set(value);
	}

	public void setFilter1_0(Integer value) {
		this.filter1_0.set(value);
	}

	public void setFilter1_1(Integer value) {
		this.filter1_1.set(value);
	}

	public void setFilter1_2(Integer value) {
		this.filter1_2.set(value);
	}

	public void setFilter1_3(Integer value) {
		this.filter1_3.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> addrTXProperty() {
		return this.addrTX;
	}

	public ObjectProperty<Integer> bootRXProperty() {
		return this.bootRX;
	}

	public ObjectProperty<Integer> bootTXProperty() {
		return this.bootTX;
	}

	public ObjectProperty<Integer> intvlTXProperty() {
		return this.intvlTX;
	}

	public ObjectProperty<Integer> mask0Property() {
		return this.mask0;
	}

	public ObjectProperty<Integer> filter0_0Property() {
		return this.filter0_0;
	}

	public ObjectProperty<Integer> filter0_1Property() {
		return this.filter0_1;
	}

	public ObjectProperty<Integer> mask1Property() {
		return this.mask1;
	}

	public ObjectProperty<Integer> filter1_0Property() {
		return this.filter1_0;
	}

	public ObjectProperty<Integer> filter1_1Property() {
		return this.filter1_1;
	}

	public ObjectProperty<Integer> filter1_2Property() {
		return this.filter1_2;
	}

	public ObjectProperty<Integer> filter1_3Property() {
		return this.filter1_3;
	}
}

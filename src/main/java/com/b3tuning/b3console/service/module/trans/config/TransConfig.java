package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.module.CanBusConfig;
import com.b3tuning.b3console.service.module.ConfigBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TransConfig extends ConfigBase implements Serializable {
	private static final long serialVersionUID = -1672464467145141989L;

	public ObjectProperty<CanBusConfig>  canBus;
	public ObjectProperty<Ems22AConfig>  ems22A;
	public ObjectProperty<Vnh5019Config> vnh5019;
	public ObjectProperty<PidConfig>     pid;

	public TransConfig() {
		this.canBus  = new SimpleObjectProperty<>();
		this.ems22A  = new SimpleObjectProperty<>();
		this.vnh5019 = new SimpleObjectProperty<>();
		this.pid     = new SimpleObjectProperty<>();
		super.trackProperties(canBus, ems22A, vnh5019, pid);
	}

	public TransConfig(CanBusConfig canBusConfig, Ems22AConfig ems22A, Vnh5019Config vnh5019, PidConfig pid) {
		this();
		this.canBus.set(canBusConfig);
		this.ems22A.set(ems22A);
		this.vnh5019.set(vnh5019);
		this.pid.set(pid);
	}

	public TransConfig copy() {
		return new TransConfig(this.getCanBus().get(), this.getEms22A().get(), this.getVnh5019().get(),
		                       this.getPid().get());
	}
}

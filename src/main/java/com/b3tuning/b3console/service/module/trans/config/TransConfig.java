package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.module.CanBusConfig;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ModuleType;
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
		this.canBus  = new SimpleObjectProperty<>(new CanBusConfig());
		this.ems22A  = new SimpleObjectProperty<>(new Ems22AConfig());
		this.vnh5019 = new SimpleObjectProperty<>(new Vnh5019Config());
		this.pid     = new SimpleObjectProperty<>(new PidConfig());
		super.trackProperties(canBus, ems22A, vnh5019, pid);
	}

	public TransConfig(ModuleType type) {
		this();
		super.getType().set(type);
	}

	public TransConfig(ModuleType type, CanBusConfig canBusConfig, Ems22AConfig ems22A, Vnh5019Config vnh5019,
	                   PidConfig pid) {
		this();
		this.canBus.set(canBusConfig);
		this.ems22A.set(ems22A);
		this.vnh5019.set(vnh5019);
		this.pid.set(pid);
		super.getType().set(type);
	}

	public TransConfig copy() {
		return new TransConfig(this.getType().get(), this.getCanBus().get(), this.getEms22A().get(),
		                       this.getVnh5019().get(), this.getPid().get());
	}
}

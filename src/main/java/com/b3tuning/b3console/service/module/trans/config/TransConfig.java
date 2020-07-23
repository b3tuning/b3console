package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.CanBusConfig;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ModuleType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.XSlf4j;

import static com.b3tuning.b3console.service.module.ModuleType.TRANS;
import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@XSlf4j
public class TransConfig extends EditableEntity implements ConfigBase {

	private final ObjectProperty<CanBusConfig>  canBus;
	private final ObjectProperty<Ems22AConfig>  ems22A;
	private final ObjectProperty<Vnh5019Config> vnh5019;
	private final ObjectProperty<PidConfig>     pid;

	public TransConfig() {
		this.canBus  = new SimpleObjectProperty<>(new CanBusConfig());
		this.ems22A  = new SimpleObjectProperty<>(new Ems22AConfig());
		this.vnh5019 = new SimpleObjectProperty<>(new Vnh5019Config());
		this.pid     = new SimpleObjectProperty<>(new PidConfig());
		super.trackProperties(canBus, ems22A, vnh5019, pid);

		track(canBus);
		track(ems22A);
		track(vnh5019);
		track(pid);
	}

	private void track(ObjectProperty<? extends EditableEntity> prop) {
		manage(nonNullValuesOf(prop).subscribe(p -> {
			log.entry("Tracking new {}", p);
			super.trackProperties(p.dirtyBinding());
			if (isTrackingChanges()) {
				p.startTrackingChanges();
			}
		}));
	}

	public TransConfig(CanBusConfig canBusConfig, Ems22AConfig ems22A, Vnh5019Config vnh5019,
	                   PidConfig pid) {
		this();
		setCanBus(canBusConfig);
		setEms22A(ems22A);
		setVnh5019(vnh5019);
		setPid(pid);
	}

//	public TransConfig clone() {
//		return new TransConfig(this.getCanBus(),
//		                       this.getEms22A(),
//		                       this.getVnh5019(),
//		                       this.getPid());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public ModuleType getType() {
		return TRANS;
	}

	public CanBusConfig getCanBus() {
		return this.canBus.get();
	}

	public Ems22AConfig getEms22A() {
		return this.ems22A.get();
	}

	public Vnh5019Config getVnh5019() {
		return this.vnh5019.get();
	}

	public PidConfig getPid() {
		return this.pid.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setCanBus(CanBusConfig config) {
		this.canBus.set(config);
	}

	public void setEms22A(Ems22AConfig config) {
		this.ems22A.set(config);
	}

	public void setVnh5019(Vnh5019Config config) {
		this.vnh5019.set(config);
	}

	public void setPid(PidConfig config) {
		this.pid.set(config);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<CanBusConfig> canBusProperty() {
		return this.canBus;
	}

	public ObjectProperty<Ems22AConfig> ems22AProperty() {
		return this.ems22A;
	}

	public ObjectProperty<Vnh5019Config> vnh5019Property() {
		return this.vnh5019;
	}

	public ObjectProperty<PidConfig> pidProperty() {
		return this.pid;
	}
}

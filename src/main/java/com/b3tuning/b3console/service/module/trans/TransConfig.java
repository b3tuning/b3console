package com.b3tuning.b3console.service.module.trans;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.CanBusConfig;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.Serializable;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class TransConfig extends EditableEntity implements Serializable {

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
		return new TransConfig(this.getCanBusConfig(), this.getEms22A(), this.getVnh5019(), this.getPid());
	}

	public CanBusConfig getCanBusConfig() {
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

	public void setCanBusConfig(CanBusConfig value) {
		this.canBus.set(value);
	}

	public void setEms22A(Ems22AConfig value) {
		this.ems22A.set(value);
	}

	public void setVnh5019(Vnh5019Config value) {
		this.vnh5019.set(value);
	}

	public void setPid(PidConfig value) {
		this.pid.set(value);
	}

	public ObjectProperty<CanBusConfig> canBusConfigProperty() {
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

	public static class Ems22AConfig extends EditableEntity implements Serializable {

		private static final long            serialVersionUID = -1816774402792280218L;
		private              IntegerProperty encMax;
		private              IntegerProperty encMin;

		public Ems22AConfig() {
			this.encMax = new SimpleIntegerProperty();
			this.encMin = new SimpleIntegerProperty();
			super.trackProperties(this.encMax, this.encMin);
		}

		public Ems22AConfig(int max, int min) {
			this();
			this.encMax.set(max);
			this.encMin.set(max);
		}

		public int getEncMax() {
			return this.encMax.get();
		}

		public int getEncMin() {
			return this.encMin.get();
		}

		public void setEncMax(int value) {
			this.encMax.set(value);
		}

		public void setEncMin(int value) {
			this.encMin.set(value);
		}

		public IntegerProperty encMaxProperty() {
			return this.encMax;
		}

		public IntegerProperty encMinProperty() {
			return this.encMin;
		}
	}

	public static class Vnh5019Config extends EditableEntity implements Serializable {

		private static final long serialVersionUID = 4510253484722703807L;

		private IntegerProperty maxCurrent;

		public Vnh5019Config() {
			this.maxCurrent = new SimpleIntegerProperty();
			super.trackProperties(maxCurrent);
		}

		public Vnh5019Config(int max) {
			this();
			this.maxCurrent.set(max);
		}

		public int getMaxCurrent() {
			return this.maxCurrent.get();
		}

		public void setMaxCurrent(int value) {
			this.maxCurrent.set(value);
		}

		public IntegerProperty maxCurrentProperty() {
			return this.maxCurrent;
		}
	}

	public static class PidConfig extends EditableEntity implements Serializable {
		private static final long serialVersionUID = -2484001825137621307L;

		private DoubleProperty aggKd;
		private DoubleProperty aggKi;
		private DoubleProperty aggKp;
		private DoubleProperty consKd;
		private DoubleProperty consKi;
		private DoubleProperty consKp;

		public PidConfig() {
			this.aggKd  = new SimpleDoubleProperty();
			this.aggKi  = new SimpleDoubleProperty();
			this.aggKp  = new SimpleDoubleProperty();
			this.consKd = new SimpleDoubleProperty();
			this.consKi = new SimpleDoubleProperty();
			this.consKp = new SimpleDoubleProperty();
			super.trackProperties(this.aggKd, this.aggKi, this.aggKp, this.consKd, this.consKi, this.consKp);
		}

		public PidConfig(double aKd, double aKi, double aKp, double cKd, double cKi, double cKp) {
			this();
			this.aggKd.set(aKd);
			this.aggKi.set(aKi);
			this.aggKp.set(aKp);
			this.consKd.set(cKd);
			this.consKi.set(cKi);
			this.consKp.set(cKp);
		}

		public double getAggKd() {
			return this.aggKd.get();
		}

		public double getAggKi() {
			return this.aggKi.get();
		}

		public double getAggKp() {
			return this.aggKp.get();
		}

		public double getConsKd() {
			return this.consKd.get();
		}

		public double getConsKi() {
			return this.consKi.get();
		}

		public double getConsKp() {
			return this.consKp.get();
		}

		public void setAggKd(double value) {
			this.aggKd.set(value);
		}

		public void setAggKi(double value) {
			this.aggKi.set(value);
		}

		public void setAggKp(double value) {
			this.aggKp.set(value);
		}

		public void setConsKd(double value) {
			this.consKd.set(value);
		}

		public void setConsKi(double value) {
			this.consKi.set(value);
		}

		public void setConsKp(double value) {
			this.consKp.set(value);
		}

		public DoubleProperty aggKdProperty() {
			return this.aggKd;
		}

		public DoubleProperty aggKiProperty() {
			return this.aggKi;
		}

		public DoubleProperty aggKpProperty() {
			return this.aggKp;
		}

		public DoubleProperty consKdProperty() {
			return this.consKd;
		}

		public DoubleProperty consKiProperty() {
			return this.consKi;
		}

		public DoubleProperty consKpProperty() {
			return this.consKp;
		}
	}
}

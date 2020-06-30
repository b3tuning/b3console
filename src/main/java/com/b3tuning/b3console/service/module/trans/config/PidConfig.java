/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  PidConfig is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class PidConfig extends EditableEntity {

	private final ObjectProperty<Double> aggKd;
	private final ObjectProperty<Double> aggKi;
	private final ObjectProperty<Double> aggKp;
	private final ObjectProperty<Double> consKd;
	private final ObjectProperty<Double> consKi;
	private final ObjectProperty<Double> consKp;

	public PidConfig() {
		this.aggKd  = new SimpleObjectProperty<>();
		this.aggKi  = new SimpleObjectProperty<>();
		this.aggKp  = new SimpleObjectProperty<>();
		this.consKd = new SimpleObjectProperty<>();
		this.consKi = new SimpleObjectProperty<>();
		this.consKp = new SimpleObjectProperty<>();
		super.trackProperties(this.aggKd, this.aggKi, this.aggKp, this.consKd, this.consKi, this.consKp);
	}

	public PidConfig(Double aKd, Double aKi, Double aKp, Double cKd, Double cKi, Double cKp) {
		this();
		this.aggKd.set(aKd);
		this.aggKi.set(aKi);
		this.aggKp.set(aKp);
		this.consKd.set(cKd);
		this.consKi.set(cKi);
		this.consKp.set(cKp);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Double getAggKd() {
		return this.aggKd.get();
	}

	public Double getAggKi() {
		return this.aggKi.get();
	}

	public Double getAggKp() {
		return this.aggKp.get();
	}

	public Double getConsKd() {
		return this.consKd.get();
	}

	public Double getConsKi() {
		return this.consKi.get();
	}

	public Double getConsKp() {
		return this.consKp.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setAggKd(Double value) {
		this.aggKd.set(value);
	}

	public void setAggKi(Double value) {
		this.aggKi.set(value);
	}

	public void setAggKp(Double value) {
		this.aggKp.set(value);
	}

	public void setConsKd(Double value) {
		this.consKd.set(value);
	}

	public void setConsKi(Double value) {
		this.consKi.set(value);
	}

	public void setConsKp(Double value) {
		this.consKp.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Double> aggKdProperty() {
		return this.aggKd;
	}

	public ObjectProperty<Double> aggKiProperty() {
		return this.aggKi;
	}

	public ObjectProperty<Double> aggKpProperty() {
		return this.aggKp;
	}

	public ObjectProperty<Double> consKdProperty() {
		return this.consKd;
	}

	public ObjectProperty<Double> consKiProperty() {
		return this.consKi;
	}

	public ObjectProperty<Double> consKpProperty() {
		return this.consKp;
	}

}

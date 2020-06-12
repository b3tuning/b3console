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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PidConfig extends EditableEntity implements Serializable {
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
}

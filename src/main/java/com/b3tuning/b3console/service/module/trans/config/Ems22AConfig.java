/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  Ems22AConfig is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.trans.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Ems22AConfig extends EditableEntity implements Serializable {
	private static final long serialVersionUID = -1816774402792280218L;

	private IntegerProperty encMax;
	private IntegerProperty encMin;

	public Ems22AConfig() {
		this.encMax = new SimpleIntegerProperty();
		this.encMin = new SimpleIntegerProperty();
		super.trackProperties(this.encMax, this.encMin);
	}

	public Ems22AConfig(int max, int min) {
		this();
		this.encMax.set(max);
		this.encMin.set(min);
	}
}

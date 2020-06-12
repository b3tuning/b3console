/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  Vnh5019Config is part of b3console
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
public class Vnh5019Config extends EditableEntity implements Serializable {
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
}

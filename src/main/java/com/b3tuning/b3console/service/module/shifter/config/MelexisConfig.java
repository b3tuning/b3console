/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MelexisConfig is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.shifter.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * MelexisConfig
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MelexisConfig extends EditableEntity implements Serializable {
	private static final long serialVersionUID = -3082171139589377360L;

	private IntegerProperty range;

	public MelexisConfig() {
		this.range = new SimpleIntegerProperty();
		super.trackProperties(range);
	}

	public MelexisConfig(int rangeValue) {
		this();
		this.range.set(rangeValue);
	}
}

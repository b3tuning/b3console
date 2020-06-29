package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *  Created on:  May 18, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class ConfigBase extends EditableEntity implements Serializable {
	private static final long serialVersionUID = 710870165244969653L;

	private ObjectProperty<ModuleType> type;

	public ConfigBase() {
		this.type = new SimpleObjectProperty<>();
		super.trackProperties(this.type);
	}

	public ConfigBase(ModuleType type) {
		this();
		this.type.set(type);
	}

//	public ConfigBase copy() {
//		return new ConfigBase(this.getType().get());
//	}
}

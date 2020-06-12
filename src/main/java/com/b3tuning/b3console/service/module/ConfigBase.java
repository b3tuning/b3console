package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
public class ConfigBase extends EditableEntity implements Serializable {
	private static final long serialVersionUID = 710870165244969653L;

	private StringProperty             name;
	private LongProperty               createdAt;
	private LongProperty               updatedAt;
	private ObjectProperty<ModuleType> type;

	public ConfigBase() {
		this.name      = new SimpleStringProperty();
		this.createdAt = new SimpleLongProperty();
		this.updatedAt = new SimpleLongProperty();
		this.type      = new SimpleObjectProperty<>();
		super.trackProperties(this.name, this.createdAt, this.updatedAt, this.type);
	}

	public ConfigBase(String name, Long created, Long updatedAt, ModuleType type) {
		this();
		this.name.set(name);
		this.createdAt.set(created);
		this.updatedAt.set(updatedAt);
		this.type.set(type);
	}

	public ConfigBase(ModuleType type) {
		this();
		long now = System.currentTimeMillis();
		this.name.set(null);
		this.createdAt.set(now);
		this.updatedAt.set(now);
		this.type.set(type);
	}

	public ConfigBase(String name, ModuleType type) {
		this(type);
		this.name.set(name);
	}

	public ConfigBase copy() {
		return new ConfigBase(this.getName().get(), this.getCreatedAt().get(), this.getUpdatedAt().get(),
		                      this.getType().get());
	}
}

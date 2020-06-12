package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/*
 *  Created on:  May 18, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
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
		this.setName(name);
	}

	public ConfigBase copy() {
		return new ConfigBase(this.getName(), this.getCreatedAt(), this.getUpdatedAt(), this.getType());
	}

	public String getName() {
		return name.get();
	}

	public Long getCreatedAt() {
		return createdAt.get();
	}

	public Long getUpdatedAt() {
		return updatedAt.get();
	}

	public ModuleType getType() {
		return type.get();
	}

	public void setName(String value) {
		this.name.set(value);
	}

	public void setCreatedAt(Long value) {
		this.createdAt.set(value);
	}

	public void setUpdatedAt(Long value) {
		this.updatedAt.set(value);
	}

	public void setType(ModuleType value) {
		this.type.set(value);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public LongProperty createdAtProperty() {
		return createdAt;
	}

	public LongProperty updatedAtProperty() {
		return updatedAt;
	}

	public ObjectProperty<ModuleType> typeProperty() {
		return type;
	}
}

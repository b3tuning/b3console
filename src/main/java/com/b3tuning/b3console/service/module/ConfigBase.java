package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.Date;

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

	private StringProperty       name;
	private ObjectProperty<Date> createdAt;
	private ObjectProperty<Date> updatedAt;

	public ConfigBase() {
		this.name      = new SimpleStringProperty();
		this.createdAt = new SimpleObjectProperty<>();
		this.updatedAt = new SimpleObjectProperty<>();
		super.trackProperties(this.name, this.createdAt, this.updatedAt);
	}

	public ConfigBase(String name, Date created, Date updatedAt) {
		this();
		this.name.set(name);
		this.createdAt.set(created);
		this.updatedAt.set(updatedAt);
	}

	public ConfigBase copy() {
		return new ConfigBase(this.getName(), this.getCreatedAt(), this.getUpdatedAt());
	}

	public String getName() {
		return name.get();
	}

	public Date getCreatedAt() {
		return createdAt.get();
	}

	public Date getUpdatedAt() {
		return updatedAt.get();
	}

	public void setName(String value) {
		this.name.set(value);
	}

	public void setCreatedAt(Date value) {
		this.createdAt.set(value);
	}

	public void setUpdatedAt(Date value) {
		this.updatedAt.set(value);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public ObjectProperty<Date> createdAtProperty() {
		return createdAt;
	}

	public ObjectProperty<Date> updatedAtProperty() {
		return updatedAt;
	}
}

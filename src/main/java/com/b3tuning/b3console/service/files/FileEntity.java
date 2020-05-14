package com.b3tuning.b3console.service.files;

import com.b3tuning.b3console.view.utils.TreeElement;
import com.google.common.collect.Lists;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class FileEntity implements TreeElement {

	private final StringProperty name;
	private final StringProperty alias;
	private final StringProperty mimeType;
	private final ObjectProperty<Date> createdAt;
	private final ObjectProperty<Date> lastModified;
	private final LongProperty size;
	private final StringProperty relativePath;

	private final List<FileEntity> children;


	public FileEntity(Builder builder) {
		this.name         = new SimpleStringProperty();
		this.alias        = new SimpleStringProperty();
		this.mimeType     = new SimpleStringProperty();
		this.createdAt    = new SimpleObjectProperty<>();
		this.lastModified = new SimpleObjectProperty<>();
		this.size         = new SimpleLongProperty(builder.size);
		this.relativePath = new SimpleStringProperty();

		setIfNotNull(name, builder.name);
		setIfNotNull(alias, builder.alias);
		setIfNotNull(mimeType, builder.mimeType);
		setIfNotNull(createdAt, builder.createdAt);
		setIfNotNull(lastModified, builder.lastModified);
		setIfNotNull(relativePath, builder.relativePath);

		children = Lists.newArrayList();
	}

	public static Builder builder() {
		return new Builder();
	}

	@Setter
	@Accessors(fluent = true)
	public static class Builder {
		String           name;
		String           alias;
		String           mimeType;
		Date             createdAt;
		Date             lastModified;
		long             size;
		String           relativePath;
		List<FileEntity> children;

		public com.b3tuning.b3console.service.files.FileEntity build() {
			return new com.b3tuning.b3console.service.files.FileEntity(this);
		}
	}

	public String getName() {
		return name.get();
	}

	public String getAlias() {
		return alias.get();
	}

	public String getMimeType() {
		return mimeType.get();
	}

	public Date getCreatedAt() {
		return createdAt.get();
	}

	public Date getLastModified() {
		return lastModified.get();
	}

	public long getSize() {
		return size.get();
	}

	public String getRelativePath() {
		return relativePath.get();
	}

	public void setName(String name) {
		this.name.setValue(name);
	}

	public void setAlias(String alias) {
		this.alias.setValue(alias);
	}

	public void setMimeType(String mimeType) {
		this.mimeType.setValue(mimeType);
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt.setValue(createdAt);
	}

	public void setLastModified(Date lastModified) {
		this.lastModified.setValue(lastModified);
	}

	public void setSize(Long size) {
		this.size.setValue(size);
	}

	public void setRelativePath(String relativePath) {
		this.relativePath.setValue(relativePath);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty aliasProperty() {
		return alias;
	}

	public StringProperty mimeTypeProperty() {
		return mimeType;
	}

	public ObjectProperty<Date> createdAtProperty() {
		return createdAt;
	}

	public ObjectProperty<Date> lastModifiedProperty() {
		return lastModified;
	}

	public LongProperty sizeProperty() {
		return size;
	}

	public StringProperty relativePathProperty() {
		return relativePath;
	}

	public List<FileEntity> getChildren() {
		return children;
	}

	public void setChildren(List<FileEntity> children) {
		this.children.clear();
		this.children.addAll(children);
	}

	private <T> void setIfNotNull(Property<T> property, T value) {
		if (null != value) {
			property.setValue(value);
		}
	}

	@Override
	public boolean isDirectory() {
		return size == null || size.get() == 0;
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public boolean isFile() {
		return !isDirectory();
	}


	@Override
	public long lastModified() {
		if (getLastModified() == null) {
			return 0;
		}
		return getLastModified().getTime();
	}

	public long createdAt() {
		if (getCreatedAt() == null) {
			return 0;
		}
		return getCreatedAt().getTime();
	}
}

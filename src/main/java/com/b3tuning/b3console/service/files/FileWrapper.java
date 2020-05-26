package com.b3tuning.b3console.service.files;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class FileWrapper implements TreeElement, Serializable {

	private static final long serialVersionUID = 3803661886906494446L;

	private final File file;

	public FileWrapper(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	@Override
	public boolean isDirectory() {
		return file.isDirectory();
	}

	@Override
	public boolean isHidden() {
		return file.isHidden();
	}

	@Override
	public List<FileWrapper> getChildren() {
		if (file == null || file.listFiles() == null) {
			return Lists.newArrayList();
		}
		return Arrays.stream(requireNonNull(file.listFiles())).map(FileWrapper::new).collect(Collectors.toList());
	}

	@Override
	public String getName() {
		return file.getName();
	}

	@Override
	public boolean isFile() {
		return file.isFile();
	}

	@Override
	public long getSize() {
		return file.length();
	}

	@Override
	public long lastModified() {
		return file.lastModified();
	}
}
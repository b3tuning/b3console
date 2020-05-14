package com.b3tuning.b3console.view.file;

import com.b3tuning.b3console.view.BaseViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.util.Collection;

/*
 *  Created on:  Apr 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class FileDialogViewModel extends BaseViewModel {

	private ObjectProperty<File>                 rootPath;
	private ObjectProperty<ObservableList<File>> filesToIngest;
	private ObjectProperty<ObservableList<File>> invalidFiles;

	@Inject
	public FileDialogViewModel() {
		log.entry();
		rootPath      = new SimpleObjectProperty<>();
		filesToIngest = new SimpleObjectProperty<>(FXCollections.observableArrayList());
		invalidFiles  = new SimpleObjectProperty<>(FXCollections.observableArrayList());
	}

	void setRootPath(File rootPath) {
		this.rootPath.setValue(rootPath);
	}

	ObjectProperty<File> rootPathProperty() {
		return rootPath;
	}

	void setFilesToIngest(Collection<File> supported) {
		filesToIngest.get().setAll(supported);
	}

	ObjectProperty<ObservableList<File>> filesToIngestProperty() {
		return filesToIngest;
	}

	void setInvalidFiles(Collection<File> unsupported) {
		invalidFiles.get().setAll(unsupported);
	}

	ObjectProperty<ObservableList<File>> invalidFilesProperty() {
		return invalidFiles;
	}

}

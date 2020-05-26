
/*
 *  Created on:  May 25, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
package com.b3tuning.b3console.service.files.filemanager;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.ConfigBase;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

@XSlf4j
public class FileManager {

	private final UserPreferences preferences;

	private NotificationCenter globalNotifications;

	private RecentFile currentFile;

	ObjectProperty<ObservableList<RecentFile>> recentFiles = new SimpleObjectProperty<>(
			FXCollections.emptyObservableList());

	@Inject
	public FileManager(UserPreferences preferences, NotificationCenter notifications) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notifications;

		loadRecentFiles();
	}

	private void loadRecentFiles() {
		recentFiles.get().addAll(preferences.getRecentFiles());
	}

	private void updateRecentFiles() {
		preferences.setRecentFiles(recentFiles.get());
	}

	public RecentFile openFile(RecentFile recentFile) {
		log.entry(recentFile);
		currentFile = recentFile;
		currentFile.setLastAccessed(System.currentTimeMillis());
		recentFiles.get().remove(recentFile);
		recentFiles.get().add(currentFile);
		recentFiles.get().sorted();
		updateRecentFiles();
		return currentFile;
	}

	public RecentFile getCurrentFile() {
		return currentFile;
	}

	private void saveFile(ConfigBase config) {
		log.entry();
		if (currentFile == null) {
			log.error("currentFile is null!!!");
			return;
		}
		try (FileOutputStream fout = new FileOutputStream(currentFile.getPath(), false);
		     ObjectOutputStream oos = new ObjectOutputStream(fout)) {
			oos.writeObject(config);
		}
		catch (Exception ex) {
			log.error("Unable to saveFile, file: {} , ex: {}", currentFile, ex.getMessage());
		}
	}

	private void closeFile(RecentFile recentFile) {
		log.entry(recentFile);
		currentFile = null;
	}

	public ObjectProperty<ObservableList<RecentFile>> recentFilesProperty() {
		return recentFiles;
	}

}


/*
 *  Created on:  May 25, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
package com.b3tuning.b3console.service.files.filemanager;

import com.b3tuning.b3console.control.menubar.file.subs.NewConfigDialog;
import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleType;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

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

	private void updateRecentFiles(RecentFile recentFile) {
		log.entry(recentFile);
		currentFile = recentFile;
		currentFile.setLastAccessed(System.currentTimeMillis());
		recentFiles.get().remove(recentFile);
		recentFiles.get().add(currentFile);
		recentFiles.get().sorted();
		updateRecentFiles();
	}

	public static Optional<ConfigBase> createNewConfigDialog() {
		return NewConfigDialog.createNewConfigDialog();
	}

	public ConfigBase createNewFile(ModuleType type) {
		updateRecentFiles(new RecentFile().setType(type));
		return new ConfigBase(type);
	}

	public ConfigBase openFile(RecentFile recentFile) {
		return openFile(recentFile.getPath());
	}

	public ConfigBase openFile(Stage stage) {
		log.entry();

		ConfigBase  config   = null;
		FileChooser chooser  = new FileChooser();
		File        selected = chooser.showOpenDialog(stage);
		if (selected != null) {
			config = openFile(selected.getAbsolutePath());
		}
		return config;
	}

	public ConfigBase openFile(String path) {
		log.entry(path);
		ConfigBase config = null;
		try (InputStream in = new FileInputStream(path);
		     ObjectInputStream ois = new ObjectInputStream(in)) {
			config = (ConfigBase) ois.readObject();
			updateRecentFiles(recentFileFromConfig(config, path));
		}
		catch (Exception ex) {
			log.error("Unable to openFile, path: {} , ex: {}", path, ex.getMessage());
		}
		return config;
	}

	public RecentFile recentFileFromConfig(ConfigBase config, String path) {
		return new RecentFile(config.getName(), path, config.getType(), null);
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

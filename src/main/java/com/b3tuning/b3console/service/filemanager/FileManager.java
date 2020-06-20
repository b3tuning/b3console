
/*
 *  Created on:  May 25, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
package com.b3tuning.b3console.service.filemanager;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.ConfigBaseResource;
import com.b3tuning.b3console.service.module.ModuleType;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.NonNull;
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
	private final FileChooser     chooser;

	private final NotificationCenter globalNotifications;

	private RecentFile currentFile;

	private final ObjectProperty<ObservableList<RecentFile>> recentFiles = new SimpleObjectProperty<>(
			FXCollections.observableArrayList());

	@Inject
	public FileManager(UserPreferences preferences, NotificationCenter notifications, FileChooser chooser) {
		log.entry();
		this.preferences = preferences;
		this.chooser     = chooser;
		this.chooser.setInitialDirectory(new File(preferences.getBrowseLocalPath()));
		this.chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("B3Tuning Module Config", "*.b3t"));
		this.globalNotifications = notifications;

		loadRecentFiles();
	}

	private void loadRecentFiles() {
		log.entry();
		recentFiles.get().addAll(preferences.getRecentFiles());
		log.info("Stored Recent Files = {}", preferences.getRecentFiles());
		log.info("RecentFiles loaded = {}", recentFiles);
	}

	private void updateRecentFiles() {
		log.entry();
		preferences.setRecentFiles(recentFiles.get());
	}

	private void updateRecentFiles(RecentFile recentFile) {
		log.entry(recentFile);
		currentFile = recentFile;
		currentFile.setLastAccessed(System.currentTimeMillis());
		recentFiles.get().removeAll(recentFile);
		recentFiles.get().add(currentFile);
		recentFiles.get().sorted();
		updateRecentFiles();
	}

	public void newFileAction() {
		log.entry();
	}

	public static Optional<ConfigBase> createNewConfigDialog() {
		return NewConfigDialog.createNewConfigDialog();
	}

	public ConfigBase createNewFile(ModuleType type) {
		updateRecentFiles(new RecentFile().setType(type));
		return new ConfigBase(type);
	}

	public void openFileAction(Window window) {
		log.entry();
		File selected = chooser.showOpenDialog(window);
		if (null != selected) {
			openFile(selected.getAbsolutePath());
		}
	}

	public ConfigBase openFile(RecentFile recentFile) {
		return openFile(recentFile.getPath());
	}

	//	public ConfigBase openFile(Window stage) {
//		log.entry();
//
//		ConfigBase config   = null;
//		File       selected = chooser.showOpenDialog(stage);
//		if (selected != null) {
//			config = openFile(selected.getAbsolutePath());
//		}
//		return config;
//	}
//
	public ConfigBase openFile(@NonNull String path) {
		log.entry(path);
		ConfigBase config = null;
		try (InputStream in = new FileInputStream(path);
		     ObjectInputStream ois = new ObjectInputStream(in)) {
			ConfigBaseResource resource = (ConfigBaseResource) ois.readObject();
			config = ConfigBaseAssembler.assemble(resource);
			updateRecentFiles(recentFileFromConfig(config, path));
		}
		catch (Exception ex) {
			log.error("Unable to openFile, path: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return config;
	}

	public RecentFile recentFileFromConfig(ConfigBase config, String path) {
		log.entry();
		return new RecentFile(config.getName().get(), path, config.getType().get(), config.getUpdatedAt().get());
	}

	public RecentFile getCurrentFile() {
		return currentFile;
	}

	public void saveConfig(ConfigBase base) {
		log.entry();
		File file = chooser.showSaveDialog(new Stage());
		if (file != null) {
			saveConfig(base, file);
		}
	}

	private void saveConfig(ConfigBase base, File file) {
		log.entry();
		ConfigBaseResource resource = ConfigBaseAssembler.assemble(base);
		try (FileOutputStream out = new FileOutputStream(file.getAbsolutePath(), false);
		     ObjectOutputStream oos = new ObjectOutputStream(out)) {
			oos.writeObject(resource);
		}
		catch (Exception ex) {
			log.error("Unable to saveFile, file: {} , ex: {}", file.getAbsolutePath(), ex.getMessage(), ex);
			ex.printStackTrace();
		}
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
			log.error("Unable to saveFile, file: {} , ex: {}", currentFile, ex.getMessage(), ex);
		}
	}

	private void closeFile(RecentFile recentFile) {
		log.entry(recentFile);
		currentFile = null;
	}

	public ObjectProperty<ObservableList<RecentFile>> recentFilesProperty() {
		return recentFiles;
	}

	public void newFileAction(Window window) {
		log.entry();

	}

	public void closeFileAction() {
		log.entry();
		// TODO: impl
	}

	public void saveFileAction() {
		log.entry();
		// TODO: impl
	}

	public void saveAsFileAction() {
		log.entry();
		// TODO: impl
	}

	public void sendFileAction() {
		log.entry();
		// TODO: impl
	}
}


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
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
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

import static com.b3tuning.b3console.prefs.UserPreferences.RECENT_FILE_DEFAULT;

@XSlf4j
public class FileManager {

	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;
	private final FileChooser        chooser;
	private final NewConfigDialog    newConfigDialog;

	private final ObservableList<String> recentFiles = FXCollections.observableArrayList();

	@Inject
	public FileManager(UserPreferences preferences, NotificationCenter notifications, FileChooser chooser,
	                   NewConfigDialog newConfigDialog) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notifications;
		this.chooser             = chooser;
		this.chooser.setInitialDirectory(new File(preferences.getBrowseLocalPath()));
		this.chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("B3Tuning Module Config", "*.b3t"));
		this.newConfigDialog = newConfigDialog;

		loadRecentFiles();
	}

	private void loadRecentFiles() {
		log.entry();
		recentFiles.addAll(preferences.getRecentFiles());
		log.info("RecentFiles loaded = {}", recentFiles);
	}

	private void updateRecentFiles() {
		log.entry();
		preferences.setRecentFiles(recentFiles);
		globalNotifications.publish("UPDATE_RECENTS");
	}

	private void updateRecentFiles(String recentFile) {
		log.entry(recentFile);
		recentFiles.removeAll(recentFile, RECENT_FILE_DEFAULT);
		recentFiles.add(0, recentFile);
		updateRecentFiles();
	}

	public Optional<ConfigBase> newFileAction() {
		log.entry();
		return newConfigDialog.createNewConfigDialog();
	}

	public File openFileAction(Window window) {
		log.entry();
		return chooser.showOpenDialog(window);
	}

	public ConfigBase openFile(@NonNull String path) {
		log.entry(path);
		ConfigBase config = null;
		try (InputStream in = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(in)) {
			ConfigBaseResource resource = (ConfigBaseResource) ois.readObject();
			config = ConfigBaseAssembler.assemble(resource);
			updateRecentFiles(path);
		}
		catch (Exception ex) {
			log.error("Unable to openFile, path: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
		return config;
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

//	private void saveFile(ConfigBase config) {
//		log.entry();
//		if (currentFile == null) {
//			log.error("currentFile is null!!!");
//			return;
//		}
//		try (FileOutputStream fout = new FileOutputStream(currentFile.getPath(), false);
//		     ObjectOutputStream oos = new ObjectOutputStream(fout)) {
//			oos.writeObject(config);
//		}
//		catch (Exception ex) {
//			log.error("Unable to saveFile, file: {} , ex: {}", currentFile, ex.getMessage(), ex);
//		}
//	}

	public ObservableList<String> recentFilesProperty() {
		return recentFiles;
	}

	public void closeFileAction() {
		log.entry();
		// TODO: impl
	}

	public void saveFileAction(ConfigBase configBase) {
		log.entry();
		saveConfig(configBase);
	}

	public void saveFileAsAction() {
		log.entry();
		// TODO: impl
	}

	public void sendFileAction() {
		log.entry();
		// TODO: impl
	}
}

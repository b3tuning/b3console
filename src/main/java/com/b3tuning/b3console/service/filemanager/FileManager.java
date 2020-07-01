
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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.FileChooser;
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

import static com.b3tuning.b3console.App.APP_NAME;

@XSlf4j
public class FileManager {

	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;
	private final FileChooser        chooser;
	private final NewConfigDialog    newConfigDialog;

	private final ObjectProperty<ConfigBase> config           = new SimpleObjectProperty<>(null);
	private final BooleanProperty            configLoaded     = new SimpleBooleanProperty(false);
	private final StringProperty             configLoadedPath = new SimpleStringProperty(null);

	@Inject
	public FileManager(UserPreferences preferences, NotificationCenter notifications, FileChooser chooser,
	                   NewConfigDialog newConfigDialog) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notifications;
		this.chooser             = chooser;
		this.chooser.setInitialDirectory(new File(preferences.getBrowseLocalPath()));
		this.chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(APP_NAME, "*.b3t"));
		this.newConfigDialog = newConfigDialog;

		configLoaded.bind(config.isNotNull());
	}

	private void setConfig(ConfigBase openFile) {
		config.set(openFile);
	}

	private void setConfigPath(String path) {
		configLoadedPath.set(path);
	}

	private void readFile(@NonNull String path) {
		log.entry(path);
		try (InputStream in = new FileInputStream(path);
		     ObjectInputStream stream = new ObjectInputStream(in)) {
			ConfigBaseResource resource = (ConfigBaseResource) stream.readObject();
			setConfig(ConfigBaseAssembler.assemble(resource));
		}
		catch (Exception ex) {
			log.error("Unable to openFile, path: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}

	private void writeFile(@NonNull String path, @NonNull ConfigBase base) {
		log.entry(path);
		ConfigBaseResource resource = ConfigBaseAssembler.assemble(base);
		try (FileOutputStream out = new FileOutputStream(path, false);
		     ObjectOutputStream stream = new ObjectOutputStream(out)) {
			stream.writeObject(resource);
			setConfigPath(path);
		}
		catch (Exception ex) {
			log.error("Unable to saveFile, file: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}

	private void handleOpenFile(String path) {
		setConfig(openFile(path));
	}

	public void newFileAction() {
		log.entry();
		newConfigDialog.createNewConfigDialog().ifPresent(configBase -> {
			log.entry(configBase);
			// TODO::::::::
			if (configLoadedPath.isNotNull().get()) {
				log.error("configLoadedPath is NOT NULL");
				setConfigPath(null);
			}
			setConfig(configBase);
		});
	}

	public String openFileAction(Window window) {
		log.entry();
		String path     = null;
		File   selected = chooser.showOpenDialog(window);
		if (null != selected) {
			path = selected.getAbsolutePath();
			handleOpenFile(path);
		}
		return path;
	}

	public ConfigBase openFile(@NonNull String path) {
		log.entry(path);
		ConfigBase config = null;
		try (InputStream in = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(in)) {
			ConfigBaseResource resource = (ConfigBaseResource) ois.readObject();
			log.error("OBJECT TYPE = {}", resource.getType());
			config = ConfigBaseAssembler.assemble(resource);
		}
		catch (Exception ex) {
			log.error("Unable to openFile, path: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
		setConfigPath(path);
		return config;
	}

	private void saveConfig(Window window, boolean saveAs) {
		log.entry(saveAs);
		if (saveAs || configLoadedPath.isNull().get()) {
			File file = chooser.showSaveDialog(window);
			if (null == file) {
				return;
			}
			setConfigPath(file.getAbsolutePath());
		}
		writeFile(configLoadedPath.get(), config.get());
	}

	public String openRecentFileAction(String path) {
		return null;
	}

	public void closeFileAction() {
		log.entry();
		setConfig(null);
		setConfigPath(null);
	}

	public String saveFileAsAction(Window window, boolean saveAs) {
		log.entry(saveAs);
		saveConfig(window, saveAs);
		return configLoadedPath.get();
	}

	public void sendFileAction() {
		log.entry();
		// TODO: impl
	}

	public BooleanProperty configLoadedProperty() {
		return configLoaded;
	}
}

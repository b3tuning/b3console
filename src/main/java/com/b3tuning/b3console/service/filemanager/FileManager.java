
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
import com.google.common.collect.Lists;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
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
import java.util.List;
import java.util.Optional;

import static com.b3tuning.b3console.App.APP_NAME;
import static com.b3tuning.b3console.prefs.UserPreferences.MAX_RECENT;
import static com.b3tuning.b3console.prefs.UserPreferences.RECENT_FILE_DEFAULT;

@XSlf4j
public class FileManager {

	public static final String LOAD_CONFIG    = "LOAD_CONFIG";
	public static final String MANAGE_RECENTS = "Manage Recents...";

	private int CURRENT_MAX_RECENT;

	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;
	private final FileChooser        chooser;
	private final NewConfigDialog    newConfigDialog;

	private final ObservableList<MenuItem> recentFiles  = FXCollections.observableArrayList();
	private       String                   loadedConfig = "";

	@Inject
	public FileManager(UserPreferences preferences, NotificationCenter notifications, FileChooser chooser,
	                   NewConfigDialog newConfigDialog) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notifications;
		this.chooser             = chooser;
		this.chooser.setInitialDirectory(new File(preferences.getBrowseLocalPath()));
		this.chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(APP_NAME, "*.b3t"));
		this.newConfigDialog    = newConfigDialog;
		this.CURRENT_MAX_RECENT = preferences.getMaxRecentFiles();

		loadRecentFiles();

		globalNotifications.subscribe(MAX_RECENT, (key, payload) -> CURRENT_MAX_RECENT = (int) payload[0]);
	}

	private void loadRecentFiles() {
		log.entry();
		for (String path : preferences.getRecentFiles()) {
			MenuItem item = assembleMenuItem(path);
			if (RECENT_FILE_DEFAULT.equals(path)) {
				item.setDisable(true);
			}
			recentFiles.add(item);
		}
		recentFiles.add(new SeparatorMenuItem());
		recentFiles.add(manageRecentsMenuItem());
		log.info("RecentFiles loaded = {}", recentFiles);
	}

	private MenuItem setAction(MenuItem item) {
		item.setOnAction(event -> {
			log.entry();
			openRecentFileAction(item.getText());
		});
		return item;
	}

	private MenuItem assembleMenuItem(String path) {
		return setAction(new MenuItem(path));
	}

	private void openRecentFileAction(String path) {
		log.entry(path);
		handleOpenFile(path);
	}

	private void handleOpenFile(String path) {
		setConfig(openFile(path));
	}

	private void updateRecentFiles(String path) {
		recentFiles.removeIf(item -> !(item instanceof SeparatorMenuItem) &&
		                             (item.getText().equals(path) || item.getText().equals(RECENT_FILE_DEFAULT)));
		recentFiles.add(0, assembleMenuItem(path));
		while (recentFiles.size() > (CURRENT_MAX_RECENT + 2)) {
			recentFiles.remove(CURRENT_MAX_RECENT - 1);
		}
		saveRecentFiles();
	}

	private void setConfig(ConfigBase openFile) {
		globalNotifications.publish(LOAD_CONFIG, openFile);
	}

	private MenuItem manageRecentsMenuItem() {
		MenuItem item = new MenuItem(MANAGE_RECENTS);
		item.setOnAction(event -> {
			log.entry();
			openManageRecentsAction();
		});
		return item;
	}

	private void openManageRecentsAction() {
		log.entry();
		// TODO: open ContextMenu to remove recentFiles
	}

	private void saveRecentFiles() {
		log.entry();
		List<String> r = Lists.newArrayList();
		for (MenuItem item : recentFiles) {
			if (item instanceof SeparatorMenuItem) {
				continue;
			}
			String path = item.getText();
			if (path.equals(MANAGE_RECENTS) || path.equals(RECENT_FILE_DEFAULT)) {
				continue;
			}
			r.add(path);
		}
		preferences.setRecentFiles(r);
	}

	public Optional<ConfigBase> newFileAction() {
		log.entry();
		return newConfigDialog.createNewConfigDialog();
	}

	public void openFileAction(Window window) {
		log.entry();
		File selected = chooser.showOpenDialog(window);
		if (null != selected) {
			String path = selected.getAbsolutePath();
			handleOpenFile(path);
		}
	}

	public ConfigBase openFile(@NonNull String path) {
		log.entry(path);
		ConfigBase config = null;
		try (InputStream in = new FileInputStream(path); ObjectInputStream ois = new ObjectInputStream(in)) {
			ConfigBaseResource resource = (ConfigBaseResource) ois.readObject();
			log.error("OBJECT TYPE = {}", resource.getType());
			config = ConfigBaseAssembler.assemble(resource);
			updateRecentFiles(path);
		}
		catch (Exception ex) {
			log.error("Unable to openFile, path: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
		loadedConfig = path;
		return config;
	}

	public void saveConfig(ConfigBase base) {
		log.entry();
		if (loadedConfig.isEmpty()) {
			log.error("LOADED CONFIG = {}", loadedConfig);
			saveAsConfig(base);
		} else {
			saveConfig(base, loadedConfig);
		}
	}

	public void saveAsConfig(ConfigBase base) {
		log.entry();
		File file = chooser.showSaveDialog(new Stage());
		if (file != null) {
			saveConfig(base, file.getAbsolutePath());
		}
	}

	private void saveConfig(ConfigBase base, String path) {
		log.entry();
		ConfigBaseResource resource = ConfigBaseAssembler.assemble(base);
		try (FileOutputStream out = new FileOutputStream(path, false);
		     ObjectOutputStream oos = new ObjectOutputStream(out)) {
			oos.writeObject(resource);
			updateRecentFiles(path);
		}
		catch (Exception ex) {
			log.error("Unable to saveFile, file: {} , ex: {}", path, ex.getMessage(), ex);
			ex.printStackTrace();
		}
		loadedConfig = path;
	}

	public ObservableList<MenuItem> recentFilesProperty() {
		return recentFiles;
	}

	public void closeFileAction() {
		log.entry();
		loadedConfig = "";
		// TODO: impl
	}

	public void saveFileAction(ConfigBase configBase) {
		log.entry();
		saveConfig(configBase);
	}

	public void saveFileAsAction(ConfigBase configBase) {
		log.entry();
		saveAsConfig(configBase);
	}

	public void sendFileAction() {
		log.entry();
		// TODO: impl
	}
}

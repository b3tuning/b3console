/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  FileMenuViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.file;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.filemanager.FileManager;
import com.b3tuning.b3console.view.BaseViewModel;
import com.google.common.collect.Lists;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Window;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.util.List;

import static com.b3tuning.b3console.prefs.UserPreferences.MAX_RECENT;
import static com.b3tuning.b3console.prefs.UserPreferences.RECENT_FILE_DEFAULT;
import static org.reactfx.EventStreams.nonNullValuesOf;

@XSlf4j
public class FileMenuViewModel extends BaseViewModel {

	public static final  String EXIT_ACTION    = "exit_action";
	private static final String MANAGE_RECENTS = "Manage Recents...";

	private int CURRENT_MAX_RECENT;

	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;
	private final FileManager        fileManager;

	private final ObservableList<MenuItem> recentFiles = FXCollections.observableArrayList();

	@Inject
	public FileMenuViewModel(UserPreferences preferences, NotificationCenter notificationCenter, FileManager manager) {
		log.entry();
		this.preferences         = preferences;
		this.globalNotifications = notificationCenter;
		this.fileManager         = manager;
		this.CURRENT_MAX_RECENT  = preferences.getMaxRecentFiles();

		loadRecentFiles();
		limitRecentFiles();

		globalNotifications.subscribe(MAX_RECENT, (key, payload) -> {
			log.entry(key, payload);
			CURRENT_MAX_RECENT = (int) payload[0];
			limitRecentFiles();
		});

		manage(nonNullValuesOf(fileManager.configPathProperty()).subscribe(path -> {
			log.entry(path);
			updateRecentFiles(path);
		}));
	}

	/////////////////////////////////////////////////////////////////////////////
	/// Recent Files methods
	/////////////////////////////////////////////////////////////////////////////
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

	private void updateRecentFiles(String path) {
		log.entry(path);
		recentFiles.removeIf(item -> !(item instanceof SeparatorMenuItem) &&
		                             (item.getText().equals(path) || item.getText().equals(RECENT_FILE_DEFAULT)));
		recentFiles.add(0, assembleMenuItem(path));
		limitRecentFiles();
		saveRecentFiles();
	}

	private void limitRecentFiles() {
		while (recentFiles.size() > (CURRENT_MAX_RECENT + 2)) {
			recentFiles.remove(CURRENT_MAX_RECENT - 1);
		}
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

	private MenuItem assembleMenuItem(String path) {
		return setAction(new MenuItem(path));
	}

	private MenuItem setAction(MenuItem item) {
		item.setOnAction(event -> {
			log.entry();
			openRecentFileAction(item.getText());
		});
		return item;
	}

	/////////////////////////////////////////////////////////////////////////////
	/// Menu actions
	/////////////////////////////////////////////////////////////////////////////
	void newFileAction() {
		log.entry();
		fileManager.newFileAction();
	}

	void openFileAction(Window window) {
		log.entry();
		fileManager.openFileAction(window);
	}

	void openRecentFileAction(String path) {
		log.entry(path);
		fileManager.openRecentFileAction(path);
	}

	void closeFileAction() {
		log.entry();
		fileManager.closeFileAction();
	}

	void saveFileAsAction(Window window, boolean saveAs) {
		log.entry(saveAs);
		fileManager.saveFileAsAction(window, saveAs);
	}

	void sendFileAction() {
		log.entry();
		fileManager.sendFileAction();
	}

	void exitAction() {
		log.entry();
		globalNotifications.publish(EXIT_ACTION);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX Properties for the view
	/////////////////////////////////////////////////////////////////////////////
	ObservableList<MenuItem> recentFilesProperty() {
		return recentFiles;
	}

	public BooleanProperty configLoadedProperty() {
		return fileManager.configLoadedProperty();
	}
}

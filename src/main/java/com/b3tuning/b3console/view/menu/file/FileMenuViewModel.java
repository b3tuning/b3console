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

import com.b3tuning.b3console.service.filemanager.FileManager;
import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

import static com.b3tuning.b3console.prefs.UserPreferences.RECENT_FILE_DEFAULT;

@XSlf4j
public class FileMenuViewModel extends BaseViewModel {

	public static final String EXIT_ACTION = "exit_action";

	private final NotificationCenter globalNotifications;
	private final FileManager        fileManager;

	private final ObservableList<MenuItem> recentFiles = FXCollections.observableArrayList();

	@Inject
	public FileMenuViewModel(NotificationCenter notificationCenter, FileManager manager) {
		log.entry();
		this.globalNotifications = notificationCenter;
		this.fileManager         = manager;

		initRecentFiles();
	}

	private void initRecentFiles() {
		List<String> recents = fileManager.recentFilesProperty();
		for (String path : recents) {
			MenuItem item = new MenuItem(path);
			if (!path.equals(RECENT_FILE_DEFAULT)) {
				item.setOnAction(event -> {
					log.entry();
					openRecentFileAction(item.getText());
				});
			} else {
				item.setDisable(true);
			}
			recentFiles.add(item);
		}
	}

	private void handleOpenFile(String path) {
		recentFiles.removeIf(item -> item.getText().equals(path) || item.getText().equals(RECENT_FILE_DEFAULT));
		recentFiles.add(0, new MenuItem(path));
		setConfig(fileManager.openFile(path));
	}

	ObservableList<MenuItem> recentFilesProperty() {
		return recentFiles;
	}

	void newFileAction() {
		log.entry();
		fileManager.newFileAction().ifPresent(this::setConfig);
	}

	void openFileAction(Window window) {
		log.entry();
		File selected = fileManager.openFileAction(window);
		if (null != selected) {
			String path = selected.getAbsolutePath();
			handleOpenFile(path);
		}
	}

	void openRecentFileAction(String path) {
		log.entry(path);
		handleOpenFile(path);
	}

	void closeFileAction() {
		log.entry();
		fileManager.closeFileAction();
		unsetConfig();
	}

	void saveFileAction() {
		log.entry();
		fileManager.saveFileAction(configProperty().get());
	}

	void saveFileAsAction() {
		log.entry();
		fileManager.saveFileAsAction();
	}

	void sendFileAction() {
		log.entry();
		fileManager.sendFileAction();
	}

	void exitAction() {
		log.entry();
		globalNotifications.publish(EXIT_ACTION);
	}
}

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
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class FileMenuViewModel extends BaseViewModel {

	public static final String EXIT_ACTION = "exit_action";

	private final NotificationCenter globalNotifications;
	private final FileManager        fileManager;

	@Inject
	public FileMenuViewModel(NotificationCenter notificationCenter, FileManager manager) {
		log.entry();
		this.globalNotifications = notificationCenter;
		this.fileManager         = manager;

	}

	ObservableList<MenuItem> recentFilesProperty() {
		return fileManager.recentFilesProperty();
	}

	void newFileAction() {
		log.entry();
		fileManager.newFileAction().ifPresent(this::setConfig);
	}

	void openFileAction(Window window) {
		log.entry();
		fileManager.openFileAction(window);
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

/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  FileMenuView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.file;

import com.b3tuning.b3console.service.files.filemanager.RecentFile;
import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class FileMenuView extends BaseView<FileMenuViewModel> {

	@FXML private Menu fileMenu;
	@FXML private Menu recentFilesMenu;

	private final            NotificationCenter globalNotifications;
	@InjectViewModel private FileMenuViewModel  viewModel;

	@Inject
	public FileMenuView(NotificationCenter notificationCenter) {
		log.entry();
		this.globalNotifications = notificationCenter;
	}

	public void initialize() {
		log.entry();

		constructFileMenu();

		constructRecentFilesMenu();
	}

	private void constructRecentFilesMenu() {
		ObservableList<RecentFile> files = viewModel.getRecents().get();
		if (files.isEmpty()) {
			return;
		}
		recentFilesMenu.getItems().clear();
		for (RecentFile r : files) {
			MenuItem item = new MenuItem(r.getName());
			item.setUserData(r.getPath());
			item.addEventHandler(ActionEvent.ACTION, event -> {
				log.entry();
				globalNotifications.publish("LOAD_FILE", item.getUserData());
			});
		}
	}

	private void constructFileMenu() {
		fileMenu.getItems().add(new MenuItem("TESTING"));
	}

}

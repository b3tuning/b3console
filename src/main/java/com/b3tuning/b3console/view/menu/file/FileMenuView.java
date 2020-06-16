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

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class FileMenuView extends BaseView<FileMenuViewModel> {

	@FXML private Menu fileMenu;

	private final NotificationCenter globalNotifications;
	@InjectViewModel private FileMenuViewModel viewModel;

	@Inject
	public FileMenuView(NotificationCenter notificationCenter) {
		log.entry();
		this.globalNotifications = notificationCenter;
	}

	public void initialize() {
		log.entry();

		constructFileMenu();
	}

	private void constructFileMenu() {
		fileMenu.getItems().add(new MenuItem("TESTING"));
	}

}

/*
 *  Created on:  Jun 10, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MenuView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu;

import com.b3tuning.b3console.service.files.filemanager.FileManager;
import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class MenuView extends BaseView<MenuViewModel> {

	@FXML private MenuBar menuBar;
	@FXML private Menu    fileMenu;

	@FXML private Menu editMenu;

	@FXML private Menu viewMenu;

	@FXML private Menu onlineMenu;

	@FXML private Menu toolsMenu;

	@FXML private Menu helpMenu;

	@InjectViewModel private MenuViewModel viewModel;

	private final NotificationCenter globalNotifications;
	private final FileManager manager;

	@Inject
	public MenuView(NotificationCenter globalNotifications, FileManager manager) {
		log.entry();

		this.globalNotifications = globalNotifications;
		this.manager = manager;
	}

	public void initialize() {
		log.entry();

	}
}

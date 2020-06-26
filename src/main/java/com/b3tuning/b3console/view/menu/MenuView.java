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

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.menu.file.FileMenuView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class MenuView extends BaseView<MenuViewModel> {

	@SuppressWarnings("unused")
	@FXML private Menu         fileMenu;
	@SuppressWarnings("unused")
	@FXML private FileMenuView fileMenuController;

	@InjectViewModel private MenuViewModel viewModel;

	@Inject
	public MenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();
		viewModel.configLoadedProperty().bind(fileMenuController.fileMenuConfigLoadedProperty());
	}
}

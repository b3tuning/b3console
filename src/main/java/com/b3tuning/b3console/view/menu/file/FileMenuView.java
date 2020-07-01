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
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class FileMenuView extends BaseView<FileMenuViewModel> {

	@FXML private Menu     recentFilesMenu;
	@FXML private MenuItem closeFileMenuItem;
	@FXML private MenuItem saveFileMenuItem;
	@FXML private MenuItem saveFileAsMenuItem;
	@FXML private MenuItem sendFileMenuItem;

	@InjectViewModel private FileMenuViewModel viewModel;

	@Inject
	public FileMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		closeFileMenuItem.disableProperty().bind(viewModel.configLoadedProperty().not());
		saveFileMenuItem.disableProperty().bind(viewModel.configLoadedProperty().not());
		saveFileAsMenuItem.disableProperty().bind(viewModel.configLoadedProperty().not());
		sendFileMenuItem.disableProperty().bind(viewModel.configLoadedProperty().not());

		Bindings.bindContent(recentFilesMenu.getItems(), viewModel.recentFilesProperty());
	}

	@FXML
	private void newFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.newFileAction();
	}

	@FXML
	private void openFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.openFileAction(getWindow(event));
	}

	@FXML
	private void closeFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.closeFileAction();
	}

	@FXML
	private void saveFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.saveFileAsAction(getWindow(event), false);
	}

	@FXML
	private void saveFileAsAction(ActionEvent event) {
		log.entry(event);
		viewModel.saveFileAsAction(getWindow(event), true);
	}

	@FXML
	private void sendFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.sendFileAction();
	}

	@FXML
	private void exitAction(ActionEvent event) {
		log.entry(event);
		viewModel.exitAction();
	}

	private static Window getWindow(ActionEvent event) {
		MenuItem item = (MenuItem) event.getSource();
		return item.getParentPopup().getOwnerWindow();
	}
}

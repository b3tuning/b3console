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

import com.b3tuning.b3console.service.filemanager.RecentFile;
import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class FileMenuView extends BaseView<FileMenuViewModel> {

	@FXML private Menu recentFilesMenu;

	@InjectViewModel private FileMenuViewModel viewModel;

	@Inject
	public FileMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();
		constructRecentFilesMenu();
	}

	private void constructRecentFilesMenu() {
		log.entry();
		ObservableList<RecentFile> files = viewModel.getRecents().get();
		if (files.isEmpty()) {
			log.entry("NO RECENT FILES");
			return;
		}
		recentFilesMenu.getItems().clear();
		for (RecentFile r : files) {
			MenuItem item = new MenuItem(r.getName());
			item.setUserData(r.getPath());
			item.setOnAction(event -> {
				log.entry();
				openRecentFileAction(item, event);
			});
			recentFilesMenu.getItems().add(item);
		}
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
	private void openRecentFileAction(MenuItem item, ActionEvent event) {
		log.entry(item, event);
		viewModel.openRecentFileAction(item.getUserData().toString());
	}

	@FXML
	private void closeFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.closeFileAction();
	}

	@FXML
	private void saveFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.saveFileAction();
	}

	@FXML
	private void saveAsFileAction(ActionEvent event) {
		log.entry(event);
		viewModel.saveAsFileAction();
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

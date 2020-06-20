package com.b3tuning.b3console.view.settings;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;

import static com.b3tuning.b3console.view.root.FontAwesome.FOLDER_OPEN;
import static javafx.event.ActionEvent.ACTION;
import static org.reactfx.EventStreams.eventsOf;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class SettingsMenuView extends BaseView<SettingsMenuViewModel> {

	@FXML private Button    selectDirectory;
	@FXML private TextField downloadPath;
	@FXML private Label     version;

	@InjectViewModel private SettingsMenuViewModel viewModel;

	@Inject
	public SettingsMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		downloadPath.textProperty().bindBidirectional(viewModel.downloadPathProperty());

		selectDirectory.setText(FOLDER_OPEN);
		manage(eventsOf(selectDirectory, ACTION).subscribe(e -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			Stage            primaryStage     = (Stage) selectDirectory.getScene().getWindow();
			File             selected         = directoryChooser.showDialog(primaryStage);
			if (selected != null) {
				downloadPath.setText(selected.getAbsolutePath());
			}
			selectDirectory.getParent().requestFocus();
		}));

		version.textProperty().bind(viewModel.versionStringProperty());
	}
}
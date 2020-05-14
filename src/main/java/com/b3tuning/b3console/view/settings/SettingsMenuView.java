package com.b3tuning.b3console.view.settings;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;

import static com.b3tuning.b3console.view.root.FontAwesome.FOLDER_OPEN;
import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction.DOOR;
import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction.SHIFTER;
import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction.TRANS;
import static javafx.event.ActionEvent.ACTION;
import static org.reactfx.EventStreams.eventsOf;
import static org.reactfx.EventStreams.nonNullValuesOf;

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

	@FXML Button    selectDirectory;
	@FXML TextField downloadPath;

	@FXML private Label version;

	@FXML private ToggleGroup  moduleGroup;
	@FXML private ToggleButton doorButton;
	@FXML private ToggleButton shifterButton;
	@FXML private ToggleButton transButton;

	@FXML private            ComboBox<String>      port;
	@InjectViewModel private SettingsMenuViewModel viewModel;

	@Inject
	public SettingsMenuView() {
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

//		port.itemsProperty().bind(viewModel.);
		doorButton.setText("Door Control");
		doorButton.setUserData(DOOR);
		doorButton.setSelected(doorButton.getUserData() == (viewModel.moduleProperty().get()));

		shifterButton.setText("Shifter Control");
		shifterButton.setUserData(SHIFTER);
		shifterButton.setSelected(shifterButton.getUserData() == viewModel.moduleProperty().get());

		transButton.setText("Trans Control");
		transButton.setUserData(TRANS);
		transButton.setSelected(transButton.getUserData() == viewModel.moduleProperty().get());

		manage(nonNullValuesOf(moduleGroup.selectedToggleProperty()).subscribe(v -> {
			ModuleAction action = (ModuleAction) v.getUserData();
			viewModel.onButtonAction(action);
		}));

		version.textProperty().bind(viewModel.versionStringProperty());
	}
}
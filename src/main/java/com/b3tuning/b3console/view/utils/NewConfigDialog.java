/*
 *  Created on:  Jun 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.utils;

import com.b3tuning.b3console.App;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleType;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.XSlf4j;

import java.util.Optional;

import static com.b3tuning.b3console.App.DEFAULT_CSS;

@XSlf4j
public class NewConfigDialog {

	public static final double GRID_H_GAP      = 10;
	public static final double GRID_V_GAP      = 15;
	public static final double GRID_PADDING    = 10;
	public static final double TEXT_PREF_WIDTH = 300.0;

	public static Optional<ConfigBase> createNewConfigDialog() {
		log.entry();

		// Custom dialog to for new config
		Dialog<ConfigBase> dialog = new Dialog<>();
		dialog.setTitle("New Configuration");
		dialog.setHeaderText("Enter a name and select a Module type to configure");
		dialog.getDialogPane().getStylesheets().add(App.class.getResource(DEFAULT_CSS).toExternalForm());
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		// Create the custom grid
		GridPane grid = new GridPane();
		grid.setHgap(GRID_H_GAP);
		grid.setVgap(GRID_V_GAP);
		grid.setPadding(new Insets(GRID_PADDING, GRID_PADDING, GRID_PADDING, GRID_PADDING));

		// Create the config name text fields
		TextField configName = new TextField();
		configName.setPrefWidth(TEXT_PREF_WIDTH);
		configName.minWidthProperty().bind(configName.prefWidthProperty());

		// Add TextField to grid
		grid.add(new Label("Config Name:"), 0, 0);
		grid.add(configName, 1, 0);

		// Create the module type choice box
		ChoiceBox<ModuleType> type = new ChoiceBox<>();
		type.getItems().setAll(ModuleType.values());

		// Add ChoiceBox to grid
		grid.add(new Label("Module Type:"), 0, 1);
		grid.add(type, 1, 1);

		// Enable/Disable OK Button depending on whether a config name a module type are entered
		Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
		okButton.disableProperty().bind(Bindings.isEmpty(configName.textProperty())
		                                        .or(type.getSelectionModel().selectedItemProperty().isNull()));

		dialog.getDialogPane().setContent(grid);

		// Request focus on the config TextField by default
		Platform.runLater(configName::requestFocus);

		// Create a new ConfigBase when OK button is clicked
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return new ConfigBase(configName.getText(), type.getValue());
			}
			return null;
		});

		return dialog.showAndWait();
	}
}

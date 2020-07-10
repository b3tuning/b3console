/*
 *  Created on:  Jun 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.filemanager;

import com.b3tuning.b3console.App;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ModuleType;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.service.module.trans.config.TransConfig;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.XSlf4j;

import java.util.Optional;

import static com.b3tuning.b3console.App.DEFAULT_CSS;

@XSlf4j
public class NewConfigDialog {

	public static final double VBOX_PADDING = 10;

	public Optional<ConfigBase> createNewConfigDialog() {
		log.entry();

		// Custom dialog to for new config
		Dialog<ConfigBase> dialog = new Dialog<>();
		dialog.setTitle("New Configuration");
		dialog.setHeaderText("Select a Module type to configure");
		dialog.getDialogPane().getStylesheets().add(App.class.getResource(DEFAULT_CSS).toExternalForm());
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		// Create the custom grid
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(VBOX_PADDING, VBOX_PADDING, VBOX_PADDING, VBOX_PADDING));

		// Create the module type choice box
		ChoiceBox<ModuleType> type = new ChoiceBox<>();
		type.getItems().setAll(ModuleType.values());

		// Add ChoiceBox to grid
		vBox.getChildren().add(new Label("Module Type:"));
		vBox.getChildren().add(type);

		// Enable/Disable OK Button depending on whether a config name a module type are entered
		Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
		okButton.disableProperty().bind(type.getSelectionModel().selectedItemProperty().isNull());

		dialog.getDialogPane().setContent(vBox);

		// Request focus on the config choiceBox by default
		Platform.runLater(type::requestFocus);

		// Create a new ConfigBase when OK button is clicked
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return switch (type.getValue()) {
					case DOOR -> new DoorConfig();
					case SHIFTER -> new ShifterConfig();
					case TRANS -> new TransConfig();
				};
			}
			return null;
		});
		return dialog.showAndWait();
	}
}

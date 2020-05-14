package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction.DOOR;
import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction.SHIFTER;
import static com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleAction.TRANS;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class ConfigMenuView extends BaseView<ConfigMenuViewModel> {

	@FXML private AnchorPane doorPlaceholder;
	@FXML private AnchorPane shifterPlaceholder;
	@FXML private AnchorPane transPlaceholder;

	@FXML private Button open;
	@FXML private Button create;
	@FXML private Button cancel;
	@FXML private Button saveToFile;
	@FXML private Button sendToModule;
	@FXML private Button getFromModule;

	@InjectViewModel private ConfigMenuViewModel viewModel;

	@Inject
	public ConfigMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		doorPlaceholder.setVisible(viewModel.getModule().get().equals(DOOR));
		doorPlaceholder.managedProperty().bind(doorPlaceholder.visibleProperty());
		doorPlaceholder.disableProperty().bind(doorPlaceholder.visibleProperty().not());

		shifterPlaceholder.setVisible(viewModel.getModule().get().equals(SHIFTER));
		shifterPlaceholder.managedProperty().bind(shifterPlaceholder.visibleProperty());
		shifterPlaceholder.disableProperty().bind(shifterPlaceholder.visibleProperty().not());

		transPlaceholder.setVisible(viewModel.getModule().get().equals(TRANS));
		transPlaceholder.managedProperty().bind(transPlaceholder.visibleProperty());
		transPlaceholder.disableProperty().bind(transPlaceholder.visibleProperty().not());

	}
}

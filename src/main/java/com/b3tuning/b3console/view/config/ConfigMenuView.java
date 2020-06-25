package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import com.b3tuning.b3console.view.root.FontAwesome;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static org.reactfx.EventStreams.eventsOf;

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

	@FXML private StackPane  centerPane;

	@FXML private ToggleGroup  moduleGroup;
	@FXML private ToggleButton doorButton;
	@FXML private ToggleButton shifterButton;
	@FXML private ToggleButton transButton;

	@FXML private Button createButton;
	@FXML private Button loadFromFile;
	@FXML private Button saveToFile;
	@FXML private Button getFromModule;
	@FXML private Button sendToModule;

	@InjectViewModel private ConfigMenuViewModel viewModel;

	private final NotificationCenter globalNotifications;

	@Inject
	public ConfigMenuView(NotificationCenter globalNotifications) {
		log.entry();
		this.globalNotifications = globalNotifications;
	}

	public void initialize() {
		log.entry();

		manage(eventsOf(doorButton, ActionEvent.ACTION).subscribe(e -> viewModel.onDoorClicked()));

		manage(eventsOf(shifterButton, ActionEvent.ACTION).subscribe(e -> viewModel.onShifterClicked()));

		manage(eventsOf(transButton, ActionEvent.ACTION).subscribe(e -> viewModel.onTransClicked()));

		createButton.setText(FontAwesome.PLUS);
		createButton.visibleProperty().bind(moduleGroup.selectedToggleProperty().isNotNull());
		manage(eventsOf(createButton, MouseEvent.MOUSE_CLICKED).subscribe(e -> viewModel.onCreateClicked()));

		loadFromFile.setText(FontAwesome.LOAD_FILE);
		loadFromFile.visibleProperty().bind(moduleGroup.selectedToggleProperty().isNotNull());
		manage(eventsOf(loadFromFile, MouseEvent.MOUSE_CLICKED).subscribe(e -> viewModel.loadConfigFromFile()));

		saveToFile.setText(FontAwesome.SAVE);
		saveToFile.visibleProperty().bind(moduleGroup.selectedToggleProperty().isNotNull());
		manage(eventsOf(saveToFile, MouseEvent.MOUSE_CLICKED).subscribe(e -> viewModel.saveConfigToFile()));

		getFromModule.setText(FontAwesome.GET_FROM_MODULE);
		getFromModule.visibleProperty().bind(moduleGroup.selectedToggleProperty().isNotNull());
		manage(eventsOf(getFromModule, MouseEvent.MOUSE_CLICKED).subscribe(e -> viewModel.getConfigFromModule()));

		sendToModule.setText(FontAwesome.SEND_TO_MODULE);
		sendToModule.visibleProperty().bind(moduleGroup.selectedToggleProperty().isNotNull());
		manage(eventsOf(sendToModule, MouseEvent.MOUSE_CLICKED).subscribe(e -> viewModel.sendConfigToModule()));

		viewModel.setChildViewPane(centerPane);

		initNotifications();

	}

	private void initNotifications() {
		log.entry();

		globalNotifications.subscribe(ClickButtonNotification.class.getName(), (key, payload) -> {
			ClickButtonNotification n = ((ClickButtonNotification) payload[0]);
			log.entry(n.getButtonId());
			if (n.getButtonId() != null) {
				moduleGroup.selectToggle(null);
				ToggleButton btn = null;
				if ("doorButton".equals(n.getButtonId())) {
					btn = doorButton;
				} else if ("shifterButton".equals(n.getButtonId())) {
					btn = shifterButton;
				} else if ("transButton".equals(n.getButtonId())) {
					btn = transButton;
				}
				assert btn != null;
				btn.fire();
				if (!btn.isSelected()) {
					moduleGroup.selectToggle(btn);
				}
				centerPane.requestFocus();
			}
		});
	}
}

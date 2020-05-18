package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.config.ConfigMenuViewModel.MenuAction;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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

	@FXML private AnchorPane menuPane;
	@FXML private StackPane  centerPane;

	@FXML private ToggleGroup  moduleGroup;
	@FXML private ToggleButton doorButton;
	@FXML private ToggleButton shifterButton;
	@FXML private ToggleButton transButton;

//	@FXML private Button open;
//	@FXML private Button create;
//	@FXML private Button cancel;
//	@FXML private Button saveToFile;
//	@FXML private Button sendToModule;
//	@FXML private Button getFromModule;

	@InjectViewModel private ConfigMenuViewModel viewModel;

	private NotificationCenter globalNotifications;

	@Inject
	public ConfigMenuView(NotificationCenter globalNotifications) {
		log.entry();
		this.globalNotifications = globalNotifications;
	}

	public void initialize() {
		log.entry();

		doorButton.setUserData(MenuAction.DOOR);
		manage(eventsOf(doorButton, ActionEvent.ACTION).subscribe(e -> viewModel.onDoorClicked()));

		shifterButton.setUserData(MenuAction.SHIFTER);
		manage(eventsOf(shifterButton, ActionEvent.ACTION).subscribe(e -> viewModel.onShifterClicked()));

		transButton.setUserData(MenuAction.TRANS);
		manage(eventsOf(transButton, ActionEvent.ACTION).subscribe(e -> viewModel.onTransClicked()));

		viewModel.setChildViewPane(centerPane);

		initNotifications();

	}

	private void initNotifications() {
		log.entry();

		globalNotifications.subscribe(ClickButtonNotification.class.getName(), (key, payload) -> {
			ClickButtonNotification n = ((ClickButtonNotification) payload[0]);
			log.entry(n.getMainMenuItemAction(), n.getButtonId());
			if (MainMenuItemAction.CONFIG.equals(n.getMainMenuItemAction()) && n.getButtonId() != null) {
				moduleGroup.selectToggle(null);
				ToggleButton btn = null;
				if ("doorButton".equals(n.getButtonId())) {
					btn = doorButton;
				} else if ("shifterButton".equals(n.getButtonId())) {
					btn = shifterButton;
				} else if ("transButton".equals(n.getButtonId())) {
					btn = transButton;
				}
				btn.fire();
				if (!btn.isSelected()) {
					moduleGroup.selectToggle(btn);
				}
				centerPane.requestFocus();
			}
		});
	}
}

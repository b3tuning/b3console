/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  OnlineView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.online;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class OnlineMenuView extends BaseView<OnlineMenuViewModel> {

	@FXML private Menu onlineMenu;

	private final NotificationCenter globalNotifications;

	@InjectViewModel private OnlineMenuViewModel viewModel;

	@Inject
	public OnlineMenuView(NotificationCenter notifications) {
		log.entry();
		this.globalNotifications = notifications;
	}

	public void initialize() {
		log.entry();

	}

	@FXML
	private void connectAction(ActionEvent event) {
		log.entry();
	}

	@FXML
	private void disconnectAction(ActionEvent event) {
		log.entry();
	}

	@FXML
	private void monitorAction(ActionEvent event) {
		log.entry();
	}
}

package com.b3tuning.b3console.view.root;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class RootView extends BaseView<RootViewModel> {

	@FXML StackPane centerPane;
	@FXML SplitPane centerSplitPane;

	@InjectViewModel private RootViewModel viewModel;

	@SuppressWarnings("unused")
	private final NotificationCenter globalNotifications;

	@Inject
	public RootView(NotificationCenter notificationCenter) {
		log.entry();
		this.globalNotifications = notificationCenter;
	}

	public void initialize() {
		log.entry();

		viewModel.setChildViewPane(centerPane);

		initializeNotifications();

	}

	private void initializeNotifications() {

		// child views/viewModels may request that a menu item is pressed (e.g. after cancelling navigation due to having a dirty page)
		globalNotifications.subscribe(ClickButtonNotification.class.getName(), (key, payload) -> {
			ClickButtonNotification n = ((ClickButtonNotification) payload[0]);
			if (n.getButtonId() != null) {
				log.entry(n.getButtonId());
			}
		});
	}
}

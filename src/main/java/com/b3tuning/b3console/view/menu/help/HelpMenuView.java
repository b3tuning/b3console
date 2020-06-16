/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  HelpView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.help;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class HelpMenuView extends BaseView<HelpMenuViewModel> {

	@FXML private Menu helpMenu;

	private final NotificationCenter globalNotifications;

	@InjectViewModel private HelpMenuViewModel viewModel;

	@Inject
	public HelpMenuView(NotificationCenter notifications) {
		log.entry();
		this.globalNotifications = notifications;
	}

	public void initialize() {
		log.entry();

	}

}

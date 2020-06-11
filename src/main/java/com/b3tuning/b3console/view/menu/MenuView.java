/*
 *  Created on:  Jun 10, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MenuView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.b3tuning.b3console.view.root.RootViewModel.CONFIG_LOADED;

@XSlf4j
public class MenuView extends BaseView<MenuViewModel> {

	@InjectViewModel private MenuViewModel viewModel;

	private final NotificationCenter globalNotifications;

	@Inject
	public MenuView(NotificationCenter globalNotifications) {
		log.entry();

		this.globalNotifications = globalNotifications;
	}

	public void initialize() {
		log.entry();

	}
}

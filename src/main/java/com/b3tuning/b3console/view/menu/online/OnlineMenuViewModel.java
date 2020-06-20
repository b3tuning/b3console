/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  OnlineViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.online;

import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class OnlineMenuViewModel extends BaseViewModel {

	private final NotificationCenter globalNotifications;

	@Inject
	public OnlineMenuViewModel(NotificationCenter notifications) {
		log.entry();
		this.globalNotifications = notifications;
	}

	void connectAction() {
		log.entry();
	}

	void disconnectAction() {
		log.entry();
	}

	void monitorAction() {
		log.entry();
	}
}

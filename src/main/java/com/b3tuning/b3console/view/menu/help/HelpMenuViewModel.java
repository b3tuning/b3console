/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  HelpViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.help;

import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class HelpMenuViewModel extends BaseViewModel {

	private final NotificationCenter globalNotifications;

	@Inject
	public HelpMenuViewModel(NotificationCenter notifications) {
		log.entry();
		this.globalNotifications = notifications;
	}

}

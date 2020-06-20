package com.b3tuning.b3console.view.notifications;

import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import lombok.Data;
import lombok.extern.slf4j.XSlf4j;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
@Data
public class ClickButtonNotification {

	private String buttonId;

	public static void fire(NotificationCenter globalNotifications) {
		fire(globalNotifications, null);
	}

	public static void fire(NotificationCenter globalNotifications, String buttonId) {
		log.entry(buttonId);
		ClickButtonNotification notification = new ClickButtonNotification();
		notification.setButtonId(buttonId);
		globalNotifications.publish(ClickButtonNotification.class.getName(), notification);
	}
}

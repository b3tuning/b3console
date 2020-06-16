/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  FileMenuViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.file;

import com.b3tuning.b3console.service.files.filemanager.FileManager;
import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class FileMenuViewModel extends BaseViewModel {

	private final NotificationCenter globalNotifications;
	private final FileManager fileManager;

	@Inject
	public FileMenuViewModel(NotificationCenter notificationCenter, FileManager manager) {
		log.entry();
		this.globalNotifications = notificationCenter;
		this.fileManager = manager;
	}
}

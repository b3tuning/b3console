/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  EditViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.edit;

import com.b3tuning.b3console.service.edit.EditManager;
import com.b3tuning.b3console.view.BaseViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class EditMenuViewModel extends BaseViewModel {

	private final NotificationCenter globalNotifications;
	private final EditManager        editManager;

	private BooleanProperty hasUndo = new SimpleBooleanProperty();
	private BooleanProperty hasRedo = new SimpleBooleanProperty();

	@Inject
	public EditMenuViewModel(NotificationCenter notifications, EditManager editManager) {
		log.entry();
		this.globalNotifications = notifications;
		this.editManager         = editManager;


	}

	public BooleanProperty getHasUndo() {
		return hasUndo;
	}

	public BooleanProperty getHasRedo() {
		return hasRedo;
	}
}

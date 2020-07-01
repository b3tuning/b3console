package com.b3tuning.b3console.view.config.trans;

import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.EditableViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.utils.AlertUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class TransConfigViewModel extends BaseViewModel implements EditableViewModel, Refreshable {

	private final BooleanProperty dirty = new SimpleBooleanProperty(false);

	@Inject
	public TransConfigViewModel() {
		log.entry();
	}

	@Override
	public void refresh() {
		log.entry();
		if (dirty.get()) {
			AlertUtils.warn(saveChangesMessage());
//		} else {
////			config.set(moduleService.getTransConfig());
//			originalConfig = getTransConfigFromBase().clone();
//			getTransConfigFromBase().resetTrackingChanges();
//			dirty.set(false);
		}
	}

	@Override public BooleanProperty dirtyProperty() {
		return null;
	}

	@Override public boolean isDirty() {
		return false;
	}

	@Override public String saveChangesMessage() {
		return null;
	}

	@Override public void navigationCancelledAction() {

	}
}

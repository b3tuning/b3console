package com.b3tuning.b3console.view;

import javafx.beans.property.BooleanProperty;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public interface EditableViewModel {

	/**
	 * Indicates if the viewmodel is dirty
	 *
	 * @return
	 */
	BooleanProperty dirtyProperty();

	boolean isDirty();

	/**
	 * Message to be displayed if changes are required
	 *
	 * @return
	 */
	String saveChangesMessage();

	/**
	 * Action to be taken if navigation away from this view was cancelled due to an event such as
	 * dirty
	 */
	void navigationCancelledAction();

}

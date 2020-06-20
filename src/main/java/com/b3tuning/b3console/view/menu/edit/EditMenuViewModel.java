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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class EditMenuViewModel extends BaseViewModel {

	private final EditManager editManager;

	private final BooleanProperty hasUndo          = new SimpleBooleanProperty();
	private final BooleanProperty hasRedo          = new SimpleBooleanProperty();
	private final BooleanProperty selected         = new SimpleBooleanProperty();
	private final BooleanProperty clipBoardContent = new SimpleBooleanProperty();

	@Inject
	public EditMenuViewModel(EditManager editManager) {
		log.entry();
		this.editManager = editManager;
	}

	public BooleanProperty getHasUndo() {
		return hasUndo;
	}

	public BooleanProperty getHasRedo() {
		return hasRedo;
	}

	public BooleanProperty isSelected() {
		return selected;
	}

	public BooleanProperty hasClipBoardContent() {
		return clipBoardContent;
	}

	void undoAction() {
		log.entry();
		editManager.undoAction();
	}

	void redoAction() {
		log.entry();
		editManager.redoAction();
	}

	void cutAction() {
		log.entry();
		editManager.cutAction();
	}

	void copyAction() {
		log.entry();
		editManager.copyAction();
	}

	void pasteAction() {
		log.entry();
		editManager.pasteAction();
	}

	void deleteAction() {
		log.entry();
		editManager.deleteAction();
	}
}

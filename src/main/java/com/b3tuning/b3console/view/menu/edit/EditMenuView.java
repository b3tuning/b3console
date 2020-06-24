/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  EditView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.edit;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class EditMenuView extends BaseView<EditMenuViewModel> {

	@FXML private MenuItem undoMenuItem;
	@FXML private MenuItem redoMenuItem;
	@FXML private MenuItem cutMenuItem;
	@FXML private MenuItem copyMenuItem;
	@FXML private MenuItem pasteMenuItem;
	@FXML private MenuItem deleteMenuItem;

	@InjectViewModel private EditMenuViewModel viewModel;

	@Inject
	public EditMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		undoMenuItem.disableProperty().bind(viewModel.getHasUndo().not());
		redoMenuItem.disableProperty().bind(viewModel.getHasRedo().not());
		cutMenuItem.disableProperty().bind(viewModel.isSelected().not());
		copyMenuItem.disableProperty().bind(cutMenuItem.disableProperty());
		pasteMenuItem.disableProperty().bind(viewModel.hasClipBoardContent().not());
		deleteMenuItem.disableProperty().bind(cutMenuItem.disableProperty());
	}

	@FXML
	private void undoAction(ActionEvent event) {
		log.entry(event);
		viewModel.undoAction();
	}

	@FXML
	private void redoAction(ActionEvent event) {
		log.entry(event);
		viewModel.redoAction();
	}

	@FXML
	private void cutAction(ActionEvent event) {
		log.entry(event);
		viewModel.cutAction();
	}

	@FXML
	private void copyAction(ActionEvent event) {
		log.entry(event);
		viewModel.copyAction();
	}

	@FXML
	private void pasteAction(ActionEvent event) {
		log.entry(event);
		viewModel.pasteAction();
	}

	@FXML
	private void deleteAction(ActionEvent event) {
		log.entry(event);
		viewModel.deleteAction();
	}
}

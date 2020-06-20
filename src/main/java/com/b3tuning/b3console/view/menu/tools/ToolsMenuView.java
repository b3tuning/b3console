/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ToolsView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.tools;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class ToolsMenuView extends BaseView<ToolsMenuViewModel> {

	@InjectViewModel private ToolsMenuViewModel viewModel;

	@Inject
	public ToolsMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

	}

	@FXML
	private void optionsAction(ActionEvent event) {
		log.entry(event);
		viewModel.optionsAction();
	}
}

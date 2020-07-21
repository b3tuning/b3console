package com.b3tuning.b3console.view.config.trans;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.config.CanBusConfigView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class TransConfigView extends BaseView<TransConfigViewModel> {

	@FXML private AnchorPane       canBusConfig;
	@FXML private CanBusConfigView canBusConfigController;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private TransConfigViewModel viewModel;

	@Inject
	public TransConfigView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();
			c.canBusProperty().bindBidirectional(canBusConfigController.getViewModel().configProperty());
		}));
		initializeValidation();
	}

	private void initializeValidation() {
		log.entry();
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

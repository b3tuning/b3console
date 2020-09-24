package com.b3tuning.b3console.view.config.trans;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.config.CanBusConfigView;
import com.b3tuning.b3console.view.utils.buttonInputField.IntegerButtonInputField;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

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

	@FXML private IntegerButtonInputField encMax;
	@FXML private IntegerButtonInputField encMin;

	@FXML private IntegerButtonInputField maxCurrent;

	@FXML private IntegerButtonInputField aggKp;
	@FXML private IntegerButtonInputField aggKi;
	@FXML private IntegerButtonInputField aggKd;

	@FXML private IntegerButtonInputField consKp;
	@FXML private IntegerButtonInputField consKi;
	@FXML private IntegerButtonInputField consKd;

	@SuppressWarnings("unused")
	@FXML
	private AnchorPane canBusConfig;

	@SuppressWarnings("unused")
	@FXML
	private CanBusConfigView canBusConfigController;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private TransConfigViewModel viewModel;

	@Inject
	public TransConfigView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		DoubleStringConverter doubleToString = new DoubleStringConverter();

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();
			canBusConfigController.getViewModel().configProperty().set(c.getCanBus());
			c.canBusProperty().bindBidirectional(canBusConfigController.getViewModel().configProperty());

			encMax.textProperty().bindBidirectional(c.ems22AProperty().get().encMaxProperty(), intToString);
			encMin.textProperty().bindBidirectional(c.ems22AProperty().get().encMinProperty(), intToString);

			maxCurrent.textProperty().bindBidirectional(c.vnh5019Property().get().maxCurrentProperty(), intToString);

			aggKp.textProperty().bindBidirectional(c.pidProperty().get().aggKpProperty(), doubleToString);
			aggKi.textProperty().bindBidirectional(c.pidProperty().get().aggKiProperty(), doubleToString);
			aggKd.textProperty().bindBidirectional(c.pidProperty().get().aggKdProperty(), doubleToString);

			consKp.textProperty().bindBidirectional(c.pidProperty().get().consKpProperty(), doubleToString);
			consKi.textProperty().bindBidirectional(c.pidProperty().get().consKiProperty(), doubleToString);
			consKd.textProperty().bindBidirectional(c.pidProperty().get().consKdProperty(), doubleToString);
		}));
		initializeValidation();
	}

	private void initializeValidation() {
		log.entry();

		visualizer.setDecoration(new StyleClassValidationDecoration());

		visualizer.initVisualization(viewModel.encMaxValidation(), encMax.getTextField(), true);
		visualizer.initVisualization(viewModel.encMinValidation(), encMin.getTextField(), true);

		visualizer.initVisualization(viewModel.currentMaxValidation(), maxCurrent.getTextField(), true);

		visualizer.initVisualization(viewModel.aggKpValidation(), aggKp.getTextField(), true);
		visualizer.initVisualization(viewModel.aggKiValidation(), aggKi.getTextField(), true);
		visualizer.initVisualization(viewModel.aggKdValidation(), aggKd.getTextField(), true);

		visualizer.initVisualization(viewModel.consKpValidation(), consKp.getTextField(), true);
		visualizer.initVisualization(viewModel.consKiValidation(), consKi.getTextField(), true);
		visualizer.initVisualization(viewModel.consKdValidation(), consKd.getTextField(), true);
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

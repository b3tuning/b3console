/*
 *  Created on:  Jul 20, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  CanBusConfigView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import com.b3tuning.b3console.view.utils.buttonInputField.ButtonInputField;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import javax.inject.Inject;

import static org.reactfx.EventStreams.nonNullValuesOf;

@XSlf4j
public class CanBusConfigView extends BaseView<CanBusConfigViewModel> {

	@FXML private ButtonInputField addrTX;
	@FXML private ButtonInputField intvlTX;
	@FXML private ButtonInputField bootTX;
	@FXML private ButtonInputField bootRX;
	@FXML private ButtonInputField mask0;
	@FXML private ButtonInputField filter0_0;
	@FXML private ButtonInputField filter0_1;
	@FXML private ButtonInputField mask1;
	@FXML private ButtonInputField filter1_0;
	@FXML private ButtonInputField filter1_1;
	@FXML private ButtonInputField filter1_2;
	@FXML private ButtonInputField filter1_3;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private CanBusConfigViewModel viewModel;

	@Inject
	public CanBusConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		addrTX.setFormatter(new IntegerTextFormatter());
		intvlTX.setFormatter(new IntegerTextFormatter());
		bootTX.setFormatter(new IntegerTextFormatter());
		bootRX.setFormatter(new IntegerTextFormatter());
		mask0.setFormatter(new IntegerTextFormatter());
		filter0_0.setFormatter(new IntegerTextFormatter());
		filter0_1.setFormatter(new IntegerTextFormatter());
		mask1.setFormatter(new IntegerTextFormatter());
		filter1_0.setFormatter(new IntegerTextFormatter());
		filter1_1.setFormatter(new IntegerTextFormatter());
		filter1_2.setFormatter(new IntegerTextFormatter());
		filter1_3.setFormatter(new IntegerTextFormatter());

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();

			addrTX.textProperty().bindBidirectional(c.addrTXProperty(), intToString);
			intvlTX.textProperty().bindBidirectional(c.intvlTXProperty(), intToString);
			bootTX.textProperty().bindBidirectional(c.bootTXProperty(), intToString);
			bootRX.textProperty().bindBidirectional(c.bootRXProperty(), intToString);
			mask0.textProperty().bindBidirectional(c.mask0Property(), intToString);
			filter0_0.textProperty().bindBidirectional(c.filter0_0Property(), intToString);
			filter0_1.textProperty().bindBidirectional(c.filter0_1Property(), intToString);
			mask1.textProperty().bindBidirectional(c.mask1Property(), intToString);
			filter1_0.textProperty().bindBidirectional(c.filter1_0Property(), intToString);
			filter1_1.textProperty().bindBidirectional(c.filter1_1Property(), intToString);
			filter1_2.textProperty().bindBidirectional(c.filter1_2Property(), intToString);
			filter1_3.textProperty().bindBidirectional(c.filter1_3Property(), intToString);
		}));

		initializeValidation();
	}

	private void initializeValidation() {
		log.entry();

		visualizer.setDecoration(new StyleClassValidationDecoration());
		visualizer.initVisualization(viewModel.addrTXValidation(), addrTX.getTextField(), true);
		visualizer.initVisualization(viewModel.bootRXValidation(), bootRX.getTextField(), true);
		visualizer.initVisualization(viewModel.bootTXValidation(), bootTX.getTextField(), true);
		visualizer.initVisualization(viewModel.intvlTXValidation(), intvlTX.getTextField(), true);
		visualizer.initVisualization(viewModel.mask0Validation(), mask0.getTextField(), true);
		visualizer.initVisualization(viewModel.filter00Validation(), filter0_0.getTextField(), true);
		visualizer.initVisualization(viewModel.filter01Validation(), filter0_1.getTextField(), true);
		visualizer.initVisualization(viewModel.mask1Validation(), mask1.getTextField(), true);
		visualizer.initVisualization(viewModel.filter10Validation(), filter1_0.getTextField(), true);
		visualizer.initVisualization(viewModel.filter11Validation(), filter1_1.getTextField(), true);
		visualizer.initVisualization(viewModel.filter12Validation(), filter1_2.getTextField(), true);
		visualizer.initVisualization(viewModel.filter13Validation(), filter1_3.getTextField(), true);
	}

	public CanBusConfigViewModel getViewModel() {
		return viewModel;
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

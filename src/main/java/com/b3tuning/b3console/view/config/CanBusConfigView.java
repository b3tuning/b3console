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
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import javax.inject.Inject;

import static org.reactfx.EventStreams.nonNullValuesOf;

@XSlf4j
public class CanBusConfigView extends BaseView<CanBusConfigViewModel> {

	@FXML private Spinner<Integer> addrTX;
	@FXML private Spinner<Integer> intvlTX;
	@FXML private Spinner<Integer> bootTX;
	@FXML private Spinner<Integer> bootRX;
	@FXML private Spinner<Integer> mask0;
	@FXML private Spinner<Integer> filter0_0;
	@FXML private Spinner<Integer> filter0_1;
	@FXML private Spinner<Integer> mask1;
	@FXML private Spinner<Integer> filter1_0;
	@FXML private Spinner<Integer> filter1_1;
	@FXML private Spinner<Integer> filter1_2;
	@FXML private Spinner<Integer> filter1_3;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private CanBusConfigViewModel viewModel;

	@Inject
	public CanBusConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		initSpinner(addrTX);
		initSpinner(intvlTX);
		initSpinner(bootTX);
		initSpinner(bootRX);
		initSpinner(mask0);
		initSpinner(filter0_0);
		initSpinner(filter0_1);
		initSpinner(mask1);
		initSpinner(filter1_0);
		initSpinner(filter1_1);
		initSpinner(filter1_2);
		initSpinner(filter1_3);

		setFormatter(addrTX.getEditor());
		setFormatter(intvlTX.getEditor());
		setFormatter(bootTX.getEditor());
		setFormatter(bootRX.getEditor());
		setFormatter(mask0.getEditor());
		setFormatter(filter0_0.getEditor());
		setFormatter(filter0_1.getEditor());
		setFormatter(mask1.getEditor());
		setFormatter(filter1_0.getEditor());
		setFormatter(filter1_1.getEditor());
		setFormatter(filter1_2.getEditor());
		setFormatter(filter1_3.getEditor());

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();

			addrTX.getEditor().textProperty().bindBidirectional(c.addrTXProperty(), intToString);
			intvlTX.getEditor().textProperty().bindBidirectional(c.intvlTXProperty(), intToString);
			bootTX.getEditor().textProperty().bindBidirectional(c.bootTXProperty(), intToString);
			bootRX.getEditor().textProperty().bindBidirectional(c.bootRXProperty(), intToString);
			mask0.getEditor().textProperty().bindBidirectional(c.mask0Property(), intToString);
			filter0_0.getEditor().textProperty().bindBidirectional(c.filter0_0Property(), intToString);
			filter0_1.getEditor().textProperty().bindBidirectional(c.filter0_1Property(), intToString);
			mask1.getEditor().textProperty().bindBidirectional(c.mask1Property(), intToString);
			filter1_0.getEditor().textProperty().bindBidirectional(c.filter1_0Property(), intToString);
			filter1_1.getEditor().textProperty().bindBidirectional(c.filter1_1Property(), intToString);
			filter1_2.getEditor().textProperty().bindBidirectional(c.filter1_2Property(), intToString);
			filter1_3.getEditor().textProperty().bindBidirectional(c.filter1_3Property(), intToString);
		}));

		initializeValidation();
	}

	private void setFormatter(TextField textField) {
		textField.setTextFormatter(new IntegerTextFormatter());
	}

	private void initSpinner(Spinner<Integer> spinner) {
		// TODO: fix this fox HEX
		spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1023));
		spinner.getEditor().setAlignment(Pos.BASELINE_RIGHT);
	}

	private void initializeValidation() {
		log.entry();

		visualizer.setDecoration(new StyleClassValidationDecoration());
		visualizer.initVisualization(viewModel.addrTXValidation(), addrTX, true);
		visualizer.initVisualization(viewModel.bootRXValidation(), bootRX, true);
		visualizer.initVisualization(viewModel.bootTXValidation(), bootTX, true);
		visualizer.initVisualization(viewModel.intvlTXValidation(), intvlTX, true);
		visualizer.initVisualization(viewModel.mask0Validation(), mask0, true);
		visualizer.initVisualization(viewModel.filter00Validation(), filter0_0, true);
		visualizer.initVisualization(viewModel.filter01Validation(), filter0_1, true);
		visualizer.initVisualization(viewModel.mask1Validation(), mask1, true);
		visualizer.initVisualization(viewModel.filter10Validation(), filter1_0, true);
		visualizer.initVisualization(viewModel.filter11Validation(), filter1_1, true);
		visualizer.initVisualization(viewModel.filter12Validation(), filter1_2, true);
		visualizer.initVisualization(viewModel.filter13Validation(), filter1_3, true);
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

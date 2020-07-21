package com.b3tuning.b3console.view.config.shifter;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.config.CanBusConfigView;
import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
public class ShifterConfigView extends BaseView<ShifterConfigViewModel> {

	@FXML private Spinner<Integer> brightness;
	@FXML private Spinner<Integer> color;
	@FXML private Spinner<Integer> delay;

	@FXML private Spinner<Integer> x1Park;
	@FXML private Spinner<Integer> y1Park;
	@FXML private Spinner<Integer> x2Park;
	@FXML private Spinner<Integer> y2Park;

	@FXML private Spinner<Integer> x1Reverse;
	@FXML private Spinner<Integer> y1Reverse;
	@FXML private Spinner<Integer> x2Reverse;
	@FXML private Spinner<Integer> y2Reverse;

	@FXML private Spinner<Integer> x1Neutral;
	@FXML private Spinner<Integer> y1Neutral;
	@FXML private Spinner<Integer> x2Neutral;
	@FXML private Spinner<Integer> y2Neutral;

	@FXML private Spinner<Integer> x1Drive;
	@FXML private Spinner<Integer> y1Drive;
	@FXML private Spinner<Integer> x2Drive;
	@FXML private Spinner<Integer> y2Drive;

	@FXML private Spinner<Integer> x1Manual;
	@FXML private Spinner<Integer> y1Manual;
	@FXML private Spinner<Integer> x2Manual;
	@FXML private Spinner<Integer> y2Manual;

	@FXML private Spinner<Integer> x1Up;
	@FXML private Spinner<Integer> y1Up;
	@FXML private Spinner<Integer> x2Up;
	@FXML private Spinner<Integer> y2Up;

	@FXML private Spinner<Integer> x1Down;
	@FXML private Spinner<Integer> y1Down;
	@FXML private Spinner<Integer> x2Down;
	@FXML private Spinner<Integer> y2Down;

	@SuppressWarnings("unused")
	@FXML
	private AnchorPane canBusConfig;

	@SuppressWarnings("unused")
	@FXML
	private CanBusConfigView canBusConfigController;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private ShifterConfigViewModel viewModel;

	@Inject
	public ShifterConfigView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		initSpinner(brightness);
		initSpinner(color);
		initSpinner(delay);

		setFormatter(brightness.getEditor());
		setFormatter(color.getEditor());
		setFormatter(delay.getEditor());

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();
			c.canBusProperty().bindBidirectional(canBusConfigController.getViewModel().configProperty());

			brightness.getEditor().textProperty()
			          .bindBidirectional(c.indicatorProperty().get().brightnessProperty(), intToString);
			color.getEditor().textProperty()
			     .bindBidirectional(c.indicatorProperty().get().colorProperty(), intToString);
			delay.getEditor().textProperty().bindBidirectional(c.indicatorProperty().get().waitProperty(), intToString);
		}));

		initializeValidation();
	}

	private void setFormatter(TextField textField) {
		textField.setTextFormatter(new IntegerTextFormatter());
	}

	private void initSpinner(Spinner<Integer> spinner) {
		spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1023));
		spinner.getEditor().setAlignment(Pos.BASELINE_RIGHT);
	}

	private void initializeValidation() {
		log.entry();

		visualizer.setDecoration(new StyleClassValidationDecoration());

		visualizer.initVisualization(viewModel.brightnessValidation(), brightness, true);
		visualizer.initVisualization(viewModel.colorValidation(), color, true);
		visualizer.initVisualization(viewModel.delayValidation(), delay, true);
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

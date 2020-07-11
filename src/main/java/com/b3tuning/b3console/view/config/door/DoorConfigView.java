package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
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
public class DoorConfigView extends BaseView<DoorConfigViewModel> {

	@FXML private ScrollPane       scrollPane;
	@FXML private Spinner<Integer> mirrorSelectDriverMax;
	@FXML private Spinner<Integer> mirrorSelectDriverMin;
	@FXML private Spinner<Integer> mirrorSelectFoldMax;
	@FXML private Spinner<Integer> mirrorSelectFoldMin;
	@FXML private Spinner<Integer> mirrorSelectPassengerMax;
	@FXML private Spinner<Integer> mirrorSelectPassengerMin;

	@FXML private Spinner<Integer> mirrorActionDownMax;
	@FXML private Spinner<Integer> mirrorActionDownMin;
	@FXML private Spinner<Integer> mirrorActionLeftMax;
	@FXML private Spinner<Integer> mirrorActionLeftMin;
	@FXML private Spinner<Integer> mirrorActionRightMax;
	@FXML private Spinner<Integer> mirrorActionRightMin;
	@FXML private Spinner<Integer> mirrorActionUpMax;
	@FXML private Spinner<Integer> mirrorActionUpMin;

	@FXML private Spinner<Integer> windowActionAutoDownMax;
	@FXML private Spinner<Integer> windowActionAutoDownMin;
	@FXML private Spinner<Integer> windowActionAutoUpMax;
	@FXML private Spinner<Integer> windowActionAutoUpMin;
	@FXML private Spinner<Integer> windowActionDownMax;
	@FXML private Spinner<Integer> windowActionDownMin;
	@FXML private Spinner<Integer> windowActionUpMax;
	@FXML private Spinner<Integer> windowActionUpMin;

	@FXML private Spinner<Integer> driverWindowMaxCurrent;
	@FXML private Spinner<Integer> passengerWindowMaxCurrent;

	private final ControlsFxVisualizer validationVisualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		initSpinner(mirrorSelectDriverMax);
		initSpinner(mirrorSelectDriverMin);
		initSpinner(mirrorSelectFoldMax);
		initSpinner(mirrorSelectFoldMin);
		initSpinner(mirrorSelectPassengerMax);
		initSpinner(mirrorSelectPassengerMin);
		initSpinner(mirrorActionDownMax);
		initSpinner(mirrorActionDownMin);
		initSpinner(mirrorActionLeftMax);
		initSpinner(mirrorActionLeftMin);
		initSpinner(mirrorActionRightMax);
		initSpinner(mirrorActionRightMin);
		initSpinner(mirrorActionUpMax);
		initSpinner(mirrorActionUpMin);
		initSpinner(windowActionAutoDownMax);
		initSpinner(windowActionAutoDownMin);
		initSpinner(windowActionAutoUpMax);
		initSpinner(windowActionAutoUpMin);
		initSpinner(windowActionDownMax);
		initSpinner(windowActionDownMin);
		initSpinner(windowActionUpMax);
		initSpinner(windowActionUpMin);
		initSpinner(driverWindowMaxCurrent);
		initSpinner(passengerWindowMaxCurrent);

		setFormatter(mirrorSelectDriverMax.getEditor());
		setFormatter(mirrorSelectDriverMin.getEditor());
		setFormatter(mirrorSelectFoldMax.getEditor());
		setFormatter(mirrorSelectFoldMin.getEditor());
		setFormatter(mirrorSelectPassengerMax.getEditor());
		setFormatter(mirrorSelectPassengerMin.getEditor());
		setFormatter(mirrorActionDownMax.getEditor());
		setFormatter(mirrorActionDownMin.getEditor());
		setFormatter(mirrorActionLeftMax.getEditor());
		setFormatter(mirrorActionLeftMin.getEditor());
		setFormatter(mirrorActionRightMax.getEditor());
		setFormatter(mirrorActionRightMin.getEditor());
		setFormatter(mirrorActionUpMax.getEditor());
		setFormatter(mirrorActionUpMin.getEditor());
		setFormatter(windowActionAutoDownMax.getEditor());
		setFormatter(windowActionAutoDownMin.getEditor());
		setFormatter(windowActionAutoUpMax.getEditor());
		setFormatter(windowActionAutoUpMin.getEditor());
		setFormatter(windowActionDownMax.getEditor());
		setFormatter(windowActionDownMin.getEditor());
		setFormatter(windowActionUpMax.getEditor());
		setFormatter(windowActionUpMin.getEditor());
		setFormatter(driverWindowMaxCurrent.getEditor());
		setFormatter(passengerWindowMaxCurrent.getEditor());

		manage(nonNullValuesOf(viewModel.doorConfigProperty()).subscribe(c -> {
			log.entry();

			manage(nonNullValuesOf(c.mirrorSelectProperty()).subscribe(ms -> {
				mirrorSelectDriverMax.getEditor().textProperty().bindBidirectional(ms.driverMaxProperty(), intToString);
				mirrorSelectDriverMin.getEditor().textProperty().bindBidirectional(ms.driverMinProperty(), intToString);
				mirrorSelectFoldMax.getEditor().textProperty().bindBidirectional(ms.foldMaxProperty(), intToString);
				mirrorSelectFoldMin.getEditor().textProperty().bindBidirectional(ms.foldMinProperty(), intToString);
				mirrorSelectPassengerMax.getEditor().textProperty()
				                        .bindBidirectional(ms.passengerMaxProperty(), intToString);
				mirrorSelectPassengerMin.getEditor().textProperty()
				                        .bindBidirectional(ms.passengerMinProperty(), intToString);
			}));

			manage(nonNullValuesOf(c.mirrorActionProperty()).subscribe(ma -> {
				mirrorActionDownMax.getEditor().textProperty().bindBidirectional(ma.downMaxProperty(), intToString);
				mirrorActionDownMin.getEditor().textProperty().bindBidirectional(ma.downMinProperty(), intToString);
				mirrorActionLeftMax.getEditor().textProperty().bindBidirectional(ma.leftMaxProperty(), intToString);
				mirrorActionLeftMin.getEditor().textProperty().bindBidirectional(ma.leftMinProperty(), intToString);
				mirrorActionRightMax.getEditor().textProperty().bindBidirectional(ma.rightMaxProperty(), intToString);
				mirrorActionRightMin.getEditor().textProperty().bindBidirectional(ma.rightMinProperty(), intToString);
				mirrorActionUpMax.getEditor().textProperty().bindBidirectional(ma.upMaxProperty(), intToString);
				mirrorActionUpMin.getEditor().textProperty().bindBidirectional(ma.upMinProperty(), intToString);
			}));

			manage(nonNullValuesOf(c.windowActionProperty()).subscribe(wa -> {
				windowActionAutoDownMax.getEditor().textProperty()
				                       .bindBidirectional(wa.autoDownMaxProperty(), intToString);
				windowActionAutoDownMin.getEditor().textProperty()
				                       .bindBidirectional(wa.autoDownMinProperty(), intToString);
				windowActionAutoUpMax.getEditor().textProperty().bindBidirectional(wa.autoUpMaxProperty(), intToString);
				windowActionAutoUpMin.getEditor().textProperty().bindBidirectional(wa.autoUpMinProperty(), intToString);
				mirrorActionDownMax.getEditor().textProperty().bindBidirectional(wa.downMaxProperty(), intToString);
				windowActionDownMin.getEditor().textProperty().bindBidirectional(wa.downMinProperty(), intToString);
				windowActionUpMax.getEditor().textProperty().bindBidirectional(wa.upMaxProperty(), intToString);
				windowActionUpMin.getEditor().textProperty().bindBidirectional(wa.upMinProperty(), intToString);
			}));

			driverWindowMaxCurrent.getEditor().textProperty()
			                      .bindBidirectional(c.driverWindowMaxCurrentProperty(), intToString);

			passengerWindowMaxCurrent.getEditor().textProperty()
			                         .bindBidirectional(c.passengerWindowMaxCurrentProperty(), intToString);
		}));
		initializeValidation();
	}

	private void setFormatter(TextField textField) {
		textField.setTextFormatter(new IntegerTextFormatter());
	}

	private void initSpinner(Spinner<Integer> spinner) {
		spinner.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1023));
	}

	private void initializeValidation() {
		log.entry();
		validationVisualizer.setDecoration(new StyleClassValidationDecoration());
		validationVisualizer.initVisualization(viewModel.mirrorActionDownMaxValidation(), mirrorActionDownMax, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionDownMinValidation(), mirrorActionDownMin, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionLeftMaxValidation(), mirrorActionLeftMax, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionLeftMinValidation(), mirrorActionLeftMin, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionRightMaxValidation(), mirrorActionRightMax, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionRightMinValidation(), mirrorActionRightMin, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionUpMaxValidation(), mirrorActionUpMax, true);
		validationVisualizer.initVisualization(viewModel.mirrorActionUpMinValidation(), mirrorActionUpMin, true);
		validationVisualizer
				.initVisualization(viewModel.mirrorSelectDriverMaxValidation(), mirrorSelectDriverMax, true);
		validationVisualizer
				.initVisualization(viewModel.mirrorSelectDriverMinValidation(), mirrorSelectDriverMin, true);
		validationVisualizer.initVisualization(viewModel.mirrorSelectFoldMaxValidation(), mirrorSelectFoldMax, true);
		validationVisualizer.initVisualization(viewModel.mirrorSelectFoldMinValidation(), mirrorSelectFoldMin, true);
		validationVisualizer
				.initVisualization(viewModel.mirrorSelectPassengerMinValidation(), mirrorSelectPassengerMin, true);
		validationVisualizer
				.initVisualization(viewModel.mirrorSelectPassengerMaxValidation(), mirrorSelectPassengerMax, true);
		validationVisualizer
				.initVisualization(viewModel.windowActionAutoDownMaxValidation(), windowActionAutoDownMax, true);
		validationVisualizer
				.initVisualization(viewModel.windowActionAutoDownMinValidation(), windowActionAutoDownMin, true);
		validationVisualizer
				.initVisualization(viewModel.windowActionAutoUpMaxValidation(), windowActionAutoUpMax, true);
		validationVisualizer
				.initVisualization(viewModel.windowActionAutoUpMinValidation(), windowActionAutoUpMin, true);
		validationVisualizer.initVisualization(viewModel.windowActionDownMaxValidation(), windowActionDownMax, true);
		validationVisualizer.initVisualization(viewModel.windowActionDownMinValidation(), windowActionDownMin, true);
		validationVisualizer.initVisualization(viewModel.windowActionUpMaxValidation(), windowActionUpMax, true);
		validationVisualizer.initVisualization(viewModel.windowActionUpMinValidation(), windowActionUpMin, true);
		validationVisualizer
				.initVisualization(viewModel.driverWindowMaxCurrentValidation(), driverWindowMaxCurrent, true);
		validationVisualizer
				.initVisualization(viewModel.passengerWindowMaxCurrentValidation(), passengerWindowMaxCurrent, true);

	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

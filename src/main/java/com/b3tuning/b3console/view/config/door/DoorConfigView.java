package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.beans.property.DoubleProperty;
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

	@FXML private Spinner<Integer> msDriverMax;
	@FXML private Spinner<Integer> msDriverMin;
	@FXML private Spinner<Integer> msFoldMax;
	@FXML private Spinner<Integer> msFoldMin;
	@FXML private Spinner<Integer> msPassMax;
	@FXML private Spinner<Integer> msPassMin;

	@FXML private Spinner<Integer> maUpMax;
	@FXML private Spinner<Integer> maUpMin;
	@FXML private Spinner<Integer> maDownMax;
	@FXML private Spinner<Integer> maDownMin;
	@FXML private Spinner<Integer> maLeftMax;
	@FXML private Spinner<Integer> maLeftMin;
	@FXML private Spinner<Integer> maRightMax;
	@FXML private Spinner<Integer> maRightMin;

	@FXML private Spinner<Integer> waAutoUpMax;
	@FXML private Spinner<Integer> waAutoUpMin;
	@FXML private Spinner<Integer> waUpMax;
	@FXML private Spinner<Integer> waUpMin;
	@FXML private Spinner<Integer> waDownMax;
	@FXML private Spinner<Integer> waDownMin;
	@FXML private Spinner<Integer> waAutoDownMax;
	@FXML private Spinner<Integer> waAutoDownMin;

	@FXML private Spinner<Integer> dMaxCurrent;
	@FXML private Spinner<Integer> pMaxCurrent;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		initSpinner(msDriverMax);
		initSpinner(msDriverMin);
		initSpinner(msFoldMax);
		initSpinner(msFoldMin);
		initSpinner(msPassMax);
		initSpinner(msPassMin);

		initSpinner(maUpMax);
		initSpinner(maUpMin);
		initSpinner(maDownMax);
		initSpinner(maDownMin);
		initSpinner(maLeftMax);
		initSpinner(maLeftMin);
		initSpinner(maRightMax);
		initSpinner(maRightMin);

		initSpinner(waAutoUpMax);
		initSpinner(waAutoUpMin);
		initSpinner(waUpMax);
		initSpinner(waUpMin);
		initSpinner(waDownMax);
		initSpinner(waDownMin);
		initSpinner(waAutoDownMax);
		initSpinner(waAutoDownMin);

		initSpinner(dMaxCurrent);
		initSpinner(pMaxCurrent);

		setFormatter(msDriverMax.getEditor());
		setFormatter(msDriverMin.getEditor());
		setFormatter(msFoldMax.getEditor());
		setFormatter(msFoldMin.getEditor());
		setFormatter(msPassMax.getEditor());
		setFormatter(msPassMin.getEditor());

		setFormatter(maUpMax.getEditor());
		setFormatter(maUpMin.getEditor());
		setFormatter(maDownMax.getEditor());
		setFormatter(maDownMin.getEditor());
		setFormatter(maLeftMax.getEditor());
		setFormatter(maLeftMin.getEditor());
		setFormatter(maRightMax.getEditor());
		setFormatter(maRightMin.getEditor());

		setFormatter(waAutoUpMax.getEditor());
		setFormatter(waAutoUpMin.getEditor());
		setFormatter(waUpMax.getEditor());
		setFormatter(waUpMin.getEditor());
		setFormatter(waDownMax.getEditor());
		setFormatter(waDownMin.getEditor());
		setFormatter(waAutoDownMax.getEditor());
		setFormatter(waAutoDownMin.getEditor());

		setFormatter(dMaxCurrent.getEditor());
		setFormatter(pMaxCurrent.getEditor());

		manage(nonNullValuesOf(viewModel.doorConfigProperty()).subscribe(c -> {
			log.entry();

			manage(nonNullValuesOf(c.mirrorSelectProperty()).subscribe(ms -> {
				msDriverMax.getEditor().textProperty().bindBidirectional(ms.driverMaxProperty(), intToString);
				msDriverMin.getEditor().textProperty().bindBidirectional(ms.driverMinProperty(), intToString);
				msFoldMax.getEditor().textProperty().bindBidirectional(ms.foldMaxProperty(), intToString);
				msFoldMin.getEditor().textProperty().bindBidirectional(ms.foldMinProperty(), intToString);
				msPassMax.getEditor().textProperty().bindBidirectional(ms.passengerMaxProperty(), intToString);
				msPassMin.getEditor().textProperty().bindBidirectional(ms.passengerMinProperty(), intToString);
			}));

			manage(nonNullValuesOf(c.mirrorActionProperty()).subscribe(ma -> {
				maUpMax.getEditor().textProperty().bindBidirectional(ma.upMaxProperty(), intToString);
				maUpMin.getEditor().textProperty().bindBidirectional(ma.upMinProperty(), intToString);
				maDownMax.getEditor().textProperty().bindBidirectional(ma.downMaxProperty(), intToString);
				maDownMin.getEditor().textProperty().bindBidirectional(ma.downMinProperty(), intToString);
				maLeftMax.getEditor().textProperty().bindBidirectional(ma.leftMaxProperty(), intToString);
				maLeftMin.getEditor().textProperty().bindBidirectional(ma.leftMinProperty(), intToString);
				maRightMax.getEditor().textProperty().bindBidirectional(ma.rightMaxProperty(), intToString);
				maRightMin.getEditor().textProperty().bindBidirectional(ma.rightMinProperty(), intToString);
			}));

			manage(nonNullValuesOf(c.windowActionProperty()).subscribe(wa -> {
				waAutoUpMax.getEditor().textProperty().bindBidirectional(wa.autoUpMaxProperty(), intToString);
				waAutoUpMin.getEditor().textProperty().bindBidirectional(wa.autoUpMinProperty(), intToString);
				waUpMax.getEditor().textProperty().bindBidirectional(wa.upMaxProperty(), intToString);
				waUpMin.getEditor().textProperty().bindBidirectional(wa.upMinProperty(), intToString);
				maDownMax.getEditor().textProperty().bindBidirectional(wa.downMaxProperty(), intToString);
				waDownMin.getEditor().textProperty().bindBidirectional(wa.downMinProperty(), intToString);
				waAutoDownMax.getEditor().textProperty().bindBidirectional(wa.autoDownMaxProperty(), intToString);
				waAutoDownMin.getEditor().textProperty().bindBidirectional(wa.autoDownMinProperty(), intToString);
			}));

			dMaxCurrent.getEditor().textProperty().bindBidirectional(c.driverWindowMaxCurrentProperty(), intToString);
			pMaxCurrent.getEditor().textProperty().bindBidirectional(c.passengerWindowMaxCurrentProperty(), intToString);
		}));
//		initializeValidation();
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
		visualizer.initVisualization(viewModel.mirrorActionDownMaxValidation(), maDownMax, true);
		visualizer.initVisualization(viewModel.mirrorActionDownMinValidation(), maDownMin, true);
		visualizer.initVisualization(viewModel.mirrorActionLeftMaxValidation(), maLeftMax, true);
		visualizer.initVisualization(viewModel.mirrorActionLeftMinValidation(), maLeftMin, true);
		visualizer.initVisualization(viewModel.mirrorActionRightMaxValidation(), maRightMax, true);
		visualizer.initVisualization(viewModel.mirrorActionRightMinValidation(), maRightMin, true);
		visualizer.initVisualization(viewModel.mirrorActionUpMaxValidation(), maUpMax, true);
		visualizer.initVisualization(viewModel.mirrorActionUpMinValidation(), maUpMin, true);

		visualizer.initVisualization(viewModel.mirrorSelectDriverMaxValidation(), msDriverMax, true);
		visualizer.initVisualization(viewModel.mirrorSelectDriverMinValidation(), msDriverMin, true);
		visualizer.initVisualization(viewModel.mirrorSelectFoldMaxValidation(), msFoldMax, true);
		visualizer.initVisualization(viewModel.mirrorSelectFoldMinValidation(), msFoldMin, true);
		visualizer.initVisualization(viewModel.mirrorSelectPassengerMinValidation(), msPassMin, true);
		visualizer.initVisualization(viewModel.mirrorSelectPassengerMaxValidation(), msPassMax, true);

		visualizer.initVisualization(viewModel.windowActionAutoDownMaxValidation(), waAutoDownMax, true);
		visualizer.initVisualization(viewModel.windowActionAutoDownMinValidation(), waAutoDownMin, true);
		visualizer.initVisualization(viewModel.windowActionAutoUpMaxValidation(), waAutoUpMax, true);
		visualizer.initVisualization(viewModel.windowActionAutoUpMinValidation(), waAutoUpMin, true);
		visualizer.initVisualization(viewModel.windowActionDownMaxValidation(), waDownMax, true);
		visualizer.initVisualization(viewModel.windowActionDownMinValidation(), waDownMin, true);
		visualizer.initVisualization(viewModel.windowActionUpMaxValidation(), waUpMax, true);
		visualizer.initVisualization(viewModel.windowActionUpMinValidation(), waUpMin, true);

		visualizer.initVisualization(viewModel.driverWindowMaxCurrentValidation(), dMaxCurrent, true);
		visualizer.initVisualization(viewModel.passengerWindowMaxCurrentValidation(), pMaxCurrent, true);

	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

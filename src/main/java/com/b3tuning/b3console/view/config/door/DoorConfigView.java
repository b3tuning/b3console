package com.b3tuning.b3console.view.config.door;

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

	@FXML private ButtonInputField msDriverMax;
	@FXML private ButtonInputField msDriverMin;
	@FXML private ButtonInputField msFoldMax;
	@FXML private ButtonInputField msFoldMin;
	@FXML private ButtonInputField msPassMax;
	@FXML private ButtonInputField msPassMin;

	@FXML private ButtonInputField maUpMax;
	@FXML private ButtonInputField maUpMin;
	@FXML private ButtonInputField maDownMax;
	@FXML private ButtonInputField maDownMin;
	@FXML private ButtonInputField maLeftMax;
	@FXML private ButtonInputField maLeftMin;
	@FXML private ButtonInputField maRightMax;
	@FXML private ButtonInputField maRightMin;

	@FXML private ButtonInputField waAutoUpMax;
	@FXML private ButtonInputField waAutoUpMin;
	@FXML private ButtonInputField waUpMax;
	@FXML private ButtonInputField waUpMin;
	@FXML private ButtonInputField waDownMax;
	@FXML private ButtonInputField waDownMin;
	@FXML private ButtonInputField waAutoDownMax;
	@FXML private ButtonInputField waAutoDownMin;

	@FXML private ButtonInputField dMaxCurrent;
	@FXML private ButtonInputField pMaxCurrent;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		msDriverMax.setFormatter(new IntegerTextFormatter());
		msDriverMin.setFormatter(new IntegerTextFormatter());
		msFoldMax.setFormatter(new IntegerTextFormatter());
		msFoldMin.setFormatter(new IntegerTextFormatter());
		msPassMax.setFormatter(new IntegerTextFormatter());
		msPassMin.setFormatter(new IntegerTextFormatter());

		maUpMax.setFormatter(new IntegerTextFormatter());
		maUpMin.setFormatter(new IntegerTextFormatter());
		maDownMax.setFormatter(new IntegerTextFormatter());
		maDownMin.setFormatter(new IntegerTextFormatter());
		maLeftMax.setFormatter(new IntegerTextFormatter());
		maLeftMin.setFormatter(new IntegerTextFormatter());
		maRightMax.setFormatter(new IntegerTextFormatter());
		maRightMin.setFormatter(new IntegerTextFormatter());

		waAutoUpMax.setFormatter(new IntegerTextFormatter());
		waAutoUpMin.setFormatter(new IntegerTextFormatter());
		waUpMax.setFormatter(new IntegerTextFormatter());
		waUpMin.setFormatter(new IntegerTextFormatter());
		waDownMax.setFormatter(new IntegerTextFormatter());
		waDownMin.setFormatter(new IntegerTextFormatter());
		waAutoDownMax.setFormatter(new IntegerTextFormatter());
		waAutoDownMin.setFormatter(new IntegerTextFormatter());

		dMaxCurrent.setFormatter(new IntegerTextFormatter());
		pMaxCurrent.setFormatter(new IntegerTextFormatter());

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();

			manage(nonNullValuesOf(c.mirrorSelectProperty()).subscribe(ms -> {
				msDriverMax.textProperty().bindBidirectional(ms.driverMaxProperty(), intToString);
				msDriverMin.textProperty().bindBidirectional(ms.driverMinProperty(), intToString);
				msFoldMax.textProperty().bindBidirectional(ms.foldMaxProperty(), intToString);
				msFoldMin.textProperty().bindBidirectional(ms.foldMinProperty(), intToString);
				msPassMax.textProperty().bindBidirectional(ms.passengerMaxProperty(), intToString);
				msPassMin.textProperty().bindBidirectional(ms.passengerMinProperty(), intToString);
			}));

			manage(nonNullValuesOf(c.mirrorActionProperty()).subscribe(ma -> {
				maUpMax.textProperty().bindBidirectional(ma.upMaxProperty(), intToString);
				maUpMin.textProperty().bindBidirectional(ma.upMinProperty(), intToString);
				maDownMax.textProperty().bindBidirectional(ma.downMaxProperty(), intToString);
				maDownMin.textProperty().bindBidirectional(ma.downMinProperty(), intToString);
				maLeftMax.textProperty().bindBidirectional(ma.leftMaxProperty(), intToString);
				maLeftMin.textProperty().bindBidirectional(ma.leftMinProperty(), intToString);
				maRightMax.textProperty().bindBidirectional(ma.rightMaxProperty(), intToString);
				maRightMin.textProperty().bindBidirectional(ma.rightMinProperty(), intToString);
			}));

			manage(nonNullValuesOf(c.windowActionProperty()).subscribe(wa -> {
				waAutoUpMax.textProperty().bindBidirectional(wa.autoUpMaxProperty(), intToString);
				waAutoUpMin.textProperty().bindBidirectional(wa.autoUpMinProperty(), intToString);
				waUpMax.textProperty().bindBidirectional(wa.upMaxProperty(), intToString);
				waUpMin.textProperty().bindBidirectional(wa.upMinProperty(), intToString);
				maDownMax.textProperty().bindBidirectional(wa.downMaxProperty(), intToString);
				waDownMin.textProperty().bindBidirectional(wa.downMinProperty(), intToString);
				waAutoDownMax.textProperty().bindBidirectional(wa.autoDownMaxProperty(), intToString);
				waAutoDownMin.textProperty().bindBidirectional(wa.autoDownMinProperty(), intToString);
			}));

			dMaxCurrent.textProperty().bindBidirectional(c.driverWindowMaxCurrentProperty(), intToString);
			pMaxCurrent.textProperty().bindBidirectional(c.passengerWindowMaxCurrentProperty(), intToString);

		}));

		initializeValidation();
	}

	private void initializeValidation() {
		log.entry();

		visualizer.setDecoration(new StyleClassValidationDecoration());

		visualizer.initVisualization(viewModel.mirrorActionDownMaxValidation(), maDownMax.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionDownMinValidation(), maDownMin.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionLeftMaxValidation(), maLeftMax.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionLeftMinValidation(), maLeftMin.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionRightMaxValidation(), maRightMax.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionRightMinValidation(), maRightMin.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionUpMaxValidation(), maUpMax.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorActionUpMinValidation(), maUpMin.getTextField(), true);

		visualizer.initVisualization(viewModel.mirrorSelectDriverMaxValidation(), msDriverMax.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorSelectDriverMinValidation(), msDriverMin.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorSelectFoldMaxValidation(), msFoldMax.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorSelectFoldMinValidation(), msFoldMin.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorSelectPassengerMinValidation(), msPassMin.getTextField(), true);
		visualizer.initVisualization(viewModel.mirrorSelectPassengerMaxValidation(), msPassMax.getTextField(), true);

		visualizer.initVisualization(viewModel.windowActionAutoDownMaxValidation(), waAutoDownMax.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionAutoDownMinValidation(), waAutoDownMin.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionAutoUpMaxValidation(), waAutoUpMax.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionAutoUpMinValidation(), waAutoUpMin.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionDownMaxValidation(), waDownMax.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionDownMinValidation(), waDownMin.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionUpMaxValidation(), waUpMax.getTextField(), true);
		visualizer.initVisualization(viewModel.windowActionUpMinValidation(), waUpMin.getTextField(), true);

		visualizer.initVisualization(viewModel.driverWindowMaxCurrentValidation(), dMaxCurrent.getTextField(), true);
		visualizer.initVisualization(viewModel.passengerWindowMaxCurrentValidation(), pMaxCurrent.getTextField(), true);

	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

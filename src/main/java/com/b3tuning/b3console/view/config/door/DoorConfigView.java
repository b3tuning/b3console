package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.service.module.door.config.MirrorActionConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorSelectConfig;
import com.b3tuning.b3console.service.module.door.config.WindowActionConfig;
import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.buttonInputField.IntegerButtonInputField;
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

	@FXML private IntegerButtonInputField msDriverMax;
	@FXML private IntegerButtonInputField msDriverMin;
	@FXML private IntegerButtonInputField msFoldMax;
	@FXML private IntegerButtonInputField msFoldMin;
	@FXML private IntegerButtonInputField msPassMax;
	@FXML private IntegerButtonInputField msPassMin;

	@FXML private IntegerButtonInputField maUpMax;
	@FXML private IntegerButtonInputField maUpMin;
	@FXML private IntegerButtonInputField maDownMax;
	@FXML private IntegerButtonInputField maDownMin;
	@FXML private IntegerButtonInputField maLeftMax;
	@FXML private IntegerButtonInputField maLeftMin;
	@FXML private IntegerButtonInputField maRightMax;
	@FXML private IntegerButtonInputField maRightMin;

	@FXML private IntegerButtonInputField waAutoUpMax;
	@FXML private IntegerButtonInputField waAutoUpMin;
	@FXML private IntegerButtonInputField waUpMax;
	@FXML private IntegerButtonInputField waUpMin;
	@FXML private IntegerButtonInputField waDownMax;
	@FXML private IntegerButtonInputField waDownMin;
	@FXML private IntegerButtonInputField waAutoDownMax;
	@FXML private IntegerButtonInputField waAutoDownMin;

	@FXML private IntegerButtonInputField dMaxCurrent;
	@FXML private IntegerButtonInputField pMaxCurrent;

	private final ControlsFxVisualizer visualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();

			MirrorSelectConfig ms = c.getMirrorSelect();
			msDriverMax.textProperty().bindBidirectional(ms.driverMaxProperty(), intToString);
			msDriverMin.textProperty().bindBidirectional(ms.driverMinProperty(), intToString);
			msFoldMax.textProperty().bindBidirectional(ms.foldMaxProperty(), intToString);
			msFoldMin.textProperty().bindBidirectional(ms.foldMinProperty(), intToString);
			msPassMax.textProperty().bindBidirectional(ms.passengerMaxProperty(), intToString);
			msPassMin.textProperty().bindBidirectional(ms.passengerMinProperty(), intToString);

			MirrorActionConfig ma = c.getMirrorAction();
			maUpMax.textProperty().bindBidirectional(ma.upMaxProperty(), intToString);
			maUpMin.textProperty().bindBidirectional(ma.upMinProperty(), intToString);
			maDownMax.textProperty().bindBidirectional(ma.downMaxProperty(), intToString);
			maDownMin.textProperty().bindBidirectional(ma.downMinProperty(), intToString);
			maLeftMax.textProperty().bindBidirectional(ma.leftMaxProperty(), intToString);
			maLeftMin.textProperty().bindBidirectional(ma.leftMinProperty(), intToString);
			maRightMax.textProperty().bindBidirectional(ma.rightMaxProperty(), intToString);
			maRightMin.textProperty().bindBidirectional(ma.rightMinProperty(), intToString);

			WindowActionConfig wa = c.getWindowAction();
			waAutoUpMax.textProperty().bindBidirectional(wa.autoUpMaxProperty(), intToString);
			waAutoUpMin.textProperty().bindBidirectional(wa.autoUpMinProperty(), intToString);
			waUpMax.textProperty().bindBidirectional(wa.upMaxProperty(), intToString);
			waUpMin.textProperty().bindBidirectional(wa.upMinProperty(), intToString);
			maDownMax.textProperty().bindBidirectional(wa.downMaxProperty(), intToString);
			waDownMin.textProperty().bindBidirectional(wa.downMinProperty(), intToString);
			waAutoDownMax.textProperty().bindBidirectional(wa.autoDownMaxProperty(), intToString);
			waAutoDownMin.textProperty().bindBidirectional(wa.autoDownMinProperty(), intToString);

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

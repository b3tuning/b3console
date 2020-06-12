package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
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

	@FXML private TextField mirrorActionDownMax;
	@FXML private TextField mirrorActionDownMin;
	@FXML private TextField mirrorActionLeftMax;
	@FXML private TextField mirrorActionLeftMin;
	@FXML private TextField mirrorActionRightMax;
	@FXML private TextField mirrorActionRightMin;
	@FXML private TextField mirrorActionUpMax;
	@FXML private TextField mirrorActionUpMin;

	@FXML private TextField mirrorSelectDriverMax;
	@FXML private TextField mirrorSelectDriverMin;
	@FXML private TextField mirrorSelectFoldMax;
	@FXML private TextField mirrorSelectFoldMin;
	@FXML private TextField mirrorSelectPassengerMax;
	@FXML private TextField mirrorSelectPassengerMin;

	@FXML private TextField windowActionAutoDownMax;
	@FXML private TextField windowActionAutoDownMin;
	@FXML private TextField windowActionAutoUpMax;
	@FXML private TextField windowActionAutoUpMin;
	@FXML private TextField windowActionDownMax;
	@FXML private TextField windowActionDownMin;
	@FXML private TextField windowActionUpMax;
	@FXML private TextField windowActionUpMin;

	@FXML private TextField driverWindowMaxCurrent;
	@FXML private TextField passengerWindowMaxCurrent;

	private ControlsFxVisualizer validationVisualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();
			manage(nonNullValuesOf(c.getMirrorAction()).subscribe(ma -> {
				mirrorActionDownMax.textProperty().bindBidirectional(ma.getDownMax(), intToString);
				mirrorActionDownMin.textProperty().bindBidirectional(ma.getDownMin(), intToString);
				mirrorActionLeftMax.textProperty().bindBidirectional(ma.getLeftMax(), intToString);
				mirrorActionLeftMin.textProperty().bindBidirectional(ma.getLeftMin(), intToString);
				mirrorActionRightMax.textProperty().bindBidirectional(ma.getRightMax(), intToString);
				mirrorActionRightMin.textProperty().bindBidirectional(ma.getRightMin(), intToString);
				mirrorActionUpMax.textProperty().bindBidirectional(ma.getUpMax(), intToString);
				mirrorActionUpMin.textProperty().bindBidirectional(ma.getUpMin(), intToString);
			}));

			manage(nonNullValuesOf(c.getMirrorSelect()).subscribe(ms -> {
				mirrorSelectDriverMax.textProperty().bindBidirectional(ms.getDriverMax(), intToString);
				mirrorSelectDriverMin.textProperty().bindBidirectional(ms.getDriverMin(), intToString);
				mirrorSelectFoldMax.textProperty().bindBidirectional(ms.getFoldMax(), intToString);
				mirrorSelectFoldMin.textProperty().bindBidirectional(ms.getFoldMin(), intToString);
				mirrorSelectPassengerMax.textProperty().bindBidirectional(ms.getPassengerMax(), intToString);
				mirrorSelectPassengerMin.textProperty().bindBidirectional(ms.getPassengerMin(), intToString);
			}));

			manage(nonNullValuesOf(c.getWindowAction()).subscribe(wa -> {
				windowActionAutoDownMax.textProperty().bindBidirectional(wa.getAutoDownMax(), intToString);
				windowActionAutoDownMin.textProperty().bindBidirectional(wa.getAutoDownMin(), intToString);
				windowActionAutoUpMax.textProperty().bindBidirectional(wa.getAutoUpMax(), intToString);
				windowActionAutoUpMin.textProperty().bindBidirectional(wa.getAutoUpMin(), intToString);
				mirrorActionDownMax.textProperty().bindBidirectional(wa.getDownMax(), intToString);
				windowActionDownMin.textProperty().bindBidirectional(wa.getDownMin(), intToString);
				windowActionUpMax.textProperty().bindBidirectional(wa.getUpMax(), intToString);
				windowActionUpMin.textProperty().bindBidirectional(wa.getUpMin(), intToString);
			}));

			driverWindowMaxCurrent.textProperty().bindBidirectional(c.getDriverWindowMaxCurrent(), intToString);
			passengerWindowMaxCurrent.textProperty().bindBidirectional(c.getPassengerWindowMaxCurrent(), intToString);

		}));
	}

	private void initializeValidation() {
		log.entry();
		validationVisualizer.setDecoration(new StyleClassValidationDecoration());

	}
}

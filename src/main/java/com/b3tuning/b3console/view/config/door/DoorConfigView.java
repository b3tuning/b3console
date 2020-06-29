package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.service.module.door.config.DoorConfig;
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

	private final ControlsFxVisualizer validationVisualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(cb -> {
			log.entry();
			DoorConfig c = (DoorConfig) cb;
			manage(nonNullValuesOf(c.getMirrorAction()).subscribe(ma -> {
				mirrorActionDownMax.textProperty().bindBidirectional(ma.getDownMax().asObject(), intToString);
				mirrorActionDownMin.textProperty().bindBidirectional(ma.getDownMin().asObject(), intToString);
				mirrorActionLeftMax.textProperty().bindBidirectional(ma.getLeftMax().asObject(), intToString);
				mirrorActionLeftMin.textProperty().bindBidirectional(ma.getLeftMin().asObject(), intToString);
				mirrorActionRightMax.textProperty().bindBidirectional(ma.getRightMax().asObject(), intToString);
				mirrorActionRightMin.textProperty().bindBidirectional(ma.getRightMin().asObject(), intToString);
				mirrorActionUpMax.textProperty().bindBidirectional(ma.getUpMax().asObject(), intToString);
				mirrorActionUpMin.textProperty().bindBidirectional(ma.getUpMin().asObject(), intToString);
			}));

			manage(nonNullValuesOf(c.getMirrorSelect()).subscribe(ms -> {
				mirrorSelectDriverMax.textProperty().bindBidirectional(ms.getDriverMax().asObject(), intToString);
				mirrorSelectDriverMin.textProperty().bindBidirectional(ms.getDriverMin().asObject(), intToString);
				mirrorSelectFoldMax.textProperty().bindBidirectional(ms.getFoldMax().asObject(), intToString);
				mirrorSelectFoldMin.textProperty().bindBidirectional(ms.getFoldMin().asObject(), intToString);
				mirrorSelectPassengerMax.textProperty().bindBidirectional(ms.getPassengerMax().asObject(), intToString);
				mirrorSelectPassengerMin.textProperty().bindBidirectional(ms.getPassengerMin().asObject(), intToString);
			}));

			manage(nonNullValuesOf(c.getWindowAction()).subscribe(wa -> {
				windowActionAutoDownMax.textProperty().bindBidirectional(wa.getAutoDownMax().asObject(), intToString);
				windowActionAutoDownMin.textProperty().bindBidirectional(wa.getAutoDownMin().asObject(), intToString);
				windowActionAutoUpMax.textProperty().bindBidirectional(wa.getAutoUpMax().asObject(), intToString);
				windowActionAutoUpMin.textProperty().bindBidirectional(wa.getAutoUpMin().asObject(), intToString);
				mirrorActionDownMax.textProperty().bindBidirectional(wa.getDownMax().asObject(), intToString);
				windowActionDownMin.textProperty().bindBidirectional(wa.getDownMin().asObject(), intToString);
				windowActionUpMax.textProperty().bindBidirectional(wa.getUpMax().asObject(), intToString);
				windowActionUpMin.textProperty().bindBidirectional(wa.getUpMin().asObject(), intToString);
			}));

			driverWindowMaxCurrent.textProperty()
			                      .bindBidirectional(c.getDriverWindowMaxCurrent().asObject(), intToString);
			passengerWindowMaxCurrent.textProperty()
			                         .bindBidirectional(c.getPassengerWindowMaxCurrent().asObject(), intToString);

		}));
	}

	private void initializeValidation() {
		log.entry();
		validationVisualizer.setDecoration(new StyleClassValidationDecoration());

	}
}

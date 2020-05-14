package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;


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
	}
}

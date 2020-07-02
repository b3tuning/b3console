package com.b3tuning.b3console.view.config.door;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.IntegerStringConverter;
import lombok.extern.slf4j.XSlf4j;

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

//	@FXML private TextField mirrorSelectDriverMax;
//	@FXML private TextField mirrorSelectDriverMin;
//	@FXML private TextField mirrorSelectFoldMax;
//	@FXML private TextField mirrorSelectFoldMin;
//	@FXML private TextField mirrorSelectPassengerMax;
//	@FXML private TextField mirrorSelectPassengerMin;

	@FXML private TextField windowActionAutoDownMax;
	@FXML private TextField windowActionAutoDownMin;
	@FXML private TextField windowActionAutoUpMax;
	@FXML private TextField windowActionAutoUpMin;
	@FXML private TextField windowActionDownMax;
	@FXML private TextField windowActionDownMin;
	@FXML private TextField windowActionUpMax;
	@FXML private TextField windowActionUpMin;

//	@FXML private TextField driverWindowMaxCurrent;
//	@FXML private TextField passengerWindowMaxCurrent;

//	private final ControlsFxVisualizer validationVisualizer = new ControlsFxVisualizer();

	@InjectViewModel private DoorConfigViewModel viewModel;

	@Inject
	public DoorConfigView() {}

	public void initialize() {
		log.entry();

		IntegerStringConverter intToString = new IntegerStringConverter();

		setFormatter(mirrorActionDownMax);
		setFormatter(mirrorActionDownMin);
		setFormatter(mirrorActionLeftMax);
		setFormatter(mirrorActionLeftMin);
		setFormatter(mirrorActionRightMax);
		setFormatter(mirrorActionRightMin);
		setFormatter(mirrorActionUpMax);
		setFormatter(mirrorActionUpMin);
//		setFormatter(mirrorSelectDriverMax);
//		setFormatter(mirrorSelectDriverMin);
//		setFormatter(mirrorSelectFoldMax);
//		setFormatter(mirrorSelectFoldMin);
//		setFormatter(mirrorSelectPassengerMax);
//		setFormatter(mirrorSelectPassengerMin);
		setFormatter(windowActionAutoDownMax);
		setFormatter(windowActionAutoDownMin);
		setFormatter(windowActionAutoUpMax);
		setFormatter(windowActionAutoUpMin);
		setFormatter(windowActionDownMax);
		setFormatter(windowActionDownMin);
		setFormatter(windowActionUpMax);
		setFormatter(windowActionUpMin);

		manage(nonNullValuesOf(viewModel.doorConfigProperty()).subscribe(c -> {
			log.entry();
			manage(nonNullValuesOf(c.mirrorActionProperty()).subscribe(ma -> {
				mirrorActionDownMax.textProperty().bindBidirectional(ma.downMaxProperty(), intToString);
				mirrorActionDownMin.textProperty().bindBidirectional(ma.downMinProperty(), intToString);
				mirrorActionLeftMax.textProperty().bindBidirectional(ma.leftMaxProperty(), intToString);
				mirrorActionLeftMin.textProperty().bindBidirectional(ma.leftMinProperty(), intToString);
				mirrorActionRightMax.textProperty().bindBidirectional(ma.rightMaxProperty(), intToString);
				mirrorActionRightMin.textProperty().bindBidirectional(ma.rightMinProperty(), intToString);
				mirrorActionUpMax.textProperty().bindBidirectional(ma.upMaxProperty(), intToString);
				mirrorActionUpMin.textProperty().bindBidirectional(ma.upMinProperty(), intToString);
			}));

//			manage(nonNullValuesOf(c.mirrorSelectProperty()).subscribe(ms -> {
//				mirrorSelectDriverMax.textProperty().bindBidirectional(ms.driverMaxProperty(), intToString);
//				mirrorSelectDriverMin.textProperty().bindBidirectional(ms.driverMinProperty(), intToString);
//				mirrorSelectFoldMax.textProperty().bindBidirectional(ms.foldMaxProperty(), intToString);
//				mirrorSelectFoldMin.textProperty().bindBidirectional(ms.foldMinProperty(), intToString);
//				mirrorSelectPassengerMax.textProperty().bindBidirectional(ms.passengerMaxProperty(), intToString);
//				mirrorSelectPassengerMin.textProperty().bindBidirectional(ms.passengerMinProperty(), intToString);
//			}));
//
			manage(nonNullValuesOf(c.windowActionProperty()).subscribe(wa -> {
				windowActionAutoDownMax.textProperty().bindBidirectional(wa.autoDownMaxProperty(), intToString);
				windowActionAutoDownMin.textProperty().bindBidirectional(wa.autoDownMinProperty(), intToString);
				windowActionAutoUpMax.textProperty().bindBidirectional(wa.autoUpMaxProperty(), intToString);
				windowActionAutoUpMin.textProperty().bindBidirectional(wa.autoUpMinProperty(), intToString);
				mirrorActionDownMax.textProperty().bindBidirectional(wa.downMaxProperty(), intToString);
				windowActionDownMin.textProperty().bindBidirectional(wa.downMinProperty(), intToString);
				windowActionUpMax.textProperty().bindBidirectional(wa.upMaxProperty(), intToString);
				windowActionUpMin.textProperty().bindBidirectional(wa.upMinProperty(), intToString);
			}));

//			driverWindowMaxCurrent.textProperty().bindBidirectional(c.driverWindowMaxCurrentProperty(), intToString);
//			passengerWindowMaxCurrent.textProperty()
//			                         .bindBidirectional(c.passengerWindowMaxCurrentProperty(), intToString);
		}));
	}

	private void setFormatter(TextField textField) {
		textField.setTextFormatter(new IntegerTextFormatter());
	}

//	private void initializeValidation() {
//		log.entry();
//		validationVisualizer.setDecoration(new StyleClassValidationDecoration());
//
//	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

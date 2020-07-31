package com.b3tuning.b3console.view.config.shifter;

import com.b3tuning.b3console.service.module.shifter.config.IndicatorConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterPositionConfig;
import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.config.CanBusConfigView;
import com.b3tuning.b3console.view.utils.buttonInputField.IntegerButtonInputField;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
import javafx.fxml.FXML;
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

	@FXML private IntegerButtonInputField brightness;
	@FXML private IntegerButtonInputField color;
	@FXML private IntegerButtonInputField delay;

	@FXML private IntegerButtonInputField range;

	@FXML private IntegerButtonInputField x1Park;
	@FXML private IntegerButtonInputField y1Park;
	@FXML private IntegerButtonInputField x2Park;
	@FXML private IntegerButtonInputField y2Park;

	@FXML private IntegerButtonInputField x1Reverse;
	@FXML private IntegerButtonInputField y1Reverse;
	@FXML private IntegerButtonInputField x2Reverse;
	@FXML private IntegerButtonInputField y2Reverse;

	@FXML private IntegerButtonInputField x1Neutral;
	@FXML private IntegerButtonInputField y1Neutral;
	@FXML private IntegerButtonInputField x2Neutral;
	@FXML private IntegerButtonInputField y2Neutral;

	@FXML private IntegerButtonInputField x1Drive;
	@FXML private IntegerButtonInputField y1Drive;
	@FXML private IntegerButtonInputField x2Drive;
	@FXML private IntegerButtonInputField y2Drive;

	@FXML private IntegerButtonInputField x1Manual;
	@FXML private IntegerButtonInputField y1Manual;
	@FXML private IntegerButtonInputField x2Manual;
	@FXML private IntegerButtonInputField y2Manual;

	@FXML private IntegerButtonInputField x1Up;
	@FXML private IntegerButtonInputField y1Up;
	@FXML private IntegerButtonInputField x2Up;
	@FXML private IntegerButtonInputField y2Up;

	@FXML private IntegerButtonInputField x1Down;
	@FXML private IntegerButtonInputField y1Down;
	@FXML private IntegerButtonInputField x2Down;
	@FXML private IntegerButtonInputField y2Down;

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

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();

			c.canBusProperty().bindBidirectional(canBusConfigController.getViewModel().configProperty());

			IndicatorConfig indicator = c.getIndicator();
			brightness.textProperty().bindBidirectional(indicator.brightnessProperty(), intToString);
			color.textProperty().bindBidirectional(indicator.colorProperty(), intToString);
			delay.textProperty().bindBidirectional(indicator.waitProperty(), intToString);

			range.textProperty().bindBidirectional(c.melexisProperty().get().rangeProperty(), intToString);

			ShifterPositionConfig park = c.getPark();
			x1Park.textProperty().bindBidirectional(park.x1Property(), intToString);
			y1Park.textProperty().bindBidirectional(park.y1Property(), intToString);
			x2Park.textProperty().bindBidirectional(park.x2Property(), intToString);
			y2Park.textProperty().bindBidirectional(park.y2Property(), intToString);

			ShifterPositionConfig reverse = c.getReverse();
			x1Reverse.textProperty().bindBidirectional(reverse.x1Property(), intToString);
			y1Reverse.textProperty().bindBidirectional(reverse.y1Property(), intToString);
			x2Reverse.textProperty().bindBidirectional(reverse.x2Property(), intToString);
			y2Reverse.textProperty().bindBidirectional(reverse.y2Property(), intToString);

			ShifterPositionConfig neutral = c.getNeutral();
			x1Neutral.textProperty().bindBidirectional(neutral.x1Property(), intToString);
			y1Neutral.textProperty().bindBidirectional(neutral.y1Property(), intToString);
			x2Neutral.textProperty().bindBidirectional(neutral.x2Property(), intToString);
			y2Neutral.textProperty().bindBidirectional(neutral.y2Property(), intToString);

			ShifterPositionConfig drive = c.getDrive();
			x1Drive.textProperty().bindBidirectional(drive.x1Property(), intToString);
			y1Drive.textProperty().bindBidirectional(drive.y1Property(), intToString);
			x2Drive.textProperty().bindBidirectional(drive.x2Property(), intToString);
			y2Drive.textProperty().bindBidirectional(drive.y2Property(), intToString);

			ShifterPositionConfig manual = c.getManual();
			x1Manual.textProperty().bindBidirectional(manual.x1Property(), intToString);
			y1Manual.textProperty().bindBidirectional(manual.y1Property(), intToString);
			x2Manual.textProperty().bindBidirectional(manual.x2Property(), intToString);
			y2Manual.textProperty().bindBidirectional(manual.y2Property(), intToString);

			ShifterPositionConfig up = c.getUp();
			x1Up.textProperty().bindBidirectional(up.x1Property(), intToString);
			y1Up.textProperty().bindBidirectional(up.y1Property(), intToString);
			x2Up.textProperty().bindBidirectional(up.x2Property(), intToString);
			y2Up.textProperty().bindBidirectional(up.y2Property(), intToString);

			ShifterPositionConfig down = c.getDown();
			x1Down.textProperty().bindBidirectional(down.x1Property(), intToString);
			y1Down.textProperty().bindBidirectional(down.y1Property(), intToString);
			x2Down.textProperty().bindBidirectional(down.x2Property(), intToString);
			y2Down.textProperty().bindBidirectional(down.y2Property(), intToString);
		}));

		initializeValidation();
	}

	private void initializeValidation() {
		log.entry();

		visualizer.setDecoration(new StyleClassValidationDecoration());

		visualizer.initVisualization(viewModel.brightnessValidation(), brightness.getTextField(), true);
		visualizer.initVisualization(viewModel.colorValidation(), color.getTextField(), true);
		visualizer.initVisualization(viewModel.delayValidation(), delay.getTextField(), true);

		visualizer.initVisualization(viewModel.rangeValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1ParkValidation(), x1Park.getTextField(), true);
		visualizer.initVisualization(viewModel.y1ParkValidation(), y1Park.getTextField(), true);
		visualizer.initVisualization(viewModel.x2ParkValidation(), x2Park.getTextField(), true);
		visualizer.initVisualization(viewModel.y2ParkValidation(), y2Park.getTextField(), true);

		visualizer.initVisualization(viewModel.x1ReverseValidation(), x1Reverse.getTextField(), true);
		visualizer.initVisualization(viewModel.y1ReverseValidation(), y1Reverse.getTextField(), true);
		visualizer.initVisualization(viewModel.x2ReverseValidation(), x2Reverse.getTextField(), true);
		visualizer.initVisualization(viewModel.y2ReverseValidation(), y2Reverse.getTextField(), true);

		visualizer.initVisualization(viewModel.x1NeutralValidation(), x1Neutral.getTextField(), true);
		visualizer.initVisualization(viewModel.y1NeutralValidation(), y1Neutral.getTextField(), true);
		visualizer.initVisualization(viewModel.x2NeutralValidation(), x2Neutral.getTextField(), true);
		visualizer.initVisualization(viewModel.y2NeutralValidation(), y2Neutral.getTextField(), true);

		visualizer.initVisualization(viewModel.x1DriveValidation(), x1Drive.getTextField(), true);
		visualizer.initVisualization(viewModel.y1DriveValidation(), y1Drive.getTextField(), true);
		visualizer.initVisualization(viewModel.x2DriveValidation(), x2Drive.getTextField(), true);
		visualizer.initVisualization(viewModel.y2DriveValidation(), y2Drive.getTextField(), true);

		visualizer.initVisualization(viewModel.x1ManualValidation(), x1Manual.getTextField(), true);
		visualizer.initVisualization(viewModel.y1ManualValidation(), y1Manual.getTextField(), true);
		visualizer.initVisualization(viewModel.x2ManualValidation(), x2Manual.getTextField(), true);
		visualizer.initVisualization(viewModel.y2ManualValidation(), y2Manual.getTextField(), true);

		visualizer.initVisualization(viewModel.x1UpValidation(), x1Up.getTextField(), true);
		visualizer.initVisualization(viewModel.y1UpValidation(), y1Up.getTextField(), true);
		visualizer.initVisualization(viewModel.x2UpValidation(), x2Up.getTextField(), true);
		visualizer.initVisualization(viewModel.y2UpValidation(), y2Up.getTextField(), true);

		visualizer.initVisualization(viewModel.x1DownValidation(), x1Down.getTextField(), true);
		visualizer.initVisualization(viewModel.y1DownValidation(), y1Down.getTextField(), true);
		visualizer.initVisualization(viewModel.x2DownValidation(), x2Down.getTextField(), true);
		visualizer.initVisualization(viewModel.y2DownValidation(), y2Down.getTextField(), true);

	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

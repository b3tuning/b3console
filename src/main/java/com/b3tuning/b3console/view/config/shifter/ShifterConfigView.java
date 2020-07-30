package com.b3tuning.b3console.view.config.shifter;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.config.CanBusConfigView;
import com.b3tuning.b3console.view.utils.IntegerTextFormatter;
import com.b3tuning.b3console.view.utils.buttonInputField.ButtonInputField;
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

	@FXML private ButtonInputField brightness;
	@FXML private ButtonInputField color;
	@FXML private ButtonInputField delay;

	@FXML private ButtonInputField range;

	@FXML private ButtonInputField x1Park;
	@FXML private ButtonInputField y1Park;
	@FXML private ButtonInputField x2Park;
	@FXML private ButtonInputField y2Park;

	@FXML private ButtonInputField x1Reverse;
	@FXML private ButtonInputField y1Reverse;
	@FXML private ButtonInputField x2Reverse;
	@FXML private ButtonInputField y2Reverse;

	@FXML private ButtonInputField x1Neutral;
	@FXML private ButtonInputField y1Neutral;
	@FXML private ButtonInputField x2Neutral;
	@FXML private ButtonInputField y2Neutral;

	@FXML private ButtonInputField x1Drive;
	@FXML private ButtonInputField y1Drive;
	@FXML private ButtonInputField x2Drive;
	@FXML private ButtonInputField y2Drive;

	@FXML private ButtonInputField x1Manual;
	@FXML private ButtonInputField y1Manual;
	@FXML private ButtonInputField x2Manual;
	@FXML private ButtonInputField y2Manual;

	@FXML private ButtonInputField x1Up;
	@FXML private ButtonInputField y1Up;
	@FXML private ButtonInputField x2Up;
	@FXML private ButtonInputField y2Up;

	@FXML private ButtonInputField x1Down;
	@FXML private ButtonInputField y1Down;
	@FXML private ButtonInputField x2Down;
	@FXML private ButtonInputField y2Down;

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

		brightness.setFormatter(new IntegerTextFormatter());
		color.setFormatter(new IntegerTextFormatter());
		delay.setFormatter(new IntegerTextFormatter());

		range.setFormatter(new IntegerTextFormatter());

		x1Park.setFormatter(new IntegerTextFormatter());
		y1Park.setFormatter(new IntegerTextFormatter());
		x2Park.setFormatter(new IntegerTextFormatter());
		y2Park.setFormatter(new IntegerTextFormatter());

		x1Reverse.setFormatter(new IntegerTextFormatter());
		y1Reverse.setFormatter(new IntegerTextFormatter());
		x2Reverse.setFormatter(new IntegerTextFormatter());
		y2Reverse.setFormatter(new IntegerTextFormatter());

		x1Neutral.setFormatter(new IntegerTextFormatter());
		y1Neutral.setFormatter(new IntegerTextFormatter());
		x2Neutral.setFormatter(new IntegerTextFormatter());
		y2Neutral.setFormatter(new IntegerTextFormatter());

		x1Drive.setFormatter(new IntegerTextFormatter());
		y1Drive.setFormatter(new IntegerTextFormatter());
		x2Drive.setFormatter(new IntegerTextFormatter());
		y2Drive.setFormatter(new IntegerTextFormatter());

		x1Manual.setFormatter(new IntegerTextFormatter());
		y1Manual.setFormatter(new IntegerTextFormatter());
		x2Manual.setFormatter(new IntegerTextFormatter());
		y2Manual.setFormatter(new IntegerTextFormatter());

		x1Up.setFormatter(new IntegerTextFormatter());
		y1Up.setFormatter(new IntegerTextFormatter());
		x2Up.setFormatter(new IntegerTextFormatter());
		y2Up.setFormatter(new IntegerTextFormatter());

		x1Down.setFormatter(new IntegerTextFormatter());
		y1Down.setFormatter(new IntegerTextFormatter());
		x2Down.setFormatter(new IntegerTextFormatter());
		y2Down.setFormatter(new IntegerTextFormatter());

		manage(nonNullValuesOf(viewModel.configProperty()).subscribe(c -> {
			log.entry();

			c.canBusProperty().bindBidirectional(canBusConfigController.getViewModel().configProperty());

			brightness.textProperty().bindBidirectional(c.indicatorProperty().get().brightnessProperty(), intToString);
			color.textProperty().bindBidirectional(c.indicatorProperty().get().colorProperty(), intToString);
			delay.textProperty().bindBidirectional(c.indicatorProperty().get().waitProperty(), intToString);

			range.textProperty().bindBidirectional(c.melexisProperty().get().rangeProperty(), intToString);

			x1Park.textProperty().bindBidirectional(c.parkProperty().get().x1Property(), intToString);
			y1Park.textProperty().bindBidirectional(c.parkProperty().get().y1Property(), intToString);
			x2Park.textProperty().bindBidirectional(c.parkProperty().get().x2Property(), intToString);
			y2Park.textProperty().bindBidirectional(c.parkProperty().get().y2Property(), intToString);

			x1Reverse.textProperty().bindBidirectional(c.reverseProperty().get().x1Property(), intToString);
			y1Reverse.textProperty().bindBidirectional(c.reverseProperty().get().y1Property(), intToString);
			x2Reverse.textProperty().bindBidirectional(c.reverseProperty().get().x2Property(), intToString);
			y2Reverse.textProperty().bindBidirectional(c.reverseProperty().get().y2Property(), intToString);

			x1Neutral.textProperty().bindBidirectional(c.neutralProperty().get().x1Property(), intToString);
			y1Neutral.textProperty().bindBidirectional(c.neutralProperty().get().y1Property(), intToString);
			x2Neutral.textProperty().bindBidirectional(c.neutralProperty().get().x2Property(), intToString);
			y2Neutral.textProperty().bindBidirectional(c.neutralProperty().get().y2Property(), intToString);

			x1Drive.textProperty().bindBidirectional(c.driveProperty().get().x1Property(), intToString);
			y1Drive.textProperty().bindBidirectional(c.driveProperty().get().y1Property(), intToString);
			x2Drive.textProperty().bindBidirectional(c.driveProperty().get().x2Property(), intToString);
			y2Drive.textProperty().bindBidirectional(c.driveProperty().get().y2Property(), intToString);

			x1Manual.textProperty().bindBidirectional(c.manualProperty().get().x1Property(), intToString);
			y1Manual.textProperty().bindBidirectional(c.manualProperty().get().y1Property(), intToString);
			x2Manual.textProperty().bindBidirectional(c.manualProperty().get().x2Property(), intToString);
			y2Manual.textProperty().bindBidirectional(c.manualProperty().get().y2Property(), intToString);

			x1Up.textProperty().bindBidirectional(c.upProperty().get().x1Property(), intToString);
			y1Up.textProperty().bindBidirectional(c.upProperty().get().y1Property(), intToString);
			x2Up.textProperty().bindBidirectional(c.upProperty().get().x2Property(), intToString);
			y2Up.textProperty().bindBidirectional(c.upProperty().get().y2Property(), intToString);

			x1Down.textProperty().bindBidirectional(c.downProperty().get().x1Property(), intToString);
			y1Down.textProperty().bindBidirectional(c.downProperty().get().y1Property(), intToString);
			x2Down.textProperty().bindBidirectional(c.downProperty().get().x2Property(), intToString);
			y2Down.textProperty().bindBidirectional(c.downProperty().get().y2Property(), intToString);
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

		visualizer.initVisualization(viewModel.x1ParkValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1ParkValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2ParkValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2ParkValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1ReverseValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1ReverseValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2ReverseValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2ReverseValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1NeutralValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1NeutralValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2NeutralValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2NeutralValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1DriveValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1DriveValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2DriveValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2DriveValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1ManualValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1ManualValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2ManualValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2ManualValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1UpValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1UpValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2UpValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2UpValidation(), range.getTextField(), true);

		visualizer.initVisualization(viewModel.x1DownValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y1DownValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.x2DownValidation(), range.getTextField(), true);
		visualizer.initVisualization(viewModel.y2DownValidation(), range.getTextField(), true);

	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
	}
}

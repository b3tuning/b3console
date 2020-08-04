/*
 *  Created on:  Jul 28, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  InputField is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.utils.buttonInputField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ButtonInputFieldController implements Initializable {

	@FXML private HBox      hBox;
	@FXML private Button    button;
	@FXML private TextField textField;

	@Override public void initialize(URL location, ResourceBundle resources) {
	}

	@SuppressWarnings("unused")
	public HBox getHBox() {
		return hBox;
	}

	@SuppressWarnings("unused")
	public void setHBox(HBox hBox) {
		this.hBox = hBox;
	}

	public Button getButton() {
		return button;
	}

	@SuppressWarnings("unused")
	public void setButton(Button button) {
		this.button = button;
	}

	public TextField getTextField() {
		return textField;
	}

	@SuppressWarnings("unused")
	public void setTextField(TextField textField) {
		this.textField = textField;
	}
}

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

import javafx.beans.NamedArg;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ButtonInputField extends HBox {

	ButtonInputFieldController controller;

	public ButtonInputField(@NamedArg("buttonText") String buttonText) {
		super();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ButtonInputField.fxml"));
			controller = new ButtonInputFieldController();

			loader.setController(controller);
			Node n = loader.load();
			this.getChildren().add(n);

			this.controller.getButton().setText(buttonText);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public final void setFormatter(TextFormatter<?> formatter) {
		this.getTextField().setTextFormatter(formatter);
	}

	public final TextField getTextField() {
		return this.controller.getTextField();
	}

	public final StringProperty textProperty() {
		return this.controller.getTextField().textProperty();
	}
}

package com.b3tuning.b3console.view.utils;

import de.saxsys.mvvmfx.utils.validation.CompositeValidator;
import de.saxsys.mvvmfx.utils.validation.ValidationMessage;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import lombok.extern.slf4j.XSlf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.OK;
import static javafx.scene.control.ButtonType.YES;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class AlertUtils {

	public static final String MESSAGE_WARNING = "Warning";
	public static final String MESSAGE_SERVICE_UNAVAILABLE_MSG = "Service not available, please try again later.";

	public static void warnServiceUnavailable() {
		warn(MESSAGE_SERVICE_UNAVAILABLE_MSG);
	}

	public static void validationWarn(CompositeValidator formValidator) {
		warn(formValidator.getValidationStatus()
		                  .getMessages()
		                  .stream()
		                  .map(ValidationMessage::getMessage)
		                  .collect(Collectors.toList())
		                  .toString()
		                  .replace("[", "")
		                  .replace("]", "")
		                  .replace(",", "\n\n"));
	}

	public static void warn(String content) {
		warn(MESSAGE_WARNING, content);
	}

	public static void warn(String title, String content) {
		alert(WARNING, title, content);
	}

	public static void alert(AlertType alertType, String title, String content) {
		Platform.runLater(() -> build(alertType, title, null, content).showAndWait());
	}

	public static boolean alert(AlertType alertType, String title, String header, String content,
	                            ButtonType... buttons) {

		if (Platform.isFxApplicationThread()) {
			Alert alert = build(alertType, title, header, content, buttons);
			alert.showAndWait();
			return (alert.getResult() == YES || alert.getResult() == OK);

		} else {
			final FutureTask<Boolean> dialog = new FutureTask<>(() -> {
				Alert alert = build(alertType, title, header, content, buttons);
				alert.showAndWait();
				return (alert.getResult() == YES || alert.getResult() == OK);
			});

			Platform.runLater(dialog);
			try {
				return dialog.get();
			}
			catch (InterruptedException | ExecutionException e) {
				return false;
			}
		}
	}

	private static Alert build(AlertType alertType, String title, String header, String content,
	                           ButtonType... buttons) {
		Alert alert = build(alertType, title, header, content);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(buttons);
		alert.setResizable(true);
		return alert;
	}

	public static Alert build(AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert;
	}
}

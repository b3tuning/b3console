package com.b3tuning.b3console.view.help;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.b3tuning.b3console.view.root.FontAwesome.HELP;
import static javafx.event.ActionEvent.ACTION;
import static org.reactfx.EventStreams.eventsOf;
import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class HelpView extends BaseView<HelpViewModel> {

	@FXML WebView    webView;
	@FXML AnchorPane helpPane;
	@FXML Button     detachHelpButton;

	@InjectViewModel HelpViewModel viewModel;

	@Inject
	public HelpView() {
	}

	public void initialize() {
		log.entry();

		manage(nonNullValuesOf(viewModel.helpFileProperty()).subscribe(v -> {
			log.entry(v);
			Platform.runLater(() -> webView.getEngine().load(v));
		}));

		detachHelpButton.setText(HELP);
		detachHelpButton.visibleProperty().bind(viewModel.detachedProperty().not());
		detachHelpButton.managedProperty().bind(detachHelpButton.visibleProperty());
		manage(eventsOf(detachHelpButton, ACTION).subscribe(v -> {
			viewModel.detachHelp();
		}));
	}
}

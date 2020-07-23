package com.b3tuning.b3console.view.root;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static org.reactfx.EventStreams.combine;
import static org.reactfx.EventStreams.nonNullValuesOf;
import static org.reactfx.EventStreams.valuesOf;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class RootView extends BaseView<RootViewModel> {

//	@FXML AnchorPane leftPane;
	@FXML StackPane  centerPane;
	@FXML AnchorPane topPane;
	@FXML SplitPane  centerSplitPane;

	private Node helpView;

	@InjectViewModel private RootViewModel viewModel;

	@SuppressWarnings("unused")
	private final NotificationCenter globalNotifications;

	@Inject
	public RootView(NotificationCenter notificationCenter) {
		log.entry();
		this.globalNotifications = notificationCenter;
	}

	public void initialize() {
		log.entry();

		viewModel.setChildViewPane(centerPane);

//		leftPane.visibleProperty().setValue(true);
//		leftPane.managedProperty().bind(leftPane.visibleProperty());
//		topPane.visibleProperty().bind(leftPane.visibleProperty());
//		topPane.managedProperty().bind(topPane.visibleProperty());
		topPane.visibleProperty().setValue(true);
		topPane.managedProperty().bind(topPane.visibleProperty());

		loadMenuView();
		initializeHelp();

		initializeNotifications();

	}

	private void initializeNotifications() {

		// child views/viewModels may request that a menu item is pressed (e.g. after cancelling navigation due to having a dirty page)
		globalNotifications.subscribe(ClickButtonNotification.class.getName(), (key, payload) -> {
			ClickButtonNotification n = ((ClickButtonNotification) payload[0]);
			if (n.getButtonId() != null) {
				log.entry(n.getButtonId());
			}
		});
	}

	private void initializeHelp() {
		log.entry();

		helpView = viewModel.helpView();
		helpView.visibleProperty().bind(viewModel.helpPaneVisibleProperty());

		manage(combine(valuesOf(viewModel.initializedProperty()), viewModel.displayHelpStream()).subscribe((i) -> {
			log.entry(i);
			if (!i.get1()) {
				return;
			}
			// if there is no help panel visible, add it to the splitpane
			if (!viewModel.helpPaneVisibleProperty().get()) {
				this.centerSplitPane.getItems().add(helpView);
			}
			viewModel.animateHelpPane();
			// if we animated in, bind the divider position
			if (centerSplitPane.getDividers().size() > 0) {
				nonNullValuesOf(centerSplitPane.getDividers().get(0).positionProperty())
						.subscribe(v -> viewModel.setHelpPaneLocation(1 - v.doubleValue()));
			}
		}));

		manage(nonNullValuesOf(viewModel.helpPaneLocationProperty())
				       .subscribe(v -> updateHelpPaneDivider(v.doubleValue())));
		manage(nonNullValuesOf(viewModel.helpPaneVisibleProperty()).subscribe(v -> {
			if (!v && centerSplitPane.getItems().size() > 1) {
				this.centerSplitPane.getItems().remove(helpView);
			}
		}));
		manage(nonNullValuesOf(viewModel.helpPaneOpacityProperty())
				       .subscribe(v -> helpView.setOpacity(v.doubleValue())));

	}

	private void updateHelpPaneDivider(double position) {
		if (centerSplitPane.getDividers().size() == 0) {
			return;
		}
		double newPosition     = 1 - position;
		double currentPosition = centerSplitPane.getDividerPositions()[0];
		if (Math.floor(newPosition * 100) != Math.floor(currentPosition * 100)) {
			centerSplitPane.setDividerPosition(0, newPosition);
		}
	}

	private void loadMenuView() {
		topPane.getChildren().add(viewModel.getMenuView());
	}
}

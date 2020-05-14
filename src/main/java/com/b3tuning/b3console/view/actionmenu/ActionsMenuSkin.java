package com.b3tuning.b3console.view.actionmenu;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Duration;
import lombok.Synchronized;
import org.reactfx.Guard;
import org.reactfx.Subscription;
import org.reactfx.SuspendableNo;

import static com.b3tuning.b3console.view.actionmenu.ActionsMenu.CSS_CLASS;
import static com.b3tuning.b3console.view.root.FontAwesome.COGS;
import static javafx.animation.Interpolator.EASE_IN;
import static javafx.animation.Interpolator.EASE_OUT;
import static javafx.event.ActionEvent.ACTION;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;
import static javafx.scene.input.MouseEvent.MOUSE_EXITED;
import static javafx.stage.PopupWindow.AnchorLocation.CONTENT_TOP_RIGHT;
import static org.reactfx.EventStreams.eventsOf;
import static org.reactfx.EventStreams.invalidationsOf;
import static org.reactfx.EventStreams.ticks;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class ActionsMenuSkin<T> extends SkinBase<ActionsMenu<T>> implements Skin<ActionsMenu<T>> {

	private static final String HOVER_TOOL_TIP = "Click or hover over to see the available menu options";
	private static final double POPUP_WIDTH    = 200.0;

	private              Pane   actionPane;
	private              Popup  actionPopup;
	private static final double SPACING = 5;

	private Button       menuButton;
	private Subscription subscriptions;

	private volatile boolean       actionsDisplayed       = false;
	private volatile boolean       hoveringOverActions    = false;
	private volatile boolean       hoveringOverMenuAction = false;
	private          SuspendableNo animationInProgress    = new SuspendableNo();

	// ******************** Constructors **************************************
	public ActionsMenuSkin(final ActionsMenu<T> CONTROL) {
		super(CONTROL);

		init();
		initGraphics();
		registerListeners();
	}

	/**
	 * Set the controls size
	 */
	private void init() {
	}

	/**
	 * Draw the controls nodes
	 */
	private void initGraphics() {

		// using a Popup allows us to break out of the container of any host such as a TableCell
		actionPopup = new Popup();
		actionPopup.setAutoHide(true);
		actionPopup.setHideOnEscape(true);
		actionPopup.setOnHiding(windowEvent -> animateOut());

		menuButton = new Button(COGS);
		menuButton.setTooltip(new Tooltip(HOVER_TOOL_TIP));
		menuButton.relocate(0.0, 0.0);

		// creating an invisible (but still managed) rectangle is an easy way to size the Popup
		Rectangle invisibleSpacer = new Rectangle(POPUP_WIDTH, 1.0);
		invisibleSpacer.setVisible(false); // intentionally left as managed

		// the actionpane is the container that holds the action buttons. this is what gets added to
		// the popup
		actionPane = new Pane(invisibleSpacer);
		actionPane.setPrefWidth(POPUP_WIDTH);
		actionPane.getStyleClass().add(CSS_CLASS);
		actionPopup.getContent().add(actionPane);

		// add the action buttons to the action pane
		addMenuItems();

		getChildren().addAll(menuButton);
		menuButton.toFront();
	}

	/**
	 * Register any event listeners that we are interesting in binding to
	 */
	private void registerListeners() {

		subscriptions = invalidationsOf(getSkinnable().modelProperty())
				.subscribe(s -> addMenuItems());

		// show/hide the menu if someone clicks the menu button
		subscriptions = subscriptions.and(eventsOf(menuButton, ACTION).subscribe(s -> {
			if (!actionsDisplayed) {
				animateIn();
			} else {
				animateOut();
			}
		}));

		// hide when navigating away
		subscriptions = subscriptions.and(ticks(java.time.Duration.ofMillis(200)).subscribe(t -> {
			if (!hoveringOverMenuAction && !hoveringOverActions) {
				animateOut();
			}
		}));

		// show the action menus when a user hovers over the menu button
		subscriptions = subscriptions.and(eventsOf(menuButton, MOUSE_ENTERED).subscribe(s -> {
			hoveringOverMenuAction = true;
			animateIn();
		}));

		subscriptions = subscriptions
				.and(eventsOf(menuButton, MOUSE_EXITED).subscribe(s -> hoveringOverMenuAction = false));

		subscriptions = subscriptions
				.and(eventsOf(actionPane, MOUSE_ENTERED).subscribe(s -> hoveringOverActions = true));

		subscriptions = subscriptions
				.and(eventsOf(actionPane, MOUSE_EXITED).subscribe(s -> hoveringOverActions = false));
	}

	/**
	 * Display the action items via animation. this method is synchronized to ensure that multiple
	 * mouseenters do not cause this to keep firing
	 */
	@Synchronized
	private void animateIn() {
		if (actionsDisplayed || animationInProgress.get()) {
			return;
		}
		Guard lock = animationInProgress.suspend();

		actionsDisplayed = true;
		double             x               = menuButton.getLayoutX();
		ParallelTransition slideAllButtons = new ParallelTransition();
		slideAllButtons.setOnFinished(arg0 -> lock.close());

		// must show the popup first so that its contents are laid out and thus have a width
		Bounds screenBounds = menuButton.localToScreen(menuButton.getLayoutBounds());
		actionPopup.setAnchorLocation(CONTENT_TOP_RIGHT);
		actionPopup.show(getSkinnable(), screenBounds.getMinX(), screenBounds.getMinY());

		for (Node n : actionPane.getChildren()) {
			if (n instanceof Button) {
				Button b = (Button) n;

				Action action = (Action) b.getUserData();
				if (action.getHideActionCallback() != null && action.getHideActionCallback()
				                                                    .isHidden(getSkinnable().getAssociatedEntity())) {
					b.setVisible(false);
					continue;
				}
				b.setVisible(true);

				x = x - b.getWidth() - SPACING;

				ParallelTransition slideButton = new ParallelTransition();

				TranslateTransition slide = new TranslateTransition(Duration.millis(300), b);
				slide.setFromX(menuButton.getLayoutX() - b.getWidth());
				slide.setToX(x);
				slide.setInterpolator(EASE_OUT);

				FadeTransition fadeIn = new FadeTransition(Duration.millis(300), n);
				fadeIn.setFromValue(0.0f);
				fadeIn.setToValue(1.0f);
				fadeIn.setInterpolator(EASE_IN);

				slideButton.getChildren().addAll(fadeIn, slide);
				slideAllButtons.getChildren().add(slideButton);

				b.setVisible(true);
			}
		}

		FadeTransition fadeOut = new FadeTransition(Duration.millis(300), menuButton);
		fadeOut.setToValue(0.3f);
		slideAllButtons.getChildren().add(fadeOut);

		// always ensue the menu button is placed in front else it screws with the mouse enter/exit
		// events
		menuButton.toFront();
		slideAllButtons.play();
	}

	/**
	 * Hide the action menus using animation
	 */
	@Synchronized
	private void animateOut() {
		if (!actionsDisplayed || animationInProgress.get()) {
			return;
		}
		Guard lock = animationInProgress.suspend();

		actionsDisplayed = false;
		ParallelTransition slideAllButtons = new ParallelTransition();
		slideAllButtons.setOnFinished(arg0 -> lock.close());
		menuButton.toFront();

		for (Node n : actionPane.getChildren()) {
			if (n instanceof Button) {
				Button b = (Button) n;

				ParallelTransition slideButton = new ParallelTransition();

				TranslateTransition slide = new TranslateTransition(Duration.millis(150), b);
				slide.setToX(menuButton.getLayoutX() - b.getWidth());
				slide.setInterpolator(EASE_OUT);

				FadeTransition fadeIn = new FadeTransition(Duration.millis(150), n);
				fadeIn.setFromValue(1.0f);
				fadeIn.setToValue(0.0f);
				fadeIn.setInterpolator(EASE_IN);

				slideButton.getChildren().addAll(fadeIn, slide);
				slideButton.setOnFinished((e) -> b.setVisible(false));
				slideAllButtons.getChildren().add(slideButton);
			}
		}

		FadeTransition fadeIn = new FadeTransition(Duration.millis(300), menuButton);
		fadeIn.setToValue(1.0f);
		slideAllButtons.getChildren().add(fadeIn);

		slideAllButtons.play();

		getSkinnable().setSelectedAction(null);
	}

	/**
	 * Draw the menu items
	 */
	private void addMenuItems() {
		for (Action action : getSkinnable().getModel().getActions()) {
			Button b = buildMenuItem(action);
			b.setTooltip(new Tooltip(action.getType().name()));
			actionPane.getChildren().add(b);
			b.relocate(POPUP_WIDTH - menuButton.getWidth(), 0);
			b.toBack();
		}
	}

	/**
	 * Build an individual menu item
	 */
	private Button buildMenuItem(Action action) {
		Button b;
		b = new Button(action.getType().getFontAwesomeIcon());
		b.setLayoutX(menuButton.getLayoutX());
		b.setOpacity(0.0);
		b.setUserData(action);

		// when a menu item is clicked, update the selected item property of the control so that it
		// can be bound to
		b.setOnAction(event -> {
			getSkinnable().setSelectedAction(action);
			actionPopup.hide();
		});

		b.managedProperty().bind(b.visibleProperty());

		return b;
	}

	// TODO how do we call this?
	public void dispose() {
		if (subscriptions != null) {
			subscriptions.unsubscribe();
		}
	}
}
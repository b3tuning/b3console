package com.b3tuning.b3console.view.root;

import com.b3tuning.b3console.App;
import com.b3tuning.b3console.service.filemanager.FileManager;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.config.door.DoorConfigView;
import com.b3tuning.b3console.view.config.door.DoorConfigViewModel;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigView;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigViewModel;
import com.b3tuning.b3console.view.config.trans.TransConfigView;
import com.b3tuning.b3console.view.config.trans.TransConfigViewModel;
import com.b3tuning.b3console.view.help.HelpView;
import com.b3tuning.b3console.view.help.HelpViewModel;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.menu.MenuView;
import com.b3tuning.b3console.view.menu.MenuViewModel;
import com.b3tuning.b3console.view.notifications.PopViewNotification;
import com.b3tuning.b3console.view.notifications.PushViewNotification;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;
import org.reactfx.EventSource;

import javax.inject.Inject;

import static com.b3tuning.b3console.App.DEFAULT_CSS;
import static org.reactfx.EventStreams.changesOf;
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
public class RootViewModel extends BaseViewModel {

	public static final  String HELP_DETACHED_EVENT = "help_detached";
	private static final String HELP_STAGE_TITLE    = "B3Tuning Module Help";

	private static final double HELP_WIDTH = 0.3f;

	// injected
	@Setter
	@SuppressWarnings("unused")
	private       Application        application;
	@SuppressWarnings("unused")
	private final NotificationCenter globalNotifications;
	private final ViewManager        viewManager;
	private final FileManager        fileManager;

	// exposed properties
	private final ObjectProperty<StackPane> childViewPane = new SimpleObjectProperty<>();

	private final EventSource<Boolean>       displayHelp      = new EventSource<>();
	private final BooleanProperty            helpPaneVisible  = new SimpleBooleanProperty(false);
	private final DoubleProperty             helpPaneLocation = new SimpleDoubleProperty();
	private final DoubleProperty             helpPaneOpacity  = new SimpleDoubleProperty();
	private final BooleanProperty            initialized      = new SimpleBooleanProperty(false);
	private final ObjectProperty<ConfigBase> config           = new SimpleObjectProperty<>(null);

	private final ViewTuple<MenuView, MenuViewModel> menuViewTuple;

	@Inject
	public RootViewModel(NotificationCenter globalNotifications, ViewManager viewManager, FileManager manager) {
		log.entry();

		this.globalNotifications = globalNotifications;
		this.viewManager         = viewManager;
		this.fileManager         = manager;

		menuViewTuple = FluentViewLoader.fxmlView(MenuView.class).load();
		config.bindBidirectional(fileManager.configProperty());

		initNotifications();
	}

	private void initNotifications() {
		manage(nonNullValuesOf(childViewPane).subscribe(c -> {
			log.entry();

			// dispose of any loaded views that we may have
			viewManager.destroyAll(c);
			initialized.set(true);

			// any child views that need access to their parent are handled via
			// global notifications
			// to eliminate dependencies and possible memory leaks
			globalNotifications.subscribe(PushViewNotification.class.getName(), (key, payload) -> {
				log.entry(key, payload);
				PushViewNotification notification = (PushViewNotification) payload[0];
				viewManager.push(notification.getKey(), notification.getViewTuple(), childViewPane.get(),
				                 notification.isCloseOnLostFocus(), notification.isIgnoreDirty());
			});

			globalNotifications.subscribe(PopViewNotification.class.getName(), (key, payload) -> {
				log.entry(key, payload);
				viewManager.pop(childViewPane.get(), ((PopViewNotification) payload[0]).isReloadPage());
			});
		}));

		// detach the help from the sidebar if requested
		globalNotifications.subscribe(HELP_DETACHED_EVENT, (key, payload) -> detachHelp());

		manage(changesOf(config).subscribe(configBaseChange -> {
			log.entry(configBaseChange);
			if (null == configBaseChange.getNewValue()) {
				viewManager.destroyAll(childViewPane.get());
			} else {
				log.error("CONFIG CHANGE");
				log.error(configBaseChange.getNewValue().getClass().descriptorString());
				log.error(configBaseChange.getNewValue().getClass().toString());
				switch (configBaseChange.getNewValue().getType()) {
					case DOOR -> showDoorView();
					case SHIFTER -> showShifterView();
					case TRANS -> showTransView();
				}
			}
		}));
	}

	private void showDoorView() {
		log.entry();
		if (viewManager.contains(DoorConfigView.class.getName())) {
			viewManager.toFront(DoorConfigView.class.getName());
		} else {
			ViewTuple<DoorConfigView, DoorConfigViewModel> tuple = FluentViewLoader
					.fxmlView(DoorConfigView.class).load();
			viewManager.push(DoorConfigView.class.getName(), tuple, childViewPane.get());
			tuple.getViewModel().doorConfigProperty().set((DoorConfig) config.get());
			tuple.getViewModel().configProperty().bindBidirectional(config);
			tuple.getViewModel().bindConfigs();
		}
	}

	private void showShifterView() {
		log.entry();
		if (viewManager.contains(ShifterConfigView.class.getName())) {
			viewManager.toFront(ShifterConfigView.class.getName());
		} else {
			ViewTuple<ShifterConfigView, ShifterConfigViewModel> tuple = FluentViewLoader
					.fxmlView(ShifterConfigView.class).load();
			viewManager.push(ShifterConfigView.class.getName(), tuple, childViewPane.get());
		}
	}

	private void showTransView() {
		log.entry();
		if (viewManager.contains(TransConfigView.class.getName())) {
			viewManager.toFront(TransConfigView.class.getName());
		} else {
			ViewTuple<TransConfigView, TransConfigViewModel> tuple = FluentViewLoader
					.fxmlView(TransConfigView.class).load();
			viewManager.push(TransConfigView.class.getName(), tuple, childViewPane.get());
		}
	}

	public Node helpView() {
		return helpViewTuple().getView();
	}

	private ViewTuple<HelpView, HelpViewModel> helpViewTuple() {
		return FluentViewLoader.fxmlView(HelpView.class).load();
	}

	private void detachHelp() {
		log.entry();
		if (helpPaneVisible.get()) {
			slideHelpOut();
		}

		ViewTuple<HelpView, HelpViewModel> tuple = helpViewTuple();
		tuple.getViewModel().setDetached(true);

		Stage helpStage = new Stage();
		helpStage.setTitle(HELP_STAGE_TITLE);

		Scene        helpScene = new Scene(tuple.getView(), 640, 480);
		final String uri       = App.class.getResource(DEFAULT_CSS).toExternalForm();
		helpScene.getStylesheets().add(uri);
		helpStage.setScene(helpScene);
		viewManager.republishPageContextChanged();

		helpStage.show();
	}

//			// HELP actions
//			case A_HELP:
////				application.getHostServices().showDocument(appProperties.getUserHelpUrl().toString());
//				displayHelp.push(true);
//				break;
//
//			// TOOLS actions
//			case A_OPTIONS:
////				if (viewManager.contains(ConfigMenuView.class.getName())) {
////					viewManager.toFront(ConfigMenuView.class.getName());
////				} else {
////					ViewTuple<ConfigMenuView, ConfigMenuViewModel> tuple = FluentViewLoader
////							.fxmlView(ConfigMenuView.class).load();
////					viewManager.push(ConfigMenuView.class.getName(), tuple, childViewPane.get(), A_OPTIONS);
////				}
//				if (viewManager.contains(SettingsMenuView.class.getName())) {
//					viewManager.toFront(SettingsMenuView.class.getName());
//				} else {
//					ViewTuple<SettingsMenuView, SettingsMenuViewModel> tuple = FluentViewLoader
//							.fxmlView(SettingsMenuView.class).load();
//					viewManager.push(SettingsMenuView.class.getName(), tuple, childViewPane.get(), A_OPTIONS);
//				}
//				break;
//
//			// VIEW actions
//			// TODO: figure out view
//
//			default:
//				log.error(MENU_ITEM_ERROR, action);
//
//		}
//		globalNotifications.publish(action.toString(), action);
//	}

	/**
	 * HELP PAGE ANIMATION
	 */

	public void animateHelpPane() {
		if (helpPaneVisible.get()) {
			slideHelpOut();
		} else {
			slideHelpIn();
		}
	}

	private void slideHelpIn() {
		log.entry();
		helpPaneVisible.set(false);
		helpPaneLocation.set(0);
		helpPaneOpacity.set(0);
		KeyFrame kf1      = new KeyFrame(Duration.millis(1), new KeyValue(helpPaneVisible, true));
		KeyFrame kf2      = new KeyFrame(Duration.millis(200), new KeyValue(helpPaneLocation, HELP_WIDTH));
		KeyFrame kf3      = new KeyFrame(Duration.millis(200), new KeyValue(helpPaneOpacity, 0));
		KeyFrame kf4      = new KeyFrame(Duration.millis(300), new KeyValue(helpPaneOpacity, 1));
		Timeline timeline = new Timeline(kf1, kf2, kf3, kf4);
		timeline.play();
	}

	private void slideHelpOut() {
		log.entry();
		KeyFrame kf1 = new KeyFrame(Duration.millis(50), new KeyValue(helpPaneOpacity, 0));
		KeyFrame kf2 = new KeyFrame(Duration.millis(200), new KeyValue(helpPaneLocation, 0));
		KeyFrame kf3 = new KeyFrame(Duration.millis(201), new KeyValue(helpPaneVisible, false));

		Timeline timeline = new Timeline(kf1, kf2, kf3);
		timeline.play();
	}

	/**
	 * JAVAFX PROPERTIES
	 */
	public ObjectProperty<ConfigBase> configProperty() {
		return config;
	}

	public BooleanProperty initializedProperty() {
		return initialized;
	}

	public EventSource<Boolean> displayHelpStream() {
		return displayHelp;
	}

	public BooleanProperty helpPaneVisibleProperty() {
		return helpPaneVisible;
	}

	public DoubleProperty helpPaneLocationProperty() {
		return helpPaneLocation;
	}

	public void setHelpPaneLocation(double value) {
		helpPaneLocation.set(value);
	}

	public DoubleProperty helpPaneOpacityProperty() {
		return helpPaneOpacity;
	}

	public void setChildViewPane(StackPane value) {
		childViewPane.set(value);
	}

	public Parent getMenuView() {
		return menuViewTuple.getView();
	}
}

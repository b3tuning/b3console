package com.b3tuning.b3console.view.root;

import com.b3tuning.b3console.App;
import com.b3tuning.b3console.control.menubar.MenuItemInterface;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.files.filemanager.FileManager;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.help.HelpView;
import com.b3tuning.b3console.view.help.HelpViewModel;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.menu.MenuView;
import com.b3tuning.b3console.view.menu.MenuViewModel;
import com.b3tuning.b3console.view.notifications.PopViewNotification;
import com.b3tuning.b3console.view.notifications.PushViewNotification;
import com.b3tuning.b3console.view.settings.SettingsMenuView;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel;
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
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.util.Pair;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;
import org.reactfx.EventSource;

import javax.inject.Inject;

import static com.b3tuning.b3console.App.DEFAULT_CSS;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_OPTIONS;
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

	public static final  String CONFIG_LOADED       = "config_loaded";
	public static final  String HELP_DETACHED_EVENT = "help_detached";
	private static final String HELP_STAGE_TITLE    = "B3Tuning Module Help";
	private static final String MENU_ITEM_ERROR     = "Unrecognized menu item action: '{}'";

	private static final double HELP_WIDTH = 0.3f;

	// injected
	@Setter
	private       Application        application;
	private final AppProperties      appProperties;
	@SuppressWarnings("unused")
	private final NotificationCenter globalNotifications;
	private final ViewManager        viewManager;
	private final FileManager        fileManager;

	// exposed properties
	private final ObjectProperty<Pair<MenuItemInterface, ActionEvent>> selectedMenuBarItem = new SimpleObjectProperty<>();
	private final ObjectProperty<StackPane>                            childViewPane       = new SimpleObjectProperty<>();

	private final EventSource<Boolean>       displayHelp      = new EventSource<>();
	private final BooleanProperty            helpPaneVisible  = new SimpleBooleanProperty(false);
	private final DoubleProperty             helpPaneLocation = new SimpleDoubleProperty();
	private final DoubleProperty             helpPaneOpacity  = new SimpleDoubleProperty();
	private final BooleanProperty            initialized      = new SimpleBooleanProperty(false);
	private       ObjectProperty<ConfigBase> config           = new SimpleObjectProperty<>(null);

	private final ViewTuple<MenuView, MenuViewModel>         menuViewTuple;
//	private final ViewTuple<FileMenuView, FileMenuViewModel> fileMenuViewTuple;

	@Inject
	public RootViewModel(AppProperties appProperties, NotificationCenter globalNotifications, ViewManager viewManager,
	                     FileManager manager) {
		log.entry();

		this.appProperties       = appProperties;
		this.globalNotifications = globalNotifications;
		this.viewManager         = viewManager;
		this.fileManager         = manager;

		if (appProperties.getLogLevel().isEmpty()) {
			config = new SimpleObjectProperty<>(new ConfigBase());
		}
		manage(changesOf(config).subscribe(c -> {
			log.entry();
			globalNotifications.publish(CONFIG_LOADED, config.isNotNull().getValue());
		}));
		manage(nonNullValuesOf(childViewPane).subscribe(c -> {
			log.entry();

			// dispose of any loaded views that we may have
			viewManager.destroyAll(c);

			initialized.set(true);

			manage(nonNullValuesOf(selectedMenuBarItem).subscribe(e -> {
				log.entry();
				handleAction(e);
			}));

			// any child views that need access to their parent are handled via
			// global notifications
			// to eliminate dependencies and possible memory leaks
			globalNotifications.subscribe(PushViewNotification.class.getName(), (key, payload) -> {
				log.entry(key, payload);
				PushViewNotification notification = (PushViewNotification) payload[0];
				viewManager.push(notification.getKey(), notification.getViewTuple(), childViewPane.get(),
				                 notification.getAppArea(), notification.isCloseOnLostFocus(),
				                 notification.isIgnoreDirty());
			});

			globalNotifications.subscribe(PopViewNotification.class.getName(), (key, payload) -> {
				log.entry(key, payload);
				viewManager.pop(childViewPane.get(), ((PopViewNotification) payload[0]).isReloadPage());
			});

		}));

		// detach the help from the sidebar if requested
		globalNotifications.subscribe(HELP_DETACHED_EVENT, (key, payload) -> detachHelp());
		globalNotifications.subscribe("A_RECENTS", ((key, payload) -> {
			log.error("YAY");
			Menu item = (Menu) payload[0];
			item.getItems().add(new MenuItem("TEST"));
		}));

		menuViewTuple     = FluentViewLoader.fxmlView(MenuView.class).load();
//		fileMenuViewTuple = FluentViewLoader.fxmlView(FileMenuView.class).load();
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

	/**
	 * load the relevant view depending on the menu action taken
	 *
	 * @param action - the selected menu item
	 */
	private void handleAction(Pair<MenuItemInterface, ActionEvent> action) {
		log.entry();
		log.debug("RECEIVED ACTION {}", action);
		MenuItem    menuItem = (MenuItem) action.getValue().getTarget();
		ContextMenu cm       = menuItem.getParentPopup();
		Scene       scene    = cm.getScene();
		Window      stage    = scene.getWindow();
		switch (action.getKey().getAction()) {
			// EDIT actions
			case A_UNDO:
			case A_REDO:
			case A_CUT:
			case A_COPY:
			case A_DELETE:
				break;

			// FILE actions
			case A_NEW:
				showNewConfigView();
				break;
			case A_OPEN:
				showOpenConfigDialog(stage);
				break;
			case A_RECENTS:
			case A_CLOSE:
				break;
			case A_SAVE:
				showSaveConfigDialog();
				break;
			case A_SAVE_AS:
			case A_SEND:
			case A_QUIT:

				// ONLINE actions
			case A_CONNECT:
			case A_DISCONNECT:

			case A_MONITOR_IO:
				// TODO: load monitor view
				break;

			// HELP actions
			case A_HELP:
//				application.getHostServices().showDocument(appProperties.getUserHelpUrl().toString());
				displayHelp.push(true);
				break;

			// TOOLS actions
			case A_OPTIONS:
//				if (viewManager.contains(ConfigMenuView.class.getName())) {
//					viewManager.toFront(ConfigMenuView.class.getName());
//				} else {
//					ViewTuple<ConfigMenuView, ConfigMenuViewModel> tuple = FluentViewLoader
//							.fxmlView(ConfigMenuView.class).load();
//					viewManager.push(ConfigMenuView.class.getName(), tuple, childViewPane.get(), A_OPTIONS);
//				}
				if (viewManager.contains(SettingsMenuView.class.getName())) {
					viewManager.toFront(SettingsMenuView.class.getName());
				} else {
					ViewTuple<SettingsMenuView, SettingsMenuViewModel> tuple = FluentViewLoader
							.fxmlView(SettingsMenuView.class).load();
					viewManager.push(SettingsMenuView.class.getName(), tuple, childViewPane.get(), A_OPTIONS);
				}
				break;

			// VIEW actions
			// TODO: figure out view

			default:
				log.error(MENU_ITEM_ERROR, action);

		}
		globalNotifications.publish(action.toString(), action);
	}

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

	private void showNewConfigView() {
		log.entry();
		FileManager.createNewConfigDialog().ifPresent(configBase -> config.set(configBase));
	}

	private void showOpenConfigDialog(Window stage) {
		log.entry();
		ConfigBase base = fileManager.openFile(stage);
		log.warn("Opened config = {}", base);
	}

	private void showSaveConfigDialog() {
		log.entry();
		fileManager.saveConfig(config.get());
		menuViewTuple.getView();
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

	public double getHelpPaneLocation() {
		return helpPaneLocation.get();
	}

	public DoubleProperty helpPaneLocationProperty() {
		return helpPaneLocation;
	}

	public void setHelpPaneLocation(double value) {
		helpPaneLocation.set(value);
	}

	public double getHelpPaneOpacity() {
		return helpPaneOpacity.get();
	}

	public DoubleProperty helpPaneOpacityProperty() {
		return helpPaneOpacity;
	}

	public void setChildViewPane(StackPane value) {
		childViewPane.set(value);
	}

	public ObjectProperty<StackPane> childViewPaneProperty() {
		return childViewPane;
	}

	public ObjectProperty<Pair<MenuItemInterface, ActionEvent>> selectedMenuBarItemProperty() {
		return selectedMenuBarItem;
	}

	public Parent getMenuView() {
		return menuViewTuple.getView();
	}

//	public FileMenuView getFileMenuView() {
//		return fileMenuViewTuple.getCodeBehind();
//	}
}

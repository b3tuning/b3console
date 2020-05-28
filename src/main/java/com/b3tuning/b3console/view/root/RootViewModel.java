package com.b3tuning.b3console.view.root;

import com.b3tuning.b3console.App;
import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.control.menubar.MenuItemInterface;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.config.ConfigMenuView;
import com.b3tuning.b3console.view.config.ConfigMenuViewModel;
import com.b3tuning.b3console.view.help.HelpView;
import com.b3tuning.b3console.view.help.HelpViewModel;
import com.b3tuning.b3console.view.loader.ViewManager;
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

import static com.b3tuning.b3console.control.menubar.MenuAction.A_OPTIONS;
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

	public static final  String MENU_ACTION_EVENT   = "main_menu";
	public static final  String HELP_DETACHED_EVENT = "help_detached";
	public static final  String APP_CSS             = "app.css";
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

	// exposed properties
//	private ObjectProperty<MainMenuItemModel> selectedMenuItem    = new SimpleObjectProperty<>();
	private ObjectProperty<MenuItemInterface> selectedMenuBarItem = new SimpleObjectProperty<>();
	private ObjectProperty<StackPane>         childViewPane       = new SimpleObjectProperty<>();

	private EventSource<Boolean> displayHelp      = new EventSource<>();
	private BooleanProperty      helpPaneVisible  = new SimpleBooleanProperty(false);
	private DoubleProperty       helpPaneLocation = new SimpleDoubleProperty();
	private DoubleProperty       helpPaneOpacity  = new SimpleDoubleProperty();
	private BooleanProperty      initialized      = new SimpleBooleanProperty(false);

	@Inject
	public RootViewModel(AppProperties appProperties, NotificationCenter globalNotifications, ViewManager viewManager) {
		log.entry();

		this.appProperties       = appProperties;
		this.globalNotifications = globalNotifications;
		this.viewManager         = viewManager;

		manage(nonNullValuesOf(childViewPane).subscribe(c -> {
			log.entry();

			// dispose of any loaded views that we may have
//			viewManager.destroyAll(c);
//			publish(MENU_ACTION_EVENT, SETTINGS);
			initialized.set(true);
//			handleAction(SETTINGS);

//			manage(nonNullValuesOf(selectedMenuItem).subscribe(e -> {
//				log.entry();
//				handleAction(e.getAction());
//			}));

			manage(nonNullValuesOf(selectedMenuBarItem).subscribe(e -> {
				log.entry();
				log.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!    Got Menu {}", e.getAction());
				handleAction(e.getAction());
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
		globalNotifications.subscribe(HELP_DETACHED_EVENT, (key, payload) -> {
			detachHelp();
		});
	}

//	public ObservableList<MainMenuItemModel> menuItems() {
//		List<MainMenuItemModel> menuItems = new ArrayList<>();
//		menuItems.add(new MainMenuItemModel("FILE", FOLDER_OPEN, FILE, TOP, APP));
//		menuItems.add(new MainMenuItemModel("CONFIG", MODIFY, CONFIG, TOP, APP));
//		menuItems.add(new MainMenuItemModel("TRANSFER", EXCHANGE, TRANSFER, TOP, APP));
//		menuItems.add(new MainMenuItemModel("LIVE", TACHOMETER, LIVE, TOP, APP));
//		menuItems.add(new MainMenuItemModel("SETTINGS", WRENCH, SETTINGS, TOP, APP));
//		menuItems.add(new MainMenuItemModel("HELP", QUESTION, HELP_APP, BOTTOM, EXTERNAL));
//
//		return FXCollections.observableArrayList(menuItems);
//	}

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

		Scene        helpScene = new Scene((Parent) tuple.getView(), 640, 480);
		final String uri       = App.class.getResource(APP_CSS).toExternalForm();
		helpScene.getStylesheets().add(uri);
		helpStage.setScene(helpScene);
		viewManager.republishPageContextChanged();

		helpStage.show();
	}

//	/**
//	 * load the relevant view depending on the menu action taken
//	 *
//	 * @param action - the selected menu item
//	 */
//	private void handleAction(MainMenuItemAction action) {
//		log.entry();
////		HostServicesDelegate hostServices = HostServicesFactory.getInstance(application);
//		switch (action) {
//			case FILE:
//				if (viewManager.contains(FileMenuView.class.getName())) {
//					viewManager.toFront(FileMenuView.class.getName());
//				} else {
//					ViewTuple<FileMenuView, FileMenuViewModel> tuple = FluentViewLoader.fxmlView(FileMenuView.class)
//					                                                                   .load();
//					viewManager.push(FileMenuView.class.getName(), tuple, childViewPane.get(), FILE);
//				}
//				break;
//
//			case CONFIG:
//				if (viewManager.contains(ConfigMenuView.class.getName())) {
//					viewManager.toFront(ConfigMenuView.class.getName());
//				} else {
//					ViewTuple<ConfigMenuView, ConfigMenuViewModel> tuple = FluentViewLoader
//							.fxmlView(ConfigMenuView.class).load();
//					viewManager.push(ConfigMenuView.class.getName(), tuple, childViewPane.get(), CONFIG);
//				}
//				break;
//
//			case TRANSFER:
//				if (viewManager.contains(TransferMenuView.class.getName())) {
//					viewManager.toFront(TransferMenuView.class.getName());
//				} else {
//					ViewTuple<TransferMenuView, TransferMenuViewModel> tuple = FluentViewLoader
//							.fxmlView(TransferMenuView.class).load();
//					viewManager.push(TransferMenuView.class.getName(), tuple, childViewPane.get(), TRANSFER);
//				}
//				break;
//
//			case LIVE:
//				if (viewManager.contains(LiveMenuView.class.getName())) {
//					viewManager.toFront(LiveMenuView.class.getName());
//				} else {
//					ViewTuple<LiveMenuView, LiveMenuViewModel> tuple = FluentViewLoader.fxmlView(LiveMenuView.class)
//					                                                                   .load();
//					viewManager.push(LiveMenuView.class.getName(), tuple, childViewPane.get(), LIVE);
//				}
//				break;
//
//			case SETTINGS:
//				if (viewManager.contains(SettingsMenuView.class.getName())) {
//					viewManager.toFront(SettingsMenuView.class.getName());
//				} else {
//					ViewTuple<SettingsMenuView, SettingsMenuViewModel> tuple = FluentViewLoader
//							.fxmlView(SettingsMenuView.class).load();
//					viewManager.push(SettingsMenuView.class.getName(), tuple, childViewPane.get(), SETTINGS);
//				}
//				break;
//
//			case HELP_APP:
////				 hostServices.showDocument(appProperties.getUserHelpUrl().toString());
//				displayHelp.push(true);
//				break;
//
//			default:
//				log.error(MENU_ITEM_ERROR, action);
//		}
//	}

	/**
	 * load the relevant view depending on the menu action taken
	 *
	 * @param action - the selected menu item
	 */
	private void handleAction(MenuAction action) {
		log.entry();
//		HostServicesDelegate hostServices = HostServicesFactory.getInstance(application);
		switch (action) {

			case A_OPTIONS:
				if (viewManager.contains(ConfigMenuView.class.getName())) {
					viewManager.toFront(ConfigMenuView.class.getName());
				} else {
					ViewTuple<ConfigMenuView, ConfigMenuViewModel> tuple = FluentViewLoader
							.fxmlView(ConfigMenuView.class).load();
					viewManager.push(ConfigMenuView.class.getName(), tuple, childViewPane.get(), A_OPTIONS);
				}
				break;

			case A_HELP:
				application.getHostServices().showDocument(appProperties.getUserHelpUrl().toString());
//				 hostServices.showDocument(appProperties.getUserHelpUrl().toString());
				displayHelp.push(true);
				break;

			default:
				log.error(MENU_ITEM_ERROR, action);
		}
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

	/**
	 * JAVAFX PROPERTIES
	 */
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

//	public ObjectProperty<MainMenuItemModel> selectedMenuItemProperty() {
//		return selectedMenuItem;
//	}

	public ObjectProperty<MenuItemInterface> selectedMenuBarItemProperty() {
		return selectedMenuBarItem;
	}
}

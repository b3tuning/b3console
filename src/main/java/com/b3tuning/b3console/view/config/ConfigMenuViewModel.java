package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.config.door.DoorConfigView;
import com.b3tuning.b3console.view.config.door.DoorConfigViewModel;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigView;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigViewModel;
import com.b3tuning.b3console.view.config.trans.TransConfigView;
import com.b3tuning.b3console.view.config.trans.TransConfigViewModel;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.loader.ViewManagerImpl.ViewInfo;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class ConfigMenuViewModel extends BaseViewModel implements Refreshable {

	private final UserPreferences    preferences;
	private final NotificationCenter globalNotifications;
	private       ViewManager        viewManager;

//	private final ObjectProperty<ModuleType> module = new SimpleObjectProperty<>();

	private final ObjectProperty<StackPane> childViewPane = new SimpleObjectProperty<>();

	private ViewTuple<DoorConfigView, DoorConfigViewModel>       doorConfigViewTuple;
	private ViewTuple<ShifterConfigView, ShifterConfigViewModel> shifterConfigViewTuple;
	private ViewTuple<TransConfigView, TransConfigViewModel>     transConfigViewTuple;

	@Inject
	public ConfigMenuViewModel(UserPreferences prefs, NotificationCenter notifications, ViewManager viewManager) {
		log.entry();
		this.preferences         = prefs;
		this.globalNotifications = notifications;
		this.viewManager         = viewManager;

//		module.set(ModuleType.valueOf(preferences.getModule()));
//		globalNotifications.subscribe(MODULE_EVENT, (key, payload) -> module.set((ModuleType) (payload[0])));
	}

	private void showDoorConfigView() {
		log.entry();

		String key = DoorConfigView.class.getName();
		if (!viewManager.handledDirty(key)) {
			return;
		}
		if (viewManager.contains(key)) {
			viewManager.toFront(key);
		} else {
			ViewTuple<DoorConfigView, DoorConfigViewModel> viewTuple = FluentViewLoader.fxmlView(DoorConfigView.class)
			                                                                           .load();
			// TODO: check this
			viewManager.push(key, viewTuple, childViewPane.get(), MenuAction.A_NONE);
		}
	}

	private void showShifterConfigView() {
		log.entry();

		String key = ShifterConfigView.class.getName();
		if (!viewManager.handledDirty(key)) {
			return;
		}
		if (viewManager.contains(key)) {
			viewManager.toFront(key);
		} else {
			ViewTuple<ShifterConfigView, ShifterConfigViewModel> viewTuple = FluentViewLoader
					.fxmlView(ShifterConfigView.class).load();

			// TODO: check this
			viewManager.push(key, viewTuple, childViewPane.get(), MenuAction.A_NONE);
		}
	}

	private void showTransConfigView() {
		log.entry();

		String key = TransConfigView.class.getName();
		if (!viewManager.handledDirty(key)) {
			return;
		}
		if (viewManager.contains(key)) {
			viewManager.toFront(key);
		} else {
			ViewTuple<TransConfigView, TransConfigViewModel> viewTuple = FluentViewLoader
					.fxmlView(TransConfigView.class).load();

			// TODO: check this
			viewManager.push(key, viewTuple, childViewPane.get(), MenuAction.A_NONE);
		}
	}

	public void onDoorClicked() {
		log.entry();
		showDoorConfigView();
	}

	public void onShifterClicked() {
		log.entry();
		showShifterConfigView();
	}

	public void onTransClicked() {
		log.entry();
		showTransConfigView();
	}

	public void onCreateClicked() {
		log.entry();

	}

	public void loadConfigFromFile() {
		log.entry();

	}

	public void saveConfigToFile() {
		log.entry();

	}

	public void getConfigFromModule() {
		log.entry();

	}

	public void sendConfigToModule() {
		log.entry();

	}

//	public ObjectProperty<ModuleType> getModule() {
//		return module;
//	}

	public void setChildViewPane(StackPane value) {
		childViewPane.set(value);
	}

	public ObjectProperty<StackPane> childViewPaneProperty() {
		return childViewPane;
	}

	@Override
	public void refresh() {
		log.entry();
		ViewInfo currentView = viewManager.peek(childViewPane.get());
		if (currentView != null) {
			ViewModel vm = currentView.getViewTuple().getViewModel();
			if (vm instanceof Refreshable) {
				((Refreshable) vm).refresh();
			}
		}
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();

		viewManager.destroyAll(childViewPane.get());
		viewManager = null;
	}

}

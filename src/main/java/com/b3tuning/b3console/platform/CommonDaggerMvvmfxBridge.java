package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.edit.EditManager;
import com.b3tuning.b3console.service.filemanager.FileManager;
import com.b3tuning.b3console.view.config.CanBusConfigView;
import com.b3tuning.b3console.view.config.CanBusConfigViewModel;
import com.b3tuning.b3console.view.config.ConfigMenuView;
import com.b3tuning.b3console.view.config.ConfigMenuViewModel;
import com.b3tuning.b3console.view.config.door.DoorConfigView;
import com.b3tuning.b3console.view.config.door.DoorConfigViewModel;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigView;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigViewModel;
import com.b3tuning.b3console.view.config.trans.TransConfigView;
import com.b3tuning.b3console.view.config.trans.TransConfigViewModel;
import com.b3tuning.b3console.view.menu.MenuView;
import com.b3tuning.b3console.view.menu.MenuViewModel;
import com.b3tuning.b3console.view.menu.edit.EditMenuView;
import com.b3tuning.b3console.view.menu.edit.EditMenuViewModel;
import com.b3tuning.b3console.view.menu.file.FileMenuView;
import com.b3tuning.b3console.view.menu.file.FileMenuViewModel;
import com.b3tuning.b3console.view.menu.help.HelpMenuView;
import com.b3tuning.b3console.view.menu.help.HelpMenuViewModel;
import com.b3tuning.b3console.view.menu.online.OnlineMenuView;
import com.b3tuning.b3console.view.menu.online.OnlineMenuViewModel;
import com.b3tuning.b3console.view.menu.tools.ToolsMenuView;
import com.b3tuning.b3console.view.menu.tools.ToolsMenuViewModel;
import com.b3tuning.b3console.view.menu.view.ViewMenuView;
import com.b3tuning.b3console.view.menu.view.ViewMenuViewModel;
import com.b3tuning.b3console.view.root.RootView;
import com.b3tuning.b3console.view.root.RootViewModel;
import com.b3tuning.b3console.view.settings.SettingsMenuView;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.stage.FileChooser;
import lombok.Getter;
import lombok.Setter;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public abstract class CommonDaggerMvvmfxBridge implements DaggerMvvmfxBridge {

	@Getter
	@Setter
	private ApplicationComponents applicationComponents;

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> type) {

		if (AppProperties.class.equals(type)) {
			return (T) getApplicationComponents().provideAppProperties();
		}

		if (UserPreferences.class.equals(type)) {
			return (T) getApplicationComponents().provideUserPreferences();
		}

		if (DaggerMvvmfxBridge.class.equals(type)) {
			return (T) getApplicationComponents().provideDaggerMvvmfxBridge();
		}

		if (NotificationCenter.class.equals(type)) {
			return (T) getApplicationComponents().provideNotificationCenter();
		}

		if (FileManager.class.equals(type)) {
			return (T) getApplicationComponents().provideFileManager();
		}

		if (FileChooser.class.equals(type)) {
			return (T) getApplicationComponents().provideFileChooser();
		}

		if (EditManager.class.equals(type)) {
			return (T) getApplicationComponents().provideEditManager();
		}

		if (RootView.class.equals(type)) {
			return (T) getApplicationComponents().provideRootView();
		}

		if (RootViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideRootViewModel();
		}

		//////////////////////////////////////////////////////////////////
		///////////  MENU /////////////////
		///////////////////////////////////
		if (MenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideMenuView();
		}

		if (MenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideMenuViewModel();
		}

		if (EditMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideEditMenuView();
		}

		if (EditMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideEditMenuViewModel();
		}

		if (FileMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideFileMenuView();
		}

		if (FileMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideFileMenuViewModel();
		}

		if (HelpMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideHelpMenuView();
		}

		if (HelpMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideHelpMenuViewModel();
		}

		if (OnlineMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideOnlineMenuView();
		}

		if (OnlineMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideOnlineMenuViewModel();
		}

		if (ToolsMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideToolsMenuView();
		}

		if (ToolsMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideToolsMenuViewModel();
		}

		if (ViewMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideViewMenuView();
		}

		if (ViewMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideViewMenuViewModel();
		}

		//////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////

		if (ConfigMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideConfigMenuView();
		}

		if (ConfigMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideConfigMenuViewModel();
		}

		if (DoorConfigView.class.equals(type)) {
			return (T) getApplicationComponents().provideDoorConfigView();
		}

		if (DoorConfigViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideDoorConfigViewModel();
		}


		if (CanBusConfigView.class.equals(type)) {
			return (T) getApplicationComponents().provideCanBusConfigView();
		}

		if (CanBusConfigViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideCanBusConfigViewModel();
		}

		if (ShifterConfigView.class.equals(type)) {
			return (T) getApplicationComponents().provideShifterConfigView();
		}

		if (ShifterConfigViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideShifterConfigViewModel();
		}

		if (TransConfigView.class.equals(type)) {
			return (T) getApplicationComponents().provideTransConfigView();
		}

		if (TransConfigViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideTransConfigViewModel();
		}

		if (SettingsMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideSettingsMenuView();
		}

		if (SettingsMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideSettingsMenuViewModel();
		}

//		if (HelpView.class.equals(type)) {
//			return (T) getApplicationComponents().provideHelpView();
//		}
//
//		if (HelpViewModel.class.equals(type)) {
//			return (T) getApplicationComponents().provideHelpViewModel();
//		}

		return null;
	}
}

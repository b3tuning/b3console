package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.files.filemanager.FileManager;
import com.b3tuning.b3console.view.config.ConfigMenuView;
import com.b3tuning.b3console.view.config.ConfigMenuViewModel;
import com.b3tuning.b3console.view.config.door.DoorConfigView;
import com.b3tuning.b3console.view.config.door.DoorConfigViewModel;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigView;
import com.b3tuning.b3console.view.config.shifter.ShifterConfigViewModel;
import com.b3tuning.b3console.view.config.trans.TransConfigView;
import com.b3tuning.b3console.view.config.trans.TransConfigViewModel;
import com.b3tuning.b3console.view.help.HelpView;
import com.b3tuning.b3console.view.help.HelpViewModel;
import com.b3tuning.b3console.view.live.LiveMenuView;
import com.b3tuning.b3console.view.live.LiveMenuViewModel;
import com.b3tuning.b3console.view.menu.MenuView;
import com.b3tuning.b3console.view.menu.MenuViewModel;
import com.b3tuning.b3console.view.root.RootView;
import com.b3tuning.b3console.view.root.RootViewModel;
import com.b3tuning.b3console.view.settings.SettingsMenuView;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel;
import com.b3tuning.b3console.view.transfer.TransferMenuView;
import com.b3tuning.b3console.view.transfer.TransferMenuViewModel;
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

		if (RootView.class.equals(type)) {
			return (T) getApplicationComponents().provideRootView();
		}

		if (RootViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideRootViewModel();
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

		if (MenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideMenuView();
		}

		if (MenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideMenuViewModel();
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

		if (TransferMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideTransferMenuView();
		}

		if (TransferMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideTransferMenuViewModel();
		}

		if (LiveMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideLiveMenuView();
		}

		if (LiveMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideLiveMenuViewModel();
		}

		if (SettingsMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideSettingsMenuView();
		}

		if (SettingsMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideSettingsMenuViewModel();
		}

		if (HelpView.class.equals(type)) {
			return (T) getApplicationComponents().provideHelpView();
		}

		if (HelpViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideHelpViewModel();
		}

		return null;
	}
}

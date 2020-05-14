package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.settings.SettingsService;
import com.b3tuning.b3console.view.availablefiles.AvailableFilesViewModel;
import com.b3tuning.b3console.view.config.ConfigMenuView;
import com.b3tuning.b3console.view.config.ConfigMenuViewModel;
import com.b3tuning.b3console.view.help.HelpView;
import com.b3tuning.b3console.view.help.HelpViewModel;
import com.b3tuning.b3console.view.live.LiveMenuView;
import com.b3tuning.b3console.view.live.LiveMenuViewModel;
import com.b3tuning.b3console.view.root.RootView;
import com.b3tuning.b3console.view.root.RootViewModel;
import com.b3tuning.b3console.view.settings.SettingsMenuView;
import com.b3tuning.b3console.view.settings.SettingsMenuViewModel;
import com.b3tuning.b3console.view.transfer.TransferMenuView;
import com.b3tuning.b3console.view.transfer.TransferMenuViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public interface ApplicationComponents {

	/*  MISC  */
	void inject(AppProperties appProperties);

	AppProperties provideAppProperties();

	void inject(UserPreferences userPreferences);

	UserPreferences provideUserPreferences();

	void inject(DaggerMvvmfxBridge daggerMvvmfxBridge);

	DaggerMvvmfxBridge provideDaggerMvvmfxBridge();

	void inject(NotificationCenter notificationCenter);

	NotificationCenter provideNotificationCenter();

	/*  ROOT  */
	void inject(RootView view);

	RootView provideRootView();

	void inject(RootViewModel viewModel);

	RootViewModel provideRootViewModel();

	/*  FILE  */
//	void inject(FileMenuView view);
//	FileMenuView provideFileMenuView();
//
//	void inject(FileMenuViewModel viewModel);
//	FileMenuViewModel provideFileMenuViewModel();
//
	void inject(AvailableFilesViewModel viewModel);

	AvailableFilesViewModel provideAvailableFilesViewModel();

	/*  CONFIG  */
	void inject(ConfigMenuView view);

	ConfigMenuView provideConfigMenuView();

	void inject(ConfigMenuViewModel viewModel);

	ConfigMenuViewModel provideConfigMenuViewModel();

	/*  TRANSFER  */
	void inject(TransferMenuView view);

	TransferMenuView provideTransferMenuView();

	void inject(TransferMenuViewModel viewModel);

	TransferMenuViewModel provideTransferMenuViewModel();

	/*  LIVE  */
	void inject(LiveMenuView view);

	LiveMenuView provideLiveMenuView();

	void inject(LiveMenuViewModel viewModel);

	LiveMenuViewModel provideLiveMenuViewModel();

	/* SETTINGS */
	void inject(SettingsMenuView view);

	SettingsMenuView provideSettingsMenuView();

	void inject(SettingsMenuViewModel viewModel);

	SettingsMenuViewModel provideSettingsMenuViewModel();

	void inject(SettingsService settingsService);

	SettingsService provideSettingsService();

	/*  HELP  */
	void inject(HelpView view);

	HelpView provideHelpView();

	void inject(HelpViewModel viewModel);

	HelpViewModel provideHelpViewModel();

//	void inject(SerialComms comms);
//	SerialComms provideSerialComms();
//
//	void inject(DoorModuleService service);
//	DoorModuleService provideDoorModuleService();

}

package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.files.filemanager.FileManager;
import com.b3tuning.b3console.service.settings.SettingsService;
import com.b3tuning.b3console.view.availablefiles.AvailableFilesViewModel;
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
import com.b3tuning.b3console.view.menu.edit.EditMenuView;
import com.b3tuning.b3console.view.menu.edit.EditMenuViewModel;
import com.b3tuning.b3console.view.menu.file.FileMenuView;
import com.b3tuning.b3console.view.menu.file.FileMenuViewModel;
import com.b3tuning.b3console.view.menu.MenuView;
import com.b3tuning.b3console.view.menu.MenuViewModel;
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
import com.b3tuning.b3console.view.transfer.TransferMenuView;
import com.b3tuning.b3console.view.transfer.TransferMenuViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.stage.FileChooser;

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

	void inject(FileManager fileManager);

	FileManager provideFileManager();

	void inject(FileChooser chooser);

	FileChooser provideFileChooser();

	/*  ROOT  */
	void inject(RootView view);

	RootView provideRootView();

	void inject(RootViewModel viewModel);

	RootViewModel provideRootViewModel();

	/*  MENU  */

	void inject(MenuView view);

	MenuView provideMenuView();

	void inject(MenuViewModel viewModel);

	MenuViewModel provideMenuViewModel();

	void inject(EditMenuView view);

	EditMenuView provideEditMenuView();

	void inject(EditMenuViewModel viewModel);

	EditMenuViewModel provideEditMenuViewModel();

	void inject(FileMenuView view);

	FileMenuView provideFileMenuView();

	void inject(FileMenuViewModel viewModel);

	FileMenuViewModel provideFileMenuViewModel();

	void inject(HelpMenuView view);

	HelpMenuView provideHelpMenuView();

	void inject(HelpMenuViewModel viewModel);

	HelpMenuViewModel provideHelpMenuViewModel();

	void inject(OnlineMenuView view);

	OnlineMenuView provideOnlineMenuView();

	void inject(OnlineMenuViewModel viewModel);

	OnlineMenuViewModel provideOnlineMenuViewModel();

	void inject(ToolsMenuView view);

	ToolsMenuView provideToolsMenuView();

	void inject(ToolsMenuViewModel viewModel);

	ToolsMenuViewModel provideToolsMenuViewModel();

	void inject(ViewMenuView view);

	ViewMenuView provideViewMenuView();

	void inject(ViewMenuViewModel viewModel);

	ViewMenuViewModel provideViewMenuViewModel();

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////

	void inject(AvailableFilesViewModel viewModel);

	AvailableFilesViewModel provideAvailableFilesViewModel();

	/*  CONFIG  */
	void inject(ConfigMenuView view);

	ConfigMenuView provideConfigMenuView();

	void inject(ConfigMenuViewModel viewModel);

	ConfigMenuViewModel provideConfigMenuViewModel();

	void inject(DoorConfigView view);

	DoorConfigView provideDoorConfigView();

	void inject(DoorConfigViewModel viewModel);

	DoorConfigViewModel provideDoorConfigViewModel();

	void inject(ShifterConfigView view);

	ShifterConfigView provideShifterConfigView();

	void inject(ShifterConfigViewModel viewModel);

	ShifterConfigViewModel provideShifterConfigViewModel();

	void inject(TransConfigView view);

	TransConfigView provideTransConfigView();

	void inject(TransConfigViewModel viewModel);

	TransConfigViewModel provideTransConfigViewModel();

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

}

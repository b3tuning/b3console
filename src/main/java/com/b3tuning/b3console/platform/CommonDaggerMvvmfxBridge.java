package com.b3tuning.b3console.platform;

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

		if (RootView.class.equals(type)) {
			return (T) getApplicationComponents().provideRootView();
		}

		if (RootViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideRootViewModel();
		}

//		if (FileMenuView.class.equals(type)) {
//			return (T) getApplicationComponents().provideFileMenuView();
//		}
//
//		if (FileMenuViewModel.class.equals(type)) {
//			return (T) getApplicationComponents().provideFileMenuViewModel();
//		}
//
		if (ConfigMenuView.class.equals(type)) {
			return (T) getApplicationComponents().provideConfigMenuView();
		}

		if (ConfigMenuViewModel.class.equals(type)) {
			return (T) getApplicationComponents().provideConfigMenuViewModel();
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

//		if (SerialComms.class.equals(type)) {
//			return (T) getApplicationComponents().provideSerialComms();
//		}
//
//		if (DoorModuleService.class.equals(type)) {
//			return (T) getApplicationComponents().provideDoorModuleService();
//		}

		return null;
	}

}

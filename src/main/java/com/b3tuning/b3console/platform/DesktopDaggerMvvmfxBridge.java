package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.view.file.DesktopAvailableFilesView;
import com.b3tuning.b3console.view.file.DesktopAvailableFilesViewModel;
import com.b3tuning.b3console.view.file.DesktopFilesDialogView;
import com.b3tuning.b3console.view.file.DesktopFilesDialogViewModel;
import com.b3tuning.b3console.view.file.DesktopLocalFilesView;
import com.b3tuning.b3console.view.file.DesktopLocalFilesViewModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.XSlf4j;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class DesktopDaggerMvvmfxBridge extends CommonDaggerMvvmfxBridge {

	@SuppressWarnings("unchecked")
	@SneakyThrows
	@Override
	public <T> T getInstance(Class<T> type) {
		log.entry(type.getName());

		T instance = super.getInstance(type);
		if (instance != null) {
			return instance;
		}

		DesktopComponents desktopComponents = (DesktopComponents) getApplicationComponents();

		if (DesktopAvailableFilesView.class.equals(type)) {
			return (T) desktopComponents.provideDesktopAvailableFilesView();
		}
		if (DesktopAvailableFilesViewModel.class.equals(type)) {
			return (T) desktopComponents.provideDesktopAvailableFilesViewModel();
		}
		if (DesktopLocalFilesView.class.equals(type)) {
			return (T) desktopComponents.provideDesktopLocalFilesView();
		}
		if (DesktopLocalFilesViewModel.class.equals(type)) {
			return (T) desktopComponents.provideDesktopLocalFilesViewModel();
		}

		if (DesktopFilesDialogView.class.equals(type)) {
			return (T) desktopComponents.provideDesktopFilesDialogView();
		}
		if (DesktopFilesDialogViewModel.class.equals(type)) {
			return (T) desktopComponents.provideDesktopFilesDialogViewModel();
		}

		throw new Throwable(String.format(
				"Requested class `%s` required by mvvmfx but not mapped from Dagger", type));
	}

}

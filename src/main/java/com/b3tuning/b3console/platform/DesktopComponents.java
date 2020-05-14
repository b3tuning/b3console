package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.view.file.DesktopAvailableFilesView;
import com.b3tuning.b3console.view.file.DesktopAvailableFilesViewModel;
import com.b3tuning.b3console.view.file.DesktopFilesDialogView;
import com.b3tuning.b3console.view.file.DesktopFilesDialogViewModel;
import com.b3tuning.b3console.view.file.DesktopLocalFilesView;
import com.b3tuning.b3console.view.file.DesktopLocalFilesViewModel;
import dagger.Component;

import javax.inject.Singleton;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Singleton
@Component(modules = {CommonDependenciesModule.class, DesktopDependenciesModule.class})
public interface DesktopComponents extends ApplicationComponents {


	void inject(DesktopAvailableFilesView view);

	DesktopAvailableFilesView provideDesktopAvailableFilesView();

	void inject(DesktopAvailableFilesViewModel viewModel);

	DesktopAvailableFilesViewModel provideDesktopAvailableFilesViewModel();

	void inject(DesktopLocalFilesView view);

	DesktopLocalFilesView provideDesktopLocalFilesView();

	void inject(DesktopLocalFilesViewModel model);

	DesktopLocalFilesViewModel provideDesktopLocalFilesViewModel();

	void inject(DesktopFilesDialogView view);

	DesktopFilesDialogView provideDesktopFilesDialogView();

	void inject(DesktopFilesDialogViewModel viewModel);

	DesktopFilesDialogViewModel provideDesktopFilesDialogViewModel();

}

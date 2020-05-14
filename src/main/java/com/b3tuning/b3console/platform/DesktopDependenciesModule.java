package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.service.files.FilesService;
import com.b3tuning.b3console.service.utils.FileInspectorService;
import com.b3tuning.b3console.service.utils.FileInspectorServiceImpl;
import com.b3tuning.b3console.view.availablefiles.AvailableFilesViewModel;
import com.b3tuning.b3console.view.file.DesktopAvailableFilesViewModel;
import dagger.Provides;
import dagger.Module;
import lombok.extern.slf4j.XSlf4j;
import org.apache.tika.Tika;

import javax.inject.Singleton;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
@Module
public class DesktopDependenciesModule {

	@Provides
	@Singleton
	DaggerMvvmfxBridge daggerMvvmfxBridge() {
		return new DesktopDaggerMvvmfxBridge();
	}

	@Provides
	AvailableFilesViewModel provideAvailableFilesViewModel(FilesService filesService) {
		log.entry();
		return new DesktopAvailableFilesViewModel(filesService);
	}

	@Provides
	@Singleton
	Tika provideTika() {
		log.entry();
		return new Tika();
	}

	@Provides
	@Singleton
	FileInspectorService provideFileInspectorService(AppProperties appProperties, Tika tika) {
		log.entry();
		return new FileInspectorServiceImpl(appProperties, tika);
	}

}

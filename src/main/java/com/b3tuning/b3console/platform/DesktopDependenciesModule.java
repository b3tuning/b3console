package com.b3tuning.b3console.platform;

import dagger.Module;
import dagger.Provides;
import lombok.extern.slf4j.XSlf4j;

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
}

package com.b3tuning.b3console.platform;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class DesktopInjector implements PlatformInjector {

	@Override
	public ApplicationComponents create() {
		return DaggerDesktopComponents.builder()
				.desktopDependenciesModule(new DesktopDependenciesModule())
				.commonDependenciesModule(new CommonDependenciesModule())
				.build();
	}
}

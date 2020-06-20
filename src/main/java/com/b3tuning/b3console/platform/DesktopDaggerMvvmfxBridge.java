package com.b3tuning.b3console.platform;

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

	@SneakyThrows
	@Override
	public <T> T getInstance(Class<T> type) {
		log.entry(type.getName());

		T instance = super.getInstance(type);
		if (instance != null) {
			return instance;
		}

//		DesktopComponents desktopComponents = (DesktopComponents) getApplicationComponents();

		throw new Throwable(String.format(
				"Requested class `%s` required by mvvmfx but not mapped from Dagger", type));
	}

}

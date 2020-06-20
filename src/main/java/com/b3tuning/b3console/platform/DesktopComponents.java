package com.b3tuning.b3console.platform;

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

}

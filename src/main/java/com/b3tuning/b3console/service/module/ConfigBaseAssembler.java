/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ConfigBaseAssembler is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module;

public class ConfigBaseAssembler {

	public static ConfigBase assemble(ConfigBaseResource resource) {
		return new ConfigBase(resource.getName(), resource.getCreatedAt(), resource.getUpdatedAt(), resource.getType());
	}
	public static ConfigBaseResource assemble(ConfigBase config) {
		return new ConfigBaseResource(config.getName(), config.getCreatedAt(), config.getUpdatedAt(), config.getType());
	}
}

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

import com.b3tuning.b3console.service.module.door.DoorConfigAssembler;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource;
import com.b3tuning.b3console.service.module.shifter.ShifterAssembler;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource;
import com.b3tuning.b3console.service.module.trans.TransConfigAssembler;
import com.b3tuning.b3console.service.module.trans.config.TransConfig;
import com.b3tuning.b3console.service.module.trans.resource.TransConfigResource;

public class ConfigBaseAssembler {

	public static ConfigBase assemble(ConfigBaseResource resource) {
		return switch (resource.getType()) {
			case DOOR -> DoorConfigAssembler.assemble((DoorConfigResource) resource);
			case SHIFTER -> ShifterAssembler.assemble((ShifterConfigResource) resource);
			case TRANS -> TransConfigAssembler.assemble((TransConfigResource) resource);
		};
	}

	public static ConfigBaseResource assemble(ConfigBase config) {
		return switch (config.getType().get()) {
			case DOOR -> DoorConfigAssembler.assemble((DoorConfig) config);
			case SHIFTER -> ShifterAssembler.assemble((ShifterConfig) config);
			case TRANS -> TransConfigAssembler.assemble((TransConfig) config);
		};
	}
}

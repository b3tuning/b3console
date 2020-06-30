package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorActionConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorSelectConfig;
import com.b3tuning.b3console.service.module.door.config.WindowActionConfig;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource.MirrorActionResource;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource.MirrorSelectResource;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource.WindowActionResource;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class DoorConfigAssembler extends ConfigBaseAssembler {

	public static DoorConfig assemble(DoorConfigResource resource) {
		return new DoorConfig(resource.getType(),
		                      assemble(resource.getMirrorAction()),
		                      assemble(resource.getMirrorSelect()),
		                      assemble(resource.getWindowAction()),
		                      resource.getDriverWindowMaxCurrent(),
		                      resource.getPassengerWindowMaxCurrent());
	}

	public static MirrorActionConfig assemble(MirrorActionResource resource) {
		return new MirrorActionConfig(resource.getDownMax(),
		                              resource.getDownMin(),
		                              resource.getLeftMax(),
		                              resource.getLeftMin(),
		                              resource.getRightMax(),
		                              resource.getRightMin(),
		                              resource.getUpMax(),
		                              resource.getUpMin());
	}

	public static MirrorSelectConfig assemble(MirrorSelectResource resource) {
		return new MirrorSelectConfig(resource.getDriverMax(),
		                              resource.getDriverMin(),
		                              resource.getFoldMax(),
		                              resource.getFoldMin(),
		                              resource.getPassengerMax(),
		                              resource.getPassengerMin());
	}

	public static WindowActionConfig assemble(WindowActionResource resource) {
		return new WindowActionConfig(resource.getAutoDownMax(),
		                              resource.getDownMin(),
		                              resource.getAutoUpMax(),
		                              resource.getAutoUpMin(),
		                              resource.getDownMax(),
		                              resource.getDownMin(),
		                              resource.getUpMax(),
		                              resource.getUpMin());
	}

	public static DoorConfigResource assemble(DoorConfig config) {
		return new DoorConfigResource(assemble(config.getMirrorAction()),
		                              assemble(config.getMirrorSelect()),
		                              assemble(config.getWindowAction()),
		                              config.getDriverWindowMaxCurrent(),
		                              config.getPassengerWindowMaxCurrent())
				.setSuperType(config.getType());
	}

	public static MirrorActionResource assemble(MirrorActionConfig config) {
		return new MirrorActionResource(config.getDownMax(),
		                                config.getDownMin(),
		                                config.getLeftMax(),
		                                config.getLeftMin(),
		                                config.getRightMax(),
		                                config.getRightMin(),
		                                config.getUpMax(),
		                                config.getUpMin());
	}

	public static MirrorSelectResource assemble(MirrorSelectConfig config) {
		return new MirrorSelectResource(config.getDriverMax(),
		                                config.getDriverMin(),
		                                config.getFoldMax(),
		                                config.getFoldMin(),
		                                config.getPassengerMax(),
		                                config.getPassengerMin());
	}

	public static WindowActionResource assemble(WindowActionConfig config) {
		return new WindowActionResource(config.getAutoDownMax(),
		                                config.getAutoDownMin(),
		                                config.getAutoUpMax(),
		                                config.getAutoUpMin(),
		                                config.getDownMax(),
		                                config.getDownMin(),
		                                config.getUpMax(),
		                                config.getUpMin());
	}
}

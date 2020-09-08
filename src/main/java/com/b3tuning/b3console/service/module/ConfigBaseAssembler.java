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
import com.b3tuning.b3console.service.module.shifter.ShifterAssembler;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.service.module.trans.TransConfigAssembler;
import com.b3tuning.b3console.service.module.trans.config.TransConfig;
import com.b3tuning.b3console.service.protobuf.ConfigMessage;

public class ConfigBaseAssembler {

	public static ConfigBase assemble(ConfigMessage.ConfigBaseMessage message) {
		return switch (message.getType()) {
			case DOOR -> DoorConfigAssembler.assemble(message.getDoor());
			case SHIFTER -> ShifterAssembler.assemble(message.getShifter());
			case TRANS -> TransConfigAssembler.assemble(message.getTrans());
			case UNRECOGNIZED -> null;
		};
	}

	public static ConfigMessage.ConfigBaseMessage assemble(ConfigBase config) {
		return switch (config.getType()) {
			case DOOR -> DoorConfigAssembler.assembleBase((DoorConfig) config);
			case SHIFTER -> ShifterAssembler.assembleBase((ShifterConfig) config);
			case TRANS -> TransConfigAssembler.assembleBase((TransConfig) config);
		};
	}
}

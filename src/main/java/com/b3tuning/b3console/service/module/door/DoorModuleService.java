package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.comms.SerialComms;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.protobuf.ConfigMessage;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  May 06, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class DoorModuleService {

	private final SerialComms         comms;

	@Inject
	public DoorModuleService(SerialComms comms) {
		this.comms     = comms;
	}

	// TODO: implement either (CoAP SLipMux) or (???) over JSerialComm here

	public DoorConfig getDoorConfig() {
		ConfigMessage.ConfigBaseMessage resource = comms.door();
		if (null == resource) {
			log.error("Unable to get DoorConfigResource from module");
			return null;
		}
		return DoorConfigAssembler.assemble(resource.getDoor());
	}
//  	public DoorConfig updateDoorConfig(DoorConfigRequest request) {
//		DoorConfigResource resource = new DoorConfigResource();
//		return assembler.assemble(resource);
//	}
}

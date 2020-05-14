package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.comms.SerialComms;
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


	private final DoorConfigAssembler assembler;
	private final SerialComms         comms;

	@Inject
	public DoorModuleService(DoorConfigAssembler assembler, SerialComms comms) {
		this.assembler = assembler;
		this.comms     = comms;
	}

	// TODO: implement either (CoAP SLipMux) or (???) over JSerialComm here

	public DoorConfig getDoorConfig() {
		DoorConfigResource resource = comms.door();
		if (null == resource) {
			log.error("Unable to get DoorConfigResource from module");
			return null;
		}
		return assembler.assemble(resource);
	}
//  	public DoorConfig updateDoorConfig(DoorConfigRequest request) {
//		DoorConfigResource resource = new DoorConfigResource();
//		return assembler.assemble(resource);
//	}
}

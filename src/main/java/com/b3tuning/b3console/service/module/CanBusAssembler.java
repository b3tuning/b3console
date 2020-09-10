package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.protobuf.ConfigMessage;
import lombok.extern.slf4j.XSlf4j;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class CanBusAssembler {
	public static ConfigMessage.CanBusConfigMessage assemble(CanBusConfig config) {
		if (config == null) {
			log.error("CANBUS CONFIG IS NULLL!!!!!!!!!!!!!!");
		}
		if (config.getAddrTX() == null) {
			log.error("ADDR TX IS NULLL IN CANBUS CONFIG");
		}
		return ConfigMessage.CanBusConfigMessage.newBuilder()
				.setAddrTx(config.getAddrTX())
				.setBootRx(config.getBootRX())
				.setBootTx(config.getBootTX())
				.setIntvlTx(config.getIntvlTX())
				.setMask0(config.getMask0())
				.setFilter00(config.getFilter0_0())
				.setFilter01(config.getFilter0_1())
				.setMask1(config.getMask1())
				.setFilter10(config.getFilter1_0())
				.setFilter11(config.getFilter1_1())
				.setFilter12(config.getFilter1_2())
				.setFilter13(config.getFilter1_3())
				.build();
	}

	public static CanBusConfig assemble(ConfigMessage.CanBusConfigMessage message) {
		return new CanBusConfig(message.getAddrTx(),
				message.getBootRx(),
				message.getBootTx(),
				message.getIntvlTx(),
				message.getMask0(),
				message.getFilter00(),
				message.getFilter01(),
				message.getMask1(),
				message.getFilter10(),
				message.getFilter11(),
				message.getFilter12(),
				message.getFilter13());
	}

//	public static CanBusConfig assemble(CanBusResource resource) {
//		return new CanBusConfig(resource.getAddrTX(),
//		                        resource.getBootRX(),
//		                        resource.getBootTX(),
//		                        resource.getIntvlTX(),
//		                        resource.getMask0(),
//		                        resource.getFilter0_0(),
//		                        resource.getFilter0_1(),
//		                        resource.getMask1(),
//		                        resource.getFilter1_0(),
//		                        resource.getFilter1_1(),
//		                        resource.getFilter1_2(),
//		                        resource.getFilter1_3());
//	}

//	public static CanBusResource assemble(CanBusConfig config) {
//		return new CanBusResource(config.getAddrTX(),
//		                          config.getBootRX(),
//		                          config.getBootTX(),
//		                          config.getIntvlTX(),
//		                          config.getMask0(),
//		                          config.getFilter0_0(),
//		                          config.getFilter0_1(),
//		                          config.getMask1(),
//		                          config.getFilter1_0(),
//		                          config.getFilter1_1(),
//		                          config.getFilter1_2(),
//		                          config.getFilter1_3());
//	}
}

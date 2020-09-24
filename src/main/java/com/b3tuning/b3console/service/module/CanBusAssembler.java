package com.b3tuning.b3console.service.module;

import com.b3tuning.b3console.service.protobuf.ConfigMessage;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class CanBusAssembler {
	public static ConfigMessage.CanBusConfigMessage assemble(CanBusConfig config) {
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
}

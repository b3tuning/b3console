package com.b3tuning.b3console.service.module.trans;

import com.b3tuning.b3console.service.module.CanBusAssembler;
import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.trans.config.Ems22AConfig;
import com.b3tuning.b3console.service.module.trans.config.PidConfig;
import com.b3tuning.b3console.service.module.trans.config.TransConfig;
import com.b3tuning.b3console.service.module.trans.config.Vnh5019Config;
import com.b3tuning.b3console.service.protobuf.ConfigMessage;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class TransConfigAssembler extends ConfigBaseAssembler {

	public static TransConfig assemble(ConfigMessage.TransMessage message) {
		return new TransConfig(CanBusAssembler.assemble(message.getCanBusConfig()),
				assemble(message.getEms22AConfig()),
				assemble(message.getVnh5019Config()),
				assemble(message.getPidConfig()));
	}

	public static ConfigMessage.ConfigBaseMessage assembleBase(TransConfig config) {
		return ConfigMessage.ConfigBaseMessage.newBuilder()
				.setType(ConfigMessage.Type.TRANS)
				.setTrans(assemble(config))
				.build();
	}

	private static ConfigMessage.TransMessage assemble(TransConfig config) {
		return ConfigMessage.TransMessage.newBuilder()
				.setCanBusConfig(CanBusAssembler.assemble(config.getCanBus()))
				.setEms22AConfig(assemble(config.getEms22A()))
				.setVnh5019Config(assemble(config.getVnh5019()))
				.setPidConfig(assemble(config.getPid()))
				.build();
	}

	private static Ems22AConfig assemble(ConfigMessage.TransMessage.Ems22AConfigMessage message) {
		return new Ems22AConfig(message.getEncMax(),
				message.getEncMin());
	}

	private static ConfigMessage.TransMessage.Ems22AConfigMessage assemble(Ems22AConfig config) {
		return ConfigMessage.TransMessage.Ems22AConfigMessage.newBuilder()
				.setEncMax(config.getEncMax())
				.setEncMin(config.getEncMin())
				.build();
	}

	private static Vnh5019Config assemble(ConfigMessage.TransMessage.Vnh5019ConfigMessage message) {
		return new Vnh5019Config(message.getMaxCurrent());
	}

	private static ConfigMessage.TransMessage.Vnh5019ConfigMessage assemble(Vnh5019Config config) {
		return ConfigMessage.TransMessage.Vnh5019ConfigMessage.newBuilder()
				.setMaxCurrent(config.getMaxCurrent())
				.build();
	}

	private static PidConfig assemble(ConfigMessage.TransMessage.PidConfigMessage message) {
		return new PidConfig(message.getAggKd(),
				message.getAggKi(),
				message.getAggKp(),
				message.getConsKd(),
				message.getConsKi(),
				message.getConsKp());
	}

	private static ConfigMessage.TransMessage.PidConfigMessage assemble(PidConfig config) {
		return ConfigMessage.TransMessage.PidConfigMessage.newBuilder()
				.setAggKd(config.getAggKd())
				.setAggKi(config.getAggKi())
				.setAggKp(config.getAggKp())
				.setConsKd(config.getConsKd())
				.setConsKi(config.getConsKi())
				.setConsKp(config.getConsKp())
				.build();
	}
}

package com.b3tuning.b3console.service.module.trans;

import com.b3tuning.b3console.service.module.CanBusAssembler;
import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.trans.config.Ems22AConfig;
import com.b3tuning.b3console.service.module.trans.config.PidConfig;
import com.b3tuning.b3console.service.module.trans.config.TransConfig;
import com.b3tuning.b3console.service.module.trans.config.Vnh5019Config;
import com.b3tuning.b3console.service.module.trans.resource.TransConfigResource;
import com.b3tuning.b3console.service.module.trans.resource.TransConfigResource.Ems22AResource;
import com.b3tuning.b3console.service.module.trans.resource.TransConfigResource.PidResource;
import com.b3tuning.b3console.service.module.trans.resource.TransConfigResource.Vnh5019Resource;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class TransConfigAssembler extends ConfigBaseAssembler {

	public static TransConfig assemble(TransConfigResource resource) {
		return new TransConfig(resource.getType(),
		                       CanBusAssembler.assemble(resource.getCanBus()),
		                       assemble(resource.getEms22A()),
		                       assemble(resource.getVnh5019()),
		                       assemble(resource.getPid()));
	}

	public static Ems22AConfig assemble(Ems22AResource resource) {
		return new Ems22AConfig(resource.getEncMax(),
		                        resource.getEncMin());
	}

	public static Vnh5019Config assemble(Vnh5019Resource resource) {
		return new Vnh5019Config(resource.getMaxCurrent());
	}

	public static PidConfig assemble(PidResource resource) {
		return new PidConfig(resource.getAggKd(),
		                     resource.getAggKi(),
		                     resource.getAggKp(),
		                     resource.getConsKd(),
		                     resource.getConsKi(),
		                     resource.getConsKp());
	}

	public static TransConfigResource assemble(TransConfig config) {
		return new TransConfigResource(CanBusAssembler.assemble(config.getCanBus().get()),
		                               assemble(config.getEms22A().get()),
		                               assemble(config.getVnh5019().get()),
		                               assemble(config.getPid().get()))
				.setSuperType(config.getType().get());
	}

	public static Ems22AResource assemble(Ems22AConfig config) {
		return new Ems22AResource(config.getEncMax().get(),
		                          config.getEncMin().get());
	}

	public static Vnh5019Resource assemble(Vnh5019Config config) {
		return new Vnh5019Resource(config.getMaxCurrent().get());
	}

	public static PidResource assemble(PidConfig config) {
		return new PidResource(config.getAggKd().get(),
		                       config.getAggKi().get(),
		                       config.getAggKp().get(),
		                       config.getConsKd().get(),
		                       config.getConsKi().get(),
		                       config.getConsKp().get());
	}
}

package com.b3tuning.b3console.service.module;

/*
 *  Created on:  May 05, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class CanBusAssembler {
	public static CanBusConfig assemble(CanBusResource resource) {
		return new CanBusConfig(resource.getAddrTX(),
		                        resource.getBootRX(),
		                        resource.getBootTX(),
		                        resource.getIntvlTX(),
		                        resource.getMask0(),
		                        resource.getFilter0_0(),
		                        resource.getFilter0_1(),
		                        resource.getMask1(),
		                        resource.getFilter1_0(),
		                        resource.getFilter1_1(),
		                        resource.getFilter1_2(),
		                        resource.getFilter1_3());
	}

	public static CanBusResource assemble(CanBusConfig config) {
		return new CanBusResource(config.getAddrTX().get(),
		                          config.getBootRX().get(),
		                          config.getBootTX().get(),
		                          config.getIntvlTX().get(),
		                          config.getMask0().get(),
		                          config.getFilter0_0().get(),
		                          config.getFilter0_1().get(),
		                          config.getMask1().get(),
		                          config.getFilter1_0().get(),
		                          config.getFilter1_1().get(),
		                          config.getFilter1_2().get(),
		                          config.getFilter1_3().get());
	}
}

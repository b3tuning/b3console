package com.b3tuning.b3console.service.module.shifter;

import com.b3tuning.b3console.service.module.CanBusAssembler;
import com.b3tuning.b3console.service.module.shifter.ShifterConfig.IndicatorConfig;
import com.b3tuning.b3console.service.module.shifter.ShifterConfig.MelexisConfig;
import com.b3tuning.b3console.service.module.shifter.ShifterConfig.ShifterPositionConfig;
import com.b3tuning.b3console.service.module.shifter.ShifterConfigResource.IndicatorResource;
import com.b3tuning.b3console.service.module.shifter.ShifterConfigResource.MelexisResource;
import com.b3tuning.b3console.service.module.shifter.ShifterConfigResource.ShifterPositionResource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

/*
 *  Created on:  May 01, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class ShifterAssembler {

	public com.b3tuning.b3console.service.module.shifter.ShifterConfig assemble(
			com.b3tuning.b3console.service.module.shifter.ShifterConfigResource resource) {
		return new com.b3tuning.b3console.service.module.shifter.ShifterConfig(CanBusAssembler.assemble(resource.getCanBus()),
		                                                                               assemble(resource.getIndicator()),
		                                                                               assemble(resource.getMelexis()),
		                                                                               assemble(resource.getShifters()));
	}

	public IndicatorConfig assemble(IndicatorResource resource) {
		return new IndicatorConfig(resource.getBrightness(),
		                           resource.getColor(),
		                           resource.getWait());
	}

	public MelexisConfig assemble(MelexisResource resource) {
		return new MelexisConfig(resource.getRange());
	}

	public ObservableList<ShifterPositionConfig> assemble(List<ShifterPositionResource> resources) {
		return FXCollections.observableArrayList(resources.stream().map(this::assemble).collect(
				Collectors.toList()));
	}

	public ShifterPositionConfig assemble(ShifterPositionResource resource) {
		return new ShifterPositionConfig(resource.getX1(),
		                                 resource.getY1(),
		                                 resource.getX2(),
		                                 resource.getY2(),
		                                 resource.getEnumValue());
	}

	public com.b3tuning.b3console.service.module.shifter.ShifterConfigResource assemble(
			com.b3tuning.b3console.service.module.shifter.ShifterConfig config) {
		return new com.b3tuning.b3console.service.module.shifter.ShifterConfigResource(CanBusAssembler.assemble(config.getCanBusConfig()),
		                                                                                       assemble(config.getIndicatorConfig()),
		                                                                                       assemble(config.getMelexisConfig()),
		                                                                                       assemble(config.getShifterPositionConfigs()));
	}


	public IndicatorResource assemble(IndicatorConfig config) {
		return new IndicatorResource(config.getBrightness(),
		                             config.getColor(),
		                             config.getWait());
	}

	public MelexisResource assemble(MelexisConfig config) {
		return new MelexisResource(config.getRange());
	}

	public List<ShifterPositionResource> assemble(ObservableList<ShifterPositionConfig> configs) {
		return configs.stream().map(this::assemble).collect(Collectors.toList());
	}

	public ShifterPositionResource assemble(ShifterPositionConfig config) {
		return new ShifterPositionResource(config.getX1(),
		                                   config.getY1(),
		                                   config.getX2(),
		                                   config.getY2(),
		                                   config.getEnumVal());
	}
}


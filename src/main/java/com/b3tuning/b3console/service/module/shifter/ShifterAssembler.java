package com.b3tuning.b3console.service.module.shifter;

import com.b3tuning.b3console.service.module.CanBusAssembler;
import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.shifter.config.IndicatorConfig;
import com.b3tuning.b3console.service.module.shifter.config.MelexisConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterPositionConfig;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource.IndicatorResource;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource.MelexisResource;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource.ShifterPositionResource;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

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
public class ShifterAssembler extends ConfigBaseAssembler {

	public static ShifterConfig assemble(ShifterConfigResource resource) {
		return new ShifterConfig(CanBusAssembler.assemble(resource.getCanBus()),
		                         assemble(resource.getIndicator()),
		                         assemble(resource.getMelexis()),
		                         assembleList(resource.getShifters()));
	}

	public static IndicatorConfig assemble(IndicatorResource resource) {
		return new IndicatorConfig(resource.getBrightness(),
		                           resource.getColor(),
		                           resource.getWait());
	}

	public static MelexisConfig assemble(MelexisResource resource) {
		return new MelexisConfig(resource.getRange());
	}

	public static ListProperty<ShifterPositionConfig> assembleList(List<ShifterPositionResource> resources) {
		return new SimpleListProperty<>(FXCollections.observableArrayList(resources.stream()
		                                                                           .map(ShifterAssembler::assemble)
		                                                                           .collect(Collectors.toList())));
	}

	public static ShifterPositionConfig assemble(ShifterPositionResource resource) {
		return new ShifterPositionConfig(resource.getX1(),
		                                 resource.getY1(),
		                                 resource.getX2(),
		                                 resource.getY2(),
		                                 resource.getEnumValue());
	}

	public static ShifterConfigResource assemble(ShifterConfig config) {
		return new ShifterConfigResource(CanBusAssembler.assemble(config.getCanBus()),
		                                 assemble(config.getIndicator()),
		                                 assemble(config.getMelexis()),
		                                 assemble(config.getShifterPositions()))
				.setSuperType(config.getType());
	}

	public static IndicatorResource assemble(IndicatorConfig config) {
		return new IndicatorResource(config.getBrightness(),
		                             config.getColor(),
		                             config.getWait());
	}

	public static MelexisResource assemble(MelexisConfig config) {
		return new MelexisResource(config.getRange());
	}

	public static List<ShifterPositionResource> assemble(List<ShifterPositionConfig> configs) {
		return configs.stream().map(ShifterAssembler::assemble).collect(Collectors.toList());
	}

	public static ShifterPositionResource assemble(ShifterPositionConfig config) {
		return new ShifterPositionResource(config.getX1(),
		                                   config.getY1(),
		                                   config.getX2(),
		                                   config.getY2(),
		                                   config.getEnumVal());
	}
}


package com.b3tuning.b3console.service.module.shifter;

import com.b3tuning.b3console.service.module.CanBusAssembler;
import com.b3tuning.b3console.service.module.shifter.config.IndicatorConfig;
import com.b3tuning.b3console.service.module.shifter.config.MelexisConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterPositionConfig;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource.IndicatorResource;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource.MelexisResource;
import com.b3tuning.b3console.service.module.shifter.resource.ShifterConfigResource.ShifterPositionResource;
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

	public ShifterConfig assemble(ShifterConfigResource resource) {
		return new ShifterConfig(CanBusAssembler.assemble(resource.getCanBus()),
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

	public ShifterConfigResource assemble(ShifterConfig config) {
		return new ShifterConfigResource(CanBusAssembler.assemble(config.getCanBus().get()),
		                                 assemble(config.getIndicator().get()),
		                                 assemble(config.getMelexis().get()),
		                                 assemble(config.getShifterPositions().get()));
	}

	public IndicatorResource assemble(IndicatorConfig config) {
		return new IndicatorResource(config.getBrightness().get(),
		                             config.getColor().get(),
		                             config.getWait().get());
	}

	public MelexisResource assemble(MelexisConfig config) {
		return new MelexisResource(config.getRange().get());
	}

	public List<ShifterPositionResource> assemble(ObservableList<ShifterPositionConfig> configs) {
		return configs.stream().map(this::assemble).collect(Collectors.toList());
	}

	public ShifterPositionResource assemble(ShifterPositionConfig config) {
		return new ShifterPositionResource(config.getX1().get(),
		                                   config.getY1().get(),
		                                   config.getX2().get(),
		                                   config.getY2().get(),
		                                   config.getEnumVal().get());
	}
}


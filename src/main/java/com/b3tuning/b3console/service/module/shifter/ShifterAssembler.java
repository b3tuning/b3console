package com.b3tuning.b3console.service.module.shifter;

import com.b3tuning.b3console.service.module.CanBusAssembler;
import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.shifter.config.IndicatorConfig;
import com.b3tuning.b3console.service.module.shifter.config.MelexisConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterConfig;
import com.b3tuning.b3console.service.module.shifter.config.ShifterPositionConfig;
import com.b3tuning.b3console.service.protobuf.ConfigMessage;

/*
 *  Created on:  May 01, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class ShifterAssembler extends ConfigBaseAssembler {

    public static ShifterConfig assemble(ConfigMessage.ShifterMessage message) {
        return new ShifterConfig(CanBusAssembler.assemble(message.getCanBusConfig()),
                assemble(message.getIndicatorConfig()),
                assemble(message.getMelexisConfig()),
                assemble(message.getParkConfig()),
                assemble(message.getReverseConfig()),
                assemble(message.getNeutralConfig()),
                assemble(message.getDriveConfig()),
                assemble(message.getManualConfig()),
                assemble(message.getUpConfig()),
                assemble(message.getDownConfig()));
    }

    public static ConfigMessage.ConfigBaseMessage assembleBase(ShifterConfig config) {
        return ConfigMessage.ConfigBaseMessage.newBuilder()
                .setType(ConfigMessage.Type.SHIFTER)
                .setShifter(assemble(config))
                .build();
    }

    private static ConfigMessage.ShifterMessage assemble(ShifterConfig config) {
        return ConfigMessage.ShifterMessage.newBuilder()
                .setCanBusConfig(CanBusAssembler.assemble(config.getCanBus()))
                .setIndicatorConfig(assemble(config.getIndicator()))
                .setMelexisConfig(assemble(config.getMelexis()))
                .setParkConfig(assemble(config.getPark()))
                .setReverseConfig(assemble(config.getReverse()))
                .setNeutralConfig(assemble(config.getNeutral()))
                .setDriveConfig(assemble(config.getDrive()))
                .setManualConfig(assemble(config.getManual()))
                .setUpConfig(assemble(config.getUp()))
                .setDownConfig(assemble(config.getDown()))
                .build();
    }

    private static IndicatorConfig assemble(ConfigMessage.ShifterMessage.IndicatorConfigMessage message) {
        return new IndicatorConfig(message.getBrightness(),
                message.getColor(),
                message.getWait());
    }

    private static ConfigMessage.ShifterMessage.IndicatorConfigMessage assemble(IndicatorConfig config) {
        return ConfigMessage.ShifterMessage.IndicatorConfigMessage.newBuilder()
                .setBrightness(config.getBrightness())
                .setColor(config.getColor())
                .setWait(config.getWait())
                .build();
    }

    private static MelexisConfig assemble(ConfigMessage.ShifterMessage.MelexisConfigMessage message) {
        return new MelexisConfig(message.getRange());
    }

    private static ConfigMessage.ShifterMessage.MelexisConfigMessage assemble(MelexisConfig config) {
        return ConfigMessage.ShifterMessage.MelexisConfigMessage.newBuilder()
                .setRange(config.getRange())
                .build();
    }

    private static ShifterPositionConfig assemble(ConfigMessage.ShifterMessage.ShifterPositionMessage message) {
        return new ShifterPositionConfig(message.getX1(),
                message.getY1(),
                message.getX2(),
                message.getY2(),
                message.getEnumVal());
    }

    private static ConfigMessage.ShifterMessage.ShifterPositionMessage assemble(ShifterPositionConfig config) {
        return ConfigMessage.ShifterMessage.ShifterPositionMessage.newBuilder()
                .setX1(config.getX1())
                .setY1(config.getY1())
                .setX2(config.getX2())
                .setY2(config.getY2())
                .setEnumVal(config.getEnumVal())
                .build();
    }

//	public static ShifterConfig assemble(ShifterConfigResource resource) {
//		return new ShifterConfig(CanBusAssembler.assemble(resource.getCanBus()),
//		                         assemble(resource.getIndicator()),
//		                         assemble(resource.getMelexis()),
//		                         assemble(resource.getPark()),
//		                         assemble(resource.getReverse()),
//		                         assemble(resource.getNeutral()),
//		                         assemble(resource.getDrive()),
//		                         assemble(resource.getManual()),
//		                         assemble(resource.getUp()),
//		                         assemble(resource.getDown()));
//	}

//	public static IndicatorConfig assemble(IndicatorResource resource) {
//		return new IndicatorConfig(resource.getBrightness(),
//		                           resource.getColor(),
//		                           resource.getWait());
//	}
//
//	public static MelexisConfig assemble(MelexisResource resource) {
//		return new MelexisConfig(resource.getRange());
//	}
//
//	public static ShifterPositionConfig assemble(ShifterPositionResource resource) {
//		return new ShifterPositionConfig(resource.getX1(),
//		                                 resource.getY1(),
//		                                 resource.getX2(),
//		                                 resource.getY2(),
//		                                 resource.getEnumValue());
//	}
//
//	public static ShifterConfigResource assemble(ShifterConfig config) {
//		return new ShifterConfigResource(CanBusAssembler.assemble(config.getCanBus()),
//		                                 assemble(config.getIndicator()),
//		                                 assemble(config.getMelexis()),
//		                                 assemble(config.getPark()),
//		                                 assemble(config.getReverse()),
//		                                 assemble(config.getNeutral()),
//		                                 assemble(config.getDrive()),
//		                                 assemble(config.getManual()),
//		                                 assemble(config.getUp()),
//		                                 assemble(config.getDown()))
//				.setSuperType(config.getType());
//	}
//
//	public static IndicatorResource assemble(IndicatorConfig config) {
//		return new IndicatorResource(config.getBrightness(),
//		                             config.getColor(),
//		                             config.getWait());
//	}
//
//	public static MelexisResource assemble(MelexisConfig config) {
//		return new MelexisResource(config.getRange());
//	}
//
//	public static ShifterPositionResource assemble(ShifterPositionConfig config) {
//		return new ShifterPositionResource(config.getX1(),
//		                                   config.getY1(),
//		                                   config.getX2(),
//		                                   config.getY2(),
//		                                   config.getEnumVal());
//	}
}


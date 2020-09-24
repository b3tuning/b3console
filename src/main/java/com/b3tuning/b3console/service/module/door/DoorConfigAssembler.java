package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorActionConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorSelectConfig;
import com.b3tuning.b3console.service.module.door.config.WindowActionConfig;
import com.b3tuning.b3console.service.protobuf.ConfigMessage;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class DoorConfigAssembler extends ConfigBaseAssembler {

    public static DoorConfig assemble(ConfigMessage.DoorMessage message) {
        return new DoorConfig(assemble(message.getMirrorAction()),
                assemble(message.getMirrorSelect()),
                assemble(message.getWindowAction()),
                message.getDriverWindowMaxCurrent(),
                message.getPassengerWindowMaxCurrent());
    }

    public static ConfigMessage.ConfigBaseMessage assembleBase(DoorConfig config) {
        return ConfigMessage.ConfigBaseMessage.newBuilder()
                .setType(ConfigMessage.Type.DOOR)
                .setDoor(assemble(config))
                .build();
    }

    private static ConfigMessage.DoorMessage assemble(DoorConfig config) {
        return ConfigMessage.DoorMessage.newBuilder()
                .setMirrorAction(assemble(config.getMirrorAction()))
                .setMirrorSelect(assemble(config.getMirrorSelect()))
                .setWindowAction(assemble(config.getWindowAction()))
                .setDriverWindowMaxCurrent(config.getDriverWindowMaxCurrent())
                .setPassengerWindowMaxCurrent(config.getPassengerWindowMaxCurrent())
                .build();
    }

    private static ConfigMessage.DoorMessage.MirrorActionConfigMessage assemble(MirrorActionConfig config) {
        return ConfigMessage.DoorMessage.MirrorActionConfigMessage.newBuilder()
                .setDownMax(config.getDownMax())
                .setDownMin(config.getDownMin())
                .setLeftMax(config.getLeftMax())
                .setLeftMin(config.getLeftMin())
                .setRightMax(config.getRightMax())
                .setRightMin(config.getRightMin())
                .setUpMax(config.getUpMax())
                .setUpMin(config.getUpMin())
                .build();
    }

    private static ConfigMessage.DoorMessage.MirrorSelectConfigMessage assemble(MirrorSelectConfig config) {
        return ConfigMessage.DoorMessage.MirrorSelectConfigMessage.newBuilder()
                .setDriverMax(config.getDriverMax())
                .setDriverMin(config.getDriverMin())
                .setFoldMax(config.getFoldMax())
                .setFoldMin(config.getFoldMin())
                .setPassengerMax(config.getPassengerMax())
                .setPassengerMin(config.getPassengerMin())
                .build();
    }

    private static ConfigMessage.DoorMessage.WindowActionConfigMessage assemble(WindowActionConfig config) {
        return ConfigMessage.DoorMessage.WindowActionConfigMessage.newBuilder()
                .setAutoDownMax(config.getAutoDownMax())
                .setAutoDownMin(config.getAutoDownMin())
                .setAutoUpMax(config.getAutoUpMax())
                .setAutoUpMin(config.getAutoDownMin())
                .setDownMax(config.getDownMax())
                .setDownMin(config.getDownMin())
                .setUpMax(config.getUpMax())
                .setUpMin(config.getUpMin())
                .build();
    }

    private static MirrorActionConfig assemble(ConfigMessage.DoorMessage.MirrorActionConfigMessage message) {
        return new MirrorActionConfig(message.getDownMax(),
                message.getDownMin(),
                message.getLeftMax(),
                message.getLeftMin(),
                message.getRightMax(),
                message.getRightMin(),
                message.getUpMax(),
                message.getUpMin());
    }

    public static MirrorSelectConfig assemble(ConfigMessage.DoorMessage.MirrorSelectConfigMessage message) {
        return new MirrorSelectConfig(message.getDriverMax(),
                message.getDriverMin(),
                message.getFoldMax(),
                message.getFoldMin(),
                message.getPassengerMax(),
                message.getPassengerMin());
    }

    public static WindowActionConfig assemble(ConfigMessage.DoorMessage.WindowActionConfigMessage message) {
        return new WindowActionConfig(message.getAutoDownMax(),
                message.getAutoDownMin(),
                message.getAutoUpMax(),
                message.getAutoUpMin(),
                message.getDownMax(),
                message.getDownMin(),
                message.getUpMax(),
                message.getUpMin());
    }
}

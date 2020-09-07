package com.b3tuning.b3console.service.module.door;

import com.b3tuning.b3console.service.module.ConfigBaseAssembler;
import com.b3tuning.b3console.service.module.door.config.DoorConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorActionConfig;
import com.b3tuning.b3console.service.module.door.config.MirrorSelectConfig;
import com.b3tuning.b3console.service.module.door.config.WindowActionConfig;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource.MirrorActionResource;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource.MirrorSelectResource;
import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource.WindowActionResource;
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

    public static ConfigMessage.DoorMessage assemble(DoorConfig config) {
        return ConfigMessage.DoorMessage.newBuilder()
                .setMirrorAction(assemble(config.getMirrorAction()))
                .setMirrorSelect(assemble(config.getMirrorSelect()))
                .setWindowAction(assemble(config.getWindowAction()))
                .setDriverWindowMaxCurrent(config.getDriverWindowMaxCurrent())
                .setPassengerWindowMaxCurrent(config.getPassengerWindowMaxCurrent())
                .build();
    }

    public static ConfigMessage.DoorMessage.MirrorActionConfigMessage assemble(MirrorActionConfig config) {
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

    public static ConfigMessage.DoorMessage.MirrorSelectConfigMessage assemble(MirrorSelectConfig config) {
        return ConfigMessage.DoorMessage.MirrorSelectConfigMessage.newBuilder()
                .setDriverMax(config.getDriverMax())
                .setDriverMin(config.getDriverMin())
                .setFoldMax(config.getFoldMax())
                .setFoldMin(config.getFoldMin())
                .setPassengerMax(config.getPassengerMax())
                .setPassengerMin(config.getPassengerMin())
                .build();
    }

    public static ConfigMessage.DoorMessage.WindowActionConfigMessage assemble(WindowActionConfig config) {
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

    public static DoorConfig assemble(DoorConfigResource resource) {
        return new DoorConfig(assemble(resource.getMirrorAction()),
                              assemble(resource.getMirrorSelect()),
                              assemble(resource.getWindowAction()),
                              resource.getDriverWindowMaxCurrent(),
                              resource.getPassengerWindowMaxCurrent());
    }

    public static MirrorActionConfig assemble(ConfigMessage.DoorMessage.MirrorActionConfigMessage message) {
        return new MirrorActionConfig(message.getDownMax(),
                                      message.getDownMin(),
                                      message.getLeftMax(),
                                      message.getLeftMin(),
                                      message.getRightMax(),
                                      message.getRightMin(),
                                      message.getUpMax(),
                                      message.getUpMin());
    }

    public static MirrorActionConfig assemble(MirrorActionResource resource) {
        return new MirrorActionConfig(resource.getDownMax(),
                                      resource.getDownMin(),
                                      resource.getLeftMax(),
                                      resource.getLeftMin(),
                                      resource.getRightMax(),
                                      resource.getRightMin(),
                                      resource.getUpMax(),
                                      resource.getUpMin());
    }

    public static MirrorSelectConfig assemble(MirrorSelectResource resource) {
        return new MirrorSelectConfig(resource.getDriverMax(),
                                      resource.getDriverMin(),
                                      resource.getFoldMax(),
                                      resource.getFoldMin(),
                                      resource.getPassengerMax(),
                                      resource.getPassengerMin());
    }

    public static MirrorSelectConfig assemble(ConfigMessage.DoorMessage.MirrorSelectConfigMessage message) {
        return new MirrorSelectConfig(message.getDriverMax(),
                                      message.getDriverMin(),
                                      message.getFoldMax(),
                                      message.getFoldMin(),
                                      message.getPassengerMax(),
                                      message.getPassengerMin());
    }

    public static WindowActionConfig assemble(WindowActionResource resource) {
        return new WindowActionConfig(resource.getAutoDownMax(),
                                      resource.getDownMin(),
                                      resource.getAutoUpMax(),
                                      resource.getAutoUpMin(),
                                      resource.getDownMax(),
                                      resource.getDownMin(),
                                      resource.getUpMax(),
                                      resource.getUpMin());
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

//    public static DoorConfigResource assemble(DoorConfig config) {
//        return new DoorConfigResource(assemble(config.getMirrorAction()),
//                                      assemble(config.getMirrorSelect()),
//                                      assemble(config.getWindowAction()),
//                                      config.getDriverWindowMaxCurrent(),
//                                      config.getPassengerWindowMaxCurrent())
//                .setSuperType(config.getType());
//    }
//
//    public static MirrorActionResource assemble(MirrorActionConfig config) {
//        return new MirrorActionResource(config.getDownMax(),
//                                        config.getDownMin(),
//                                        config.getLeftMax(),
//                                        config.getLeftMin(),
//                                        config.getRightMax(),
//                                        config.getRightMin(),
//                                        config.getUpMax(),
//                                        config.getUpMin());
//    }
//
//    public static MirrorSelectResource assemble(MirrorSelectConfig config) {
//        return new MirrorSelectResource(config.getDriverMax(),
//                                        config.getDriverMin(),
//                                        config.getFoldMax(),
//                                        config.getFoldMin(),
//                                        config.getPassengerMax(),
//                                        config.getPassengerMin());
//    }
//
//    public static WindowActionResource assemble(WindowActionConfig config) {
//        return new WindowActionResource(config.getAutoDownMax(),
//                                        config.getAutoDownMin(),
//                                        config.getAutoUpMax(),
//                                        config.getAutoUpMin(),
//                                        config.getDownMax(),
//                                        config.getDownMin(),
//                                        config.getUpMax(),
//                                        config.getUpMin());
//    }
}

package com.b3tuning.b3console.service.comms;

import com.b3tuning.b3console.service.module.door.resource.DoorConfigResource;
import com.fazecast.jSerialComm.SerialPort;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  May 07, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class SerialComms {

	public static final String B3_USB_PORT_DESCRIPTION = "B3Tuning Module B3";

	private SerialPort port;

	@Inject
	public SerialComms() {}

	public SerialPort getPort() {
		return port != null
		       ? port
		       : createPort();
	}

	private SerialPort createPort() {
		port = null;
		SerialPort[] ports = SerialPort.getCommPorts();
		if (ports.length == 0) {
			log.error("No Serial ports available, unable to connect to module");
		} else {
			for (SerialPort s : ports) {
				if (verifiedPort(s)) {
					port = s;
				}
			}
		}
		return port;
	}

	private static boolean verifiedPort(SerialPort s) {
		return s.getDescriptivePortName().contains(B3_USB_PORT_DESCRIPTION)
		       && s.getDescriptivePortName().contains("cu.");
	}

	public DoorConfigResource door() {
		if (null == getPort()) {
			log.error("No Serial ports available, unable to connect to module");
			return null;
		}
		DoorConfigResource resource = null;
		if (!port.openPort()) {
			log.error("Unable to open port");
			return resource;
		}
		// TODO: get the DoorConfigResource from the module
		port.closePort();
		return resource;
	}
}

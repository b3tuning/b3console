package com.b3tuning.b3console.service.comms;

import com.b3tuning.b3console.service.protobuf.ConfigMessage;
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
					log.error("FOUND SERIAL PORT!!!!");
					port = s;
				}
			}
		}
		return port;
	}

//	public void checkNonBlocking() {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		try {
//			while (true)
//			{
//				while (comPort.bytesAvailable() == 0)
//					Thread.sleep(20);
//
//				byte[] readBuffer = new byte[comPort.bytesAvailable()];
//				int numRead = comPort.readBytes(readBuffer, readBuffer.length);
//				System.out.println("Read " + numRead + " bytes.");
//			}
//		} catch (Exception e) { e.printStackTrace(); }
//		comPort.closePort();
//	}

//	public void checkSemiBlocking() {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
//		try {
//			while (true)
//			{
//				byte[] readBuffer = new byte[1024];
//				int numRead = comPort.readBytes(readBuffer, readBuffer.length);
//				System.out.println("Read " + numRead + " bytes.");
//			}
//		} catch (Exception e) { e.printStackTrace(); }
//		comPort.closePort();
//	}

//	public void checkIOStream() {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
//		InputStream in = comPort.getInputStream();
//		try
//		{
//			for (int j = 0; j < 1000; ++j)
//			     System.out.print((char)in.read());
//			in.close();
//		} catch (Exception e) { e.printStackTrace(); }
//		comPort.closePort();
//	}

//	public void checkRead() {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		comPort.addDataListener(new SerialPortDataListener() {
//			@Override
//			public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
//
//			@Override
//			public void serialEvent(SerialPortEvent event) {
//				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
//					return;
//				}
//				byte[] newData = new byte[comPort.bytesAvailable()];
//				int    numRead = comPort.readBytes(newData, newData.length);
//				System.out.println("Read " + numRead + " bytes.");
//			}
//		});
//	}

//	public void checkWrite() {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		comPort.addDataListener(new SerialPortDataListener() {
//			@Override
//			public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_WRITTEN; }
//
//			@Override
//			public void serialEvent(SerialPortEvent event) {
//				if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN) {
//					System.out.println("All bytes were successfully transmitted!");
//				}
//			}
//		});
//	}

//	public void checkBytesReceived() {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		comPort.addDataListener(new SerialPortDataListener() {
//			@Override
//			public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
//
//			@Override
//			public void serialEvent(SerialPortEvent event) {
//				byte[] newData = event.getReceivedData();
//				System.out.println("Received data of size: " + newData.length);
//				for (byte newDatum : newData) {
//					System.out.print((char) newDatum);
//				}
//				System.out.println("\n");
//			}
//		});
//	}

//	private static final class PacketListener implements SerialPortPacketListener {
//		@Override
//		public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
//
//		@Override
//		public int getPacketSize() { return 100; }
//
//		@Override
//		public void serialEvent(SerialPortEvent event) {
//			byte[] newData = event.getReceivedData();
//			System.out.println("Received data of size: " + newData.length);
//			for (byte newDatum : newData) {
//				System.out.print((char) newDatum);
//			}
//			System.out.println("\n");
//		}
//	}

//	static public void main(String[] args) {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		PacketListener listener = new PacketListener();
//		comPort.addDataListener(listener);
//		try {
//			Thread.sleep(5000);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		comPort.removeDataListener();
//		comPort.closePort();
//	}

//	private static final class MessageListener implements SerialPortMessageListener {
//		@Override
//		public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
//
//		@Override
//		public byte[] getMessageDelimiter() { return new byte[]{(byte) 0x0B, (byte) 0x65}; }
//
//		@Override
//		public boolean delimiterIndicatesEndOfMessage() { return true; }
//
//		@Override
//		public void serialEvent(SerialPortEvent event) {
//			byte[] delimitedMessage = event.getReceivedData();
//			System.out.println("Received the following delimited message: " + Arrays.toString(delimitedMessage));
//		}
//	}

//	static public void mainPacket(String[] args) {
//		SerialPort comPort = SerialPort.getCommPorts()[0];
//		comPort.openPort();
//		MessageListener listener = new MessageListener();
//		comPort.addDataListener(listener);
//		try {
//			Thread.sleep(5000);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		comPort.removeDataListener();
//		comPort.closePort();
//	}

	private static boolean verifiedPort(SerialPort s) {
		System.getProperties().list(System.out);
		log.error("SERIAL PORTS = {}", s.getDescriptivePortName());
		log.error("SERIAL PORT DESCRIPTION = {} ", s.getPortDescription());
		log.error("SERIAL PORT SYSTEM NAME = {}", s.getSystemPortName());
		return (s.getDescriptivePortName().contains(B3_USB_PORT_DESCRIPTION)
		        && s.getDescriptivePortName().contains("cu."))
		       || s.getPortDescription().contains(B3_USB_PORT_DESCRIPTION);
	}

	public ConfigMessage.ConfigBaseMessage door() {
		if (null == getPort()) {
			log.error("No Serial ports available, unable to connect to module");
			return null;
		}
		ConfigMessage.ConfigBaseMessage resource = null;
		if (!port.openPort()) {
			log.error("Unable to open port");
			return resource;
		}
		// TODO: get the DoorConfigResource from the module
		port.closePort();
		return resource;
	}
}

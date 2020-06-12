package com.b3tuning.b3console.service.module.door.resource;

import com.b3tuning.b3console.service.module.ConfigBaseResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoorConfigResource extends ConfigBaseResource implements Serializable {
	private static final long serialVersionUID = -3845572213615783487L;

	private MirrorActionResource mirrorAction;
	private MirrorSelectResource mirrorSelect;
	private WindowActionResource windowAction;

	private int driverWindowMaxCurrent;
	private int passengerWindowMaxCurrent;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MirrorActionResource implements Serializable {
		private static final long serialVersionUID = -3318161345152814145L;

		private int downMax;
		private int downMin;
		private int leftMax;
		private int leftMin;
		private int rightMax;
		private int rightMin;
		private int upMax;
		private int upMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MirrorSelectResource implements Serializable {
		private static final long serialVersionUID = 8104729977643773681L;

		private int driverMax;
		private int driverMin;
		private int foldMax;
		private int foldMin;
		private int passengerMax;
		private int passengerMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class WindowActionResource implements Serializable {
		private static final long serialVersionUID = 4042703422862305311L;

		private int autoDownMax;
		private int autoDownMin;
		private int autoUpMax;
		private int autoUpMin;
		private int downMax;
		private int downMin;
		private int upMax;
		private int upMin;
	}
}

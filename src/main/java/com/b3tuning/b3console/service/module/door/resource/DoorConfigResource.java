package com.b3tuning.b3console.service.module.door.resource;

import com.b3tuning.b3console.service.module.ConfigBaseResource;
import com.b3tuning.b3console.service.module.ModuleType;
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

	private Integer driverWindowMaxCurrent;
	private Integer passengerWindowMaxCurrent;

	public DoorConfigResource setSuperType(ModuleType type) {
		super.setType(type);
		return this;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MirrorActionResource implements Serializable {
		private static final long serialVersionUID = -3318161345152814145L;

		private Integer downMax;
		private Integer downMin;
		private Integer leftMax;
		private Integer leftMin;
		private Integer rightMax;
		private Integer rightMin;
		private Integer upMax;
		private Integer upMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MirrorSelectResource implements Serializable {
		private static final long serialVersionUID = 8104729977643773681L;

		private Integer driverMax;
		private Integer driverMin;
		private Integer foldMax;
		private Integer foldMin;
		private Integer passengerMax;
		private Integer passengerMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class WindowActionResource implements Serializable {
		private static final long serialVersionUID = 4042703422862305311L;

		private Integer autoDownMax;
		private Integer autoDownMin;
		private Integer autoUpMax;
		private Integer autoUpMin;
		private Integer downMax;
		private Integer downMin;
		private Integer upMax;
		private Integer upMin;
	}
}

package com.b3tuning.b3console.service.module.shifter.resource;

import com.b3tuning.b3console.service.module.CanBusResource;
import com.b3tuning.b3console.service.module.ConfigBaseResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/*
 *  Created on:  May 01, 2020
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
public class ShifterConfigResource extends ConfigBaseResource implements Serializable {
	private static final long serialVersionUID = 9070650989030043988L;

	private CanBusResource                canBus;
	private IndicatorResource             indicator;
	private MelexisResource               melexis;
	private List<ShifterPositionResource> shifters;

//	@JsonIgnore
//	public List<ShifterPositionResource> getShifters() {
//		return shifters != null
//		       ? shifters
//		       : Collections.emptyList();
//	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class IndicatorResource implements Serializable {
		private static final long serialVersionUID = 3014594965266984764L;

		private int brightness;
		private int color;
		private int wait;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MelexisResource implements Serializable {
		private static final long serialVersionUID = 1985825176462854188L;

		private int range;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ShifterPositionResource implements Serializable {
		private static final long serialVersionUID = -1118962386529865387L;

		private int x1;
		private int y1;
		private int x2;
		private int y2;
		private int enumValue;
	}
}

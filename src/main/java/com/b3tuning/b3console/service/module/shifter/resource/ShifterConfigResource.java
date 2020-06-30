package com.b3tuning.b3console.service.module.shifter.resource;

import com.b3tuning.b3console.service.module.CanBusResource;
import com.b3tuning.b3console.service.module.ConfigBaseResource;
import com.b3tuning.b3console.service.module.ModuleType;
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

	public ShifterConfigResource setSuperType(ModuleType type) {
		super.setType(type);
		return this;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class IndicatorResource implements Serializable {
		private static final long serialVersionUID = 3014594965266984764L;

		private Integer brightness;
		private Integer color;
		private Integer wait;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MelexisResource implements Serializable {
		private static final long serialVersionUID = 1985825176462854188L;

		private Integer range;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ShifterPositionResource implements Serializable {
		private static final long serialVersionUID = -1118962386529865387L;

		private Integer x1;
		private Integer y1;
		private Integer x2;
		private Integer y2;
		private Integer enumValue;
	}
}

package com.b3tuning.b3console.service.module.shifter;

import com.b3tuning.b3console.service.module.CanBusResource;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collections;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShifterConfigResource {
	@JsonProperty("canBus") private           CanBusResource                canBus;
	@JsonProperty("indicator") private        IndicatorResource             indicator;
	@JsonProperty("melexis") private          MelexisResource               melexis;
	@JsonProperty("shifterPositions") private List<ShifterPositionResource> shifters;

	@JsonIgnore
	public List<ShifterPositionResource> getShifters() {
		return shifters != null
		       ? shifters
		       : Collections.emptyList();
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class IndicatorResource {
		@JsonProperty("brightness") private int brightness;
		@JsonProperty("color") private      int color;
		@JsonProperty("wait") private       int wait;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class MelexisResource {
		@JsonProperty("range") private int range;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ShifterPositionResource {
		@JsonProperty("x1") private        int x1;
		@JsonProperty("y1") private        int y1;
		@JsonProperty("x2") private        int x2;
		@JsonProperty("y2") private        int y2;
		@JsonProperty("enumValue") private int enumValue;
	}
}

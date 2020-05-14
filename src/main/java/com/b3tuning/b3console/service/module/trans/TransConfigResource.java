package com.b3tuning.b3console.service.module.trans;

import com.b3tuning.b3console.service.module.CanBusResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransConfigResource {

	@JsonProperty("canBus") private  CanBusResource  canBus;
	@JsonProperty("ems22A") private  Ems22AResource  ems22A;
	@JsonProperty("vnh5019") private Vnh5019Resource vnh5019;
	@JsonProperty("pid") private     PidResource     pid;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Ems22AResource {
		@JsonProperty("encMax") private int encMax;
		@JsonProperty("encMin") private int encMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Vnh5019Resource {
		@JsonProperty("maxCurrent") private int maxCurrent;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class PidResource {
		@JsonProperty("aggKd") private  double aggKd;
		@JsonProperty("aggKi") private  double aggKi;
		@JsonProperty("aggKp") private  double aggKp;
		@JsonProperty("consKd") private double consKd;
		@JsonProperty("consKi") private double consKi;
		@JsonProperty("consKp") private double consKp;
	}
}

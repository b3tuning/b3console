package com.b3tuning.b3console.service.module;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
 *  Created on:  May 05, 2020
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
public class CanBusResource {
	@JsonProperty("addrTX") private    long addrTX;
	@JsonProperty("bootRX") private    long bootRX;
	@JsonProperty("bootTX") private    long bootTX;
	@JsonProperty("intvlTX") private   int  intvlTX;
	@JsonProperty("mask0") private     long mask0;
	@JsonProperty("filter0_0") private long filter0_0;
	@JsonProperty("filter0_1") private long filter0_1;
	@JsonProperty("mask1") private     long mask1;
	@JsonProperty("filter1_0") private long filter1_0;
	@JsonProperty("filter1_1") private long filter1_1;
	@JsonProperty("filter1_2") private long filter1_2;
	@JsonProperty("filter1_3") private long filter1_3;
}

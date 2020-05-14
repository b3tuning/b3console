package com.b3tuning.b3console.service.module.door;

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
public class DoorConfigResource {
	@JsonProperty("mirrorAction") private MirrorActionResource mirrorAction;
	@JsonProperty("mirrorSelect") private MirrorSelectResource mirrorSelect;
	@JsonProperty("windowAction") private WindowActionResource windowAction;

	@JsonProperty("driverWindowMaxCurrent") private    int driverWindowMaxCurrent;
	@JsonProperty("passengerWindowMaxCurrent") private int passengerWindowMaxCurrent;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class MirrorActionResource {
		@JsonProperty("downMax") private  int downMax;
		@JsonProperty("downMin") private  int downMin;
		@JsonProperty("leftMax") private  int leftMax;
		@JsonProperty("leftMin") private  int leftMin;
		@JsonProperty("rightMax") private int rightMax;
		@JsonProperty("rightMin") private int rightMin;
		@JsonProperty("upMax") private    int upMax;
		@JsonProperty("upMin") private    int upMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class MirrorSelectResource {
		@JsonProperty("driverMax") private    int driverMax;
		@JsonProperty("driverMin") private    int driverMin;
		@JsonProperty("foldMax") private      int foldMax;
		@JsonProperty("foldMin") private      int foldMin;
		@JsonProperty("passengerMax") private int passengerMax;
		@JsonProperty("passengerMin") private int passengerMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class WindowActionResource {
		@JsonProperty("autoDownMax") private int autoDownMax;
		@JsonProperty("autoDownMin") private int autoDownMin;
		@JsonProperty("autoUpMax") private   int autoUpMax;
		@JsonProperty("autoUpMin") private   int autoUpMin;
		@JsonProperty("downMax") private     int downMax;
		@JsonProperty("downMin") private     int downMin;
		@JsonProperty("upMax") private       int upMax;
		@JsonProperty("upMin") private       int upMin;
	}
}

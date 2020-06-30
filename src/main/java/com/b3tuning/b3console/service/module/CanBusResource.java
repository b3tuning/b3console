package com.b3tuning.b3console.service.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class CanBusResource implements Serializable {
	private static final long serialVersionUID = 7773515125415957431L;

	private Long    addrTX;
	private Long    bootRX;
	private Long    bootTX;
	private Integer intvlTX;
	private Long    mask0;
	private Long    filter0_0;
	private Long    filter0_1;
	private Long    mask1;
	private Long    filter1_0;
	private Long    filter1_1;
	private Long    filter1_2;
	private Long    filter1_3;
}

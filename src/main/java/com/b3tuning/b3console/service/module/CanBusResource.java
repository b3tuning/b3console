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

	private long addrTX;
	private long bootRX;
	private long bootTX;
	private int  intvlTX;
	private long mask0;
	private long filter0_0;
	private long filter0_1;
	private long mask1;
	private long filter1_0;
	private long filter1_1;
	private long filter1_2;
	private long filter1_3;
}

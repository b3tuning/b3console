package com.b3tuning.b3console.service.files.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuthEvent {

	/**
	 * The authentication token
	 */
	protected String authToken;

}

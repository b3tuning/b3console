package com.b3tuning.b3console.service.files.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PageableEvent extends AuthEvent {

	private Integer page;
	private Integer size;
	private String  filter;
	private String  sort;

	public PageableEvent(String authToken, Integer page, Integer size, String filter, String sort) {
		super(authToken);
		this.page = page;
		this.size = size;
		this.filter = filter;
		this.sort = sort;
	}

	public PageableEvent(String authToken) {
		super(authToken);
	}

}

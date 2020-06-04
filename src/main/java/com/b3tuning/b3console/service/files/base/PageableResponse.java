package com.b3tuning.b3console.service.files.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper=true)
/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@ToString(callSuper = true)
public class PageableResponse<T extends PageableResponse> extends BaseResponse<T> {

	private int currentPage;
	private int totalPages;
	private int totalResults;
	private int pageSize;

}

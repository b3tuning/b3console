package com.b3tuning.b3console.service.files.base;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

public abstract class ResponseConstants {

	public static final String PAGINATION_DETAILS_KEY = "page";
	public static final String PAGINATION_PAGE_SIZE_KEY = "size";
	public static final String PAGINATION_TOTAL_RESULTS_KEY = "totalElements";
	public static final String PAGINATION_TOTAL_PAGES_KEY = "totalPages";
	public static final String PAGINATION_PAGE_KEY = "number";

	public static final String EMBEDDED_KEY = "_embedded";
	public static final String LINKS_KEY = "_links";
	public static final String SELF_KEY = "self";
	public static final String HREF_KEY = "href";

	public static final String TOKEN_KEY = "token";

	public static final int DEFAULT_TIMEOUT = 20;

}

package com.b3tuning.b3console.service.files.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper=true)
public class BasePageableResponse<T extends BasePageableResponse> extends BaseResponse<T> {

	private Page page = new Page();
	
	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Page {
		private int size;
		private int totalElement;
		private int totalPages;
		private int number;
	}
	
	public int getCurrentPage() {
		return page.getNumber();
	}
	
	public int getTotalPages() {
		return page.getTotalPages();
	}
	
	public int getTotalResults() {
		return page.getTotalElement();
	}
	
	public int getPageSize() {
		return page.getSize();
	}
}

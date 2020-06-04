package com.b3tuning.b3console.service.files.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import lombok.ToString;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

//	"page": {
//		"size": 10,
//		"totalElements": 2,
//		"totalPages": 1,
//		"number": 0
//		}
@ToString
public class ResourcesPage extends ResourceWithLinks {

	@JsonIgnore
	public int getCurrentPage() {
		validate();
		return page.number;
	}

	@JsonIgnore
	public int getTotalPages() {
		validate();
		return page.totalPages;
	}

	@JsonIgnore
	public int getPageSize() {
		validate();
		return page.size;
	}

	@JsonIgnore
	public int getTotalResults() {
		validate();
		return page.totalElements;
	}

	@JsonIgnore
	private void validate() {
		Preconditions.checkArgument(page != null, "Can't deserialize 'page'!");
	}

	@JsonProperty private Page page;

	@ToString
	private static class Page {
		@JsonProperty int size;
		@JsonProperty int totalElements;
		@JsonProperty int totalPages;
		@JsonProperty int number;
	}
}

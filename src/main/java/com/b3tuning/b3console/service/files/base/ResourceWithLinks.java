package com.b3tuning.b3console.service.files.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

public class ResourceWithLinks {

	/*********************************************************
	 *
	 * Base class for entities that contain _links with hrefs.
	 * _links with a "self" href are mapped as standard,
	 * all other hrefs types, ie. "owner" can easily be
	 * retrieved by using @code getLink("owner");
	 *
	 *********************************************************/

	private static final String SELF = "self";

	@JsonProperty private Map<String, Link> _links;

	public String getLink(String rel) {
		if (_links != null) {
			Link link = _links.get(rel);
			if (link != null) {
				return link.href;
			}
		}
		return null;
	}

	public String getSelfLink() {
		return getLink(SELF);
	}

	private static class Link {
		@JsonProperty String href;
		@JsonProperty boolean templated;
	}
}

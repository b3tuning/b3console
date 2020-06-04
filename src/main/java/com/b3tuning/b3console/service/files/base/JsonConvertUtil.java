package com.b3tuning.b3console.service.files.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.extern.slf4j.Slf4j;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Slf4j
public class JsonConvertUtil {

	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	static {
		objectMapper.registerModules(new Jdk8Module());
	}

	public static String buildBody(Object resource) {
		try {
			return objectMapper.writeValueAsString(resource);
		} catch (JsonProcessingException e) {
			log.error("Unable to parse resource = {}", e.getMessage());
			return "{}";
		}
	}
}

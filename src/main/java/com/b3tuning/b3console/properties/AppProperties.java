package com.b3tuning.b3console.properties;

import com.b3tuning.b3console.service.FileType;
import com.google.common.collect.Lists;
import lombok.Data;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
public class AppProperties {

	private DefaultCSS   defaultCSS;
	private String       version;
	private int          build;
	private URI          logUrl;
	private List<String> supportedIngestFileTypes;
	private String       userAgent;
	private String       logLevel;

	private URI userHelpUrl;
	private URI apiHelpUrl;

	private LinkedHashMap<FileType, List<String>> fileTypes;

	public AppProperties() {
		defaultCSS               = new DefaultCSS();
		supportedIngestFileTypes = Lists.newArrayList();
	}

	@Data
	public static class DefaultCSS {
		private String baseFile;
	}
}

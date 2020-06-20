package com.b3tuning.b3console.properties;

import lombok.Data;

import java.net.URI;

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

	private DefaultCSS defaultCSS;
	private String     version;
	private int        build;
	private URI        logUrl;
	private String     userAgent;
	private String     logLevel;

	private URI userHelpUrl;
	private URI apiHelpUrl;

	public AppProperties() {
		defaultCSS = new DefaultCSS();
	}

	@Data
	public static class DefaultCSS {
		private String baseFile;
	}
}

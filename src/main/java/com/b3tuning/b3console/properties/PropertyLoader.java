package com.b3tuning.b3console.properties;

import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class PropertyLoader {

	private static final String APP_PROPERTIES_FILE_TEMPLATE = "/properties/app-common.properties";

	private static final String DEFAULT_CSS         = "default.css.file";
	private static final String VERSION             = "app.version";
	private static final String BUILD               = "app.build";
	private static final String LOG_URL             = "app.log.url";
	//	private static final String USER_HELP_URL        = "help.user.url";
//	private static final String API_HELP_URL         = "help.api.url";
	private static final String USER_AGENT_PROPERTY = "app.useragent";
	private static final String LOG_LEVEL_PROPERTY  = "log.level";

	private final CompositeConfiguration config;

	public PropertyLoader() {

		config = new CompositeConfiguration();
		config.addConfiguration(new SystemConfiguration());
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
				new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
						.configure(params.properties()
						                 .setURL(PropertyLoader.class.getResource(APP_PROPERTIES_FILE_TEMPLATE)));
		try {
			Configuration configs = builder.getConfiguration();
			builder.save();
			config.addConfiguration(configs);
		}
		catch (ConfigurationException cex) {
			log.catching(cex);
		}
	}

	public AppProperties initAppProperties() {
		AppProperties appProperties = new AppProperties();
		appProperties.getDefaultCSS().setBaseFile(config.getString(DEFAULT_CSS));
		appProperties.setBuild(Integer.parseInt(config.getString(BUILD)));
		appProperties.setVersion(String.format("%s.%s", config.getString(VERSION), appProperties.getBuild()));
		appProperties.setUserAgent(config.getString(USER_AGENT_PROPERTY));

		if (!StringUtils.isEmpty(config.getString(LOG_URL))) {
			appProperties.setLogUrl(URI.create(config.getString(LOG_URL)));
		}

//		appProperties.setUserHelpUrl(URI.create(config.getString(USER_HELP_URL)));
//		appProperties.setApiHelpUrl(URI.create(config.getString(API_HELP_URL)));

		appProperties.setLogLevel(config.getString(LOG_LEVEL_PROPERTY));

		log.debug("Running with properties: {}", appProperties.toString());

		return appProperties;
	}
}

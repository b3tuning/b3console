package com.b3tuning.b3console.service.files.base;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Slf4j
public class ApiRestUrlBuilder {

	public static final String NAME_FILTER = "name::%s";
	public static final String ENABLED_FILTER = "enabled::%s";


	/**
	 * The get users URL. Needs to be formatted with the base url and the
	 * organizationUid.
	 */
	private static final String GET_USERS = "%s/organizations/%s/users";


	/**
	 * The get user URL. Needs to be formatted with the base url and the
	 * userUid.
	 */
	private static final String GET_USER = "%s/organizations/%s/users/%s";
	private static final String UPDATE_USER = "%s/organizations/%s/users/%s";


	/**
	 * The post job URL. Needs to be formatted with the base url,
	 * organizationUid and projectUid
	 */
	private static final String POST_JOB = "%s/organizations/%s/projects/%s/jobs";

	/**
	 * The get jobs URL. Needs to be formatted with the base url,
	 * organizationUid and projectUid
	 */
	private static final String GET_JOBS = "%s/organizations/%s/projects/%s/jobs";

	/**
	 * The get job URL. Needs to be formatted with the base url,
	 * organizationUid and jobUid
	 */
	private static final String GET_JOB = "%s/organizations/%s/jobs/%s";

	/**
	 * The create sub organization URL. Needs to be formatted with the base url
	 * and the parent organization uid.
	 */
	private static final String CREATE_SUB_ORGANIZATION = "%s/organizations/%s/organizations";

	/**
	 * 
	 * @param baseUrl
	 *            the endpoint base url
	 * @param organizationUid
	 *            the organization uid
	 * @return the complete url
	 */
	public static String buildGetUsersUrl(final String baseUrl, final UUID organizationUid) {
		String url = String.format(GET_USERS, baseUrl, organizationUid);
		log.trace("Built url: '{}'.", url);
		return url;
	}

	/**
	 *
	 * @param baseUrl
	 *            the endpoint base url
	 * @param organizationUid
	 *            the organization uid
	 * @param filter
	 * 			  the user name to filter
	 * @return the complete url
	 */
	public static String buildGetUsersUrl(final String baseUrl, final UUID organizationUid, final String filter) {
		String url = String.format(GET_USERS.concat("?"), baseUrl, organizationUid).concat(filter);
		log.trace("Built url: '{}'.", url);
		return url;
	}

	/**
	 *
	 * @param baseUrl
	 *            the endpoint base url
	 * @param userUid
	 *            the user uid
	 * @return the complete url
	 */
	public static String buildGetUserUrl(String baseUrl, UUID organizationUid, UUID userUid) {
		String url = String.format(GET_USER, baseUrl, organizationUid, userUid);
		log.trace("Built url: '{}'.", url);
		return url;
	}

	/**
	 * 
	 * @param baseUrl
	 *            the endpoint base url
	 * @param organizationUid
	 *            the organization uid
	 * @param projectUid
	 *            the project uid
	 * @return the complete url to post a job
	 */
	public static String buildPostJobUrl(final String baseUrl, final UUID organizationUid, final UUID projectUid) {
		String url = String.format(POST_JOB, baseUrl, organizationUid, projectUid);
		log.trace("Built url: '{}'.", url);
		return url;
	}

	/**
	 * @param baseUrl
	 *            the endpoint base url
	 * @param userUid
	 *            the user uid
	 * @return the complete url
	 */
	public static String buildUpdateUserUrl(final String baseUrl, final UUID organizationUid, final UUID userUid) {
		String url = String.format(UPDATE_USER, baseUrl, organizationUid, userUid);
		log.trace("Built update user url: '{}'.", url);
		return url;
	}

	/**
	 * 
	 * @param baseUrl
	 * @param organizationUid
	 * @param projectUid
	 * @return
	 */
	public static String buildGetJobsUrl(final String baseUrl, final UUID organizationUid, final UUID projectUid) {
		String url = String.format(GET_JOBS, baseUrl, organizationUid, projectUid);
		log.trace("Built get jobs url: '{}'.", url);
		return url;
	}

	/**
	 * 
	 * @param baseUrl
	 * @param jobUid
	 * @return the get job url
	 */
	public static String buildGetJobUrl(final String baseUrl, final UUID organizationUid, final UUID jobUid) {
		String url = String.format(GET_JOB, baseUrl, organizationUid, jobUid);
		log.trace("Built get job url: '{}'.", url);
		return url;
	}

	/**
	 * 
	 * @param baseUrl
	 * @param parentOrganizationUid
	 * @return the create sub organization url
	 */
	public static String buildCreateSubOrganizationUrl(final String baseUrl, final UUID parentOrganizationUid) {
		String url = String.format(CREATE_SUB_ORGANIZATION, baseUrl, parentOrganizationUid);
		log.trace("Built url: '{}'.", url);
		return url;
	}
}

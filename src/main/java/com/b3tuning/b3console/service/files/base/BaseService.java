package com.b3tuning.b3console.service.files.base;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.b3tuning.b3console.service.files.base.RequestConstants.FILTER_QUERY_PARAM;
import static com.b3tuning.b3console.service.files.base.RequestConstants.PAGINATION_PAGE_QUERY_PARAM;
import static com.b3tuning.b3console.service.files.base.RequestConstants.PAGINATION_SIZE_QUERY_PARAM;
import static com.b3tuning.b3console.service.files.base.RequestConstants.SORT_QUERY_PARAM;
import static com.b3tuning.b3console.service.files.base.ResponseConstants.PAGINATION_DETAILS_KEY;
import static com.b3tuning.b3console.service.files.base.ResponseConstants.PAGINATION_PAGE_KEY;
import static com.b3tuning.b3console.service.files.base.ResponseConstants.PAGINATION_PAGE_SIZE_KEY;
import static com.b3tuning.b3console.service.files.base.ResponseConstants.PAGINATION_TOTAL_PAGES_KEY;
import static com.b3tuning.b3console.service.files.base.ResponseConstants.PAGINATION_TOTAL_RESULTS_KEY;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Slf4j
public abstract class BaseService {

	public static final String PARAM_ORGANIZATION_UID = "organizationUid";
	public static final String ORGANIZATION_PATH_TEMPLATE = "/organizations/{"+PARAM_ORGANIZATION_UID+"}";

	@Getter
	private final String baseUrl;


	protected BaseService(String baseUrl) {
		Preconditions.checkArgument(baseUrl != null && baseUrl.length() > 0, "Base Url not provided");

		this.baseUrl = baseUrl;
	}

	protected static Map<String, Object> buildCommonListQueryParams(PageableEvent request) {
		Map<String, Object> params = new HashMap<>();
		if (request.getPage() != null) {
			params.put(PAGINATION_PAGE_QUERY_PARAM, request.getPage());
		}
		if (request.getSize() != null) {
			params.put(PAGINATION_SIZE_QUERY_PARAM, request.getSize());
		}
		if (request.getFilter() != null && request.getFilter().length() > 0) {
			params.put(FILTER_QUERY_PARAM, request.getFilter());
		}
		if (request.getSort() != null && request.getSort().length() > 0) {
			params.put(SORT_QUERY_PARAM, request.getSort());
		}
		log.trace("query params {}", params);
		return params;
	}

	/**
	 * Checks the request is not null and contains an authentication token
	 *
	 * @param request
	 */
	protected static void checkTokenProvided(AuthEvent request) {
		Preconditions.checkArgument(request != null, "request must be null");
		Preconditions.checkArgument(request.getAuthToken() != null && request.getAuthToken().length() > 0,
		                            "Auth Token not provided");
	}

//	/**
//	 * Fills the pagination attributes with the JSONObject
//	 *
//	 * @param response
//	 * @param root
//	 */
//	protected static <T extends PageableResponse<?>> void assemblePaginationAttributes(PageableResponse<T> response, JSONObject root) {
//		JSONObject pagination = root.getJSONObject(PAGINATION_DETAILS_KEY);
//		response.setCurrentPage(pagination.getInt(PAGINATION_PAGE_KEY));
//		response.setPageSize(pagination.getInt(PAGINATION_PAGE_SIZE_KEY));
//		response.setTotalPages(pagination.getInt(PAGINATION_TOTAL_PAGES_KEY));
//		response.setTotalResults(pagination.getInt(PAGINATION_TOTAL_RESULTS_KEY));
//	}

	protected static <OUT extends PageableResponse<?>, IN extends ResourcesPage> void assemblePaginationAttributes(OUT response, IN resource) {
		response.setCurrentPage(resource.getCurrentPage());
		response.setPageSize(resource.getPageSize());
		response.setTotalPages(resource.getTotalPages());
		response.setTotalResults(resource.getTotalResults());
	}

	protected static <OUT extends BasePageableResponse<?>, IN extends ResourcesPage> void assemblePaginationAttributes(OUT response, IN resource) {
		response.getPage().setNumber(resource.getCurrentPage());
		response.getPage().setSize(resource.getPageSize());
		response.getPage().setTotalPages(resource.getTotalPages());
		response.getPage().setTotalElement(resource.getTotalResults());
	}

//	protected String getStringOptionalValue(JSONObject jsonResponse, String nameField) {
//		try {
//			if(jsonResponse.has(nameField)) {
//				return jsonResponse.getString(nameField);
//			}
//		} catch (JSONException e) {
//			try {
//				log.warn("Exception reading {} provided on response, value={}", nameField, jsonResponse.get(nameField));
//			} catch (JSONException ex) {
//				log.error(e.getMessage(), ex);
//			}
//		}
//		return null;
//	}
//
//	protected Boolean getBooleanOptionalValue(JSONObject jsonResponse, String nameField) {
//		try {
//			if(jsonResponse.has(nameField)) {
//				return jsonResponse.getBoolean(nameField);
//			} else {
//				log.warn("No {} provided on response", nameField);
//			}
//		} catch (JSONException e) {
//			try {
//				log.warn("Exception reading {} provided on response, value={}", nameField, jsonResponse.get(nameField));
//			} catch (JSONException ex) {
//				log.error(e.getMessage(), ex);
//			}
//		}
//		return null;
//	}
//
//	protected Integer getIntegerOptionalValue(JSONObject jsonResponse, String nameField) {
//		try {
//			if(jsonResponse.has(nameField)) {
//				return jsonResponse.getInt(nameField);
//			} else {
//				log.warn("No {} provided on response", nameField);
//			}
//		} catch (JSONException e) {
//			try {
//				log.warn("Exception reading {} provided on response, value={}", nameField, jsonResponse.get(nameField));
//			} catch (JSONException ex) {
//				log.error(e.getMessage(), ex);
//			}
//		}
//		return null;
//	}
//
//	protected Double getDoubleOptionalValue(JSONObject jsonResponse, String nameField) {
//		try {
//			if(jsonResponse.has(nameField)) {
//				return jsonResponse.getDouble(nameField);
//			} else {
//				log.warn("No {} provided on response", nameField);
//			}
//		} catch (JSONException e) {
//			try {
//				log.warn("Exception reading {} provided on response, value={}", nameField, jsonResponse.get(nameField));
//			} catch (JSONException ex) {
//				log.error(e.getMessage(), ex);
//			}
//		}
//		return null;
//	}
//
}

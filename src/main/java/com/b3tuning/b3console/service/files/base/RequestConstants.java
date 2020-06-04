package com.b3tuning.b3console.service.files.base;


public abstract class RequestConstants {

	public static final String ACCEPT_HEADER = "Accept";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String BEARER_HEADER_VALUE = "Bearer ";

	public static final String SUPPORT_JSON_MIME_TYPE = "application/vnd.b3tuning-v1.0+json";
	public static final String SUPPORT_HAL_JSON_MIME_TYPE = "application/vnd.b3tuning-v1.0hal+json";

	public static final String PAGINATION_PAGE_QUERY_PARAM = "page";
	public static final String PAGINATION_SIZE_QUERY_PARAM = "size";
	public static final String FILTER_QUERY_PARAM = "filter";
	public static final String SORT_QUERY_PARAM = "sort";

}

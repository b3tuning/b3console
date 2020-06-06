package com.b3tuning.b3console.service.files.file;

import com.b3tuning.b3console.service.files.base.BaseService;
import com.google.common.base.Preconditions;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//import com.mashape.unirest.request.HttpRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

import static com.b3tuning.b3console.service.files.base.RequestConstants.ACCEPT_HEADER;
import static com.b3tuning.b3console.service.files.base.RequestConstants.AUTHORIZATION_HEADER;
import static com.b3tuning.b3console.service.files.base.RequestConstants.SUPPORT_HAL_JSON_MIME_TYPE;


/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Slf4j
public class FilesApiRESTImpl { //extends BaseService implements FilesApi {

	// URL TEMPLATES - PARAMS
//	public static final String PARAM_LOCATION_UID = "locationUid";
//	public static final String PARAM_FILE_UID = "fileUid";
//	public static final String PARAM_FILE_UID_OR_PATH = "fileUidOrPath";

	// URL TEMPLATES - PATHS
//	private static final String ORGANIZATION_LOCATION_PATH_TEMPLATE = ORGANIZATION_PATH_TEMPLATE + "/locations";
//	public static final String GET_SPECIFIC_FILE_URL_PATH_TEMPLATE = ORGANIZATION_PATH_TEMPLATE + "/files/{"+PARAM_FILE_UID+"}";
//	public static final String FILE_FOR_LOCATION_URL_PATH_TEMPLATE = ORGANIZATION_LOCATION_PATH_TEMPLATE+"/{"+PARAM_LOCATION_UID+"}/files/{"+PARAM_FILE_UID_OR_PATH+"}";
//
//	public FilesApiRESTImpl(String baseUrl) {
//		super(baseUrl);
//	}
//
//	@Override
//	public FileResponse file(RetrieveFileEvent request) {
//
//		checkTokenProvided(request);
//		Preconditions.checkArgument(request.getOrganizationUid() != null, "Organization UID not provided");
//		Preconditions.checkArgument(request.getFileUidOrPath() != null, "File UID not provided");
//
//		String url = getBaseUrl() + FILE_FOR_LOCATION_URL_PATH_TEMPLATE;
//		String fileUidOrPath = null;
//
//		try {
//			// try to decode the file uid
//			fileUidOrPath = UUID.fromString(request.getFileUidOrPath()).toString();
//		} catch (IllegalArgumentException e) {
//			log.trace("Unable to get file Uid from fileUidOrPath, using filePath instead. Reason: {}", e.getMessage());
//			try {
//				// try to decode filePath
//				fileUidOrPath = Base64.getEncoder().encodeToString(request.getFileUidOrPath().getBytes("UTF-8"));
//			} catch (UnsupportedEncodingException ignored) {
//				// Can be safely ignored because UTF-8 is always supported
//			}
//		}
//
//        try {
//
//			HttpRequest http = Unirest.get(url)
//									  .routeParam(PARAM_ORGANIZATION_UID, request.getOrganizationUid().toString())
//									  .routeParam(PARAM_LOCATION_UID, request.getLocationUid().toString())
//									  .routeParam(PARAM_FILE_UID_OR_PATH, fileUidOrPath)
//									  .header(ACCEPT_HEADER, SUPPORT_HAL_JSON_MIME_TYPE)
//									  .header(AUTHORIZATION_HEADER, "Bearer " + request.getAuthToken());
//
//	        log.trace("URL: {}", http.getUrl());
//
//
//			HttpResponse<FileResource> response = http.asObject(FileResource.class);
//			log.trace("Status: {}", response.getStatus());
//
//			switch (response.getStatus()) {
//				case 200:
//                    log.trace("Response Body = {}", response.getBody());
//					return new FileResponse().setFile(response.getBody());
//				case 204:
//					return new FileResponse();
//				case 401:
//					return new FileResponse().failedAuthentication();
//				case 403:
//					return new FileResponse().failedAuthorization();
//				default:
//					return new FileResponse().failedCall(response.getStatusText());
//			}
//		} catch (UnirestException/* | IOException*/ e) {
//			return new FileResponse().failedCall(e.getMessage());
//		}
//
//	}
}

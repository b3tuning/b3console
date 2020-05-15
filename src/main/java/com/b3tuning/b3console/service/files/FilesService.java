package com.b3tuning.b3console.service.files;

import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkArgument;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class FilesService {

	private final FileAssembler assembler;

	@Inject
	public FilesService(FileAssembler assembler) {
		this.assembler = assembler;
	}


//	public FileEntity file(FileByLocationRequest request) {
//		checkArgument(request != null, "FileByLocationRequest not provided");
//		checkArgument(request.getOrganizationUid() != null, "Organization Uid not provided");
//		checkArgument(request.getLocationUid() != null, "Location Uid not provided");
//		checkArgument(request.getFileUidOrPath() != null, "File Uid not provided");
//
//		RetrieveFileEvent apiRequest = new RetrieveFileEvent(
//				request.getOrganizationUid(),
//				request.getLocationUid(),
//				request.getFileUidOrPath(),
//				user.getToken());
//
//		FileResponse response = filesApi.file(apiRequest);
//
//		log.info("get File response {}", response);
//		if (!response.isFailed()) {
//			return assembler.assemble(response.getFile());
//		}

//		ServiceResponseHelper.processFailure(response, user);

//		return null;
//	}
}

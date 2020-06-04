package com.b3tuning.b3console.service.files.file;

import com.b3tuning.b3console.service.files.base.AuthEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.UUID;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RetrieveFileEvent extends AuthEvent {

	private final UUID organizationUid;
	private final UUID locationUid;
	private final String fileUidOrPath;

	public RetrieveFileEvent(UUID organizationUid, UUID locationUid, String fileUidOrPath, String authToken) {
		super(authToken);
		this.organizationUid = organizationUid;
		this.locationUid = locationUid;
		this.fileUidOrPath = fileUidOrPath;
	}
}

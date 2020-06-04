package com.b3tuning.b3console.service.files.base;

import lombok.Data;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@SuppressWarnings({"rawtypes", "unchecked"})
@Data
public class BaseResponse <T extends BaseResponse> {

	private boolean badRequest;

	private boolean failed;

	private boolean failedAuthentication;
	
	private boolean failedAuthorization;
	
	private boolean conflict;

	private String failedReason;

	public T badRequest() {
		setFailed(true);
		setBadRequest(true);
		return (T) this;
	}

	public T failedCall(String failedReason) {
		setFailed(true);
		setFailedReason(failedReason);
		return (T) this;
	}

	public T failedAuthentication() {
		setFailed(true);
		setFailedAuthentication(true);
		return (T) this;
	}

	public T failedAuthorization() {
		setFailed(true);
		setFailedAuthorization(true);
		return (T) this;
	}
	
	public T conflict() {
		setFailed(true);
		setConflict(true);
		return (T) this;
	}

}

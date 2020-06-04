package com.b3tuning.b3console.service.files.file;

import com.b3tuning.b3console.service.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Data
@Accessors(chain=true)
@EqualsAndHashCode(callSuper=true)
public class FileResponse extends BaseResponse<FileResponse> {

	private FileResource file;
}

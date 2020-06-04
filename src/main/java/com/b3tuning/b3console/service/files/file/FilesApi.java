package com.b3tuning.b3console.service.files.file;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

public interface FilesApi {
	FileResponse file(RetrieveFileEvent request);
}
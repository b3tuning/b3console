package com.b3tuning.b3console.service.files;

import java.util.List;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public interface TreeElement {

	boolean isDirectory();

	boolean isHidden();

	boolean isFile();

	List<? extends TreeElement> getChildren();

	String getName();

	long getSize();

	long lastModified();
}

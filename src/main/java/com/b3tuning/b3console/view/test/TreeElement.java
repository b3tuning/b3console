/*
 *  Created on:  Jun 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  TreeElement is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.test;

import java.util.List;

public interface TreeElement {

	boolean isDirectory();

	boolean isHidden();

	boolean isFile();

	List<? extends TreeElement> getChildren();

	String getName();

	long getSize();

	long lastModified();
}

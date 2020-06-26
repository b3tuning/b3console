/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ToolsViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.tools;

import com.b3tuning.b3console.view.BaseViewModel;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class ToolsMenuViewModel extends BaseViewModel {

	@Inject
	public ToolsMenuViewModel() {
		log.entry();
	}

	void optionsAction() {
		log.entry();
	}
}

/*
 *  Created on:  Jun 10, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  MenuViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu;

import com.b3tuning.b3console.view.BaseViewModel;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class MenuViewModel extends BaseViewModel {

	@Inject
	public MenuViewModel() {
		log.entry();
	}

}

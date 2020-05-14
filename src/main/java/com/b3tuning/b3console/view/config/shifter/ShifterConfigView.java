package com.b3tuning.b3console.view.config.shifter;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class ShifterConfigView extends BaseView<ShifterConfigViewModel> {

	@InjectViewModel private ShifterConfigViewModel viewModel;

	@Inject
	public ShifterConfigView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

	}
}

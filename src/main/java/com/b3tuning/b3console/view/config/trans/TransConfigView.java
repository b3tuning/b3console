package com.b3tuning.b3console.view.config.trans;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.validation.visualization.ControlsFxVisualizer;
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
public class TransConfigView extends BaseView<TransConfigViewModel> {

	private final ControlsFxVisualizer validationVisualizer = new ControlsFxVisualizer();

	@InjectViewModel private TransConfigViewModel viewModel;

	@Inject
	public TransConfigView() {}

	public void initialize() {
		log.entry();
	}
}

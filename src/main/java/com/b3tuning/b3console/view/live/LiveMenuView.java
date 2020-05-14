package com.b3tuning.b3console.view.live;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class LiveMenuView extends BaseView<LiveMenuViewModel> {

//	@FXML StackPane moduleStack;

	@InjectViewModel private LiveMenuViewModel viewModel;

	@Inject
	public LiveMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();
	}
}

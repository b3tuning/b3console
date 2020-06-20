/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ViewView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.view;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class ViewMenuView extends BaseView<ViewMenuViewModel> {

	@InjectViewModel private ViewMenuViewModel viewModel;

	@Inject
	public ViewMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

	}

}

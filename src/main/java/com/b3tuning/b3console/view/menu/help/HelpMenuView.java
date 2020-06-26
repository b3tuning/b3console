/*
 *  Created on:  Jun 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  HelpView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.menu.help;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class HelpMenuView extends BaseView<HelpMenuViewModel> {

	@InjectViewModel private HelpMenuViewModel viewModel;

	@Inject
	public HelpMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();
	}
}

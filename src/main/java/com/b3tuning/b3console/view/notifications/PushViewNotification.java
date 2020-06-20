package com.b3tuning.b3console.view.notifications;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class PushViewNotification {

	private final String key;

	private final ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple;

	/**
	 * We may want to close some views (such as detail views) as soon as they lose focus
	 */
	private boolean closeOnLostFocus;

	/**
	 * Normally, a view cannot be pushed if the existing visible view is dirty. This can be
	 * overridden by setting ignoreDirty.
	 */
	private boolean ignoreDirty;
}

package com.b3tuning.b3console.view;

import de.saxsys.mvvmfx.ViewModel;
import lombok.extern.slf4j.XSlf4j;
import org.reactfx.Subscription;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class BaseViewModel implements ViewModel, Disposable {

	private Subscription subscriptions;

	protected void manage(Subscription subscription) {
		if (subscriptions == null) {
			subscriptions = subscription;
		} else {
			subscriptions = subscriptions.and(subscription);
		}
	}

	@Override
	public void dispose() {
		log.entry();
		if (subscriptions != null) {
			subscriptions.unsubscribe();
		}
	}
}

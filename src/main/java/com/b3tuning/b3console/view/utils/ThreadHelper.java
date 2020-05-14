package com.b3tuning.b3console.view.utils;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public abstract class ThreadHelper {

	/**
	 * Automatically start the Runnable on a new thread, setting as a daemon thread
	 * @param target thread
	 */
	public static void run(Runnable target) {
		Thread t = new Thread(target);
		t.setDaemon(true);
		t.start();
	}

}

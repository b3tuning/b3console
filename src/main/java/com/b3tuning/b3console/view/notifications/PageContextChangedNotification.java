package com.b3tuning.b3console.view.notifications;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@AllArgsConstructor
public class PageContextChangedNotification {

	private String view;
}

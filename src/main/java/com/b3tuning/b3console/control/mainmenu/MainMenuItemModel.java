package com.b3tuning.b3console.control.mainmenu;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@AllArgsConstructor
public class MainMenuItemModel {

	private String             expandedTitle;
	private String             compressedTitle;
	private MainMenuItemAction action;
	private Position           position;
	private Mode               mode;

	public static enum Position {
		TOP, BOTTOM;
	}

	public static enum Mode {
		APP, EXTERNAL;
	}
}

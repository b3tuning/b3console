package com.b3tuning.b3console.control.mainmenu;

/*
 *  Created on:  May 13, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public enum MainMenuItemAction {
	NONE,
	FILE,     // fa FOLDER_OPEN
	CONFIG,   // fa EDIT
	TRANSFER, // fa EXCHANGE
	LIVE,     // fa TACHOMETER
	SETTINGS, // fa COGS
	HELP_APP,

	// EDIT
	UNDO,
	REDO,
	CUT,
	COPY,
	DELETE,

	// FILE
	NEW,
	OPEN,
	OPEN_RECENT,
	CLOSE,
	SAVE,
	SAVE_AS,
	SEND,
	QUIT,

	// HELP
	HELP,

	// ONLINE
	CONNECT,
	DISCONNECT,
	MONITOR_IO,

	// TOOLS
	OPTIONS,

	// VIEW
	;
	// TEST SO MIKE CAN SEE I'M WORKING
}

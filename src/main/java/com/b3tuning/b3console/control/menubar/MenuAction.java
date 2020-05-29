package com.b3tuning.b3console.control.menubar;

/*
 *  Created on:  May 20, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public enum MenuAction {
	// EDIT
	A_UNDO,        // publish
	A_REDO,        // publish
	A_CUT,         // publish
	A_COPY,        // publish
	A_DELETE,      // publish

	// FILE
	A_NEW,         // publish
	A_OPEN,        // publish
	A_RECENTS,     // publish
	A_CLOSE,       // publish
	A_SAVE,        // publish
	A_SAVE_AS,     // publish
	A_SEND,        // publish
	A_QUIT,        // publish

	// HELP
	A_HELP,

	// ONLINE
	A_CONNECT,     // publish
	A_DISCONNECT,  // publish
	A_MONITOR_IO,

	// TOOLS
	A_OPTIONS,

	// VIEW

	// NONE
	A_NONE,
	;
}

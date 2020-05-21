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
	A_UNDO,
	A_REDO,
	A_CUT,
	A_COPY,
	A_DELETE,

	// FILE
	A_NEW,
	A_OPEN,
	A_RECENTS,
	A_CLOSE,
	A_SAVE,
	A_SAVE_AS,
	A_SEND,
	A_QUIT,

	// HELP
	A_HELP,

	// ONLINE
	A_CONNECT,
	A_DISCONNECT,
	A_MONITOR_IO,

	// TOOLS
	A_OPTIONS,

	// VIEW

	// NONE
	A_NONE,
	;
}

package com.b3tuning.b3console.control.menubar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum OnlineMenu implements MenuItemInterface {
	// ONLINE ONLINE
	// Connect
	// Disconnect
	// Monitor IO

	CONNECT("Connect", null, KeyCombination.valueOf("Ctrl+Shift+L"), e -> {log.entry();}),
	DISCONNECT("Disconnect", null, KeyCombination.valueOf("Ctrl+Shift+D"), e -> {log.entry();}),
	MONITOR_IO("Monitor IO", null, KeyCombination.valueOf("Ctrl+Shift+M"), e -> {log.entry();}),
	;

	@Getter private final String                    label;
	@Getter private final String                    icon;
	@Getter private final KeyCombination            shortcut;
	@Getter private final EventHandler<ActionEvent> action;

	OnlineMenu(String label, String icon, KeyCombination shortcut, EventHandler<ActionEvent> action) {
		this.label    = label;
		this.icon     = icon;
		this.shortcut = shortcut;
		this.action   = action;
	}

	public OnlineMenu findByLabel(String label) {
		log.entry();
		for (OnlineMenu item : OnlineMenu.values()) {
			if (item.getLabel().equals(label)) {
				return item;
			}
		}
		return null;
	}

	public String toString() {
		return label;
	}

}

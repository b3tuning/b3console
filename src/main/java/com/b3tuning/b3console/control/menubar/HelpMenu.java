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
public enum HelpMenu implements MenuItemInterface {

	// HELP HELP
	//

	Help("Help", null, KeyCombination.valueOf("Ctrl+Shift+H"), e -> {log.entry();}),
	;

	@Getter private final String                    label;
	@Getter private final String                    icon;
	@Getter private final KeyCombination            shortcut;
	@Getter private final EventHandler<ActionEvent> action;

	HelpMenu(String label, String icon, KeyCombination shortcut, EventHandler<ActionEvent> action) {
		this.label    = label;
		this.icon     = icon;
		this.shortcut = shortcut;
		this.action   = action;
	}

	public static HelpMenu findByLabel(String label) {
		log.entry();
		for (HelpMenu item : HelpMenu.values()) {
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
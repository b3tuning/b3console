package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.view.root.FontAwesome;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

import java.util.Arrays;
import java.util.List;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum MainMenuBarItem {

	FILE("File", null, Arrays.asList(FileMenu.values())),
	EDIT("Edit", null, Arrays.asList(EditMenu.values())),
	VIEW("View", null, Arrays.asList(ViewMenu.values())),
	ONLINE("Online", null, Arrays.asList(OnlineMenu.values())),
	TOOLS("Tools", null, Arrays.asList(ToolsMenu.values())),
	HELP("Help", null, Arrays.asList(HelpMenu.values()));

	@Getter private final String      label;
	@Getter private final FontAwesome icon;
	@Getter private final List<?>     items;

	MainMenuBarItem(String label, FontAwesome icon, List<?> items) {
		this.label = label;
		this.icon  = icon;
		this.items = items;
	}

	public static MainMenuBarItem findByLabel(String label) {
		log.entry();
		for (MainMenuBarItem item : MainMenuBarItem.values()) {
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

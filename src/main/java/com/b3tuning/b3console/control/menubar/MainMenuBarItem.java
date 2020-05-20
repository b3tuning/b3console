package com.b3tuning.b3console.control.menubar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;
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
public enum MainMenuBarItem implements MenuItemInterface {

	FILE("File", null, null, e -> {log.entry();}, Arrays.asList(FileMenu.values())),
	EDIT("Edit", null, null, e -> {log.entry();}, Arrays.asList(EditMenu.values())),
	VIEW("View", null, null, e -> {log.entry();}, Arrays.asList(ViewMenu.values())),
	ONLINE("Online", null, null, e -> {log.entry();}, Arrays.asList(OnlineMenu.values())),
	TOOLS("Tools", null, null, e -> {log.entry();}, Arrays.asList(ToolsMenu.values())),
	HELP("Help", null, null, e -> {log.entry();}, Arrays.asList(HelpMenu.values()));

	@Getter private final String                    label;
	@Getter private final String                    icon;
	@Getter private final KeyCombination            shortcut;
	@Getter private final EventHandler<ActionEvent> action;
	@Getter private final List<?>                   items;

	MainMenuBarItem(String label, String icon, KeyCombination shortcut, EventHandler<ActionEvent> action,
	                List<?> items) {
		this.label    = label;
		this.icon     = icon;
		this.shortcut = shortcut;
		this.action   = action;
		this.items    = items;
	}

	public MainMenuBarItem findByLabel(String label) {
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

package com.b3tuning.b3console.control.menubar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

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

	FILE("File", new Glyph("FontAwesome", FontAwesome.Glyph.FILE).size(16).color(Color.GREY), null, e -> {log.entry();}, Arrays.asList(FileMenu.values())),
	EDIT("Edit", new Glyph("FontAwesome", FontAwesome.Glyph.EDIT).size(16).color(Color.GREY), null, e -> {log.entry();}, Arrays.asList(EditMenu.values())),
	VIEW("View", new Glyph("FontAwesome", FontAwesome.Glyph.GLASS).size(16).color(Color.GREY), null, e -> {log.entry();}, Arrays.asList(ViewMenu.values())),
	ONLINE("Online", new Glyph("FontAwesome", FontAwesome.Glyph.EDIT).size(16).color(Color.GREY), null, e -> {log.entry();}, Arrays.asList(OnlineMenu.values())),
	TOOLS("Tools", new Glyph("FontAwesome", FontAwesome.Glyph.GEARS).size(16).color(Color.GREY), null, e -> {log.entry();}, Arrays.asList(ToolsMenu.values())),
	HELP("Help", new Glyph("FontAwesome", FontAwesome.Glyph.QUESTION).size(16).color(Color.GREY), null, e -> {log.entry();}, Arrays.asList(HelpMenu.values()));

	@Getter private final String                    label;
	@Getter private final Glyph                     icon;
	@Getter private final KeyCombination            shortcut;
	@Getter private final EventHandler<ActionEvent> action;
	@Getter private final List<?>                   items;

	MainMenuBarItem(String label, Glyph icon, KeyCombination shortcut, EventHandler<ActionEvent> action,
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

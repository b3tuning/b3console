package com.b3tuning.b3console.control.menubar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum FileMenu implements MenuItemInterface {
	// FILE FILE
	// Open
	// Save
	// Save as
	// Send

	OPEN("Open", new Glyph("FontAwesome", FontAwesome.Glyph.FOLDER_OPEN).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+O"), e -> {log.entry();}),
	SAVE("Save", new Glyph("FontAwesome", FontAwesome.Glyph.SAVE).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+S"), e -> {log.entry();}),
	SAVE_AS("Save As", new Glyph("FontAwesome", FontAwesome.Glyph.ELLIPSIS_H).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+A"), e -> {log.entry();}),
	SEND("Send", new Glyph("FontAwesome", FontAwesome.Glyph.SEND).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+U"), e -> {log.entry();});

	@Getter private final String                    label;
	@Getter private final Glyph                     icon;
	@Getter private final KeyCombination            shortcut;
	@Getter private final EventHandler<ActionEvent> action;

	FileMenu(String label, Glyph icon, KeyCombination shortcut, EventHandler<ActionEvent> action) {
		this.label    = label;
		this.icon     = icon;
		this.shortcut = shortcut;
		this.action   = action;
	}

	public FileMenu findByLabel(String label) {
		log.entry();
		for (FileMenu item : FileMenu.values()) {
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

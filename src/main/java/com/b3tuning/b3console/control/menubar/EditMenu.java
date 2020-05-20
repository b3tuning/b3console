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
public enum EditMenu implements MenuItemInterface {
	// EDIT EDIT
	// Undo
	// Redo
	// Cut
	// Copy
	// Delete

	UNDO("Undo", new Glyph("FontAwesome", FontAwesome.Glyph.UNDO).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Z"), e -> {log.entry();}),
	REDO("Redo", new Glyph("FontAwesome", FontAwesome.Glyph.BACKWARD).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+Z"), e -> {log.entry();}),
	CUT("Cut", new Glyph("FontAwesome", FontAwesome.Glyph.CUT).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+X"), e -> {log.entry();}),
	COPY("Copy", new Glyph("FontAwesome", FontAwesome.Glyph.COPY).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+C"), e -> {log.entry();}),
	DELETE("Delete", new Glyph("FontAwesome", FontAwesome.Glyph.REMOVE).size(16).color(Color.GREY), KeyCombination.valueOf("Ctrl+Shift+D"), e -> {log.entry();});

	@Getter private final String                    label;
	@Getter private final Glyph                    icon;
	@Getter private final KeyCombination            shortcut;
	@Getter private final EventHandler<ActionEvent> action;

	EditMenu(String label, Glyph icon, KeyCombination shortcut, EventHandler<ActionEvent> action) {
		this.label    = label;
		this.icon     = icon;
		this.shortcut = shortcut;
		this.action   = action;
	}

	public EditMenu findByLabel(String label) {
		log.entry();
		for (EditMenu item : EditMenu.values()) {
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

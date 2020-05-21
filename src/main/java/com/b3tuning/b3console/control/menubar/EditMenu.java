package com.b3tuning.b3console.control.menubar;

import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.menubar.MenuAction.A_COPY;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_CUT;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_DELETE;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_REDO;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_UNDO;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setIcon;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setShortCut;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.BACKWARD;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.COPY;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.CUT;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.REMOVE;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.UNDO;

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

	M_UNDO(A_UNDO, "Undo", setIcon(UNDO), setShortCut("Ctrl+Z"), true, false),
	M_REDO(A_REDO, "Redo", setIcon(BACKWARD), setShortCut("Ctrl+Shift+Z"), true, false),
	M_CUT(A_CUT, "Cut", setIcon(CUT), setShortCut("Ctrl+Shift+X"), true, false),
	M_COPY(A_COPY, "Copy", setIcon(COPY), setShortCut("Ctrl+Shift+C"), true, false),
	M_DELETE(A_DELETE, "Delete", setIcon(REMOVE), setShortCut("Ctrl+Shift+D"), true, false),
	;

	@Getter private final MenuAction     action;
	@Getter private final String         label;
	@Getter private final Glyph          icon;
	@Getter private final KeyCombination shortcut;
	@Getter private final boolean        selectable;
	@Getter private final boolean        separated;

	EditMenu(MenuAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable,
	         boolean separated) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.selectable = selectable;
		this.separated  = separated;
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

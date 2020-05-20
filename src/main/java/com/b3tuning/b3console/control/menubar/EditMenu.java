package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.COPY;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.CUT;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.DELETE;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.REDO;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.UNDO;

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

	UNDO_MENU(UNDO, "Undo", setIcon(FontAwesome.Glyph.UNDO),new Glyph("FontAwesome", FontAwesome.Glyph.UNDO), KeyCombination.valueOf("Ctrl+Z"), true, false),
	REDO_MENU(REDO, "Redo", new Glyph("FontAwesome", FontAwesome.Glyph.BACKWARD), KeyCombination.valueOf("Ctrl+Shift+Z"), true, false),
	CUT_MENU(CUT, "Cut", new Glyph("FontAwesome", FontAwesome.Glyph.CUT), KeyCombination.valueOf("Ctrl+Shift+X"), true, false),
	COPY_MENU(COPY, "Copy", new Glyph("FontAwesome", FontAwesome.Glyph.COPY), KeyCombination.valueOf("Ctrl+Shift+C"), true, false),
	DELETE_MENU(DELETE, "Delete", new Glyph("FontAwesome", FontAwesome.Glyph.REMOVE), KeyCombination.valueOf("Ctrl+Shift+D"), true, false),
	;

	@Getter private final MainMenuItemAction action;
	@Getter private final String             label;
	@Getter private final Glyph              icon;
	@Getter private final KeyCombination     shortcut;
	@Getter private final boolean            selectable;
	@Getter private final boolean            separated;

	EditMenu(MainMenuItemAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable,
	         boolean separated) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.selectable = selectable;
		this.separated  = separated;
	}

	public 	Glyph setIcon(Object icon) {
		return new Glyph("FontAwesome", icon);
	}

	public KeyCombination setShortCut(String keys) {
		return KeyCombination.valueOf(keys);
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

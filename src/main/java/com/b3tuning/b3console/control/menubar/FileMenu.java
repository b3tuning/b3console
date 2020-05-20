package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.CLOSE;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.NEW;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.OPEN;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.OPEN_RECENT;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.QUIT;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.SAVE;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.SAVE_AS;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.SEND;

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

	NEW_MENU(NEW, "New", new Glyph("FontAwesome", FontAwesome.Glyph.FOLDER_OPEN), KeyCombination.valueOf("Ctrl+Shift+O"), true, false),
	OPEN_MENU(OPEN, "Open", new Glyph("FontAwesome", FontAwesome.Glyph.FOLDER_OPEN), KeyCombination.valueOf("Ctrl+Shift+O"), true, false),
	OPEN_RECENT_MENU(OPEN_RECENT, "Open Recent", new Glyph("FontAwesome", FontAwesome.Glyph.FOLDER_OPEN), KeyCombination.valueOf("Ctrl+Shift+O"), true, false),
	CLOSE_MENU(CLOSE, "Close", new Glyph("FontAwesome", FontAwesome.Glyph.FOLDER_OPEN), KeyCombination.valueOf("Ctrl+Shift+O"), true, false),
	SAVE_MENU(SAVE, "Save", new Glyph("FontAwesome", FontAwesome.Glyph.SAVE), KeyCombination.valueOf("Ctrl+Shift+S"), true, true),
	SAVE_AS_MENU(SAVE_AS, "Save As", new Glyph("FontAwesome", FontAwesome.Glyph.ELLIPSIS_H), KeyCombination.valueOf("Ctrl+Shift+A"), true, false),
	SEND_MENU(SEND, "Send", new Glyph("FontAwesome", FontAwesome.Glyph.SEND), KeyCombination.valueOf("Ctrl+Shift+U"), true, true),
	QUIT_MENU(QUIT, "Quit", new Glyph("FontAwesome", FontAwesome.Glyph.SEND), KeyCombination.valueOf("Ctrl+Shift+U"), true, true),
	;

	@Getter private final MainMenuItemAction action;
	@Getter private final String             label;
	@Getter private final Glyph              icon;
	@Getter private final KeyCombination     shortcut;
	@Getter private final boolean            selectable;
	@Getter private final boolean            separated;

	FileMenu(MainMenuItemAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable,
	         boolean separated) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.selectable = selectable;
		this.separated  = separated;
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

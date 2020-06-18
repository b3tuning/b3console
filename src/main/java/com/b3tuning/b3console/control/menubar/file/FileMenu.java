package com.b3tuning.b3console.control.menubar.file;

import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.control.menubar.MenuItemInterface;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.menubar.MenuAction.A_CLOSE;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_NEW;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_OPEN;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_QUIT;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_RECENTS;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_SAVE;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_SAVE_AS;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_SEND;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setIcon;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setShortCut;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.ELLIPSIS_H;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.FOLDER_OPEN;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.SAVE;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.SEND;

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

	M_NEW(A_NEW, "New", setIcon(FOLDER_OPEN), setShortCut("Ctrl+Shift+O"), !SUBMENU, SELECTABLE, !SEPARATED),
	M_OPEN(A_OPEN, "Open", setIcon(FOLDER_OPEN), setShortCut("Ctrl+Shift+O"), !SUBMENU, SELECTABLE, !SEPARATED),
	M_RECENTS(A_RECENTS, "Open Recent", setIcon(FOLDER_OPEN), setShortCut("Ctrl+Shift+O"), SUBMENU, SELECTABLE, !SEPARATED),
	M_CLOSE(A_CLOSE, "Close", setIcon(FOLDER_OPEN), setShortCut("Ctrl+Shift+O"), !SUBMENU, SELECTABLE, !SEPARATED),
	M_SAVE(A_SAVE, "Save", setIcon(SAVE), setShortCut("Ctrl+Shift+S"), !SUBMENU, SELECTABLE, SEPARATED),
	M_SAVE_AS(A_SAVE_AS, "Save As", setIcon(ELLIPSIS_H), setShortCut("Ctrl+Shift+A"), !SUBMENU, SELECTABLE, !SEPARATED),
	M_SEND(A_SEND, "Send", setIcon(SEND), setShortCut("Ctrl+Shift+U"), !SUBMENU, SELECTABLE, SEPARATED),
	M_QUIT(A_QUIT, "Quit", setIcon(SEND), setShortCut("Ctrl+Shift+U"), !SUBMENU, SELECTABLE, SEPARATED),
	;

	@Getter private final MenuAction     action;
	@Getter private final String         label;
	@Getter private final Glyph          icon;
	@Getter private final KeyCombination shortcut;
	@Getter private final boolean        subMenu;
	@Getter private final boolean        selectable;
	@Getter private final boolean        separated;

	FileMenu(MenuAction action, String label, Glyph icon, KeyCombination shortcut, boolean subMenu, boolean selectable,
	         boolean separated) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.subMenu    = subMenu;
		this.selectable = selectable;
		this.separated  = separated;
	}

	@Override
	public FileMenu findByLabel(String label) {
		log.entry();
		for (FileMenu item : FileMenu.values()) {
			if (item.getLabel().equals(label)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return label;
	}

}

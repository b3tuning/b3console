package com.b3tuning.b3console.control.menubar.tools;

import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.control.menubar.MenuItemInterface;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.menubar.MenuAction.A_OPTIONS;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setIcon;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setShortCut;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.GEARS;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum ToolsMenu implements MenuItemInterface {
	// TOOLS TOOLS
	// Options

	M_OPTIONS(A_OPTIONS, "Options", setIcon(GEARS), setShortCut("Ctrl+Shift+O"), !SUBMENU, SELECTABLE, !SEPARATED),
	;

	@Getter private final MenuAction     action;
	@Getter private final String         label;
	@Getter private final Glyph          icon;
	@Getter private final KeyCombination shortcut;
	@Getter private final boolean        subMenu;
	@Getter private final boolean        selectable;
	@Getter private final boolean        separated;

	ToolsMenu(MenuAction action, String label, Glyph icon, KeyCombination shortcut, boolean subMenu, boolean selectable,
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
	public ToolsMenu findByLabel(String label) {
		log.entry();
		for (ToolsMenu item : ToolsMenu.values()) {
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

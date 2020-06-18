package com.b3tuning.b3console.control.menubar.help;

import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.control.menubar.MenuItemInterface;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.menubar.MenuAction.A_HELP;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setIcon;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setShortCut;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.QUESTION;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum HelpMenu implements MenuItemInterface {

	// HELP HELP
	//

	M_HELP(A_HELP, "Help", setIcon(QUESTION), setShortCut("Ctrl+Shift+H"), !SUBMENU, SELECTABLE, !SEPARATED),
	;

	@Getter private final MenuAction     action;
	@Getter private final String         label;
	@Getter private final Glyph          icon;
	@Getter private final KeyCombination shortcut;
	@Getter private final boolean        subMenu;
	@Getter private final boolean        selectable;
	@Getter private final boolean        separated;

	HelpMenu(MenuAction action, String label, Glyph icon, KeyCombination shortcut, boolean subMenu, boolean selectable,
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
	public HelpMenu findByLabel(String label) {
		log.entry();
		for (HelpMenu item : HelpMenu.values()) {
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

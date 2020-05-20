package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.NONE;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum ViewMenu implements MenuItemInterface {
	// VIEW VIEW
	//

	PLACEHOLDER_MENU(NONE, "PlaceHolder", new Glyph("FontAwesome", FontAwesome.Glyph.MAP_MARKER), KeyCombination.valueOf("Ctrl+Shift+P"), true),
	;

	@Getter private final MainMenuItemAction action;
	@Getter private final String             label;
	@Getter private final Glyph              icon;
	@Getter private final KeyCombination     shortcut;
	@Getter private final boolean            selectable;

	ViewMenu(MainMenuItemAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.selectable = selectable;
	}

	public ViewMenu findByLabel(String label) {
		log.entry();
		for (ViewMenu item : ViewMenu.values()) {
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

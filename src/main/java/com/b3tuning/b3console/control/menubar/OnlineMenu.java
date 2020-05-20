package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.CONNECT;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.DISCONNECT;
import static com.b3tuning.b3console.control.mainmenu.MainMenuItemAction.MONITOR_IO;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public enum OnlineMenu implements MenuItemInterface {
	// ONLINE ONLINE
	// Connect
	// Disconnect
	// Monitor IO

	CONNECT_MENU(CONNECT, "Connect", new Glyph("FontAwesome", FontAwesome.Glyph.EXCHANGE), KeyCombination.valueOf("Ctrl+Shift+L"), true),
	DISCONNECT_MENU(DISCONNECT, "Disconnect", new Glyph("FontAwesome", FontAwesome.Glyph.XING), KeyCombination.valueOf("Ctrl+Shift+D"), true),
	MONITOR_IO_MENU(MONITOR_IO, "Monitor IO", new Glyph("FontAwesome", FontAwesome.Glyph.TIMES), KeyCombination.valueOf("Ctrl+Shift+M"), true),
	;

	@Getter private final MainMenuItemAction action;
	@Getter private final String             label;
	@Getter private final Glyph              icon;
	@Getter private final KeyCombination     shortcut;
	@Getter private final boolean            selectable;

	OnlineMenu(MainMenuItemAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.selectable = selectable;
	}

	public OnlineMenu findByLabel(String label) {
		log.entry();
		for (OnlineMenu item : OnlineMenu.values()) {
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

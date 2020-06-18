package com.b3tuning.b3console.control.menubar.online;

import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.control.menubar.MenuItemInterface;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.Glyph;

import static com.b3tuning.b3console.control.menubar.MenuAction.A_CONNECT;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_DISCONNECT;
import static com.b3tuning.b3console.control.menubar.MenuAction.A_MONITOR_IO;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setIcon;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setShortCut;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.EXCHANGE;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.TIMES;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.XING;

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

	M_CONNECT(A_CONNECT, "Connect", setIcon(EXCHANGE), setShortCut("Ctrl+Shift+L"), !SUBMENU, SELECTABLE, !SEPARATED),
	M_DISCONNECT(A_DISCONNECT, "Disconnect", setIcon(XING), setShortCut("Ctrl+Shift+D"), !SUBMENU, SELECTABLE, !SEPARATED),
	M_MONITOR_IO(A_MONITOR_IO, "Monitor IO", setIcon(TIMES), setShortCut("Ctrl+Shift+M"), !SUBMENU, SELECTABLE, SEPARATED),
	;

	@Getter private final MenuAction     action;
	@Getter private final String         label;
	@Getter private final Glyph          icon;
	@Getter private final KeyCombination shortcut;
	@Getter private final boolean        subMenu;
	@Getter private final boolean        selectable;
	@Getter private final boolean        separated;

	OnlineMenu(MenuAction action, String label, Glyph icon, KeyCombination shortcut, boolean subMenu,
	           boolean selectable, boolean separated) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.subMenu    = subMenu;
		this.selectable = selectable;
		this.separated  = separated;
	}

	@Override
	public OnlineMenu findByLabel(String label) {
		log.entry();
		for (OnlineMenu item : OnlineMenu.values()) {
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

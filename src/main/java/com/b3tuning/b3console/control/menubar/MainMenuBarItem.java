package com.b3tuning.b3console.control.menubar;

import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.Glyph;

import java.util.List;

import static com.b3tuning.b3console.control.menubar.MenuAction.A_NONE;
import static com.b3tuning.b3console.control.menubar.MenuItemInterface.setIcon;
import static java.util.Arrays.asList;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.EDIT;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.FILE;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.GEARS;
import static org.controlsfx.glyphfont.FontAwesome.Glyph.GLASS;
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
public enum MainMenuBarItem implements MenuItemInterface {

	B_FILE(A_NONE, "File", setIcon(FILE), null, false, false, asList(FileMenu.values())),
	B_EDIT(A_NONE, "Edit", setIcon(EDIT), null, false, false, asList(EditMenu.values())),
	B_VIEW(A_NONE, "View", setIcon(GLASS), null, false, false, asList(ViewMenu.values())),
	B_ONLINE(A_NONE, "Online", setIcon(EDIT), null, false, false, asList(OnlineMenu.values())),
	B_TOOLS(A_NONE, "Tools", setIcon(GEARS), null, false, false, asList(ToolsMenu.values())),
	B_HELP(A_NONE, "Help", setIcon(QUESTION), null, false, false, asList(HelpMenu.values())),
	;

	@Getter private final MenuAction              action;
	@Getter private final String                  label;
	@Getter private final Glyph                   icon;
	@Getter private final KeyCombination          shortcut;
	@Getter private final boolean                 selectable;
	@Getter private final boolean                 separated;
	@Getter private final List<MenuItemInterface> items;

	MainMenuBarItem(MenuAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable,
	                boolean separated, List<MenuItemInterface> items) {
		this.action     = action;
		this.label      = label;
		this.icon       = icon;
		this.shortcut   = shortcut;
		this.selectable = selectable;
		this.separated  = separated;
		this.items      = items;
	}

	public MainMenuBarItem findByLabel(String label) {
		log.entry();
		for (MainMenuBarItem item : MainMenuBarItem.values()) {
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

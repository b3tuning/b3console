package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import javafx.scene.input.KeyCombination;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;

import java.util.Arrays;
import java.util.List;

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
public enum MainMenuBarItem implements MenuItemInterface {

	FILE(NONE, "File", new Glyph("FontAwesome", FontAwesome.Glyph.FILE), null, false, false, Arrays.asList(FileMenu.values())),
	EDIT(NONE, "Edit", new Glyph("FontAwesome", FontAwesome.Glyph.EDIT), null, false, false, Arrays.asList(EditMenu.values())),
	VIEW(NONE, "View", new Glyph("FontAwesome", FontAwesome.Glyph.GLASS), null, false, false, Arrays.asList(ViewMenu.values())),
	ONLINE(NONE, "Online", new Glyph("FontAwesome", FontAwesome.Glyph.EDIT), null, false, false, Arrays.asList(OnlineMenu.values())),
	TOOLS(NONE, "Tools", new Glyph("FontAwesome", FontAwesome.Glyph.GEARS), null, false, false, Arrays.asList(ToolsMenu.values())),
	HELP(NONE, "Help", new Glyph("FontAwesome", FontAwesome.Glyph.QUESTION), null, false, false, Arrays.asList(HelpMenu.values())),
	;

	@Getter private final MainMenuItemAction      action;
	@Getter private final String                  label;
	@Getter private final Glyph                   icon;
	@Getter private final KeyCombination          shortcut;
	@Getter private final boolean                 selectable;
	@Getter private final boolean                 separated;
	@Getter private final List<MenuItemInterface> items;

	MainMenuBarItem(MainMenuItemAction action, String label, Glyph icon, KeyCombination shortcut, boolean selectable,
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

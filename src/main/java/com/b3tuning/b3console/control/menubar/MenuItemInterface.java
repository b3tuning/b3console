package com.b3tuning.b3console.control.menubar;

import javafx.scene.input.KeyCombination;
import org.controlsfx.glyphfont.Glyph;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public interface MenuItemInterface {

	boolean SUBMENU = true;
	boolean SELECTABLE = true;
	boolean SEPARATED = true;

	MenuAction getAction();

	String getLabel();

	Glyph getIcon();

	KeyCombination getShortcut();

	boolean isSubMenu();

	boolean isSelectable();

	boolean isSeparated();

	static Glyph setIcon(Object icon) {
		return icon != null
		       ? new Glyph("FontAwesome", icon)
		       : null;
	}

	static KeyCombination setShortCut(String keys) {
		return keys != null
		       ? KeyCombination.valueOf(keys)
		       : null;
	}

	MenuItemInterface findByLabel(String label);

	String toString();

}

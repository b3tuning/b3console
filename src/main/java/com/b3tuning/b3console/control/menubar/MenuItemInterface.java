package com.b3tuning.b3console.control.menubar;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
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

	MainMenuItemAction getAction();

	String getLabel();

	Glyph getIcon();

	KeyCombination getShortcut();

	boolean isSelectable();

	MenuItemInterface findByLabel(String label);

	String toString();

}

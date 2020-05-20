package com.b3tuning.b3console.control.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class MainMenuBar {

	private final MenuBar menu;

	public MainMenuBar() {
		this.menu = new MenuBar();

		for (MainMenuBarItem item : MainMenuBarItem.values()) {
			this.menu.getMenus().add(createMenu(item));
		}
	}

	private Menu createMenu(MainMenuBarItem item) {
		Menu menu = new Menu(item.getLabel());
		if (item.getItems() != null) {
			for (Object barItem : item.getItems()) {
				menu.getItems().add(createMenuItem((MenuItemInterface) barItem));
			}
		}
		return menu;
	}

	private MenuItem createMenuItem(MenuItemInterface barItem) {
		MenuItem item = new MenuItem();
		item.setText(barItem.getLabel());
		item.setGraphic(null);
		item.setAccelerator(barItem.getShortcut());
		item.setOnAction(barItem.getAction());
		return item;
	}

	public MenuBar getMenuBar() {
		return this.menu;
	}
}

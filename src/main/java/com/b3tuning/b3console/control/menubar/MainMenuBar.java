package com.b3tuning.b3console.control.menubar;

import javafx.scene.Node;
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
public class MainMenuBar extends Node {

	private final MenuBar menu;

	public MainMenuBar() {
		this.menu = new MenuBar();

		for (MainMenuBarItem item : MainMenuBarItem.values()) {
			this.menu.getMenus().add(createMenu(item));
		}
	}

	private Menu createMenu(MainMenuBarItem item) {
		Menu menu = new Menu();
		menu.setText(item.getLabel());
		menu.setId(item.getLabel());
		menu.setGraphic(item.getIcon());
		menu.setAccelerator(item.getShortcut());
		menu.setOnAction(item.getAction());
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
		item.setGraphic(barItem.getIcon());
		item.setAccelerator(barItem.getShortcut());
		item.setOnAction(barItem.getAction());
		return item;
	}

	public MenuBar getMenuBar() {
		return this.menu;
	}
}

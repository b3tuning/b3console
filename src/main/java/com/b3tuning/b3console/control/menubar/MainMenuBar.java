package com.b3tuning.b3console.control.menubar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.XSlf4j;

/*
 *  Created on:  May 19, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class MainMenuBar extends Node {

	private final MenuBar menu;

	private ObjectProperty<MenuItemInterface> selectedItem = new SimpleObjectProperty<>();

	public MainMenuBar() {
		this.menu = new MenuBar();

		for (MainMenuBarItem item : MainMenuBarItem.values()) {
			this.menu.getMenus().add(createMenu(item));
		}
	}

	private Menu createMenu(MainMenuBarItem item) {
		Menu menu = new Menu();
		constructMenuItem(menu, item);
		if (item.getItems() != null) {
			for (MenuItemInterface barItem : item.getItems()) {
				menu.getItems().add(createMenuItem(barItem));
			}
		}
		return menu;
	}

	private MenuItem createMenuItem(MenuItemInterface barItem) {
		MenuItem item = new MenuItem();
		constructMenuItem(item, barItem);
		return item;
	}

	private void constructMenuItem(final MenuItem menu, final MenuItemInterface item) {
		menu.setId(item.getLabel());
		menu.setText(item.getLabel());
		menu.setGraphic(item.getIcon().size(16).color(Color.GREY));
		menu.setAccelerator(item.getShortcut());
		if (item.isSelectable()) {
			menu.setOnAction(e -> {
				log.entry();
				setSelectedItem(item);
				setSelectedItem(null);
			});
		}
	}

	public MenuBar getMenuBar() {
		return this.menu;
	}

	public MenuItemInterface getSelectedItem() {
		return selectedItem.get();
	}

	public void setSelectedItem(MenuItemInterface value) {
		selectedItem.set(value);
	}

	public ObjectProperty<MenuItemInterface> selectedItemProperty() {
		return selectedItem;
	}

}

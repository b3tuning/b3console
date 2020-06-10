package com.b3tuning.b3console.control.menubar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import lombok.extern.slf4j.XSlf4j;

import java.util.Arrays;
import java.util.stream.Collectors;

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

	private static final double MENU_BAR_ICON_SIZE  = 16;
	private static final Color  MENU_BAR_ICON_COLOR = Color.GREY;

	private final MenuBar menuBar = new MenuBar();

	private static ObjectProperty<Pair<MenuItemInterface, ActionEvent>> selectedItem = new SimpleObjectProperty<>();

	public MainMenuBar() {
		this.menuBar.getMenus().addAll(Arrays.stream(MainMenuBarItem.values())
		                                     .map(this::createMenu)
		                                     .collect(Collectors.toList()));
	}

	private Menu createMenu(MainMenuBarItem item) {
		Menu menu = new Menu();
		assembleMenu(menu, item);
		assembleSubMenus(menu, item);
		return menu;
	}

	private static void assembleMenu(final MenuItem menu, final MenuItemInterface item) {
		menu.setId(item.getLabel());
		menu.setText(item.getLabel());
		menu.setGraphic(item.getIcon().size(MENU_BAR_ICON_SIZE).color(MENU_BAR_ICON_COLOR));
		menu.setAccelerator(item.getShortcut());
	}

	private static void assembleSubMenus(final Menu menu, final MainMenuBarItem item) {
		if (item.getItems() != null) {
			item.getItems().forEach(m -> {
				if (m.isSeparated()) {
					menu.getItems().add(new SeparatorMenuItem());
				}
				menu.getItems().add(createMenuItem(m));
			});
		}
	}

	private static MenuItem createMenuItem(final MenuItemInterface item) {
		MenuItem menu = new MenuItem();
		assembleMenu(menu, item);
		return addAction(menu, item);
	}

	private static MenuItem addAction(final MenuItem menu, final MenuItemInterface item) {
		if (item.isSelectable()) {
			menu.setOnAction(e -> {
				log.entry();
				setSelectedItem(new Pair<>(item, e));
				setSelectedItem(null);
			});
		}
		return menu;
	}

	public MenuBar getMenuBar() {
		return this.menuBar;
	}

	public Pair<MenuItemInterface, ActionEvent> getSelectedItem() {
		return selectedItem.get();
	}

	public static void setSelectedItem(Pair<MenuItemInterface, ActionEvent> value) {
		selectedItem.set(value);
	}

	public ObjectProperty<Pair<MenuItemInterface, ActionEvent>> selectedItemProperty() {
		return selectedItem;
	}

}

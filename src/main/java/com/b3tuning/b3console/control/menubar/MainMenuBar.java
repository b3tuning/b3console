package com.b3tuning.b3console.control.menubar;

import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
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

	private final NotificationCenter globalNotifications;
//	private static final ObjectProperty<Pair<MenuItemInterface, ActionEvent>> selectedItem = new SimpleObjectProperty<>();

	@Inject
	public MainMenuBar(NotificationCenter notificationCenter) {
		this.globalNotifications = notificationCenter;
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

	private void assembleMenu(final MenuItem menu, final MenuItemInterface item) {
		menu.setId(item.getLabel());
		menu.setText(item.getLabel());
		menu.setGraphic(item.getIcon().size(MENU_BAR_ICON_SIZE).color(MENU_BAR_ICON_COLOR));
		menu.setAccelerator(item.getShortcut());
		menu.setUserData(item.getAction());
	}

	private void assembleSubMenus(final Menu menu, final MainMenuBarItem item) {
		if (item.getItems() != null) {
			item.getItems().forEach(m -> {
				if (m.isSeparated()) {
					menu.getItems().add(new SeparatorMenuItem());
				}
				menu.getItems().add(createMenuItem(m));
			});
		}
	}

	private MenuItem createMenuItem(final MenuItemInterface item) {
		MenuItem menu;
		if (item.isSubMenu()) {
			menu = new Menu();
		} else {
			menu = new MenuItem();
		}
		assembleMenu(menu, item);
		return addAction(menu, item);
	}

	private MenuItem addAction(final MenuItem menu, final MenuItemInterface item) {
		if (item.isSelectable()) {
			menu.setOnAction(e -> {
				log.entry();
				globalNotifications.publish(menu.getUserData().toString(), menu);

//				setSelectedItem(new Pair<>(item, e));
//				setSelectedItem(null);
			});
		}
		return menu;
	}

	public MenuBar getMenuBar() {
		return this.menuBar;
	}

//	public Pair<MenuItemInterface, ActionEvent> getSelectedItem() {
//		return selectedItem.get();
//	}
//
//	public static void setSelectedItem(Pair<MenuItemInterface, ActionEvent> value) {
//		selectedItem.set(value);
//	}
//
//	public ObjectProperty<Pair<MenuItemInterface, ActionEvent>> selectedItemProperty() {
//		return selectedItem;
//	}

}

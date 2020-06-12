package com.b3tuning.b3console.control.mainmenu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.ToggleButton;

public class MainMenu extends Control {

	private final static String CSS_CLASS = "mainmenu";

	private ObjectProperty<MainMenuItemModel>                 selectedItem = new SimpleObjectProperty<>();
	private ObjectProperty<ObservableList<MainMenuItemModel>> menuItems;

	public MainMenu() {
		getStyleClass().add(CSS_CLASS);
	}

	// ******************** Functions *******************************************
	public void selectAction(MainMenuItemAction action) {
		MainMenuSkin skin = (MainMenuSkin) getSkin();
		skin.getToggleGroup().getToggles().forEach(t -> {
			ToggleButton menuItem = (ToggleButton) t;
			String       id       = menuItem.getId();
			if (action.equals(MainMenuItemAction.valueOf(id))) {
				menuItem.setSelected(true);
				menuItem.fireEvent(new ActionEvent(menuItem, this));
			}
		});
	}

	// ******************** Properties *******************************************

	public MainMenuItemModel getSelectedItem() {
		return selectedItem.get();
	}

	public void setSelectedItem(MainMenuItemModel value) {
		selectedItem.set(value);
	}

	public ObjectProperty<MainMenuItemModel> selectedItemProperty() {
		return selectedItem;
	}

	public ObservableList<MainMenuItemModel> getMenuItems() {
		return menuItemsProperty().get();
	}

	public void setMenuItems(ObservableList<MainMenuItemModel> value) {
		menuItemsProperty().set(value);
	}

	public ObjectProperty<ObservableList<MainMenuItemModel>> menuItemsProperty() {
		if (menuItems == null) {
			menuItems = new SimpleObjectProperty<>();
		}
		return menuItems;
	}

	// ******************** Style related *************************************
	@Override protected Skin<MainMenu> createDefaultSkin() {
		return new MainMenuSkin(this);
	}

}

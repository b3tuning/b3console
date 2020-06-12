package com.b3tuning.b3console.control.mainmenu;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

import static org.reactfx.EventStreams.eventsOf;

@XSlf4j
public class MainMenuSkin extends SkinBase<MainMenu> implements Skin<MainMenu> {

	private static final double CONTAINER_PREFERRED_WIDTH = 87;
	private static final double CONTAINER_MINIMUM_WIDTH   = CONTAINER_PREFERRED_WIDTH;
	private static final double CONTAINER_MAXIMUM_WIDTH   = CONTAINER_PREFERRED_WIDTH;

	private static final double ITEM_PREFERRED_WIDTH   = CONTAINER_PREFERRED_WIDTH;// - (INSET * 2);  // REMOVED as it was padding the toggle inside the vbox
	private static final double ITEM_PREFERRED_HEIGHT  = 50;
	private static final double TITLE_PREFERRED_HEIGHT = 20;
	private static final double ITEM_MINIMUM_WIDTH     = ITEM_PREFERRED_WIDTH;
	private static final double ITEM_MINIMUM__HEIGHT   = ITEM_PREFERRED_HEIGHT;
	private static final double ITEM_MAXIMUM_WIDTH     = ITEM_PREFERRED_WIDTH;
	private static final double ITEM_MAXIMUM_HEIGHT    = ITEM_PREFERRED_HEIGHT;

	private         VBox        menuItemsTopContainer;    // region to hold the menu items
	private         VBox        menuItemsBottomContainer;    // region to hold the menu items
	@Getter private ToggleGroup toggleGroup;        // ensures only 1 ToggleButton can be selected at any
	// time

	// ******************** Constructors **************************************
	public MainMenuSkin(final MainMenu CONTROL) {
		super(CONTROL);
		init();
		initGraphics();
		registerListeners();
	}

	/**
	 * Set the controls size
	 */
	private void init() {
		// ensure the menu cannot be resized forcing the min, max and pref width to all be the same
		getSkinnable().setMinWidth(CONTAINER_MINIMUM_WIDTH);
		getSkinnable().setPrefWidth(CONTAINER_PREFERRED_WIDTH);
		getSkinnable().setMaxWidth(CONTAINER_MAXIMUM_WIDTH);
	}

	/**
	 * Draw the controls nodes
	 */
	private void initGraphics() {

		AnchorPane anchor = new AnchorPane();
		menuItemsTopContainer    = new VBox();
		menuItemsBottomContainer = new VBox();
		toggleGroup              = new ToggleGroup();

		anchor.getChildren().add(menuItemsTopContainer);
		AnchorPane.setTopAnchor(menuItemsTopContainer, 0.0);
		AnchorPane.setBottomAnchor(menuItemsBottomContainer, 0.0);
		anchor.getChildren().add(menuItemsBottomContainer);

		populateMenuItems();

		getChildren().setAll(anchor);

	}

	/**
	 * Register any event listeners that we are interesting in binding to
	 */
	private void registerListeners() {
		getSkinnable().menuItemsProperty().addListener(observable -> populateMenuItems());
		getSkinnable().visibleProperty().addListener((observable, newValue, oldValue) -> {
			if (!newValue && toggleGroup.getSelectedToggle() != null) {
				toggleGroup.getSelectedToggle().setSelected(false);
			}
		});

	}

	/**
	 * Draw the menu items
	 */
	private void populateMenuItems() {
		menuItemsTopContainer.getChildren().clear();
		menuItemsBottomContainer.getChildren().clear();

		for (MainMenuItemModel menuItem : getSkinnable().getMenuItems()) {
			AnchorPane paneItem = buildMenuItem(menuItem);
			if (MainMenuItemModel.Position.TOP.equals(menuItem.getPosition())) {
				menuItemsTopContainer.getChildren().add(paneItem);
			} else {
				menuItemsBottomContainer.getChildren().add(paneItem);
			}
		}
	}

	/**
	 * Build an individual menu item
	 */
	private AnchorPane buildMenuItem(MainMenuItemModel model) {

		ButtonBase b;
		Label      l = null;
		AnchorPane ap;

		if (MainMenuItemModel.Mode.APP.equals(model.getMode())) {
			b = new ToggleButton(model.getCompressedTitle());
			// having toggle buttons bound to a toggle group ensures that only 1 menu item may be
			// selected at any time
			((ToggleButton) b).setToggleGroup(toggleGroup);
			ap = new AnchorPane(b);
			ap.setMinSize(ITEM_MINIMUM_WIDTH, ITEM_MINIMUM__HEIGHT);
			ap.setPrefSize(ITEM_PREFERRED_WIDTH, ITEM_PREFERRED_HEIGHT);
			ap.setMaxSize(ITEM_MAXIMUM_WIDTH, ITEM_MAXIMUM_HEIGHT);
		} else {
			b = new Button(model.getCompressedTitle());
			b.setAlignment(Pos.TOP_CENTER);
			l = new Label(model.getExpandedTitle());
			l.getStyleClass().add("menu-item-text");
			l.setAlignment(Pos.CENTER);
			ap = new AnchorPane(b, l);
			ap.setMinSize(ITEM_MINIMUM_WIDTH, ITEM_MINIMUM__HEIGHT + TITLE_PREFERRED_HEIGHT);
			ap.setPrefSize(ITEM_PREFERRED_WIDTH, ITEM_PREFERRED_HEIGHT + TITLE_PREFERRED_HEIGHT);
			ap.setMaxSize(ITEM_MAXIMUM_WIDTH, ITEM_MAXIMUM_HEIGHT + TITLE_PREFERRED_HEIGHT);
		}
		b.setId(model.getAction().name());

		// when a menu item is clicked, update the selected item property of the control so that it
		// can be bound to
		eventsOf(b, ActionEvent.ACTION).subscribe(e -> {
			log.entry();
			getSkinnable().setSelectedItem(model);
			getSkinnable().setSelectedItem(null);    // reset after firing the event
		});

		ap.visibleProperty().bind(b.visibleProperty());
		ap.managedProperty().bind(ap.visibleProperty());

		// fix the size and positioning
		AnchorPane.setTopAnchor(b, 0.0);
		AnchorPane.setBottomAnchor(b, 0.0);
		AnchorPane.setLeftAnchor(b, 0.0);
		AnchorPane.setRightAnchor(b, 0.0);

		if (l != null) {
			AnchorPane.setBottomAnchor(l, 10.0);
			AnchorPane.setLeftAnchor(l, 5.0);
			AnchorPane.setRightAnchor(l, 5.0);
		}

		return ap;
	}

}
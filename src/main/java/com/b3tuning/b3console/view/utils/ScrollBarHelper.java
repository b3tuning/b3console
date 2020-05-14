package com.b3tuning.b3console.view.utils;

import com.sun.javafx.scene.control.VirtualScrollBar;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.skin.ComboBoxListViewSkin;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class ScrollBarHelper {

	/**
	 * Get the scroll bar to be consumed by a registered listener (infinite scrolling)
	 * Takes any Control as to keep this generic to be re-used anywhere
	 * Handles ComboBox, which doesn't have it's own ScrollBar as it opens a new Scene for the PopUp dialog
	 * If no vertical ScrollBar is found, returns null
	 *
	 * @param scrollable - the Control we want to get the ScrollBar from
	 * @return - the ScrollBar or null
	 */
	public ScrollBar getVerticalScrollbar(Control scrollable) {
		if (scrollable instanceof ComboBox) {
			ComboBoxListViewSkin<?> skin = ((ComboBoxListViewSkin<?>) scrollable.getSkin());
			if (skin==null) {
				return null;
			}
//			scrollable = skin.getListView();  // <---- getListView() no longer public API
			scrollable = (Control) skin.getPopupContent();
		}
		final Node scrollBar = scrollable.lookup(".scroll-bar:vertical");
		if (scrollBar instanceof VirtualScrollBar || scrollBar instanceof ScrollBar) {
			return (ScrollBar) scrollBar;
		}
		return null;
	}
}

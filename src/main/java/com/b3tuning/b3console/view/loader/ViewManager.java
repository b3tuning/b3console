package com.b3tuning.b3console.view.loader;

import com.b3tuning.b3console.view.loader.ViewManagerImpl.ViewInfo;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.scene.layout.StackPane;

import java.util.Map.Entry;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public interface ViewManager {

	void push(String key, ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple,
	          StackPane pane);

	@SuppressWarnings("unused")
	void push(String key, ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple,
	          StackPane pane, boolean closeOnLostFocus);

	void push(String key, ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple,
	          StackPane pane, boolean closeOnLostFocus, boolean ignoreDirty);

	void pop(StackPane pane, boolean reloadPage);

	ViewInfo peek(StackPane pane);

	void toFront(String key);

	void destroyAll(StackPane pane);

	@SuppressWarnings("unused")
	void destroyAllBut(String key, StackPane pane);

	boolean contains(String key);

	boolean handledDirty(String requestedKey);

	boolean hasDirty();

	Entry<String, ViewInfo> getDirty();

	@SuppressWarnings("unused")
	void republishPageContextChanged();

}

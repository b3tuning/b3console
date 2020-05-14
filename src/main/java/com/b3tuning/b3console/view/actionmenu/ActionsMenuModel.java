package com.b3tuning.b3console.view.actionmenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
public class ActionsMenuModel {

	private ObservableList<Action> actions;

	public ActionsMenuModel() {
		actions = FXCollections.observableArrayList();
	}

	public ActionsMenuModel(Action... actions) {
		this();
		this.actions.addAll(actions);
	}

	public void add(Action action) {
		this.actions.add(action);
	}
}

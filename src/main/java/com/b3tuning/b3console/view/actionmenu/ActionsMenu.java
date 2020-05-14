package com.b3tuning.b3console.view.actionmenu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class ActionsMenu<T> extends Control {

	public final static String CSS_CLASS = "actionsmenu";

	private ObjectProperty<ActionsMenuModel> model;
	private ObjectProperty<Action> selectedAction;
	private ObjectProperty<T> associatedEntity;

	public ActionsMenu(ActionsMenuModel model) {
		this.model = new SimpleObjectProperty<>(model);
		getStyleClass().add(CSS_CLASS);
	}

	// ******************** Properties *******************************************

	public ActionsMenuModel getModel() {
		return modelProperty().get();
	}

	public void setModel(ActionsMenuModel value) {
		modelProperty().set(value);
	}

	public ObjectProperty<ActionsMenuModel> modelProperty() {
		return model;
	}

	public Action getSelectedAction() {
		return (selectedAction == null)
		       ? null
		       : selectedAction.get();
	}

	public void setSelectedAction(Action value) {
		selectedActionProperty().set(value);
	}

	public ObjectProperty<Action> selectedActionProperty() {
		if (selectedAction == null) {
			selectedAction = new SimpleObjectProperty<>();
		}
		return selectedAction;
	}

	public T getAssociatedEntity() {
		return (associatedEntity == null)
		       ? null
		       : associatedEntity.get();
	}

	public void setAssociatedEntity(T value) {
		associatedEntityProperty().set(value);
	}

	public ObjectProperty<T> associatedEntityProperty() {
		if (associatedEntity == null) {
			associatedEntity = new SimpleObjectProperty<>();
		}
		return associatedEntity;
	}

	// ******************** Style related *************************************
	@Override
	protected Skin<ActionsMenu<T>> createDefaultSkin() {
		return new ActionsMenuSkin<>(this);
	}

}

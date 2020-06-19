/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.edit;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.module.ConfigBase;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.util.ArrayDeque;
import java.util.Deque;

@XSlf4j
public class EditManager {

	private final ObjectProperty<Deque<ConfigBase>> undo = new SimpleObjectProperty<>(new ArrayDeque<>());
	private final ObjectProperty<Deque<ConfigBase>> redo = new SimpleObjectProperty<>(new ArrayDeque<>());

	private int sizeMax;

	private final NotificationCenter globalNotifications;
	private final UserPreferences    preferences;

	@Inject
	public EditManager(NotificationCenter notificationCenter, UserPreferences preferences) {
		log.entry();
		this.globalNotifications = notificationCenter;
		this.preferences         = preferences;

		sizeMax = 30; // TODO: should this be user configurable?
	}

	/**
	 * Adds an undoable object to the collector.
	 *
	 * @param undoable The undoable object to add.
	 */
	public void add(final ConfigBase undoable) {
		if (undoable != null && sizeMax > 0) {
			if (undo.get().size() == sizeMax) {
				undo.get().removeLast();
			}
			undo.get().push(undoable);
			redo.get().clear(); /* The redoable objects must be removed. */
		}
	}

	/**
	 * Undoes the last undoable object.
	 */
	public void undo() {
		if (!undo.get().isEmpty()) {
			final ConfigBase undoable = undo.get().pop();
//			undoable.undo();
			redo.get().push(undoable);
		}
	}

	/**
	 * Redoes the last undoable object.
	 */
	public void redo() {
		if (!redo.get().isEmpty()) {
			final ConfigBase undoable = redo.get().pop();
//			undoable.redo();
			undo.get().push(undoable);
		}
	}

	/**
	 * @return The last undoable object or null.
	 */
	public ConfigBase getLastUndo() {
		return undo.get().isEmpty()
		       ? null
		       : undo.get().peek();
	}

	/**
	 * @return The last redoable object or null.
	 */
	public ConfigBase getLastRedo() {
		return redo.get().isEmpty()
		       ? null
		       : redo.get().peek();
	}

	/**
	 * @param max The max number of saved undoable objects. Must be great than 0.
	 */
	public void setSizeMax(final int max) {
		if (max >= 0) {
			for (int i = 0, nb = undo.get().size() - max; i < nb; i++) {
				undo.get().removeLast();
			}
			this.sizeMax = max;
		}
	}

	public ObjectProperty<Deque<ConfigBase>> undoProperty() {
		return undo;
	}

	public ObjectProperty<Deque<ConfigBase>> redoProperty() {
		return redo;
	}
}
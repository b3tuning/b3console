/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.edit;

import com.b3tuning.b3console.service.module.ConfigBase;
import lombok.extern.slf4j.XSlf4j;

import java.util.ArrayDeque;
import java.util.Deque;

@XSlf4j
public class EditManager {

	public static final EditManager INSTANCE = new EditManager();

	/**
	 * Contains the undoable objects.
	 */
	private final Deque<ConfigBase> undo;

	/**
	 * Contains the redoable objects.
	 */
	private final Deque<ConfigBase> redo;

	/**
	 * The maximal number of undo.
	 */
	private int sizeMax;

	private EditManager() {
		super();
		undo    = new ArrayDeque<>();
		redo    = new ArrayDeque<>();
		sizeMax = 30;
	}

	/**
	 * Adds an undoable object to the collector.
	 *
	 * @param undoable The undoable object to add.
	 */
	public void add(final ConfigBase undoable) {
		if (undoable != null && sizeMax > 0) {
			if (undo.size() == sizeMax) {
				undo.removeLast();
			}

			undo.push(undoable);
			redo.clear(); /* The redoable objects must be removed. */
		}
	}

	/**
	 * Undoes the last undoable object.
	 */
	public void undo() {
		if (!undo.isEmpty()) {
			final ConfigBase undoable = undo.pop();
//			undoable.undo();
			redo.push(undoable);
		}
	}

	/**
	 * Redoes the last undoable object.
	 */
	public void redo() {
		if (!redo.isEmpty()) {
			final ConfigBase undoable = redo.pop();
//			undoable.redo();
			undo.push(undoable);
		}
	}

	/**
	 * @return The last undoable object or null.
	 */
	public ConfigBase getLastUndo() {
		return undo.isEmpty()
		       ? null
		       : undo.peek();
	}

	/**
	 * @return The last redoable object or null.
	 */
	public ConfigBase getLastRedo() {
		return redo.isEmpty()
		       ? null
		       : redo.peek();
	}

	/**
	 * @param max The max number of saved undoable objects. Must be great than 0.
	 */
	public void setSizeMax(final int max) {
		if (max >= 0) {
			for (int i = 0, nb = undo.size() - max; i < nb; i++) {
				undo.removeLast();
			}
			this.sizeMax = max;
		}
	}
}
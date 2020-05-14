package com.b3tuning.b3console.service;

import com.b3tuning.b3console.view.Disposable;
import javafx.beans.binding.Binding;
import javafx.beans.value.ObservableValue;
import org.reactfx.EventSource;
import org.reactfx.Guard;
import org.reactfx.Subscription;
import org.reactfx.SuspendableEventStream;

import static org.reactfx.EventStreams.changesOf;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public abstract class EditableEntity implements Disposable {

	private final EventSource<Boolean> dirtyStream;
	private final SuspendableEventStream<Boolean> suspendableDirtyStream;
	private final Binding<Boolean> dirty;
	private Guard guardOfDirtyStream;

	private Subscription subscriptions;

	protected EditableEntity() {
		this.dirtyStream            = new EventSource<>();
		this.suspendableDirtyStream = dirtyStream.suppressible();

		// suspend the dirty stream by default, requiring callers to explicitly start tracking
		// (normally after a load)
		this.guardOfDirtyStream = suspendableDirtyStream.suspend();
		this.dirty              = suspendableDirtyStream.toBinding(false);

	}

	protected void trackProperties(ObservableValue<?>... properties) {
		if (properties == null) {
			return;
		}

		for (ObservableValue<?> property : properties) {
			manage(changesOf(property)
					       .filter(v -> v.getOldValue() != v.getNewValue())
//					.hook(v -> log.trace("Tracked property change from: {} to: {}. Tracking: {}", v.getOldValue(), v.getNewValue(), isTrackingChanges()))
					       .map(v -> true)
					       .feedTo(dirtyStream));
		}
	}

	public boolean isTrackingChanges() {
		return (guardOfDirtyStream != null && !Guard.EMPTY_GUARD.equals(guardOfDirtyStream));
	}

	public void stopTrackingChanges() {
		if (guardOfDirtyStream == null) {
			guardOfDirtyStream = suspendableDirtyStream.suspend();
		}
	}

	public void startTrackingChanges() {
		if (guardOfDirtyStream != null) {
			guardOfDirtyStream.close();
		}
	}

	public void resetTrackingChanges() {
		dirtyStream.push(false);
	}

	public Binding<Boolean> dirtyBinding() {
		return dirty;
	}

	protected void manage(Subscription subscription) {
		if (subscriptions == null) {
			subscriptions = subscription;
		} else {
			subscriptions = subscriptions.and(subscription);
		}
	}

	protected EventSource<Boolean> getDirtyStream() {
		return dirtyStream;
	}

	@Override
	public void dispose() {
		if (subscriptions != null) {
			subscriptions.unsubscribe();
		}
	}
}

package com.b3tuning.b3console.view.loader;

import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.view.Disposable;
import com.b3tuning.b3console.view.EditableViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.notifications.PageContextChangedNotification;
import com.b3tuning.b3console.view.utils.AlertUtils;
import com.google.common.base.Preconditions;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.XSlf4j;
import org.reactfx.SuspendableNo;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class ViewManagerImpl implements ViewManager {

	/**
	 * Holds a reference to all instances so that dirty views can be searched
	 * for amongst all view managers
	 */
	private static List<ViewManager> viewManagers = new ArrayList<>();

	private PageContextChangedNotification lastContextChange;
	private NotificationCenter             notifications;
	private Map<String, ViewInfo>          views;
	private SuspendableNo                  handlingDirtyIndicator = new SuspendableNo();

	@Inject
	public ViewManagerImpl(NotificationCenter notifications) {
		log.entry();
		views              = new HashMap<>();
		this.notifications = notifications;
		ViewManagerImpl.viewManagers.add(this);
	}

	/**
	 * Loads a new view by pushing to a stackpane. If the current view being
	 * displayed is dirty, the push is cancelled. If the requested view is
	 * already loaded, it is reused and pushed to the front.
	 */
	@Override
	public void push(String key, ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple, StackPane pane,
	                 MenuAction appArea) {
		push(key, viewTuple, pane, appArea, false, false);
	}

	@Override
	public void push(String key, ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple, StackPane pane,
	                 MenuAction appArea, boolean closeOnLostFocus) {
		push(key, viewTuple, pane, appArea, closeOnLostFocus, false);
	}

	@Override
	public void push(String key, ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple, StackPane pane,
	                 MenuAction appArea, boolean closeOnLostFocus, boolean ignoreDirty) {
		checkArgument(pane != null, "Pane cannot be null!");

		log.entry(key, viewTuple.getCodeBehind().getClass().getName(), viewTuple.getViewModel().getClass().getName(),
		          pane.getId(), closeOnLostFocus, ignoreDirty);

		if (!ignoreDirty && !handledDirty(key)) {
			log.trace("Push cancelled, something is dirty");
			return;
		}

		if (contains(key)) {
			toFront(key);
		} else {

			ViewInfo info = new ViewInfo(viewTuple, pane, appArea, closeOnLostFocus);
			views.put(key, info);

			if (!viewTuple.getView().managedProperty().isBound()) {
				viewTuple.getView().managedProperty().bind(viewTuple.getView().visibleProperty());
			}

			Platform.runLater(() -> {
				pane.getChildren().add(viewTuple.getView());
				hideOthers(info);
			});
		}

		publishPageContextChanged(key);
	}

	private void publishPageContextChanged(String key) {
		log.entry(key);

		lastContextChange = new PageContextChangedNotification(key);
		notifications.publish(PageContextChangedNotification.class.getName(), lastContextChange);
	}

	public void republishPageContextChanged() {
		log.entry();
		notifications.publish(PageContextChangedNotification.class.getName(), lastContextChange);
	}

	private void hideOthers(ViewInfo info) {
		log.entry(info);

		if (info.getViewTuple() == null) {
			log.error("info.getViewTuple() is null!");
			return;
		}
		Node               view    = info.getViewTuple().getView();
		MenuAction appArea = info.getAppArea();
		StackPane          pane    = info.getPane();

		List<ViewInfo> toRemove = new ArrayList<>();
		views.entrySet().stream().map(Entry::getValue).filter(vi -> pane.equals(vi.getPane()))
		     .filter(vi -> vi.getViewTuple().getView().isVisible() && !view.equals(vi.getViewTuple().getView()))
		     .forEach(vi -> {
			     if (!appArea.equals(vi.getAppArea()) || vi.isCloseOnLostFocus()) {
				     log.trace("Closing {} on lost focus", vi.viewTuple.getCodeBehind().getClass().getName());
				     toRemove.add(vi);
			     } else {
				     log.trace("Hiding {}", vi.viewTuple.getCodeBehind().getClass().getName());
				     vi.getViewTuple().getView().setVisible(false);
			     }
		     });

		for (ViewInfo vi : toRemove) {
			// lookup the corresponding entry
			Optional<Entry<String, ViewInfo>> entry = views.entrySet().stream().filter(e -> vi.equals(e.getValue()))
			                                               .findFirst();
			if (!entry.isPresent()) {
				log.error("Unable to find view entry for {}", vi.getViewTuple().getCodeBehind().getClass().getName());
				continue;
			}

			// completely destroy it
			disposeOf(entry.get().getKey(), vi);
		}
	}

	@Override
	public Entry<String, ViewInfo> getDirty() {
		log.entry();
		Entry<String, ViewInfo> dirty = views.entrySet().stream()
		                                     .filter(p -> p.getValue().viewTuple.getView().isVisible())
		                                     .filter(p -> p.getValue().getViewTuple()
		                                                   .getViewModel() instanceof EditableViewModel)
		                                     .filter(p -> ((EditableViewModel) p.getValue().getViewTuple()
		                                                                        .getViewModel()).isDirty()).findFirst()
		                                     .orElse(null);

		log.exit(dirty);
		return dirty;
	}

	/**
	 * Determine if we have any visible dirty views. If so, inform the user.
	 *
	 * @return returns true if determined that its ok to proceed ahead with the
	 * change of view
	 */
	public boolean handledDirty(String requestedKey) {
		log.entry(requestedKey, handlingDirtyIndicator.get());

		boolean handled = false;
		if (handlingDirtyIndicator.get()) {
			// when handling dirty, we need to make sure that we dont get caught
			// in a loop
			log.trace("Already being handled.");
			return handled;
		}

		handled = handlingDirtyIndicator.suspendWhile(() -> {
			Optional<Entry<String, ViewInfo>> dirty = viewManagers.stream().filter(vm -> vm.hasDirty())
			                                                      .map(vm -> vm.getDirty()).findFirst();

			if (dirty.isPresent()) {
				String   key   = dirty.get().getKey();
				ViewInfo value = dirty.get().getValue();
				log.trace("View `{}` is dirty", key);

				// if trying to move to the dirty view anyway, thats allowed
				if (requestedKey.equals(key)) {
					log.trace("Trying to push view which is the dirty view already in context, therefore allowing");
					return true;
				}

				ViewModel viewModel = value.getViewTuple().getViewModel();
				// inform the user that something needs saving
				String message = ((EditableViewModel) viewModel).saveChangesMessage();
				AlertUtils.warn(message);
				// switch to the dirty view
				toFront(key);

				// reset the view
				((EditableViewModel) viewModel).navigationCancelledAction();

				return false;
			}

			return true;
		});

		return handled;
	}

	/**
	 * Remove the top most view from the pane. If the top most view is dirty,
	 * the action is cancelled and the user is informed so. If a view/view model
	 * was popped, it is disposed of.
	 */
	@Override
	public void pop(StackPane pane, boolean refreshNextView) {
		log.entry(pane, refreshNextView);

		Platform.runLater(() -> {
			int size = pane.getChildren().size();

			// get the top most view that we're popping
			Node topView  = pane.getChildren().get(size - 1);
			Node nextView = pane.getChildren().get(size - 2);

			// find the corresponding viewTuple for the view
			Entry<String, ViewInfo>           topViewInfo  = null;
			Entry<String, ViewInfo>           nextViewInfo = null;
			Iterator<Entry<String, ViewInfo>> iter         = views.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, ViewInfo> checking = (Entry<String, ViewInfo>) iter.next();
				if (topView.equals(checking.getValue().viewTuple.getView())) {
					topViewInfo = checking;
				} else if (nextView.equals(checking.getValue().viewTuple.getView())) {
					nextViewInfo = checking;
				}
				if (topViewInfo != null && nextViewInfo != null) {
					break;
				}
			}

			Preconditions.checkArgument(topViewInfo != null,
			                            "Unable to find corresponding view model for the top view");
			Preconditions.checkArgument(nextViewInfo != null,
			                            "Unable to find corresponding view model for the next view");

			log.trace("top view: {}", topViewInfo.getKey());
			log.trace("next view: {}", nextViewInfo.getKey());

			// make sure we are not trying to remove a dirty view
			if (!handledDirty(topViewInfo.getKey())) {
				log.trace("Pop cancelled, something is dirty");
				return;
			}

			// as we're popping the top pane from the stack, make the one
			// beneath it visible
			nextView.setVisible(true);

			// now pop the top most pane
			pane.getChildren().remove(size - 1);

			publishPageContextChanged(nextViewInfo.getKey());

			// no longer interested in tracking this
			views.remove(topViewInfo.getKey());

			// refresh the new top most view if need be
			if (refreshNextView) {
				if (nextViewInfo.getValue().getViewTuple().getViewModel() instanceof Refreshable) {
					((Refreshable) nextViewInfo.getValue().getViewTuple().getViewModel()).refresh();
				}
			}

			// finally dispose of the popped one
			disposeOf(topViewInfo.getKey(), topViewInfo.getValue());
		});
	}

	/**
	 * Sometimes we may want to peek at whats on top without affecting the view
	 */
	@Override
	public ViewInfo peek(StackPane pane) {
		log.entry(pane.getId());

		// get the top most view that we're peeking at
		Node topView = pane.getChildren().get(pane.getChildren().size() - 1);

		// find the corresponding viewinfo for the view
		Entry<String, ViewInfo>           topViewInfo = null;
		Iterator<Entry<String, ViewInfo>> iter        = views.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, ViewInfo> checking = (Entry<String, ViewInfo>) iter.next();
			if (topView.equals(checking.getValue().viewTuple.getView())) {
				topViewInfo = checking;
				break;
			}
		}

		Preconditions.checkArgument(topViewInfo != null, "Unable to find corresponding view model for the top view");

		log.trace("top view: {}", topViewInfo.getKey());

		return topViewInfo.getValue();
	}

	/**
	 * Moves an existing loaded view to the front of the pane.
	 */
	@Override
	public void toFront(String key) {
		log.entry(key);

		// make sure we are not trying to remove a dirty view
		if (!handledDirty(key)) {
			log.trace("toFront cancelled, something is dirty");
			return;
		}

		ViewInfo viewInfo = views.get(key);
		if (viewInfo == null) {
			log.trace("View {} not found in view manager", key);
			return;
		}

		Platform.runLater(() -> {
			viewInfo.getViewTuple().getView().setVisible(true);
			viewInfo.getViewTuple().getView().toFront();

			publishPageContextChanged(key);

			hideOthers(viewInfo);
		});
	}

	@Data
	@AllArgsConstructor
	public static class ViewInfo {

		private ViewTuple<? extends FxmlView<?>, ? extends ViewModel> viewTuple;
		private StackPane                                             pane;
		private MenuAction                                    appArea;
		private boolean                                               closeOnLostFocus;
	}

	@Override
	public boolean contains(String key) {
		return views.containsKey(key);
	}

	/**
	 * Destroys all views/viewmodels that have been loaded in a particular pane.
	 * Example use is destroying everything related to viewing a specific
	 * project (project frame, jobs list, ingest, options) when finished with
	 * viewing project detail
	 */
	@Override
	public void destroyAll(StackPane pane) {
		log.entry(pane.getId());

		Iterator<Entry<String, ViewInfo>> iter = views.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, ViewInfo> current = (Entry<String, ViewInfo>) iter.next();
			if (pane.equals(current.getValue().getPane())) {
				iter.remove();
				disposeOf(current.getKey(), current.getValue());
			}
		}

		clearChildren(pane);
	}

	@Override
	public void destroyAllBut(String key, StackPane pane) {
		log.entry(key, pane.getId());

		ViewInfo                          soleSurvivor = null;
		Iterator<Entry<String, ViewInfo>> iter         = views.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, ViewInfo> current = (Entry<String, ViewInfo>) iter.next();
			if (pane.equals(current.getValue().getPane())) {
				if (key.equals(current.getKey())) {
					soleSurvivor = current.getValue();
				} else {
					iter.remove();
					disposeOf(current.getKey(), current.getValue());
				}
			}
		}

		if (soleSurvivor == null) {
			clearChildren(pane);
		} else {
			final ViewInfo finalSurvivor = soleSurvivor;
			Platform.runLater(() -> pane.getChildren().setAll(finalSurvivor.getViewTuple().getView()));
		}
	}

	private void clearChildren(Pane pane) {
		// View modifications needs to be on UI thread
		Platform.runLater(() -> pane.getChildren().clear());
	}

	private void disposeOf(String key, ViewInfo info) {
		Platform.runLater(() -> {
			views.remove(key);

			if (info.getViewTuple() != null && info.getPane().getChildren().contains(info.getViewTuple().getView())) {
				info.getPane().getChildren().remove(info.getViewTuple().getView());
			}
			if (info.getViewTuple().getViewModel() instanceof Disposable) {
				((Disposable) info.getViewTuple().getViewModel()).dispose();
			}
			if (info.getViewTuple().getCodeBehind() instanceof Disposable) {
				((Disposable) info.getViewTuple().getCodeBehind()).dispose();
			}
			info.setViewTuple(null);
		});
	}

	@Override
	public boolean hasDirty() {
		return getDirty() != null;
	}
}

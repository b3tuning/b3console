package com.b3tuning.b3console.view.file.testing;

import com.b3tuning.b3console.control.mainmenu.MainMenuItemAction;
import com.b3tuning.b3console.control.menubar.MenuAction;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.loader.ViewManagerImpl;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;
import org.reactfx.inhibeans.property.SimpleObjectProperty;

import javax.inject.Inject;

@XSlf4j
public class DesktopIngestViewModel extends BaseViewModel implements Refreshable {

	// injected
	private ViewManager viewManager;

	private ObjectProperty<StackPane> childViewPane = new SimpleObjectProperty<>();

	@Inject
	public DesktopIngestViewModel(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

	void onButtonAction(DesktopMenuAction action) {
		log.entry(action);

		switch (action) {
			case LOCAL -> showLocalView();
			case BROWSE -> showBrowseView();
			default -> log.error("Unrecognized menu item action: '{}'", action);
		}
	}

	private void showLocalView() {
		log.entry();

		if (viewManager.contains(DesktopLocalFilesView.class.getName())) {
			viewManager.toFront(DesktopLocalFilesView.class.getName());
		} else {
			ViewTuple<DesktopLocalFilesView, DesktopLocalFilesViewModel> viewTuple = FluentViewLoader
					.fxmlView(DesktopLocalFilesView.class).load();
//			viewTuple.getViewModel().projectProperty().bind(project);
			viewManager.push(DesktopLocalFilesView.class.getName(), viewTuple, childViewPane.get(), MenuAction.A_NONE);
//			                 MainMenuItemAction.PROJECTS);
		}
	}

	private void showBrowseView() {
		log.entry();

		if (viewManager.contains(
				DesktopAvailableFilesView.class.getName())) {
			viewManager.toFront(
					DesktopAvailableFilesView.class.getName());
		} else {
			ViewTuple<DesktopAvailableFilesView, DesktopAvailableFilesViewModel> viewTuple = FluentViewLoader
					.fxmlView(DesktopAvailableFilesView.class).load();
			viewTuple.getViewModel().projectProperty().bind(project);
			viewManager.push(DesktopAvailableFilesView.class.getName(), viewTuple,
			                 childViewPane.get(), MainMenuItemAction.PROJECTS);
		}
	}

	enum DesktopMenuAction {
		LOCAL,
		SEARCH,
		BROWSE
	}

	/**
	 * JAVA FX PROPERTIES
	 */

	void setChildViewPane(StackPane value) {
		childViewPane.set(value);
	}

	@Override
	public void refresh() {
		log.entry();
		ViewManagerImpl.ViewInfo currentView = viewManager.peek(childViewPane.get());
		if (currentView != null) {
			ViewModel vm = currentView.getViewTuple().getViewModel();
			if (vm instanceof Refreshable) {
				((Refreshable) vm).refresh();
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		viewManager.destroyAll(childViewPane.get());
	}

}

package com.b3tuning.b3console.view.file.testing;

import com.b3tuning.b3console.view.BaseView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

import static org.reactfx.EventStreams.nonNullValuesOf;

@XSlf4j
public class DesktopIngestView extends BaseView<DesktopIngestViewModel> implements IngestView<DesktopIngestViewModel> {

	@FXML private StackPane    childViewPane;
	@FXML private ToggleGroup  fileMenuGroup;
	@FXML private ToggleButton localButton;
	@FXML private ToggleButton browseButton;

	@InjectViewModel
	private DesktopIngestViewModel viewModel;

	@Inject
	public DesktopIngestView() {
	}

	public void initialize() {
		log.entry();

		manage(nonNullValuesOf(fileMenuGroup.selectedToggleProperty()).subscribe(v -> {
			DesktopIngestViewModel.MenuAction action = (DesktopIngestViewModel.MenuAction) v.getUserData();
			viewModel.onButtonAction(action);
		}));

		viewModel.setChildViewPane(childViewPane);

		localButton.setUserData(DesktopIngestViewModel.MenuAction.LOCAL);
		browseButton.setUserData(DesktopIngestViewModel.MenuAction.BROWSE);

		// Default View
		localButton.setSelected(true);
		viewModel.onButtonAction(DesktopIngestViewModel.MenuAction.LOCAL);
	}
}

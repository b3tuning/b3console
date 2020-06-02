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
public class DesktopIngestView extends BaseView<DesktopIngestViewModel> {

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
			DesktopIngestViewModel.DesktopMenuAction action = (DesktopIngestViewModel.DesktopMenuAction) v
					.getUserData();
			viewModel.onButtonAction(action);
		}));

		viewModel.setChildViewPane(childViewPane);

		localButton.setUserData(DesktopIngestViewModel.DesktopMenuAction.LOCAL);
		browseButton.setUserData(DesktopIngestViewModel.DesktopMenuAction.BROWSE);

		// Default View
		localButton.setSelected(true);
		viewModel.onButtonAction(DesktopIngestViewModel.DesktopMenuAction.LOCAL);
	}
}

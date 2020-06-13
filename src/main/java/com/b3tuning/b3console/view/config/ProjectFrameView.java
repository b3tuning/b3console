package com.b3tuning.b3console.view.config;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.notifications.ClickButtonNotification;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;

@XSlf4j
public class ProjectFrameView extends BaseView<ProjectFrameViewModel> {

	@FXML private AnchorPane   menuPane;
	@FXML private StackPane    centerPane;
	@FXML private Label        projectName;
	@FXML private ToggleButton backButton;
	@FXML private ToggleButton jobsButton;
	@FXML private ToggleButton ingestButton;
	@FXML private ToggleButton optionsButton;
	@FXML private ToggleGroup  projectMenu;
	@FXML private Button       refreshButton;

	@InjectViewModel private ProjectFrameViewModel viewModel;

	private NotificationCenter globalNotifications;

	@Inject
	public ProjectFrameView(NotificationCenter globalNotifications) {
		log.entry();
		this.globalNotifications = globalNotifications;
	}

	public void initialize() {
		log.entry();

//		// bind the title if a project is present
//		projectName.textProperty().bind(EasyBind.monadic(viewModel.projectProperty())
//		                                        .selectProperty(ProjectDetail::nameProperty).map(f -> String.format("PROJECT:  %s", f)));
//
//
//		// wire up actions
//		refreshButton.setText(FontAwesome.REFRESH);
//		manage(eventsOf(refreshButton, ActionEvent.ACTION).subscribe(v -> viewModel.refresh()));
//
//		backButton.setText(FontAwesome.BACK);
//		backButton.setUserData(MenuAction.BACK);
//		manage(eventsOf(backButton, ActionEvent.ACTION).subscribe(e -> viewModel.onBackClicked()));
//
//		jobsButton.setUserData(MenuAction.JOBS);
//		manage(eventsOf(jobsButton, ActionEvent.ACTION).subscribe(e -> viewModel.onJobsClicked()));
//
//		ingestButton.setUserData(MenuAction.INGEST);
//		manage(eventsOf(ingestButton, ActionEvent.ACTION)
//				.subscribe(e -> viewModel.onIngestClicked()));
//
//		optionsButton.setUserData(MenuAction.OPTIONS);
//		optionsButton.visibleProperty().bind(viewModel.projectEditorOrAboveProperty());
//		optionsButton.managedProperty().bind(optionsButton.visibleProperty());
//		manage(eventsOf(optionsButton, ActionEvent.ACTION)
//				.subscribe(e -> viewModel.onOptionsClicked()));
//
//		viewModel.setChildViewPane(centerPane);
//
		initNotifications();

	}

	private void initNotifications() {
		log.entry();

		globalNotifications.subscribe(ClickButtonNotification.class.getName(), (key, payload) -> {
			ClickButtonNotification n = ((ClickButtonNotification) payload[0]);
//			log.entry(n.getMainMenuItemAction(), n.getButtonId());
//			if (MainMenuItemAction.PROJECTS.equals(n.getMainMenuItemAction()) && n.getButtonId() != null) {
//				projectMenu.selectToggle(null);
//				ToggleButton btn = null;
//				if ("jobsButton".equals(n.getButtonId())) {
//					btn = jobsButton;
//				} else if ("ingestButton".equals(n.getButtonId())) {
//					btn = ingestButton;
//				} else if ("optionsButton".equals(n.getButtonId())) {
//					btn = optionsButton;
//				}
//				btn.fire();
//				if (!btn.isSelected()) {
//					projectMenu.selectToggle(btn);
//				}
//				centerPane.requestFocus();    // to get around a gotcha with the second level menu to remove the previously selected button
//			}
		});
	}

}

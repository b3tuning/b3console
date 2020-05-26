package com.b3tuning.b3console.view.file;

import com.b3tuning.b3console.App;
import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.service.FileType;
import com.b3tuning.b3console.service.utils.FileInspectorService;
import com.b3tuning.b3console.service.utils.IOUtils;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.utils.AlertUtils;
import com.b3tuning.b3console.service.files.FileWrapper;
import com.b3tuning.b3console.service.files.SupportedFileUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Background;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.b3tuning.b3console.view.file.DropAreaConstants.DEFAULT_DISABLED_DRAG_DROP_MESSAGE;
import static com.b3tuning.b3console.view.BackgroundColorConstants.GREEN_BACKGROUND;
import static com.b3tuning.b3console.view.BackgroundColorConstants.LIGHT_GRAY_BACKGROUND;
import static com.b3tuning.b3console.view.BackgroundColorConstants.RED_BACKGROUND;
import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;
import static javafx.scene.input.TransferMode.MOVE;
import static org.reactfx.EventStreams.nonNullValuesOf;
import static org.reactfx.EventStreams.valuesOf;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class FileMenuViewModel extends BaseViewModel implements Refreshable {

	static final String REFRESH_PAGE = "refresh me";

	// injected dependencies
	private FileInspectorService fileInspectorService;

	@Getter
	private UserPreferences preferences;

	// consumed by view
	private StringProperty             browsePath       = new SimpleStringProperty();
	private BooleanProperty            dropAreaDisabled = new SimpleBooleanProperty();
	private StringProperty             dropAreaText     = new SimpleStringProperty();
	private ObjectProperty<Background> dropAreaColor    = new SimpleObjectProperty<>();


	@Inject
	public FileMenuViewModel(FileInspectorService fileInspectorService, UserPreferences preferences) {
		log.entry();

		this.fileInspectorService = fileInspectorService;
		this.preferences          = preferences;


		manage(valuesOf(dropAreaDisabled).subscribe(v -> {
			log.entry(v);
			Platform.runLater(() -> {
				if (v) {
					dropAreaText.set(DEFAULT_DISABLED_DRAG_DROP_MESSAGE);
				}
			});
		}));

//		manage(nonNullValuesOf(project).subscribe(p -> {
//			log.entry();
//			dropAreaDisabled.bind((p.enabledProperty().not()).or(p.hasDeliverySettingsProperty().not()));
//		}));

		browsePath.set(preferences.getBrowseLocalPath());
		manage(nonNullValuesOf(browsePath).subscribe(preferences::setBrowseLocalPath));
	}

	void onTreeViewDragDetected(ObservableList<TreeItem<FileWrapper>> files, Dragboard db) {
		log.entry();
		if (files.isEmpty()) {
			return;
		}
		/* Build list of files to drag */
		List<File> filesToDrag = getFileList(files);
		log.trace("List of files to drag: {}", filesToDrag);

		/* Put the File name on a dragBoard */
		ClipboardContent content = new ClipboardContent();
		content.putFiles(filesToDrag);
		db.setContent(content);
	}

	/**
	 * Build the file list of files to drag.
	 */
	private List<File> getFileList(ObservableList<TreeItem<FileWrapper>> sourceList) {
		log.entry(sourceList);
		return sourceList.stream()
		                 .map(treeItem -> treeItem.getValue().getFile())
		                 .collect(Collectors.toList());
	}

	void onDropAreaDragOver(DragEvent e) {
		if (e.getDragboard().hasFiles()) {
			e.acceptTransferModes(MOVE);
		}
		e.consume();
	}

	void onDropAreaDragEntered(DragEvent e) {
		log.entry(e);
		Background bg = e.getDragboard().hasFiles()
		                ? GREEN_BACKGROUND
		                : RED_BACKGROUND;
		dropAreaColor.set(bg);
	}

	void onDropAreaDragExited(DragEvent e) {
		log.entry(e);
		dropAreaColor.set(LIGHT_GRAY_BACKGROUND);
	}

	void onDropAreaDropped(DragEvent e) {
		Dragboard db = e.getDragboard();
		if (db.hasFiles()) {
			db.getFiles().forEach(this::ingestFile);
		}
		db.clear();
		e.setDropCompleted(true);
		e.consume();
	}

	private void ingestFile(File file) {
		log.entry(file);
		if (file == null) {
			log.error("No File available");
			return;
		}

		try {
			Set<String>           paths           = IOUtils.toPaths(IOUtils.list(file));
			Map<String, FileType> classifiedFiles = fileInspectorService.classify(paths);

			Set<FileType> supportedTypes = SupportedFileUtils.getSupportedFileTypes();
			log.debug("supportedTypes: {}", supportedTypes);
			Set<File> allowed = Sets.newLinkedHashSet();
			Set<File> denied  = Sets.newLinkedHashSet();
			classifiedFiles.forEach((path, type) -> {
				if (supportedTypes.contains(type)) {
					allowed.add(new File(path));
				} else {
					denied.add(new File(path));
				}
			});

			if (!denied.isEmpty() && !confirm(file, allowed, denied)) {
				log.info("User canceled");
				return;
			}

			if (!allowed.isEmpty()) {
				log.debug("will upload files: {}", allowed);
//				taskManager.scheduleTask(file, allowed, user, project);
			} else {
				log.debug("no files to upload");
			}
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			AlertUtils.alert(Alert.AlertType.ERROR, "Something went wrong", e.getMessage());
		}
	}

	private boolean confirm(File path, Set<File> allowed, Set<File> denied) {
		log.entry(path, allowed, denied);
		Preconditions.checkNotNull(path);
		Preconditions.checkNotNull(allowed);
		Preconditions.checkNotNull(denied);

		FileDialogViewModel dialogViewModel = new FileDialogViewModel();
		dialogViewModel.setRootPath(path);
		dialogViewModel.setFilesToIngest(allowed);
		dialogViewModel.setInvalidFiles(denied);

		ViewTuple<FileDialogView, FileDialogViewModel> viewTuple = FluentViewLoader
				.fxmlView(FileDialogView.class)
				.viewModel(dialogViewModel)
				.load();

		Dialog<ButtonType> dlg = new Dialog<>();
		dlg.getDialogPane().getStylesheets().add(App.class.getResource("app.css").toExternalForm());
		dlg.setResizable(false);

		dlg.getDialogPane().setContent(viewTuple.getView());

		ButtonType ok     = new ButtonType("OK", OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", CANCEL_CLOSE);
		dlg.getDialogPane().getButtonTypes().addAll(cancel, ok);

		Optional<ButtonType> result = dlg.showAndWait();
		return result.isPresent() && result.get() == ok;
	}

	void onChangeAction(Stage stage) {
		log.entry();
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File             selected         = directoryChooser.showDialog(stage);
		if (selected != null) {
			String path = selected.getAbsolutePath();
			preferences.setBrowseLocalPath(path);
			browsePath.set(path);
		}
	}

	/**
	 * JAVAFX PROPERTIES
	 */
	StringProperty browsePathProperty() {
		return browsePath;
	}

	BooleanProperty dropAreaDisableProperty() {
		return dropAreaDisabled;
	}

	StringProperty dropAreaTextProperty() {
		return dropAreaText;
	}

	ObjectProperty<Background> dropAreaColorProperty() {
		return dropAreaColor;
	}

	@Override
	public void refresh() {
		publish(REFRESH_PAGE, new Object());
	}

}

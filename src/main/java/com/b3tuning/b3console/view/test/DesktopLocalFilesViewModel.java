/*
 *  Created on:  Jun 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  DesktopLocalFilesViewModel is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.test;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.view.BackgroundColorConstants;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.Refreshable;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.reactfx.EventStreams.nonNullValuesOf;
import static org.reactfx.EventStreams.valuesOf;

@XSlf4j
public class DesktopLocalFilesViewModel extends BaseViewModel implements Refreshable {

	static final         String REFRESH_PAGE      = "refresh me";
	private static final String DRAG_DROP_MESSAGE = "Drag files here to process a file through the workflow of this project";

	@Getter
	private final UserPreferences preferences;

	// consumed by view
	private final StringProperty             browsePath       = new SimpleStringProperty();
	private final BooleanProperty            dropAreaDisabled = new SimpleBooleanProperty();
	private final StringProperty             dropAreaText     = new SimpleStringProperty();
	private final ObjectProperty<Background> dropAreaColor    = new SimpleObjectProperty<>();

	@Inject
	public DesktopLocalFilesViewModel(UserPreferences preferences) {
		log.entry();

		this.preferences = preferences;

		manage(valuesOf(dropAreaDisabled).subscribe(v -> {
			log.entry(v);
			Platform.runLater(() -> {
				if (v) {
					dropAreaText.set("DropAreaConstants.DEFAULT_DISABLED_DRAG_DROP_MESSAGE");
				} else {
					dropAreaText.set(DRAG_DROP_MESSAGE);
				}
			});
		}));

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

		/* Put the File name on a drag board */
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
			e.acceptTransferModes(TransferMode.MOVE);
		}
		e.consume();
	}

	void onDropAreaDragEntered(DragEvent e) {
		log.entry(e);
		Background bg = e.getDragboard().hasFiles()
		                ? BackgroundColorConstants.GREEN_BACKGROUND
		                : BackgroundColorConstants.RED_BACKGROUND;
		dropAreaColor.set(bg);
	}

	void onDropAreaDragExited(DragEvent e) {
		log.entry(e);
		dropAreaColor.set(BackgroundColorConstants.LIGHT_GRAY_BACKGROUND);
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

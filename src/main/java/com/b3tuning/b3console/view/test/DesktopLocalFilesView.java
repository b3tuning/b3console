/*
 *  Created on:  Jun 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  DesktopLocalFilesView is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.test;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.root.FontAwesome;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

import static org.reactfx.EventStreams.eventsOf;
import static org.reactfx.EventStreams.nonNullValuesOf;

@XSlf4j
public class DesktopLocalFilesView extends BaseView<DesktopLocalFilesViewModel> {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

	@FXML private Label     dropArea;
	@FXML private TextField browsePath;
	@FXML private Button    changeButton;

	@FXML private TreeTableView<FileWrapper>                  treeView;
	@FXML private TreeTableColumn<FileWrapper, String>        fileColumn;
	@FXML private TreeTableColumn<FileWrapper, Number>        sizeColumn;
	@FXML private TreeTableColumn<FileWrapper, LocalDateTime> lastModifiedColumn;

	@InjectViewModel
	private DesktopLocalFilesViewModel viewModel;

	@Inject
	public DesktopLocalFilesView() {
	}

	public void initialize() {
		log.entry();

		initTree();

		dropArea.disableProperty().bind(viewModel.dropAreaDisableProperty());
		dropArea.textProperty().bind(viewModel.dropAreaTextProperty());
		dropArea.backgroundProperty().bind(viewModel.dropAreaColorProperty());

		browsePath.textProperty().bindBidirectional(viewModel.browsePathProperty());

		changeButton.setText(FontAwesome.FOLDER_OPEN);

		subscribeToNotifications();
	}

	private void subscribeToNotifications() {
		manage(eventsOf(dropArea, DragEvent.DRAG_OVER).subscribe(e -> viewModel.onDropAreaDragOver(e)));

		manage(eventsOf(dropArea, DragEvent.DRAG_ENTERED).subscribe(e -> viewModel.onDropAreaDragEntered(e)));

		manage(eventsOf(dropArea, DragEvent.DRAG_EXITED).subscribe(e -> viewModel.onDropAreaDragExited(e)));

		manage(eventsOf(changeButton, ActionEvent.ACTION)
				       .subscribe(e -> viewModel.onChangeAction((Stage) changeButton.getScene().getWindow())));

		// hack for the tree view disclosure node (triangle) to not disappear
		manage(eventsOf(treeView, MouseEvent.MOUSE_DRAGGED).subscribe(e -> treeView.getParent().requestFocus()));
		manage(eventsOf(treeView, MouseEvent.MOUSE_CLICKED).subscribe(e -> treeView.getParent().requestFocus()));

		manage(eventsOf(treeView, MouseEvent.DRAG_DETECTED).subscribe(e -> {
			log.entry();
			if (treeView.getSelectionModel().getSelectedItems().size() > 0) {
				Dragboard db = treeView.startDragAndDrop(TransferMode.MOVE);
				viewModel.onTreeViewDragDetected(treeView.getSelectionModel().getSelectedItems(), db);
			}
			e.consume();
		}));

		manage(eventsOf(treeView, DragEvent.DRAG_DONE).subscribe(e -> treeView.getSelectionModel().clearSelection()));

		manage(nonNullValuesOf(viewModel.browsePathProperty()).subscribe(this::load));

		viewModel.subscribe(DesktopLocalFilesViewModel.REFRESH_PAGE,
		                    (k, p) -> load(viewModel.browsePathProperty().get()));

	}

	private void load(String path) {
		log.entry(path);
		FileWrapper rootFile = new FileWrapper(new File(path));
		@SuppressWarnings("unchecked")
		TreeItem<FileWrapper> root = (TreeItem<FileWrapper>) TreeItemBuilderHelper.createNode(rootFile);
		treeView.setRoot(root);
	}

	private void initTree() {
		log.entry();

		treeView.setShowRoot(false);
		treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		treeView.getSelectionModel().setCellSelectionEnabled(true);

		fileColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().getName()));

		sizeColumn.setCellValueFactory(param -> {
			if (param.getValue().getValue().isFile()) {
				return new SimpleLongProperty(param.getValue().getValue().getSize());
			} else {
				return new SimpleObjectProperty<>(); // Don't show 0 / fileSize for directories
			}
		});

		sizeColumn.setCellFactory(column -> new TreeTableCell<>() {

			@Override
			protected void updateItem(Number item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty) {
					setText(null);
				} else {
					setText(ConversionUtil.readableFileSize((Long) item));
				}
			}
		});

		// by using a custom CellValueFactory and CellFactory, we can ensure that sort still happens
		// on the raw date value instead of the display string
		lastModifiedColumn.setCellValueFactory(param -> {
			if (param.getValue().getValue().isFile()) {
				long timestamp = param.getValue().getValue().lastModified();
				return new SimpleObjectProperty<>(
						LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
						                        TimeZone.getDefault().toZoneId()));
			} else {
				return new SimpleObjectProperty<>();
			}
		});

		lastModifiedColumn.setCellFactory(column -> new TreeTableCell<>() {

			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null || empty) {
					setText(null);
				} else {
					// Format date.
					setText(DATE_FORMATTER.format(item));
				}
			}
		});
	}
}

package com.b3tuning.b3console.view.file;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.ConversionUtil;
import com.b3tuning.b3console.service.files.FileWrapper;
import com.b3tuning.b3console.view.utils.TreeItemBuilderHelper;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.Dragboard;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

import static com.b3tuning.b3console.view.file.FileMenuViewModel.REFRESH_PAGE;
import static com.b3tuning.b3console.view.root.FontAwesome.FOLDER_OPEN;
import static javafx.event.ActionEvent.ACTION;
import static javafx.scene.control.SelectionMode.MULTIPLE;
import static javafx.scene.input.DragEvent.DRAG_DONE;
import static javafx.scene.input.DragEvent.DRAG_DROPPED;
import static javafx.scene.input.DragEvent.DRAG_ENTERED;
import static javafx.scene.input.DragEvent.DRAG_EXITED;
import static javafx.scene.input.DragEvent.DRAG_OVER;
import static javafx.scene.input.MouseEvent.DRAG_DETECTED;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_DRAGGED;
import static javafx.scene.input.TransferMode.MOVE;
import static org.reactfx.EventStreams.eventsOf;
import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class FileMenuView extends BaseView<FileMenuViewModel> {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

	@FXML private Label     dropArea;
	@FXML private TextField browsePath;
	@FXML private Button    changeButton;

	@FXML private TreeTableView<FileWrapper>                  treeView;
	@FXML private TreeTableColumn<FileWrapper, String>        fileColumn;
	@FXML private TreeTableColumn<FileWrapper, Number>        sizeColumn;
	@FXML private TreeTableColumn<FileWrapper, LocalDateTime> lastModifiedColumn;


	@InjectViewModel
	private FileMenuViewModel viewModel;

	@Inject
	public FileMenuView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		initTree();

		dropArea.disableProperty().bind(viewModel.dropAreaDisableProperty());
		dropArea.textProperty().bind(viewModel.dropAreaTextProperty());
		dropArea.backgroundProperty().bind(viewModel.dropAreaColorProperty());

		browsePath.textProperty().bindBidirectional(viewModel.browsePathProperty());

		changeButton.setText(FOLDER_OPEN);

		subscribeToNotifications();
	}

	private void subscribeToNotifications() {
		manage(eventsOf(dropArea, DRAG_OVER).subscribe(e -> viewModel.onDropAreaDragOver(e)));

		manage(eventsOf(dropArea, DRAG_ENTERED).subscribe(e -> viewModel.onDropAreaDragEntered(e)));

		manage(eventsOf(dropArea, DRAG_EXITED).subscribe(e -> viewModel.onDropAreaDragExited(e)));

		manage(eventsOf(dropArea, DRAG_DROPPED).subscribe(e -> viewModel.onDropAreaDropped(e)));

		manage(eventsOf(changeButton, ACTION)
				       .subscribe(e -> viewModel.onChangeAction((Stage) changeButton.getScene().getWindow())));

		// hack for the tree view disclosure node (triangle) to not disappear
		manage(eventsOf(treeView, MOUSE_DRAGGED).subscribe(e -> treeView.getParent().requestFocus()));
		manage(eventsOf(treeView, MOUSE_CLICKED).subscribe(e -> treeView.getParent().requestFocus()));

		manage(eventsOf(treeView, DRAG_DETECTED).subscribe(e -> {
			log.entry();
			if (treeView.getSelectionModel().getSelectedItems().size() > 0) {
				Dragboard db = treeView.startDragAndDrop(MOVE);
				viewModel.onTreeViewDragDetected(treeView.getSelectionModel().getSelectedItems(), db);
			}
			e.consume();
		}));

		manage(eventsOf(treeView, DRAG_DONE).subscribe(e -> treeView.getSelectionModel().clearSelection()));

		manage(nonNullValuesOf(viewModel.browsePathProperty()).subscribe(this::load));

		viewModel.subscribe(REFRESH_PAGE, (k, p) -> load(viewModel.browsePathProperty().get()));

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
		treeView.getSelectionModel().setSelectionMode(MULTIPLE);
		treeView.getSelectionModel().setCellSelectionEnabled(true);

		fileColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().getName()));

		sizeColumn.setCellValueFactory(param -> {
			if (param.getValue().getValue().isFile()) {
				return new SimpleLongProperty(param.getValue().getValue().getSize());
			} else {
				return new SimpleObjectProperty<>(); // Don't show 0 / fileSize for directories
			}
		});

		sizeColumn.setCellFactory(column -> new TreeTableCell<FileWrapper, Number>() {

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

		lastModifiedColumn.setCellFactory(column -> new TreeTableCell<FileWrapper, LocalDateTime>() {

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

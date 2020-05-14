package com.b3tuning.b3console.view.file;

import com.b3tuning.b3console.service.files.FileEntity;
import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.ConversionUtil;
import com.b3tuning.b3console.view.utils.TreeItemBuilderHelper;
import com.b3tuning.b3console.view.utils.TreeItemStreamSupport;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

import static com.b3tuning.b3console.view.root.FontAwesome.FOLDER_OPEN;
import static org.reactfx.EventStreams.changesOf;
import static org.reactfx.EventStreams.eventsOf;
import static org.reactfx.EventStreams.nonNullValuesOf;
import static org.reactfx.EventStreams.valuesOf;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class DesktopAvailableFilesView extends BaseView<DesktopAvailableFilesViewModel> {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

	@FXML private Label     dropArea;
	@FXML private TextField browsePath;
	@FXML private Button    changeButton;

	@FXML private TreeTableView<FileEntity>                  treeView;
	@FXML private TreeTableColumn<FileEntity, String>        fileColumn;
	@FXML private TreeTableColumn<FileEntity, String>        sizeColumn;
	@FXML private TreeTableColumn<FileEntity, LocalDateTime> lastModifiedColumn;

	@InjectViewModel
	private DesktopAvailableFilesViewModel viewModel;

	@Inject
	public DesktopAvailableFilesView() {
		log.entry();
	}

	public void initialize() {
		log.entry();

		initTree();

		dropArea.disableProperty().bind(viewModel.dropAreaDisableProperty());
		dropArea.textProperty().bind(viewModel.dropAreaTextProperty());
		dropArea.backgroundProperty().bind(viewModel.dropAreaColorProperty());

		// create placeholder text for table
		Label placeHolderText = new Label();
		placeHolderText.textProperty().bind(viewModel.availableFilesTablePlaceholderProperty());
		treeView.setPlaceholder(placeHolderText);

		browsePath.textProperty().bindBidirectional(viewModel.browsePathProperty());

		changeButton.setText(FOLDER_OPEN);

		subscribeToNotifications();
	}

	private void subscribeToNotifications() {
		manage(eventsOf(dropArea, DragEvent.DRAG_OVER).subscribe(e -> viewModel.onDropAreaDragOver(e)));

		manage(eventsOf(dropArea, DragEvent.DRAG_ENTERED).subscribe(e -> viewModel.onDropAreaDragEntered(e)));

		manage(eventsOf(dropArea, DragEvent.DRAG_EXITED).subscribe(e -> viewModel.onDropAreaDragExited(e)));

		manage(eventsOf(dropArea, DragEvent.DRAG_DROPPED).subscribe(e -> viewModel.onDropAreaDropped(e)));

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

		manage(nonNullValuesOf(viewModel.fileProperty()).subscribe(this::createNodeAndAttachToTree));

		// Originally there were 2 subscriptions, nonNullValuesOf, and valuesOf
		// with valuesOf only operating on null and setting root null so just simplify as...
		// Set root to the viewModel.rootElementProperty even when it's null
		manage(valuesOf(viewModel.rootElementProperty()).subscribe(root -> treeView.setRoot(root)));

	}

	private void initTree() {
		log.entry();

		treeView.setShowRoot(false);
		treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		treeView.getSelectionModel().setCellSelectionEnabled(true);

		fileColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().aliasProperty());
		sizeColumn.setCellValueFactory(cellData -> {
			if (cellData.getValue().getValue().isFile()) {
				return new SimpleStringProperty(
						ConversionUtil.readableFileSize(cellData.getValue().getValue().getSize()));
			} else {
				return new SimpleStringProperty();
			}
		});
		lastModifiedColumn.setCellValueFactory(cellData -> {
			if (cellData.getValue().getValue().isFile()) {
				long timestamp = cellData.getValue().getValue().lastModified();
				return new SimpleObjectProperty<>(
						LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
						                        TimeZone.getDefault().toZoneId()));
			} else {
				return new SimpleObjectProperty<>();
			}
		});
		lastModifiedColumn.setCellFactory(column -> new TreeTableCell<FileEntity, LocalDateTime>() {

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

	private void createNodeAndAttachToTree(FileEntity treeNode) {
		if (treeNode == null) {
			return;
		}
		if (viewModel.getRootElement() == null) {
			buildTree(treeNode);
			return;
		}
		log.entry();

		addTreeChild(treeNode);
	}

	private void buildTree(FileEntity rootFile) {
		log.entry(rootFile);
		if (viewModel.getRootElement() != null) {
			return;
		}
		@SuppressWarnings("unchecked")
		TreeItem<FileEntity> root = (TreeItem<FileEntity>) TreeItemBuilderHelper.createNode(rootFile);
		setOnExpand(root);
		viewModel.setRootElement(root);
	}

	@SuppressWarnings("unchecked")
	private void addTreeChild(FileEntity file) {
		TreeItemStreamSupport.stream(viewModel.getRootElement())
		                     .filter(c -> c.getValue().getUid().equals(file.getUid()))
		                     .findAny()
		                     .ifPresent(c -> {
			                     TreeItem<FileEntity> childNode = (TreeItem<FileEntity>) TreeItemBuilderHelper
					                     .createNode(file);
			                     setOnExpand(childNode);
			                     c.getChildren().setAll(childNode.getChildren());
		                     });
	}

	private void setOnExpand(TreeItem<FileEntity> item) {
		item.getChildren().forEach(child -> manage(changesOf(child.expandedProperty()).subscribe(e -> {
			if (e.getOldValue() != e.getNewValue() && e.getNewValue()) {
				if (child.getChildren().isEmpty()) {
					viewModel.fileProperty().set(child.getValue());
					viewModel.loadFile();
				}
			}
		})));
	}
}

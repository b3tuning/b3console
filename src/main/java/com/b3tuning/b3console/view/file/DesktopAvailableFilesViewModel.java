package com.b3tuning.b3console.view.file;

import com.b3tuning.b3console.service.files.FileEntity;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.availablefiles.AvailableFilesViewModel;
import com.b3tuning.b3console.view.utils.TreeItemStreamSupport;
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
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.reactfx.Guard;
import org.reactfx.SuspendableNo;

import javax.inject.Inject;
import java.io.File;
import java.text.DateFormat;
import java.util.List;
import java.util.stream.Collectors;

import static com.b3tuning.b3console.view.BackgroundColorConstants.GREEN_BACKGROUND;
import static com.b3tuning.b3console.view.BackgroundColorConstants.LIGHT_GRAY_BACKGROUND;
import static com.b3tuning.b3console.view.BackgroundColorConstants.RED_BACKGROUND;
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
public class DesktopAvailableFilesViewModel extends BaseViewModel implements AvailableFilesViewModel, Refreshable {

	private static final String DEFAULT_SORT      = "name";
	private static final String ROOT_FILE_PATH    = "/";
	private static final String DRAG_DROP_MESSAGE = "Select a Location to view available files";

	private static DateFormat dateTimeFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

	// data sources
	private ObjectProperty<FileEntity> file              = new SimpleObjectProperty<>();
	private SuspendableNo              loadingInProgress = new SuspendableNo();

	// consumed by view
	private StringProperty                       availableFilesTablePlaceholder = new SimpleStringProperty();
	private BooleanProperty                      dropAreaDisabled               = new SimpleBooleanProperty();
	private StringProperty                       dropAreaText                   = new SimpleStringProperty();
	private ObjectProperty<Background>           dropAreaColor                  = new SimpleObjectProperty<>();
	private ObjectProperty<TreeItem<FileEntity>> rootElement                    = new SimpleObjectProperty<>();

	// injected dependencies
//	private FilesService filesService;

	@Inject
	public DesktopAvailableFilesViewModel(/*FilesService filesService*/) {
		log.entry();

//		this.filesService = filesService;

		manage(valuesOf(dropAreaDisabled).subscribe(e -> {
			log.entry(e);
			Platform.runLater(() -> {
				if (e) {
					dropAreaText.set(DropAreaConstants.DEFAULT_DISABLED_DRAG_DROP_MESSAGE);
				} else {
					dropAreaText.set(DRAG_DROP_MESSAGE);
				}
			});
		}));
	}

	void onTreeViewDragDetected(ObservableList<TreeItem<FileEntity>> files, Dragboard db) {
		log.entry();
		if (files.isEmpty()) {
			return;
		}
		/* Build list of files to drag */
		List<File> filesToDrag = getFileList(files);
		log.trace("List of files to drag: {}", filesToDrag);

		/* Put the File name on a dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putFiles(filesToDrag);
		db.setContent(content);
	}

	/**
	 * Build the file list of files to drag.
	 */
	private List<File> getFileList(ObservableList<TreeItem<FileEntity>> sourceList) {
		log.entry(sourceList);
		return sourceList.stream()
		                 .map(treeItem -> new File(treeItem.getValue().getName().toString()))
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
		                ? GREEN_BACKGROUND
		                : RED_BACKGROUND;
		dropAreaColor.set(bg);
	}

	void onDropAreaDragExited(DragEvent e) {
		log.entry(e);
		dropAreaColor.set(LIGHT_GRAY_BACKGROUND);
	}

	void onDropAreaDropped(DragEvent e) {
		log.entry();
		Dragboard      db      = e.getDragboard();
		MutableBoolean success = new MutableBoolean(true);
		if (db.hasFiles()) {
			db.getFiles().forEach(file -> {
				try {
					// set the uid
					String sourceFileName = file.getName();
					FileEntity entity = TreeItemStreamSupport.stream(getRootElement())
					                                         .map(TreeItem::getValue)
					                                         .filter(f -> f.getName().equals(sourceFileName))
					                                         .findAny()
					                                         .orElse(null);
					if (entity == null) {
						log.error("can not locate dragged item in the tree : error {}", file);
						return;
					}
					entity.setRelativePath(null);
//					ProcessChain.create()
//					            .addRunnableInPlatformThread(
//							            () -> dropAreaText.set(DropAreaConstants.PROCESSING_FILES_MESSAGE))
//					            .addRunnableInExecutor(() -> createSourceFileJob(entity.getUid()))
//					            .addRunnableInPlatformThread(() -> dropAreaText.set(entity.isDirectory()
//					                                                                ? DropAreaConstants.DROPPED_REMOTE_DIR_MESSAGE
//					                                                                : String.format(
//							                                                                DropAreaConstants.DROPPED_MESSAGE,
//							                                                                entity.getAlias())))
//					            .onException(ex -> {
//						            success.setValue(false);
//						            log.error("Unable to process chain for posting jobs {}", ex);
//						            dropAreaText.set(DropAreaConstants.DEFAULT_LOCATION_SELECTED_MESSAGE);
//					            })
//					            .run();
				}
				catch (IllegalArgumentException iae) {
					log.error(iae.getMessage());
					success.setValue(false);
				}
			});
		}
		e.setDropCompleted(success.getValue());
		e.consume();
	}

	void loadFile() {
		log.entry();

		if (loadingInProgress.get()) {
			log.trace("Already loading therefore not performing a duplicate page load");
			return;
		}

		final Guard lock = loadingInProgress.suspend();

		availableFilesTablePlaceholder.set("Loading files...");

//		ProcessChain.create()
//		            .addRunnableInExecutor(() -> processFilesResponseFromServer(
//				            requestFilesDataFromServer(selectedLocation.get().getUid(),
//				                                       file.get() == null
//				                                       ? ROOT_FILE_PATH
//				                                       : file.get().getUid().toString())))
//		            .addRunnableInPlatformThread(() -> {
//			            availableFilesTablePlaceholder
//					            .set("There are no files that you have permission to view.");
//			            lock.close();
//		            }).onException(e -> {
//			log.error("Unable to retrieve the list of files", e);
//			lock.close();
//		}).run();
	}

//	private FileEntity requestFilesDataFromServer(UUID locationUid, String fileUidOrPath) {
//		log.entry(locationUid, fileUidOrPath);
//
//		FileByLocationRequest request = new FileByLocationRequest(
//				user.getOrganizationUid(),
//				locationUid,
//				fileUidOrPath);
//		FileEntity response = filesService.file(request);
//		if (response != null) {
//			orderJobAliases(response);
//		} else {
//			log.debug("NO FILES TO SHOW!");
//		}
//		return response;
//	}

//	private void orderJobAliases(FileEntity file) {
//		List<FileEntity> sortedChildren = file.getChildren().stream()
//		                                      .filter(c -> fromShortDateFormat(c.getAlias()) != null)
//		                                      .sorted(Comparator.comparing(c -> fromShortDateFormat(c.getAlias()),
//		                                                                   Comparator.reverseOrder()))
//		                                      .collect(Collectors.toList());
//
//		if (!sortedChildren.isEmpty()) {
//			file.setChildren(sortedChildren);
//		}
//	}

//	private Date fromShortDateFormat(String dateFormat) {
//		try {
//			return dateTimeFormatter.parse(dateFormat);
//		}
//		catch (ParseException e) {
//			log.error("Error parsing date {}", dateFormat);
//			return null;
//		}
//	}

//	private void processFilesResponseFromServer(FileEntity data) {
//		log.entry();
//		if (data == null) {
//			log.error("could not retrieve the files");
//			return;
//		}
//		Platform.runLater(() -> file.set(data));
//
//	}

	private void clearTree() {
		file.setValue(null);
		setRootElement(null);
	}


	//////////////////////////////////////////////////////////////
	// JAVA FX PROPERTIES
	//////////////////////////////////////////////////////////////

	TreeItem<FileEntity> getRootElement() {
		return rootElement.get();
	}

	void setRootElement(TreeItem<FileEntity> root) {
		rootElement.set(root);
	}

	ObjectProperty<TreeItem<FileEntity>> rootElementProperty() {
		return rootElement;
	}

	ObjectProperty<FileEntity> fileProperty() {
		return file;
	}

	StringProperty availableFilesTablePlaceholderProperty() {
		return availableFilesTablePlaceholder;
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

	////////////////////////////////////////////////////////////


	@Override
	public void refresh() {
		log.entry();
		loadFile();
	}

	@Override
	public void dispose() {
		log.entry();
		super.dispose();
		clearTree();
	}
}

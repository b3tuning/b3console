package com.b3tuning.b3console.view.file.testing;

import com.b3tuning.b3console.service.files.FileEntity;
import com.b3tuning.b3console.service.files.FilesService;
import com.b3tuning.b3console.view.BackgroundColorConstants;
import com.b3tuning.b3console.view.BaseViewModel;
import com.b3tuning.b3console.view.Refreshable;
import com.b3tuning.b3console.view.availablefiles.AvailableFilesViewModel;
import com.b3tuning.b3console.view.file.DropAreaConstants;
import com.b3tuning.b3console.view.utils.TreeItemStreamSupport;
import io.datafx.core.concurrent.ProcessChain;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
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
import org.reactfx.Indicator;
import org.reactfx.inhibeans.property.SimpleBooleanProperty;
import org.reactfx.inhibeans.property.SimpleObjectProperty;

import javax.inject.Inject;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.reactfx.EventStreams.nonNullValuesOf;
import static org.reactfx.EventStreams.valuesOf;

/**
 * Created by jhildebrand on 11/11/15
 */
@XSlf4j
public class DesktopAvailableFilesViewModel extends BaseViewModel implements AvailableFilesViewModel, Refreshable {

	private static final String DEFAULT_SORT      = "name";
	private static final String ROOT_FILE_PATH    = "/";
	private static final String DRAG_DROP_MESSAGE = "Select a Location to view available files";

	private static DateFormat dateTimeFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

	// data sources
	private ObjectProperty<ProjectDetail>            project           = new SimpleObjectProperty<>();
	private ObjectProperty<ObservableList<Location>> locations         = new SimpleObjectProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Location>                 selectedLocation  = new SimpleObjectProperty<>();
	private ObjectProperty<FileEntity>               file              = new SimpleObjectProperty<>();
	private Indicator                                loadingInProgress = new Indicator();

	// consumed by view
	private StringProperty                       locationsPlaceholder           = new SimpleStringProperty();
	private StringProperty                       availableFilesTablePlaceholder = new SimpleStringProperty();
	private BooleanProperty                      dropAreaDisabled               = new SimpleBooleanProperty();
	private StringProperty                       dropAreaText                   = new SimpleStringProperty();
	private ObjectProperty<Background>           dropAreaColor                  = new SimpleObjectProperty<>();
	private ObjectProperty<TreeItem<FileEntity>> rootElement                    = new SimpleObjectProperty<>();

	// injected dependencies
	private AuthenticatedUser user;
	private LocationsService  locationsService;
	private FilesService      filesService;
	private JobsService       jobsService;

	@Inject
	public DesktopAvailableFilesViewModel(AuthenticatedUser user,
	                                      LocationsService locationsService,
	                                      FilesService filesService,
	                                      JobsService jobsService) {
		log.entry();

		this.user = user;
		this.locationsService = locationsService;
		this.filesService = filesService;
		this.jobsService = jobsService;

		manage(valuesOf(dropAreaDisabled).subscribe(e -> {
			log.entry(e);
			Platform.runLater(()->{
				if (e) {
					dropAreaText.set(DropAreaConstants.DEFAULT_DISABLED_DRAG_DROP_MESSAGE);
				} else {
					dropAreaText.set(DRAG_DROP_MESSAGE);
				}
			});
		}));

		// when a location has been selected from drop down, populate the files view
		manage(nonNullValuesOf(selectedLocationProperty()).subscribe(loc -> {
			if (loc != null) {
				dropAreaText.set(DropAreaConstants.DEFAULT_LOCATION_SELECTED_MESSAGE);
				clearTree();
				loadFile();
			}
		}));

		manage(nonNullValuesOf(project).subscribe(p -> {
			log.entry();
			dropAreaDisabled.bind((p.enabledProperty().not()).or(p.hasDeliverySettingsProperty().not()));
			loadLocations();
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
		                 .map(treeItem -> new File(treeItem.getValue().getUid().toString()))
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

	void onDropAreaDropped(DragEvent e) {
		log.entry();
		Dragboard      db      = e.getDragboard();
		MutableBoolean success = new MutableBoolean(true);
		if (db.hasFiles()) {
			db.getFiles().forEach(file -> {
				try {
					// set the uid
					UUID sourceFileUid = UUID.fromString(file.getName());
					FileEntity entity = TreeItemStreamSupport.stream(getRootElement())
					                                         .map(TreeItem::getValue)
					                                         .filter(f -> f.getUid().equals(sourceFileUid))
					                                         .findAny()
					                                         .orElse(null);
					if (entity == null) {
						log.error("can not locate dragged item in the tree : error {}", file);
						return;
					}
					entity.setRelativePath(null);
					ProcessChain.create()
					            .addRunnableInPlatformThread(() -> dropAreaText.set(DropAreaConstants.PROCESSING_FILES_MESSAGE))
					            .addRunnableInExecutor(() -> createSourceFileJob(entity.getUid()))
					            .addRunnableInPlatformThread(() -> dropAreaText.set(entity.isDirectory()
					                                                                ? DropAreaConstants.DROPPED_REMOTE_DIR_MESSAGE
					                                                                : String.format(DropAreaConstants.DROPPED_MESSAGE, entity.getAlias())))
					            .onException(ex -> {
						            success.setValue(false);
						            log.error("Unable to process chain for posting jobs", ex);
						            dropAreaText.set(DropAreaConstants.DEFAULT_LOCATION_SELECTED_MESSAGE);
					            })
					            .run();
				} catch (IllegalArgumentException iae) {
					log.error(iae.getMessage());
					success.setValue(false);
				}
			});
		}
		e.setDropCompleted(success.getValue());
		e.consume();
	}

	private void loadLocations() {
		log.entry();

		if (loadingInProgress.isOn()) {
			log.trace("Already loading therefore not performing a duplicate page load");
			return;
		}

		final Guard lock = loadingInProgress.on();
		locationsPlaceholder.set("Loading locations...");

		ProcessChain.create()
		            .addRunnableInExecutor(() -> processLocationsResponseFromServer(requestLocationsDataFromServer()))
		            .addRunnableInPlatformThread(() -> {
			            locationsPlaceholder
					            .set("There are no locations that you have permission to view.");
			            lock.close();
		            }).onException(e -> {
			log.error("Unable to retrieve the list of locations", e);
			lock.close();
		}).run();
	}

	private LocationsResponse requestLocationsDataFromServer() {
		log.entry();
		LocationsByOrganizationRequest request = new LocationsByOrganizationRequest(
				user.getOrganizationUid()).setSize(Integer.MAX_VALUE);
		request.setSort(DEFAULT_SORT);
		return locationsService.locations(request);
	}

	private void processLocationsResponseFromServer(LocationsResponse data) {
		if (data == null) {
			log.error("could not retrieve the data");
			return;
		}
		locations.get().setAll(data.getLocations().stream()
		                           .filter(l -> l.getLocationType().equals(LocationType.Router))
		                           .collect(Collectors.toList()));
	}

	void loadFile() {
		log.entry();

		if (loadingInProgress.isOn()) {
			log.trace("Already loading therefore not performing a duplicate page load");
			return;
		}

		final Guard lock = loadingInProgress.on();

		availableFilesTablePlaceholder.set("Loading files...");

		ProcessChain.create()
		            .addRunnableInExecutor(() -> processFilesResponseFromServer(
				            requestFilesDataFromServer(selectedLocation.get().getUid(),
				                                       file.get() == null ? ROOT_FILE_PATH : file.get().getUid().toString())))
		            .addRunnableInPlatformThread(() -> {
			            availableFilesTablePlaceholder
					            .set("There are no files that you have permission to view.");
			            lock.close();
		            }).onException(e -> {
			log.error("Unable to retrieve the list of files", e);
			lock.close();
		}).run();
	}

	private FileEntity requestFilesDataFromServer(UUID locationUid, String fileUidOrPath) {
		log.entry(locationUid, fileUidOrPath);

		FileByLocationRequest request = new FileByLocationRequest(
				user.getOrganizationUid(),
				locationUid,
				fileUidOrPath);
		FileEntity response = filesService.file(request);
		if (response != null) {
			orderJobAliases(response);
		} else {
			log.debug("NO FILES TO SHOW!");
		}
		return response;
	}

	private void orderJobAliases(FileEntity file) {
		List<FileEntity> sortedChildren = file.getChildren().stream()
		                                      .filter(c -> fromShortDateFormat(c.getAlias()) != null)
		                                      .sorted(Comparator.comparing(c -> fromShortDateFormat(c.getAlias()), Comparator.reverseOrder()))
		                                      .collect(Collectors.toList());

		if (!sortedChildren.isEmpty()) {
			file.setChildren(sortedChildren);
		}
	}

	private Date fromShortDateFormat(String dateFormat) {
		try {
			return dateTimeFormatter.parse(dateFormat);
		} catch (ParseException e) {
			log.error("Error parsing date {}", dateFormat);
			return null;
		}
	}

	private void processFilesResponseFromServer(FileEntity data) {
		log.entry();
		if (data == null) {
			log.error("could not retrieve the files");
			return;
		}
		Platform.runLater(() -> file.set(data));

	}

	private void createSourceFileJob(UUID sourceFileUid) {
		ProcessChain.create()
		            .addSupplierInExecutor(() -> {
			            JobRequest jobRequest = new JobRequest().setSourceFileUid(sourceFileUid)
			                                                    .setOrganizationUid(user.getOrganizationUid())
			                                                    .setProjectUid(project.get().getUid());
			            return jobsService.createSourceFileJob(jobRequest);
		            })
		            .addConsumerInPlatformThread((job) -> {
			            if (job == null) {
				            log.error("Error in job creation");
			            } else {
				            jobsService.getRemoteJobAdded().set(true);
			            }

		            })
		            .onException(e -> log.error("Unable to process chain for ingest", e)).run();
	}

	private void clearTree() {
		file.setValue(null);
		setRootElement(null);
	}


	//////////////////////////////////////////////////////////////
	// JAVA FX PROPERTIES
	//////////////////////////////////////////////////////////////

	public ObjectProperty<Location> selectedLocationProperty() {
		return selectedLocation;
	}

	public ObjectProperty<ProjectDetail> projectProperty() {
		return project;
	}

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

	ObjectProperty<ObservableList<Location>> locationsProperty() {
		return locations;
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

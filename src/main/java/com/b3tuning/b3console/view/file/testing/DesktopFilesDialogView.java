package com.b3tuning.b3console.view.file.testing;

import com.b3tuning.b3console.view.BaseView;
import com.b3tuning.b3console.view.utils.ConversionUtil;
import com.b3tuning.b3console.view.utils.TableViewUtils;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.XSlf4j;
import org.fxmisc.easybind.EasyBind;

import javax.inject.Inject;
import java.io.File;
import java.nio.file.Path;

import static org.reactfx.EventStreams.valuesOf;

@XSlf4j
public class DesktopFilesDialogView extends BaseView<DesktopFilesDialogViewModel> {
	
	private static int MAX_ROWS_TO_DISPLAY = 6;

	@InjectViewModel
	public DesktopFilesDialogViewModel viewModel;

	@FXML Label description;
	@FXML TableView<File>           filesToIngestTable;
	@FXML TableColumn<File, String> fileToIngestNameColumn;
	@FXML TableColumn<File, String> fileToIngestSizeColumn;
	@FXML TableView<File>           invalidFilesTable;
	@FXML TableColumn<File, String> invalidFileNameColumn;
	@FXML TableColumn<File, String> invalidFileSizeColumn;

	private TableViewUtils tableViewUtils;
	
	@Inject
	public DesktopFilesDialogView(TableViewUtils tableViewUtils) {
		log.entry();
		this.tableViewUtils = tableViewUtils;
	}

	public void initialize() {
		log.entry();

		Path rootPath = viewModel.rootPathProperty().get().toPath();

		description.textProperty().bind(EasyBind.map(viewModel.rootPathProperty(), file ->
				"Based on the delivery settings configured for this project, some of the files selected for ingest are not supported."
				+ "\nIf you continue the only the SUPPORTED files will be uploaded."
				+ "\n\nPath: " + file
		));

		filesToIngestTable.itemsProperty().bind(viewModel.filesToIngestProperty());

		fileToIngestNameColumn.setCellValueFactory(f -> new SimpleStringProperty(rootPath.relativize(f.getValue().toPath()).toString()));
		fileToIngestSizeColumn.setCellValueFactory(f -> new SimpleStringProperty(
				ConversionUtil.readableFileSize(f.getValue().length())));

		invalidFilesTable.itemsProperty().bind(viewModel.invalidFilesProperty());

		invalidFileNameColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
		invalidFileSizeColumn.setCellValueFactory(f -> new SimpleStringProperty(ConversionUtil.readableFileSize(f.getValue().length())));

		// set table heights (used for auto-resizing height)
		filesToIngestTable.setFixedCellSize(TableViewUtils.DEFAULT_ROW_HEIGHT);
		manage(valuesOf(viewModel.filesToIngestProperty()).subscribe(c -> {
			log.entry();
			tableViewUtils.bindTableHeight(filesToIngestTable, MAX_ROWS_TO_DISPLAY);
		}));

		invalidFilesTable.setFixedCellSize(TableViewUtils.DEFAULT_ROW_HEIGHT);
		manage(valuesOf(viewModel.invalidFilesProperty()).subscribe(c -> {
			log.entry();
			tableViewUtils.bindTableHeight(invalidFilesTable, MAX_ROWS_TO_DISPLAY);
		}));
	}

}

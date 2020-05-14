package com.b3tuning.b3console.view.utils;

import com.google.common.collect.Lists;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.TransferMode;
import lombok.Data;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static javafx.scene.control.SelectionMode.MULTIPLE;
import static javafx.scene.input.KeyCombination.SHORTCUT_DOWN;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class TableViewUtils {

	private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

	private static final int DEFAULT_HEADER_HEIGHT = 26;
	public static final int DEFAULT_ROW_HEIGHT = 40;
	private static final int DEFAULT_MARGIN = 5;
	private static final int DEFAULT_MAX_ROWS = 10;


	/**
	 * Helper to set table height based on content (with optional maxRow limiter)
	 *
	 * @param table        - the table in context
	 * @param rowHeight    - the height of a single row
	 * @param headerHeight - the height of the table header
	 * @param margin       - a value for the margins
	 * @param maxRows      - Optional max number of table rows (can be null)
	 */
	public void bindTableHeight(TableView<?> table, int rowHeight, int headerHeight, int margin, Integer maxRows) {
		log.entry(rowHeight, headerHeight, margin, maxRows);

		NumberBinding minRows = Bindings.max(1, Bindings.size(table.getItems()));
		table.prefHeightProperty().bind(Bindings.min(maxRows, minRows)
		                                        .multiply(table.fixedCellSizeProperty())
		                                        .add(headerHeight)
		                                        .add(margin));
		table.minHeightProperty().bind(table.prefHeightProperty());
		table.maxHeightProperty().bind(table.prefHeightProperty());

		log.exit(table.getPrefHeight());
	}

	public void bindTableHeight(TableView<?> table) {
		bindTableHeight(table, DEFAULT_ROW_HEIGHT, DEFAULT_HEADER_HEIGHT, DEFAULT_MARGIN, DEFAULT_MAX_ROWS);
	}

	public void bindTableHeight(TableView<?> table, Integer maxRows) {
		bindTableHeight(table, DEFAULT_ROW_HEIGHT, DEFAULT_HEADER_HEIGHT, DEFAULT_MARGIN, maxRows);
	}

	/**
	 * drag/drop re-order table rows...
	 */
	public <T> void dragDropTableSetup(TableView<T> tableView) {
		tableView.setRowFactory(tv -> {
			TableRow<T> row = new TableRow<>();
			row.setOnDragDetected(event -> {
				if (!row.isEmpty()) {
					Integer index = row.getIndex();
					Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
					db.setDragView(row.snapshot(null, null));
					ClipboardContent cc = new ClipboardContent();
					cc.put(SERIALIZED_MIME_TYPE, index);
					db.setContent(cc);
					event.consume();
				}
			});
			row.setOnDragOver(event -> {
				Dragboard db = event.getDragboard();
				if (db.hasContent(SERIALIZED_MIME_TYPE)) {
					if (row.getIndex() != (Integer) db.getContent(SERIALIZED_MIME_TYPE)) {
						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
						event.consume();
					}
				}
			});
			row.setOnDragDropped(event -> {
				Dragboard db = event.getDragboard();
				if (db.hasContent(SERIALIZED_MIME_TYPE)) {
					int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
					T draggedObject = tableView.getItems().remove(draggedIndex);
					int dropIndex;
					if (row.isEmpty()) {
						dropIndex = tableView.getItems().size();
					} else {
						dropIndex = row.getIndex();
					}
					tableView.getItems().add(dropIndex, draggedObject);
					event.setDropCompleted(true);
					tableView.getSelectionModel().select(dropIndex);
					event.consume();
				}
			});
			return row;
		});
	}

	/**
	 * Provide the `Copy` and `Select All` context menus and shortcuts. This also enables
	 * the MULTIPLE selection mode on the table.
	 * <p>
	 * NOTE: the shortcut doesn't work properly when there are more than 1 TableView on
	 * the same page and instead only works for the last TableView registered.
	 * One example of this is the Job Details page.
	 */
	public static void installCopyHandlers(TableView<?> table) {
		table.getSelectionModel().setCellSelectionEnabled(true);
		table.getSelectionModel().setSelectionMode(MULTIPLE);

		getOrCreateContextMenu(table).getItems()
		                             .addAll(createCopyContextMenu(table), createSelectAllContextMenu(table));
	}

	public static MenuItem createCopyContextMenu(TableView<?> table) {
		MenuItem copyMenu = new MenuItem("Copy");
		copyMenu.setOnAction(new TableCopyEventHandler(table));
		copyMenu.setAccelerator(new KeyCodeCombination(KeyCode.C, SHORTCUT_DOWN));
		return copyMenu;
	}

	@Data
	public static class TableCopyEventHandler implements EventHandler<ActionEvent> {
		private final TableView<?> table;

		@Override
		public void handle(ActionEvent event) {
			String copy = selectionToString(table);
			if (StringUtils.isNotBlank(copy)) {
				setClipboardContents(copy);
			}
		}
	}

	@Data
	public static class TableSelectAllEventHandler implements EventHandler<ActionEvent> {
		private final TableView<?> table;

		@Override
		public void handle(ActionEvent event) {
			table.getSelectionModel().selectAll();
		}
	}

	public static MenuItem createSelectAllContextMenu(TableView<?> table) {
		MenuItem selectAllMenu = new MenuItem("Select All");
		selectAllMenu.setOnAction(new TableSelectAllEventHandler(table));
		selectAllMenu.setAccelerator(new KeyCodeCombination(KeyCode.A, SHORTCUT_DOWN));
		return selectAllMenu;
	}

	public static void installCopyHandlers(TableView<?>... tables) {
		for (TableView<?> table : tables) {
			installCopyHandlers(table);
		}
	}

	/**
	 * Creates a string representation of the selected contents of the TableView.
	 * If a single sell is selected then the contents of that cell are copied.
	 * If multiple cells are selected then a string is built with each cell separated
	 * by a tab and each row by a new line. Only the selected cells are included.
	 * <p>
	 * For example given the table:
	 * | a | b |
	 * | c | d |
	 * And selections:
	 * - a     : "a"
	 * - a,b   : "a\tb\n"
	 * - a,b,c : "a\tb\nc\t\n"
	 * - b,d   : "\tb\n\td\n
	 * <p>
	 * This format allows selection of a single value to be pasted anywhere as well as
	 * multiple values that can be pasted into a spreadsheet.
	 */
	private static <S> String selectionToString(TableView<S> table) {
		StringBuilder sb = new StringBuilder();

		ObservableList<TablePosition> selectedCells = table.getSelectionModel().getSelectedCells();
		if (selectedCells.size() == 1) {
			TablePosition<?, ?> tablePosition = selectedCells.get(0);
			sb.append(tablePosition.getTableColumn().getCellObservableValue(tablePosition.getRow()).getValue()
			                       .toString());

		} else if (selectedCells.size() > 1) {
			for (Integer row : table.getSelectionModel().getSelectedIndices()) {
				sb.append(buildRow(table, row, "\t")).append("\n");
			}
		} else {
			log.debug("nothing selected");
		}
		return sb.toString();
	}

	private static <S> String buildRow(TableView<S> table, int row, String separator) {
		TableSelectionModel<S> selectionModel = table.getSelectionModel();
		List<String> items = Lists.newArrayList();
		for (TableColumn<S, ?> column : table.getColumns()) {
			if (selectionModel.isSelected(row, column)
			    && column.getCellObservableValue(row) != null
			    && column.getCellObservableValue(row).getValue() != null) {
				items.add(column.getCellObservableValue(row).getValue().toString());
			} else {
				items.add("");
			}
		}
		if (separator == null) {
			separator = "";
		}
		return String.join(separator, items);
	}

	private static void setClipboardContents(String contents) {
		ClipboardContent content = new ClipboardContent();
		content.putString(contents);
		Clipboard.getSystemClipboard().setContent(content);
	}

	private static ContextMenu getOrCreateContextMenu(TableView<?> table) {
		if (table.getContextMenu() == null) {
			table.setContextMenu(new ContextMenu());
		}
		return table.getContextMenu();
	}
}

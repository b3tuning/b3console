package com.b3tuning.b3console.view.actionmenu;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import lombok.extern.slf4j.XSlf4j;
import org.reactfx.EventStreams;

import static javafx.geometry.Pos.CENTER_LEFT;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class ActionMenuColumnBuilder<S, T> {

	ObjectProperty<S>      selectable;
	ObjectProperty<Action> selectableAction;

	public ActionMenuColumnBuilder(ObjectProperty<S> selectable,
	                               ObjectProperty<Action> selectableAction) {
		this.selectable       = selectable;
		this.selectableAction = selectableAction;
	}

	/**
	 * Column with edit/delete action based on the given parameters
	 */
	public Callback<TableColumn<S, T>, TableCell<S, T>> getActionMenuCell(
			ActionsMenuModel actions) {
		return new Callback<>() {

			@Override
			public TableCell<S, T> call(TableColumn<S, T> param) {

				final TableCell<S, T> cell = new TableCell<>() {

					@Override
					public void updateItem(T value, boolean empty) {
						super.updateItem(value, empty);

						ActionsMenu<S> menu = new ActionsMenu<>(actions);
						HBox           hbox = new HBox();

						final TableCell<S, T> c = this;
						@SuppressWarnings("unchecked") TableRow<S> tableRow = c.getTableRow();

						if (empty || tableRow == null || tableRow.getItem() == null) {
							c.setGraphic(null);
							c.setText(null);
							return;
						}

						// build the menu
						if (menu.getAssociatedEntity() == null) {
							S item = tableRow.getItem();

							menu.setAssociatedEntity(item);

							// if an action has been selected by the user, inform the caller
							EventStreams.valuesOf(menu.selectedActionProperty()).subscribe(a -> {
								log.entry(menu.getSelectedAction(), item);
								selectable.setValue(item);
								selectableAction.set(menu.getSelectedAction());
							});

							// display it

							hbox.getChildren().add(menu);
						}
						setGraphic(hbox);
					}
				};
				cell.setAlignment(CENTER_LEFT);
				return cell;
			}
		};
	}

	public Callback<TreeTableColumn<S, T>, TreeTableCell<S, T>> getTreeActionMenuCell(
			ActionsMenuModel actions) {

		return new Callback<>() {

			@Override
			public TreeTableCell<S, T> call(TreeTableColumn<S, T> param) {

				final TreeTableCell<S, T> cell = new TreeTableCell<>() {
					ActionsMenu<S> menu = new ActionsMenu<>(actions);
					HBox hbox = new HBox();

					@Override
					public void updateItem(T value, boolean empty) {
						super.updateItem(value, empty);
						final TreeTableCell<S, T> c = this;
						setText(null);

						TreeTableRow<S> treeTableRow = c.getTreeTableRow();

						if (empty || treeTableRow.isEmpty() || treeTableRow.getIndex() < 0
						    || treeTableRow.getIndex() >= param.getTreeTableView().getExpandedItemCount()) {
							setGraphic(null);
							c.setText(null);
							return;
						}

						TreeItem<S> treeItem = treeTableRow.getTreeTableView()
						                                   .getTreeItem(treeTableRow.getIndex());
						if (treeItem == null || treeItem.getValue() == null) {
							setGraphic(null);
							c.setText(null);
							return;
						}
						// skip not expanded items
						if (treeItem.getParent() != null && !treeItem.getParent().isExpanded()) {
							setGraphic(null);
							c.setText(null);
							return;
						}

						// build the menu
						if (menu.getAssociatedEntity() == null) {
							log.debug("Creating ActionsMenu for `{}`", treeTableRow.getItem());
							menu.setAssociatedEntity(treeItem.getValue());

							// if an action has been selected by the user, inform the caller
							EventStreams.nonNullValuesOf(menu.selectedActionProperty()).subscribe(a -> {
								log.entry(menu.getSelectedAction(), treeTableRow.getItem());
								selectable.setValue(treeTableRow.getItem());
								selectableAction.set(menu.getSelectedAction());
							});

							// display it
							hbox.getChildren().add(menu);
						}
						setGraphic(hbox);
					}
				};
				cell.setAlignment(CENTER_LEFT);
				return cell;
			}
		};
	}
}

package com.b3tuning.b3console.view.utils;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.util.Map;

import static javafx.scene.control.TableColumn.SortType.ASCENDING;

/*
 *  Created on:  Apr 16, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class SortUtility {

	public <S> String generateSort(ObservableList<TableColumn<S, ?>> sortColumns, Map<String, String> fieldMappings,
	                               String defaultSort) {

		StringBuilder sort = new StringBuilder();

		if (sortColumns != null && !sortColumns.isEmpty()) {
			for (TableColumn<S, ?> column : sortColumns) {
				if (fieldMappings.containsKey(column.getId())) {
					if (sort.length() > 0) {
						sort.append(",");
					}
					sort.append(fieldMappings.get(column.getId()));

					if (ASCENDING.equals(column.getSortType())) {
						sort.append(",asc");
					} else {
						sort.append(",desc");
					}
				}
			}
		}

		if (sort.length() == 0) {
			return defaultSort;
		} else {
			return sort.toString();
		}
	}
}

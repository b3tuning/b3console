package com.b3tuning.b3console.view.utils;

import javafx.scene.control.TreeItem;

import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class TreeItemStreamSupport {


	public static <T> Stream<TreeItem<T>> stream(TreeItem<T> rootItem) {
		return asStream(new TreeItemIterator<>(rootItem));
	}

	private static <T> Stream<TreeItem<T>> asStream(TreeItemIterator<T> iterator) {
		Iterable<TreeItem<T>> iterable = () -> iterator;

		return StreamSupport.stream(
				iterable.spliterator(),
				false
		                           );
	}


	private static class TreeItemIterator<T> implements Iterator<TreeItem<T>> {
		private Stack<TreeItem<T>> stack = new Stack<>();

		TreeItemIterator(TreeItem<T> root) {
			stack.push(root);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public TreeItem<T> next() {
			TreeItem<T> nextItem = stack.pop();
			nextItem.getChildren().forEach(stack::push);

			return nextItem;
		}
	}
}

/*
 *  Created on:  Jun 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  TreeItemBuilderHelper is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.view.test;

import com.google.common.collect.Maps;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.apache.commons.io.FilenameUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

public class TreeItemBuilderHelper {

	private static final String ICON_CONTAINER_STYLE      = "tree-item-graphic-container";
	private static final String DIRECTORY_TYPE_IDENTIFIER = ".";

	private static final Map<String, Image> iconCache = Maps.newConcurrentMap();

	public static TreeItem<? extends TreeElement> createNode(final TreeElement item) {
		HBox      iconContainer = new HBox();
		ImageView imageView     = new ImageView();
		iconContainer.getStyleClass().add(ICON_CONTAINER_STYLE);
		iconContainer.getChildren().add(imageView);

		setIcon(item, imageView);

		return new TreeItem<>(item, iconContainer) {
			private boolean isLeaf;
			private boolean isFirstTimeChildren = true;
			private boolean isFirstTimeLeaf = true;

			@Override
			public ObservableList<TreeItem<TreeElement>> getChildren() {
				if (isFirstTimeChildren) {
					isFirstTimeChildren = false;
					super.getChildren().setAll(buildChildren(this));
				}

				return super.getChildren();
			}

			@Override
			public boolean isLeaf() {
				if (isFirstTimeLeaf) {
					isFirstTimeLeaf = false;
					isLeaf          = !getValue().isDirectory();
				}

				return isLeaf;
			}

			@SuppressWarnings("unchecked")
			private ObservableList<TreeItem<TreeElement>> buildChildren(TreeItem<TreeElement> treeItem1) {
				TreeElement f = treeItem1.getValue();
				if (f != null && f.isDirectory()) {
					List<? extends TreeElement> files = f.getChildren();
					if (files != null) {
						ObservableList<TreeItem<TreeElement>> children = FXCollections.observableArrayList();
						for (TreeElement childFile : files) {
							if (childFile.isHidden()) {
								continue;
							}
							children.add((TreeItem<TreeElement>) createNode(childFile));
						}
						return children;
					}
				}
				return FXCollections.emptyObservableList();
			}
		};
	}

	private static void setIcon(TreeElement f, ImageView imageView) {

		final String fileExtension = f.isDirectory()
		                             ? DIRECTORY_TYPE_IDENTIFIER
		                             : FilenameUtils
				                             .getExtension(f.getName());
		Image iconImage = iconCache.get(fileExtension);
		if (iconImage != null) {
			imageView.setImage(iconImage);
		} else {

			Runnable fetchIcon = () -> {
				final javax.swing.JFileChooser fc   = new javax.swing.JFileChooser();
				javax.swing.Icon               icon = fc.getUI().getFileView(fc).getIcon(getFileFromTreeElement(f));

				BufferedImage bufferedImage = new BufferedImage(
						icon.getIconWidth(),
						icon.getIconHeight(),
						BufferedImage.TYPE_INT_ARGB
				);
				icon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);

				Platform.runLater(() -> {
					Image fxImage = SwingFXUtils.toFXImage(
							bufferedImage, null
					                                      );
					imageView.setImage(fxImage);
					iconCache.put(fileExtension, fxImage);
				});

			};

			javax.swing.SwingUtilities.invokeLater(fetchIcon);
		}

	}

	private static File getFileFromTreeElement(TreeElement element) {
		if (element instanceof FileWrapper) {
			return ((FileWrapper) element).getFile();
		}
		return new File(element.getName());
	}

}

package com.b3tuning.b3console.view.utils;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/*
 *  Created on:  May 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
public class BackgroundColorConstants {

	private static final Color COLOR_RED   = Color.color(1.0, 0, 0, 0.10);
	private static final Color COLOR_GREEN = Color.color(0, 1.0, 0, 0.10);
	private static final Color LIGHT_GRAY  = Color.rgb(245, 245, 245, 0.10);

	public static final Background GREEN_BACKGROUND      = new Background(
			new BackgroundFill(COLOR_GREEN, CornerRadii.EMPTY, Insets.EMPTY));
	public static final Background RED_BACKGROUND        = new Background(
			new BackgroundFill(COLOR_RED, CornerRadii.EMPTY, Insets.EMPTY));
	public static final Background LIGHT_GRAY_BACKGROUND = new Background(
			new BackgroundFill(LIGHT_GRAY, CornerRadii.EMPTY, Insets.EMPTY));

}

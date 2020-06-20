/*
 *  Created on:  Jun 08, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  OpenConfigDialog is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.filemanager;

import com.b3tuning.b3console.service.module.ConfigBase;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;

@XSlf4j
public class OpenConfigDialog {

	private final FileManager manager;

	public OpenConfigDialog(FileManager manager) {
		this.manager = manager;
	}

	public ConfigBase openConfigDialog(Stage stage) {
		log.entry();

		ConfigBase  config   = null;
		FileChooser chooser  = new FileChooser();
		File        selected = chooser.showOpenDialog(stage);
		if (selected != null) {
			config = manager.openFile(selected.getAbsolutePath());
		}
		return config;
	}
}

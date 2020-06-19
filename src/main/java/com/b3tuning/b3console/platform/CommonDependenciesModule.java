package com.b3tuning.b3console.platform;

import com.b3tuning.b3console.prefs.UserPreferences;
import com.b3tuning.b3console.properties.AppProperties;
import com.b3tuning.b3console.properties.PropertyLoader;
import com.b3tuning.b3console.service.EntityPatcher;
import com.b3tuning.b3console.service.comms.SerialComms;
import com.b3tuning.b3console.service.edit.EditManager;
import com.b3tuning.b3console.service.files.FileAssembler;
import com.b3tuning.b3console.service.files.filemanager.FileManager;
import com.b3tuning.b3console.service.module.door.DoorConfigAssembler;
import com.b3tuning.b3console.service.module.door.DoorModuleService;
import com.b3tuning.b3console.service.module.shifter.ShifterAssembler;
import com.b3tuning.b3console.service.module.trans.TransConfigAssembler;
import com.b3tuning.b3console.service.settings.SettingsService;
import com.b3tuning.b3console.view.loader.ViewManager;
import com.b3tuning.b3console.view.loader.ViewManagerImpl;
import com.b3tuning.b3console.view.utils.ScrollBarHelper;
import com.b3tuning.b3console.view.utils.SortUtility;
import com.b3tuning.b3console.view.utils.TableViewUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.utils.notifications.NotificationCenter;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.XSlf4j;

import javax.inject.Singleton;

/*
 *  Created on:  Apr 15, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
@Module
public class CommonDependenciesModule {

	@Provides
	NotificationCenter provideNotificationCenter() {
		return MvvmFX.getNotificationCenter();
	}

	@Provides
	ViewManager provideViewManager(NotificationCenter notifications) {
		return new ViewManagerImpl(notifications);
	}

	/****************
	 * * SERVICES * *
	 ****************/
	@Provides
	@Singleton
	EntityPatcher provideEntityPatcher() {
		return new EntityPatcher();
	}

	@Provides
	@Singleton
	SettingsService provideSettingsService(UserPreferences preferences) {
		return new SettingsService(preferences);
	}

	@Provides
	@Singleton
	DoorModuleService provideDoorModuleService(DoorConfigAssembler assembler, SerialComms comms) {
		return new DoorModuleService(assembler, comms);
	}

	@Provides
	@Singleton
	FileChooser provideFileChooser() {
		return new FileChooser();
	}

	@Provides
	@Singleton
	ObjectMapper provideObjectMapper() {
		return new ObjectMapper();
	}

	@Provides
	@Singleton
	SerialComms provideSerialComms() {
		return new SerialComms();
	}

	@Provides
	@Singleton
	FileManager provideFileManager(UserPreferences preferences, NotificationCenter notifications, FileChooser chooser) {
		return new FileManager(preferences, notifications, chooser);
	}

	@Provides
	@Singleton
	EditManager provideEditManager(NotificationCenter notificationCenter, UserPreferences preferences) {
		return new EditManager(notificationCenter, preferences);
	}

	/******************
	 * * ASSEMBLERS * *
	 ******************/
	@Provides
	@Singleton
	FileAssembler provideFileAssembler() {
		return new FileAssembler();
	}

	@Provides
	@Singleton
	DoorConfigAssembler provideDoorConfigAssembler() {
		return new DoorConfigAssembler();
	}

	@Provides
	@Singleton
	ShifterAssembler provideShifterAssembler() {
		return new ShifterAssembler();
	}

	@Provides
	@Singleton
	TransConfigAssembler provideTransConfigAssembler() {
		return new TransConfigAssembler();
	}

	/***************
	 * * HELPERS * *
	 ***************/
	@Provides
	@Singleton
	UserPreferences provideUserPreferences(ObjectMapper mapper) {
		return new UserPreferences(mapper);
	}

	@Provides
	@Singleton
	AppProperties provideAppProperties() {
		return new PropertyLoader().initAppProperties();
	}

	@Provides
	@Singleton
	SortUtility provideSortUtility() {
		return new SortUtility();
	}

	@Provides
	@Singleton
	TableViewUtils provideTableViewUtils() {
		return new TableViewUtils();
	}

	@Provides
	@Singleton
	ScrollBarHelper provideScrollBarHelper() {
		return new ScrollBarHelper();
	}

}

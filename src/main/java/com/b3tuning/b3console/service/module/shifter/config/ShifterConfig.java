package com.b3tuning.b3console.service.module.shifter.config;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.CanBusConfig;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ModuleType;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.XSlf4j;

import java.util.ArrayList;
import java.util.List;

import static org.reactfx.EventStreams.changesOf;
import static org.reactfx.EventStreams.nonNullValuesOf;

/*
 *  Created on:  Apr 29, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@XSlf4j
public class ShifterConfig extends EditableEntity implements ConfigBase {

	private final ObjectProperty<ModuleType>          type;
	private final ObjectProperty<CanBusConfig>        canBus;
	private final ObjectProperty<IndicatorConfig>     indicator;
	private final ObjectProperty<MelexisConfig>       melexis;
	private final ListProperty<ShifterPositionConfig> shifterPositions; // 7 total items, PARK, REVERSE, NEUTRAL, DRIVE, DOWN, MANUAL, UP

	public ShifterConfig() {
		this.type             = new SimpleObjectProperty<>();
		this.canBus           = new SimpleObjectProperty<>(new CanBusConfig());
		this.indicator        = new SimpleObjectProperty<>(new IndicatorConfig());
		this.melexis          = new SimpleObjectProperty<>(new MelexisConfig());
		this.shifterPositions = new SimpleListProperty<>(
				FXCollections.observableArrayList(new ShifterPositionConfig()));
		super.trackProperties(this.canBus,
		                      this.melexis,
		                      this.indicator,
		                      this.shifterPositions);
		manage(changesOf(this.shifterPositions.get()).map(v -> true).feedTo(getDirtyStream()));

		track(canBus);
		track(indicator);
		track(melexis);
//		track(shifterPositions);
	}

	private void track(ObjectProperty<? extends EditableEntity> prop) {
		manage(nonNullValuesOf(prop).subscribe(p -> {
			log.entry("Tracking new {}", p);
			super.trackProperties(p.dirtyBinding());
			if (isTrackingChanges()) {
				p.startTrackingChanges();
			}
		}));
	}

	public ShifterConfig(ModuleType type) {
		this();
		this.type.set(type);
	}

	public ShifterConfig(ModuleType type, CanBusConfig canBusConfig, IndicatorConfig indicatorConfig,
	                     MelexisConfig melexisConfig, List<ShifterPositionConfig> positions) {
		this(type);
		this.canBus.set(canBusConfig);
		this.indicator.set(indicatorConfig);
		this.melexis.set(melexisConfig);
		this.shifterPositions.setAll(positions);
	}

	public ShifterConfig clone() {
		return new ShifterConfig(this.getType(),
		                         this.getCanBus(),
		                         this.getIndicator(),
		                         this.getMelexis(),
		                         new ArrayList<ShifterPositionConfig>(this.getShifterPositions()));
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public ModuleType getType() {
		return this.type.get();
	}

	public CanBusConfig getCanBus() {
		return this.canBus.get();
	}

	public IndicatorConfig getIndicator() {
		return this.indicator.get();
	}

	public MelexisConfig getMelexis() {
		return this.melexis.get();
	}

	public List<ShifterPositionConfig> getShifterPositions() {
		return this.shifterPositions.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setType(ModuleType type) {
		this.type.set(type);
	}

	public void setCanBus(CanBusConfig config) {
		this.canBus.set(config);
	}

	public void setIndicator(IndicatorConfig config) {
		this.indicator.set(config);
	}

	public void setMelexis(MelexisConfig config) {
		this.melexis.set(config);
	}

	public void setShifterPositions(List<ShifterPositionConfig> configs) {
		this.shifterPositions.get().setAll(configs);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<ModuleType> typeProperty() {
		return this.type;
	}

	ObjectProperty<CanBusConfig> canBusProperty() {
		return canBus;
	}

	ObjectProperty<IndicatorConfig> indicatorProperty() {
		return indicator;
	}

	ObjectProperty<MelexisConfig> melexisProperty() {
		return melexis;
	}

	ListProperty<ShifterPositionConfig> shifterPositionsProperty() {
		return shifterPositions;
	}

	@Override
	public void dispose() {
		super.dispose();
		if (shifterPositions.get() != null) {
			for (ShifterPositionConfig s : shifterPositions.get()) {
				s.dispose();
			}
		}
	}

}

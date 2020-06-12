package com.b3tuning.b3console.service.module.shifter.config;

import com.b3tuning.b3console.service.module.CanBusConfig;
import com.b3tuning.b3console.service.module.ConfigBase;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.reactfx.EventStreams.changesOf;

/*
 *  Created on:  Apr 29, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ShifterConfig extends ConfigBase implements Serializable {
	private static final long serialVersionUID = -3770714227800505079L;

	public ObjectProperty<CanBusConfig>        canBus;
	public ObjectProperty<IndicatorConfig>     indicator;
	public ObjectProperty<MelexisConfig>       melexis;
	public ListProperty<ShifterPositionConfig> shifterPositions; // 7 total items, PARK, REVERSE, NEUTRAL, DRIVE, DOWN, MANUAL, UP

	public ShifterConfig() {
		this.canBus           = new SimpleObjectProperty<>();
		this.indicator        = new SimpleObjectProperty<>();
		this.melexis          = new SimpleObjectProperty<>();
		this.shifterPositions = new SimpleListProperty<>(FXCollections.observableArrayList());
		super.trackProperties(this.canBus, this.melexis, this.indicator, this.shifterPositions);
		manage(changesOf(this.shifterPositions.get()).map(v -> true).feedTo(getDirtyStream()));
	}

	public ShifterConfig(CanBusConfig canBusConfig, IndicatorConfig indicatorConfig, MelexisConfig melexisConfig,
	                     List<ShifterPositionConfig> positions) {
		this();
		this.canBus.set(canBusConfig);
		this.indicator.set(indicatorConfig);
		this.melexis.set(melexisConfig);
		this.shifterPositions.setAll(positions);
	}

	public ShifterConfig copy() {
		return new ShifterConfig(this.getCanBus().get(), this.getIndicator().get(), this.getMelexis().get(),
		                         new ArrayList<>(this.getShifterPositions().get()));
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

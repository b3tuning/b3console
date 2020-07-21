package com.b3tuning.b3console.service.module.shifter.config;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.CanBusConfig;
import com.b3tuning.b3console.service.module.ConfigBase;
import com.b3tuning.b3console.service.module.ModuleType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.XSlf4j;

import static com.b3tuning.b3console.service.module.ModuleType.SHIFTER;
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

	private final ObjectProperty<CanBusConfig>          canBus;
	private final ObjectProperty<IndicatorConfig>       indicator;
	private final ObjectProperty<MelexisConfig>         melexis;
	private final ObjectProperty<ShifterPositionConfig> park;
	private final ObjectProperty<ShifterPositionConfig> reverse;
	private final ObjectProperty<ShifterPositionConfig> neutral;
	private final ObjectProperty<ShifterPositionConfig> drive;
	private final ObjectProperty<ShifterPositionConfig> manual;
	private final ObjectProperty<ShifterPositionConfig> up;
	private final ObjectProperty<ShifterPositionConfig> down;

	public ShifterConfig() {
		this.canBus    = new SimpleObjectProperty<>(new CanBusConfig());
		this.indicator = new SimpleObjectProperty<>(new IndicatorConfig());
		this.melexis   = new SimpleObjectProperty<>(new MelexisConfig());
		this.park      = new SimpleObjectProperty<>(new ShifterPositionConfig(1));
		this.reverse   = new SimpleObjectProperty<>(new ShifterPositionConfig(2));
		this.neutral   = new SimpleObjectProperty<>(new ShifterPositionConfig(3));
		this.drive     = new SimpleObjectProperty<>(new ShifterPositionConfig(4));
		this.manual    = new SimpleObjectProperty<>(new ShifterPositionConfig(6));
		this.up        = new SimpleObjectProperty<>(new ShifterPositionConfig(7));
		this.down      = new SimpleObjectProperty<>(new ShifterPositionConfig(5));

		super.trackProperties(this.canBus,
		                      this.melexis,
		                      this.indicator,
		                      this.park,
		                      this.reverse,
		                      this.neutral,
		                      this.drive,
		                      this.manual,
		                      this.up,
		                      this.down);

		track(canBus);
		track(indicator);
		track(melexis);
		track(park);
		track(reverse);
		track(neutral);
		track(drive);
		track(manual);
		track(up);
		track(down);
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

	public ShifterConfig(CanBusConfig canBusConfig, IndicatorConfig indicatorConfig,
	                     MelexisConfig melexisConfig,
	                     ShifterPositionConfig parkConfig,
	                     ShifterPositionConfig reverseConfig,
	                     ShifterPositionConfig neutralConfig,
	                     ShifterPositionConfig driveConfig,
	                     ShifterPositionConfig manualConfig,
	                     ShifterPositionConfig upConfig,
	                     ShifterPositionConfig downConfig) {
		this();
		this.canBus.set(canBusConfig);
		this.indicator.set(indicatorConfig);
		this.melexis.set(melexisConfig);
		this.park.set(parkConfig);
		this.reverse.set(reverseConfig);
		this.neutral.set(neutralConfig);
		this.drive.set(driveConfig);
		this.manual.set(manualConfig);
		this.up.set(upConfig);
		this.down.set(downConfig);
	}

	public ShifterConfig clone() {
		return new ShifterConfig(this.getCanBus(),
		                         this.getIndicator(),
		                         this.getMelexis(),
		                         this.getPark(),
		                         this.getReverse(),
		                         this.getNeutral(),
		                         this.getDrive(),
		                         this.getManual(),
		                         this.getUp(),
		                         this.getDown());
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public ModuleType getType() {
		return SHIFTER;
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

	public ShifterPositionConfig getPark() {
		return this.park.get();
	}

	public ShifterPositionConfig getReverse() {
		return this.reverse.get();
	}

	public ShifterPositionConfig getNeutral() {
		return this.neutral.get();
	}

	public ShifterPositionConfig getDrive() {
		return this.drive.get();
	}

	public ShifterPositionConfig getManual() {
		return this.manual.get();
	}

	public ShifterPositionConfig getUp() {
		return this.up.get();
	}

	public ShifterPositionConfig getDown() {
		return this.down.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setCanBus(CanBusConfig config) {
		this.canBus.set(config);
	}

	public void setIndicator(IndicatorConfig config) {
		this.indicator.set(config);
	}

	public void setMelexis(MelexisConfig config) {
		this.melexis.set(config);
	}

	public void setPark(ShifterPositionConfig config) {
		this.park.set(config);
	}

	public void setReverse(ShifterPositionConfig config) {
		this.reverse.set(config);
	}

	public void setNeutral(ShifterPositionConfig config) {
		this.neutral.set(config);
	}

	public void setDrive(ShifterPositionConfig config) {
		this.drive.set(config);
	}

	public void setManual(ShifterPositionConfig config) {
		this.manual.set(config);
	}

	public void setUp(ShifterPositionConfig config) {
		this.up.set(config);
	}

	public void setDown(ShifterPositionConfig config) {
		this.down.set(config);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<CanBusConfig> canBusProperty() {
		return canBus;
	}

	public ObjectProperty<IndicatorConfig> indicatorProperty() {
		return indicator;
	}

	public ObjectProperty<MelexisConfig> melexisProperty() {
		return melexis;
	}

	public ObjectProperty<ShifterPositionConfig> parkProperty() {
		return park;
	}

	public ObjectProperty<ShifterPositionConfig> reverseProperty() {
		return reverse;
	}

	public ObjectProperty<ShifterPositionConfig> neutralProperty() {
		return neutral;
	}

	public ObjectProperty<ShifterPositionConfig> driveProperty() {
		return drive;
	}

	public ObjectProperty<ShifterPositionConfig> manualProperty() {
		return manual;
	}

	public ObjectProperty<ShifterPositionConfig> upProperty() {
		return up;
	}

	public ObjectProperty<ShifterPositionConfig> downProperty() {
		return down;
	}

	@Override
	public void dispose() {
		super.dispose();
//		if (shifterPositions.get() != null) {
//			for (ShifterPositionConfig s : shifterPositions.get()) {
//				s.dispose();
//			}
//		}
	}

}

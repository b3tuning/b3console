package com.b3tuning.b3console.service.module.shifter;

import com.b3tuning.b3console.service.EditableEntity;
import com.b3tuning.b3console.service.module.CanBusConfig;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
public class ShifterConfig extends EditableEntity implements Serializable {

	private static final long serialVersionUID = 6491927089324353068L;

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

	public com.b3tuning.b3console.service.module.shifter.ShifterConfig copy() {
		return new com.b3tuning.b3console.service.module.shifter.ShifterConfig(this.getCanBusConfig(), this.getIndicatorConfig(), this.getMelexisConfig(),
		                                                                               new ArrayList<>(this.getShifterPositionConfigs()));
	}

	public CanBusConfig getCanBusConfig() {
		return canBus.get();
	}

	public IndicatorConfig getIndicatorConfig() {
		return indicator.get();
	}

	public MelexisConfig getMelexisConfig() {
		return melexis.get();
	}

	public ObservableList<ShifterPositionConfig> getShifterPositionConfigs() {
		return shifterPositions.get();
	}

	public void setCanBusConfig(CanBusConfig value) {
		this.canBus.set(value);
	}

	public void setIndicatorConfig(IndicatorConfig value) {
		this.indicator.set(value);
	}

	public void setMelexisConfig(MelexisConfig value) {
		this.melexis.set(value);
	}

	public void setShifterPositions(List<ShifterPositionConfig> values) {
		this.shifterPositions.get().setAll(values);
	}

	public ObjectProperty<CanBusConfig> canBusConfigProperty() {
		return canBus;
	}

	public ObjectProperty<IndicatorConfig> indicatorConfigProperty() {
		return indicator;
	}

	public ObjectProperty<MelexisConfig> melexisConfigProperty() {
		return melexis;
	}

	public ListProperty<ShifterPositionConfig> shifterPositionConfigListProperty() {
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

	/**
	 * MelexisConfig
	 */
	public static class MelexisConfig extends EditableEntity implements Serializable {
		private static final long serialVersionUID = 1009408752450992602L;

		private IntegerProperty range;

		public MelexisConfig() {
			this.range = new SimpleIntegerProperty();
			super.trackProperties(range);
		}

		public MelexisConfig(int rangeValue) {
			this();
			this.range.set(rangeValue);
		}

		public int getRange() {
			return range.get();
		}

		public void setRange(int value) {
			range.set(value);
		}

		public IntegerProperty rangeProperty() {
			return range;
		}
	}

	/**
	 * IndicatorConfig
	 */
	public static class IndicatorConfig extends EditableEntity implements Serializable {
		private static final long serialVersionUID = 8536974986658985519L;

		private IntegerProperty brightness; // uint_8t - needs mask 0x0FF?
		private IntegerProperty color;      // uint_8t - needs mask 0xFF?
		private IntegerProperty wait;       // uint_8t - needs mask 0xFF?

		public IndicatorConfig() {
			this.brightness = new SimpleIntegerProperty();
			this.color      = new SimpleIntegerProperty();
			this.wait       = new SimpleIntegerProperty();
			super.trackProperties(brightness, color, wait);
		}

		public IndicatorConfig(int brightnessValue, int colorValue, int waitValue) {
			this();
			this.brightness.set(brightnessValue);
			this.color.set(colorValue);
			this.wait.set(waitValue);
		}

		public int getBrightness() {
			return brightness.get();
		}

		public int getColor() {
			return color.get();
		}

		public int getWait() {
			return wait.get();
		}

		public void setBrightness(int value) {
			brightness.set(value);
		}

		public void setColor(int value) {
			color.set(value);
		}

		public void setWait(int value) {
			wait.set(value);
		}

		public IntegerProperty brightnessProperty() {
			return brightness;
		}

		public IntegerProperty colorProperty() {
			return color;
		}

		public IntegerProperty waitProperty() {
			return wait;
		}
	}

	/**
	 * Shifter Position Config
	 */
	public static class ShifterPositionConfig extends EditableEntity implements Serializable {
		private static final long serialVersionUID = -2495890607919657827L;

		private IntegerProperty x1; // int16_t - needs mask?
		private IntegerProperty x2; // int16_t - needs mask?
		private IntegerProperty y1; // int16_t - needs mask?
		private IntegerProperty y2; // int16_t - needs mask?
		private IntegerProperty enumVal;

		public ShifterPositionConfig() {
			this.x1      = new SimpleIntegerProperty();
			this.y1      = new SimpleIntegerProperty();
			this.x2      = new SimpleIntegerProperty();
			this.y2      = new SimpleIntegerProperty();
			this.enumVal = new SimpleIntegerProperty();
			super.trackProperties(x1, y1, x2, y2, enumVal);
		}

		public ShifterPositionConfig(int x1Value, int y1Value, int x2Value, int y2Value, int enumValue) {
			this();
			this.x1.set(x1Value);
			this.y1.set(y1Value);
			this.x2.set(x2Value);
			this.y2.set(y2Value);
			this.enumVal.set(enumValue);
		}

		public int getX1() {
			return x1.get();
		}

		public int getY1() {
			return y1.get();
		}

		public int getX2() {
			return x2.get();
		}

		public int getY2() {
			return y2.get();
		}

		public int getEnumVal() {
			return enumVal.get();
		}

		public void setX1(int value) {
			x1.set(value);
		}

		public void setY1(int value) {
			y1.set(value);
		}

		public void setX2(int value) {
			x2.set(value);
		}

		public void setY2(int value) {
			y2.set(value);
		}

		public void setEnumVal(int value) {
			enumVal.set(value);
		}

		public IntegerProperty x1Property() {
			return x1;
		}

		public IntegerProperty y1Property() {
			return y1;
		}

		public IntegerProperty x2Property() {
			return x2;
		}

		public IntegerProperty y2Property() {
			return y2;
		}

		public IntegerProperty enumValProperty() {
			return enumVal;
		}
	}
}

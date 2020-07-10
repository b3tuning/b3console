/*
 *  Created on:  Jul 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  DoorConfigFlat is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.door.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.XSlf4j;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@XSlf4j
public class DoorConfigFlat extends EditableEntity {

//	private final ObjectProperty<ModuleType> type;

	private final ObjectProperty<Integer> downMax;
	private final ObjectProperty<Integer> downMin;
	private final ObjectProperty<Integer> leftMax;
	private final ObjectProperty<Integer> leftMin;
	private final ObjectProperty<Integer> rightMax;
	private final ObjectProperty<Integer> rightMin;
	private final ObjectProperty<Integer> upMax;
	private final ObjectProperty<Integer> upMin;

	private final ObjectProperty<Integer> driverMax;
	private final ObjectProperty<Integer> driverMin;
	private final ObjectProperty<Integer> foldMax;
	private final ObjectProperty<Integer> foldMin;
	private final ObjectProperty<Integer> passengerMax;
	private final ObjectProperty<Integer> passengerMin;

	private final ObjectProperty<Integer> autoDownMax;
	private final ObjectProperty<Integer> autoDownMin;
	private final ObjectProperty<Integer> autoUpMax;
	private final ObjectProperty<Integer> autoUpMin;
	private final ObjectProperty<Integer> downMaxWin;
	private final ObjectProperty<Integer> downMinWin;
	private final ObjectProperty<Integer> upMaxWin;
	private final ObjectProperty<Integer> upMinWin;

	private final ObjectProperty<Integer> driverWindowMaxCurrent;
	private final ObjectProperty<Integer> passengerWindowMaxCurrent;

	public DoorConfigFlat() {
//		this.type = new SimpleObjectProperty<>();

		this.downMax  = new SimpleObjectProperty<>();
		this.downMin  = new SimpleObjectProperty<>();
		this.leftMax  = new SimpleObjectProperty<>();
		this.leftMin  = new SimpleObjectProperty<>();
		this.rightMax = new SimpleObjectProperty<>();
		this.rightMin = new SimpleObjectProperty<>();
		this.upMax    = new SimpleObjectProperty<>();
		this.upMin    = new SimpleObjectProperty<>();

		this.driverMax    = new SimpleObjectProperty<>();
		this.driverMin    = new SimpleObjectProperty<>();
		this.foldMax      = new SimpleObjectProperty<>();
		this.foldMin      = new SimpleObjectProperty<>();
		this.passengerMax = new SimpleObjectProperty<>();
		this.passengerMin = new SimpleObjectProperty<>();

		this.autoDownMax = new SimpleObjectProperty<>();
		this.autoDownMin = new SimpleObjectProperty<>();
		this.autoUpMax   = new SimpleObjectProperty<>();
		this.autoUpMin   = new SimpleObjectProperty<>();
		this.downMaxWin  = new SimpleObjectProperty<>();
		this.downMinWin  = new SimpleObjectProperty<>();
		this.upMaxWin    = new SimpleObjectProperty<>();
		this.upMinWin    = new SimpleObjectProperty<>();

		this.driverWindowMaxCurrent    = new SimpleObjectProperty<>();
		this.passengerWindowMaxCurrent = new SimpleObjectProperty<>();
		super.trackProperties(this.downMax,
		                      this.downMin,
		                      this.leftMax,
		                      this.leftMin,
		                      this.rightMax,
		                      this.rightMin,
		                      this.upMax,
		                      this.upMin,

		                      this.driverMax,
		                      this.driverMin,
		                      this.foldMax,
		                      this.foldMin,
		                      this.passengerMax,
		                      this.passengerMin,

		                      this.autoDownMax,
		                      this.autoDownMin,
		                      this.autoUpMax,
		                      this.autoUpMin,
		                      this.downMaxWin,
		                      this.downMinWin,
		                      this.upMaxWin,
		                      this.upMinWin,

		                      this.driverWindowMaxCurrent,
		                      this.passengerWindowMaxCurrent);
	}

//	public DoorConfigFlat(ModuleType type) {
//		this();
////		this.type.set(type);
//	}

	public DoorConfigFlat(//ModuleType type,
	                      Integer downMax, Integer downMin, Integer leftMax, Integer leftMin,
	                      Integer rightMax, Integer rightMin, Integer upMax, Integer upMin,
	                      Integer driverMax, Integer driverMin, Integer foldMax, Integer foldMin,
	                      Integer passengerMax, Integer passengerMin,
	                      Integer autoDownMax, Integer autoDownMin, Integer autoUpMax, Integer autoUpMin,
	                      Integer downMaxWin, Integer downMinWin, Integer upMaxWin, Integer upMinWin,
	                      Integer driverMaxCurrent, Integer passMax) {
		this();

		setDownMax(downMax);
		setDownMin(downMin);
		setLeftMax(leftMax);
		setLeftMin(leftMin);
		setRightMax(rightMax);
		setRightMin(rightMin);
		setUpMax(upMax);
		setUpMin(upMin);

		setDriverMax(driverMax);
		setDriverMin(driverMin);
		setFoldMax(foldMax);
		setFoldMin(foldMin);
		setPassengerMax(passengerMax);
		setPassengerMin(passengerMin);

		setAutoDownMax(autoDownMax);
		setAutoDownMin(autoDownMin);
		setAutoUpMax(autoUpMax);
		setAutoUpMin(autoUpMin);
		setDownMaxWin(downMaxWin);
		setDownMinWin(downMinWin);
		setUpMaxWin(upMaxWin);
		setUpMinWin(upMinWin);

		setDriverWindowMaxCurrent(driverMaxCurrent);
		setPassengerWindowMaxCurrent(passMax);
	}

	public DoorConfigFlat clone() {
		return new DoorConfigFlat(//this.getType(),
		                          this.getDownMax(),
		                          this.getDownMin(),
		                          this.getLeftMax(),
		                          this.getLeftMin(),
		                          this.getRightMax(),
		                          this.getRightMin(),
		                          this.getUpMax(),
		                          this.getUpMin(),

		                          this.getDriverMax(),
		                          this.getDriverMin(),
		                          this.getFoldMax(),
		                          this.getFoldMin(),
		                          this.getPassengerMax(),
		                          this.getPassengerMin(),

		                          this.getAutoDownMax(),
		                          this.getAutoDownMin(),
		                          this.getAutoUpMax(),
		                          this.getAutoUpMin(),
		                          this.getDownMaxWin(),
		                          this.getDownMinWin(),
		                          this.getUpMaxWin(),
		                          this.getUpMinWin(),

		                          this.getDriverWindowMaxCurrent(), this.getPassengerWindowMaxCurrent());
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

//	public ModuleType getType() {
//		return this.type.get();
//	}

	public Integer getDownMax() {
		return this.downMax.get();
	}

	public Integer getDownMin() {
		return this.downMin.get();
	}

	public Integer getLeftMax() {
		return this.leftMax.get();
	}

	public Integer getLeftMin() {
		return this.leftMin.get();
	}

	public Integer getRightMax() {
		return this.rightMax.get();
	}

	public Integer getRightMin() {
		return this.rightMin.get();
	}

	public Integer getUpMax() {
		return this.upMax.get();
	}

	public Integer getUpMin() {
		return this.upMin.get();
	}

	public Integer getDriverMax() {
		return this.driverMax.get();
	}

	public Integer getDriverMin() {
		return this.driverMin.get();
	}

	public Integer getFoldMax() {
		return this.foldMax.get();
	}

	public Integer getFoldMin() {
		return this.foldMin.get();
	}

	public Integer getPassengerMax() {
		return this.passengerMax.get();
	}

	public Integer getPassengerMin() {
		return this.passengerMin.get();
	}

	public Integer getAutoDownMax() {
		return this.autoDownMax.get();
	}

	public Integer getAutoDownMin() {
		return this.autoDownMin.get();
	}

	public Integer getAutoUpMax() {
		return this.autoUpMax.get();
	}

	public Integer getAutoUpMin() {
		return this.autoUpMin.get();
	}

	public Integer getDownMaxWin() {
		return this.downMaxWin.get();
	}

	public Integer getDownMinWin() {
		return this.downMinWin.get();
	}

	public Integer getUpMaxWin() {
		return this.upMaxWin.get();
	}

	public Integer getUpMinWin() {
		return this.upMinWin.get();
	}

	public Integer getDriverWindowMaxCurrent() {
		return this.driverWindowMaxCurrent.get();
	}

	public Integer getPassengerWindowMaxCurrent() {
		return this.passengerWindowMaxCurrent.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

//	public void setType(ModuleType type) {
//		this.type.set(type);
//	}

	public void setDownMax(Integer value) {
		this.downMax.set(value);
	}

	public void setDownMin(Integer value) {
		this.downMin.set(value);
	}

	public void setLeftMax(Integer value) {
		this.leftMax.set(value);
	}

	public void setLeftMin(Integer value) {
		this.leftMin.set(value);
	}

	public void setRightMax(Integer value) {
		this.rightMax.set(value);
	}

	public void setRightMin(Integer value) {
		this.rightMin.set(value);
	}

	public void setUpMax(Integer value) {
		this.upMax.set(value);
	}

	public void setUpMin(Integer value) {
		this.upMin.set(value);
	}

	public void setDriverMax(Integer value) {
		this.driverMax.set(value);
	}

	public void setDriverMin(Integer value) {
		this.driverMin.set(value);
	}

	public void setFoldMax(Integer value) {
		this.foldMax.set(value);
	}

	public void setFoldMin(Integer value) {
		this.foldMin.set(value);
	}

	public void setPassengerMax(Integer value) {
		this.passengerMax.set(value);
	}

	public void setPassengerMin(Integer value) {
		this.passengerMin.set(value);
	}

	public void setAutoDownMax(Integer value) {
		this.autoDownMax.set(value);
	}

	public void setAutoDownMin(Integer value) {
		this.autoDownMin.set(value);
	}

	public void setAutoUpMax(Integer value) {
		this.autoUpMax.set(value);
	}

	public void setAutoUpMin(Integer value) {
		this.autoUpMin.set(value);
	}

	public void setDownMaxWin(Integer value) {
		this.downMaxWin.set(value);
	}

	public void setDownMinWin(Integer value) {
		this.downMinWin.set(value);
	}

	public void setUpMaxWin(Integer value) {
		this.upMaxWin.set(value);
	}

	public void setUpMinWin(Integer value) {
		this.upMinWin.set(value);
	}

	public void setDriverWindowMaxCurrent(Integer value) {
		this.driverWindowMaxCurrent.set(value);
	}

	public void setPassengerWindowMaxCurrent(Integer value) {
		this.passengerWindowMaxCurrent.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

//	public ObjectProperty<ModuleType> typeProperty() {
//		return this.type;
//	}

	public ObjectProperty<Integer> downMaxProperty() {
		return this.downMax;
	}

	public ObjectProperty<Integer> downMinProperty() {
		return this.downMin;
	}

	public ObjectProperty<Integer> leftMaxProperty() {
		return this.leftMax;
	}

	public ObjectProperty<Integer> leftMinProperty() {
		return this.leftMin;
	}

	public ObjectProperty<Integer> rightMaxProperty() {
		return this.rightMax;
	}

	public ObjectProperty<Integer> rightMinProperty() {
		return this.rightMin;
	}

	public ObjectProperty<Integer> upMaxProperty() {
		return this.upMax;
	}

	public ObjectProperty<Integer> upMinProperty() {
		return this.upMin;
	}

	public ObjectProperty<Integer> driverMaxProperty() {
		return driverMax;
	}

	public ObjectProperty<Integer> driverMinProperty() {
		return driverMin;
	}

	public ObjectProperty<Integer> foldMaxProperty() {
		return foldMax;
	}

	public ObjectProperty<Integer> foldMinProperty() {
		return foldMin;
	}

	public ObjectProperty<Integer> passengerMaxProperty() {
		return passengerMax;
	}

	public ObjectProperty<Integer> passengerMinProperty() {
		return passengerMin;
	}

	public ObjectProperty<Integer> autoDownMaxProperty() {
		return this.autoDownMax;
	}

	public ObjectProperty<Integer> autoDownMinProperty() {
		return this.autoDownMin;
	}

	public ObjectProperty<Integer> autoUpMaxProperty() {
		return this.autoUpMax;
	}

	public ObjectProperty<Integer> autoUpMinProperty() {
		return this.autoUpMin;
	}

	public ObjectProperty<Integer> downMaxPropertyWin() {
		return this.downMaxWin;
	}

	public ObjectProperty<Integer> downMinPropertyWin() {
		return this.downMinWin;
	}

	public ObjectProperty<Integer> upMaxPropertyWin() {
		return this.upMaxWin;
	}

	public ObjectProperty<Integer> upMinPropertyWin() {
		return this.upMinWin;
	}

	public ObjectProperty<Integer> driverWindowMaxCurrentProperty() {
		return this.driverWindowMaxCurrent;
	}

	public ObjectProperty<Integer> passengerWindowMaxCurrentProperty() {
		return this.passengerWindowMaxCurrent;
	}
}

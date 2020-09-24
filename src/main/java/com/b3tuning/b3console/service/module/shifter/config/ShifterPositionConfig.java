/*
 *  Created on:  Jun 11, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ShifterPositionConfig is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.module.shifter.config;

import com.b3tuning.b3console.service.EditableEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.experimental.Accessors;

/**
 * Shifter Position Config
 */
@Accessors(chain = true)
public class ShifterPositionConfig extends EditableEntity {

	private final ObjectProperty<Integer> x1;
	private final ObjectProperty<Integer> x2;
	private final ObjectProperty<Integer> y1;
	private final ObjectProperty<Integer> y2;
	private final ObjectProperty<Integer> enumVal;

	public ShifterPositionConfig() {
		this.x1      = new SimpleObjectProperty<>();
		this.y1      = new SimpleObjectProperty<>();
		this.x2      = new SimpleObjectProperty<>();
		this.y2      = new SimpleObjectProperty<>();
		this.enumVal = new SimpleObjectProperty<>();
		super.trackProperties(x1, y1, x2, y2, enumVal);
	}

	public ShifterPositionConfig(Integer enumValue) {
		this();
		this.enumVal.set(enumValue);
	}

	public ShifterPositionConfig(Integer x1Value, Integer y1Value, Integer x2Value, Integer y2Value,
	                             Integer enumValue) {
		this(enumValue);
		setX1(x1Value);
		setY1(y1Value);
		setX2(x2Value);
		setY2(y2Value);
	}

//	public ShifterPositionConfig clone() {
//		return new ShifterPositionConfig(getX1(),
//		                                 getY1(),
//		                                 getX2(),
//		                                 getY2(),
//		                                 getEnumVal());
//	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX getters
	/////////////////////////////////////////////////////////////////////////////

	public Integer getX1() {
		return this.x1.get();
	}

	public Integer getX2() {
		return this.x2.get();
	}

	public Integer getY1() {
		return this.y1.get();
	}

	public Integer getY2() {
		return this.y2.get();
	}

	public Integer getEnumVal() {
		return this.enumVal.get();
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX setters
	/////////////////////////////////////////////////////////////////////////////

	public void setX1(Integer value) {
		this.x1.set(value);
	}

	public void setX2(Integer value) {
		this.x2.set(value);
	}

	public void setY1(Integer value) {
		this.y1.set(value);
	}

	public void setY2(Integer value) {
		this.y2.set(value);
	}

	/////////////////////////////////////////////////////////////////////////////
	/// JavaFX properties
	/////////////////////////////////////////////////////////////////////////////

	public ObjectProperty<Integer> x1Property() {
		return this.x1;
	}

	public ObjectProperty<Integer> x2Property() {
		return this.x2;
	}

	public ObjectProperty<Integer> y1Property() {
		return this.y1;
	}

	public ObjectProperty<Integer> y2Property() {
		return this.y2;
	}

	public ObjectProperty<Integer> enumValProperty() {
		return this.enumVal;
	}

}

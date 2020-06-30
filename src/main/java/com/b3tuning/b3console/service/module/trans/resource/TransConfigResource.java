package com.b3tuning.b3console.service.module.trans.resource;

import com.b3tuning.b3console.service.module.CanBusResource;
import com.b3tuning.b3console.service.module.ConfigBaseResource;
import com.b3tuning.b3console.service.module.ModuleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 *  Created on:  May 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransConfigResource extends ConfigBaseResource implements Serializable {
	private static final long serialVersionUID = 1720074716098745388L;

	private CanBusResource  canBus;
	private Ems22AResource  ems22A;
	private Vnh5019Resource vnh5019;
	private PidResource     pid;

	public TransConfigResource setSuperType(ModuleType type) {
		super.setType(type);
		return this;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Ems22AResource implements Serializable {
		private static final long serialVersionUID = -7027758278002629955L;

		private Integer encMax;
		private Integer encMin;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Vnh5019Resource implements Serializable {
		private static final long serialVersionUID = -7928153578540675319L;

		private Integer maxCurrent;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PidResource implements Serializable {
		private static final long serialVersionUID = -6051337241588930804L;

		private Double aggKd;
		private Double aggKi;
		private Double aggKp;
		private Double consKd;
		private Double consKi;
		private Double consKp;
	}
}

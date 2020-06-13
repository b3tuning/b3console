package com.b3tuning.b3console.service.files.filemanager;

import com.b3tuning.b3console.service.module.ModuleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/*
 *  Created on:  May 21, 2020
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
@EqualsAndHashCode(of = {"name", "path", "type"})
public class RecentFile implements Comparable<RecentFile>, Serializable {

	private static final long serialVersionUID = -8768741433061399525L;

	private String     name;
	private String     path;
	private ModuleType type;
	private Long       lastAccessed;

	@Override public int compareTo(@NotNull RecentFile o) {
		if (this == o) {
			return 0;
		}
		long d = this.lastAccessed - o.lastAccessed;
		return ((d == 0)
		        ? 0
		        : ((d < 0)
		           ? -1
		           : 1));
	}
}

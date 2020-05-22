package com.b3tuning.b3console.control.menubar.recent;

import com.b3tuning.b3console.view.settings.SettingsMenuViewModel.ModuleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecentFile implements Comparable<RecentFile> {

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

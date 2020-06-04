package com.b3tuning.b3console.service.files.file;

import com.b3tuning.b3console.service.files.base.ResourceWithLinks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 *  Created on:  Jun 04, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class FileResource extends ResourceWithLinks {

	private UUID   uid;
	private String name;
	private String alias;
	private Long   size;
	private boolean multiple;
	@JsonProperty("relative_path")    private String relativePath;
	@JsonProperty("mime_type")        private String mimeType;
	@JsonProperty("create_at")        private Date   createdAt;
	@JsonProperty("updated_at")       private Date   lastModified;
	@JsonProperty("compression_type") private String compressionType;

	/*******************************
	 *                             *
	 * embedded Object Properties  *
	 *                             *
	 *******************************/
	@JsonProperty("_embedded") private Embeddeds embedded;

	@Data
	static class Embeddeds {
		private List<FileResource> children = Lists.newArrayList();
	}

	@JsonIgnore
	public List<FileResource> getChildren() {
		return embedded != null ? this.embedded.getChildren() : Collections.emptyList();
	}
}
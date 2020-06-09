/*
 *  Created on:  Jun 09, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 *  ServiceUtils is part of b3console
 *
 *  Copyright (C) 2020 B3Tuning, LLC.
 */

package com.b3tuning.b3console.service.files.monitor.service.utils;

import com.b3tuning.b3console.service.files.file.FileResource;
import com.b3tuning.b3console.service.files.file.FilesApi;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import lombok.Value;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

@XSlf4j
public class ServiceUtils {

	@Autowired private FilesApi filesApi;

	@Value("${locationId}") private UUID locationId;

	private Pattern UUID_PATTERN = Pattern.compile("[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}");

	public void processQueuedIndexedFile(Path mountPath, File file, FileIndexResource.Mode mode) {
		buildIndexEvent(mountPath, file, mode).ifPresent(request -> {
			filesApi.indexFiles(request);
			log.trace("Modifying File '{}'", file.getName());
		});
	}

	private Optional<FileIndexRequest> buildIndexEvent(Path mountPath, File file, FileIndexResource.Mode mode) {
		return buildFileResource(file.toPath())
				.flatMap(resource -> parseOrganizationUid(file.toPath())
						         .map(orgUid -> {
							         long              indexStartTime = System.currentTimeMillis();
							         Set<FileResource> resources      = Sets.newHashSet(resource);
							         FileIndexResource indexResource  = buildIndexResource(resources, mode, indexStartTime);

							         return new FileIndexRequest(indexResource, mountPath.toString(), locationId, orgUid);
						         })
				        );
	}

	public FileIndexResource buildIndexResource(Set<FileResource> files, FileIndexResource.Mode mode, long indexStartTime) {
		return new FileIndexResource(files, mode, indexStartTime);
	}

	public Optional<FileResource> buildFileResource(Path path) {
		Preconditions.checkArgument(path != null, "path not provided");

		// check if file exits
		if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			log.info("File {} does does not exist!", path);
			return Optional.empty();
		}

		try {
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			return Optional.of(
					new FileResource(path.getFileName().toString(),
					                 path.toFile().length(),
					                 path.getParent().toString(),
					                 locationId,
					                 new Date(attr.creationTime().toMillis()),
					                 new Date(attr.lastModifiedTime().toMillis())));
		} catch (IOException e) {
			log.error("Unable to get file attributes `{}`", path);
			return Optional.empty();
		}
	}

	/**
	 * Parses organization uid from the imposed directory structure '/files/{organizationId}/../path'
	 *
	 * @param path -
	 * @return organization uid
	 */
	public Optional<UUID> parseOrganizationUid(Path path) {
		log.entry(path);
		Preconditions.checkArgument(path!=null, "path not provided");

		Path absolutePath = path.toAbsolutePath();
		return StreamSupport.stream(absolutePath.spliterator(), false)
		                    .map(p -> p.toString())
		                    .filter(str -> UUID_PATTERN.matcher(str).matches())
		                    .map(str -> {
			                    UUID orgUid = UUID.fromString(str);
			                    log.trace("parsed orgUid {} for file {}", orgUid, path.getFileName());
			                    return orgUid;
		                    })
		                    .findFirst();
	}

}

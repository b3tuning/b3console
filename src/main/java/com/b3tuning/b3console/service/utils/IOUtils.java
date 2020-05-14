package com.b3tuning.b3console.service.utils;

import lombok.extern.slf4j.XSlf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 *  Created on:  Apr 23, 2020
 *      Author: James Hildebrand
 *              of B3Tuning
 *              b3tuning@gmail.com
 *
 * Copyright (C) 2020 B3Tuning, LLC.
 */
@XSlf4j
public class IOUtils {
//	public static final int DEFAULT_BUFFER_SIZE = 1 * 1024 * 1024;

//	public static final int TAR_BLOCK_SIZE = 1048576; // default is 10240
//	public static final int TAR_RECORD_SIZE = 16384;  // default is 512, increasing record size helps when streaming as the tar output stream sends a record at a time

//	private static void ensureFileExists(File file) throws IOException {
//		if (!file.exists()) {
//			throw new FileNotFoundException(file.toString());
//		}
//	}

//	public static void ensureDirectory(File directory) throws IOException {
//		directory.mkdirs();
//		if (!directory.exists()) {
//			throw new IOException("Failed to create directory: `" + directory + "`");
//		}
//		if (!directory.isDirectory()) {
//			throw new IOException("Not a directory: `" + directory + "`");
//		}
//	}

//	public static long copyFile(File file, OutputStream out) throws IOException {
//		try (FileInputStream fis = new FileInputStream(file)) {
//			return copy(fis, out, file.length());
//		}
//	}

//	public static long copyFile(File file, OutputStream out, byte[] buffer) throws IOException {
//		try (FileInputStream fis = new FileInputStream(file)) {
//			return copy(fis, out, 0, file.length(), buffer);
//		}
//	}

//	public static long copyFile(InputStream in, File file) throws IOException {
//		try (FileOutputStream fos = new FileOutputStream(file)) {
//			return copy(in, fos);
//		}
//	}

//	public static long copyFile(InputStream in, File file, byte[] buffer) throws IOException {
//		try (FileOutputStream fos = new FileOutputStream(file)) {
//			return copy(in, fos, 0, -1, buffer);
//		}
//	}

//	public static long copyFile(InputStream in, File file, long length) throws IOException {
//		try (FileOutputStream fos = new FileOutputStream(file)) {
//			return copy(in, fos, length);
//		}
//	}

//	public static long copy(InputStream in, OutputStream out) throws IOException {
//		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
//		return copy(in, out, buffer);
//	}

//	public static long copy(InputStream in, OutputStream out, long length) throws IOException {
//		int bufferSize = (int) Math.min(DEFAULT_BUFFER_SIZE, length);
//		byte[] buffer = new byte[bufferSize];
//		return copy(in, out, 0, length, buffer);
//	}

//	public static long copy(InputStream in, OutputStream out, byte[] buffer) throws IOException {
//		return copy(in, out, 0, -1, buffer);
//	}

//	public static long copy(InputStream in, OutputStream out, long offset, long length, byte[] buffer)
//	throws IOException {
//		return org.apache.commons.io.IOUtils.copyLarge(in, out, offset, length, buffer);
//	}

//	public List<FileEntity> getAllEntries(File root) throws IOException {
//		return getAllEntries(root, true);
//	}

//	public List<FileEntity> getAllEntries(File root, boolean includeDirs) throws IOException {
//		return getAllEntries(root, includeDirs, true);
//	}

//	public List<FileEntity> getAllEntries(File root, boolean includeDirs, boolean includeHidden)
//	throws IOException {
//		return getEntries(root, null, includeDirs, includeHidden);
//	}

//	public List<FileEntity> getEntries(File root, Set<File> files) throws IOException {
//		return getEntries(root, files, true);
//	}

//	public List<FileEntity> getEntries(File root, Set<File> files, boolean includeDirs) throws IOException {
//		return getEntries(root, files, includeDirs, true);
//	}

//	public List<FileEntity> getEntries(File root, Set<File> files, boolean includeDirs, boolean includeHidden)
//	throws IOException {
//		if (!root.isDirectory()) {
//			if (root.isHidden() && !includeHidden) {
//				return Collections.emptyList();
//			}
//			return Lists.newArrayList(new FileEntity(buildEntity(root)));//root.getAbsolutePath(), root.getName()));
//		}
//
//		if (null == files) {
//			files = list(root, includeDirs, includeHidden);
//		}
//
//		final File finalRoot = root.getAbsoluteFile();
//		return files.stream()
//		            .map(f -> new FileEntity(buildEntity(
//				            f)))//(f.getAbsolutePath()), finalRoot.toPath().relativize(f.toPath()).toString()))
//		            .filter(e -> StringUtils.isNotBlank(e.getRelativePath()))
//		            .collect(Collectors.toList());
//	}

//	@SneakyThrows private FileEntity.Builder buildEntity(File f) {
//		return new FileEntity.Builder().alias(f.getName()).lastModified(new Date(f.lastModified())).name(f.getName())
//		                               .relativePath(f.getCanonicalPath()).size(f.length());
//
//	}
//	public static Set<File> list(Set<File> files) throws IOException {
//		return list(files, true);
//	}
//
//	public static Set<File> list(Set<File> files, boolean includeDirs) throws IOException {
//		return list(files, includeDirs, true);
//	}
//
//	public static Set<File> list(Set<File> files, boolean includeDirs, boolean includeHidden) throws IOException {
//		Set<File> expanded = Sets.newLinkedHashSet();
//		for (File file : files) {
//			expanded.addAll(list(file, includeDirs, includeHidden));
//		}
//		return expanded;
//	}

	// another version of list that rethrows the exception from a stream
//	public static Set<File> list(Set<File> files, boolean includeDirs, boolean includeHidden) throws IOException {
//		return files.stream()
//				.flatMap(FunctionUtils.<File, Stream<? extends File>, IOException>rethrowFunction(f -> list(f, includeDirs, includeHidden).stream()))
//				.collect(Collectors.toCollection(LinkedHashSet::new));
//	}

	public static Set<File> list(File root) throws IOException {
		return list(root, true);
	}

	public static Set<File> list(File root, boolean includeDirs) throws IOException {
		return list(root, includeDirs, true);
	}

	public static Set<File> list(File root, boolean includeDirs, boolean includeHidden) throws IOException {
		return Files.walk(root.toPath())
		            .filter(p -> includeDirs || !Files.isDirectory(p))
		            .filter(p -> includeHidden || !isHidden(p))
		            .map(Path::toFile)
		            .collect(Collectors.toCollection(LinkedHashSet::new));
	}

	public static Set<String> toPaths(Set<File> files) {
		return files.stream()
		            .map(f -> f.isDirectory()
		                      ? f.getAbsolutePath() + "/"
		                      : f.getAbsolutePath())
		            .collect(Collectors.toCollection(LinkedHashSet::new));
	}

	public static boolean isHidden(Path path) {
		try {
			boolean hidden = Files.isHidden(path);
			if (hidden) {
				// make it easier to find hidden file bugs like we had the one with .m3u8
				log.trace("hidden file: `{}`", path);
			}
			return hidden;
		}
		catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

//	public static long calculateTotalSize(List<FileEntry> fileEntries) {
//		return fileEntries.stream()
//		                  .map(e -> new File(e.getFile().getPath()))
//		                  .filter(File::isFile)
//		                  .mapToLong(File::length)
//		                  .sum();
//	}
//
//	public static long read(InputStream in, byte[] bytes, int offset, int length) throws IOException {
//		return org.apache.commons.io.IOUtils.read(in, bytes, offset, length);
//	}
}

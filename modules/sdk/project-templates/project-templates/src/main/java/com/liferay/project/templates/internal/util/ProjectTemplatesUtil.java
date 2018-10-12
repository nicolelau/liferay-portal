/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.project.templates.internal.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Gregory Amerson
 */
public class ProjectTemplatesUtil {

	public static File getArchetypeFile(String artifactId) {
		if (_archetypeFiles.containsKey(artifactId)) {
			return _archetypeFiles.get(artifactId);
		}

		Properties archetypesListProperties = getArchetypes();

		if (archetypesListProperties.containsKey(artifactId)) {
			String version = String.valueOf(
				archetypesListProperties.get(artifactId));

			try {
				String jarName = getArchetypeJarName(artifactId, version);

				InputStream inputStream =
					ProjectTemplatesUtil.class.getResourceAsStream(jarName);

				Path archetypePath = Files.createTempFile(
					"temp-archetype", null);

				Files.copy(
					inputStream, archetypePath,
					StandardCopyOption.REPLACE_EXISTING);

				File archetypeFile = archetypePath.toFile();

				_archetypeFiles.put(artifactId, archetypeFile);

				archetypeFile.deleteOnExit();

				return archetypeFile;
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return null;
	}

	public static File getArchetypeFile(String artifactId, File file)
		throws IOException {

		File returnValue = getArchetypeFile(artifactId);

		if (returnValue == null) {
			try (JarFile jarFile = new JarFile(file)) {
				Enumeration<JarEntry> enumeration = jarFile.entries();

				while (enumeration.hasMoreElements()) {
					JarEntry jarEntry = enumeration.nextElement();

					if (jarEntry.isDirectory()) {
						continue;
					}

					String name = jarEntry.getName();

					if (!name.startsWith(artifactId + "-")) {
						continue;
					}

					Path archetypePath = Files.createTempFile(
						"temp-archetype", null);

					Files.copy(
						jarFile.getInputStream(jarEntry), archetypePath,
						StandardCopyOption.REPLACE_EXISTING);

					File archetypeFile = archetypePath.toFile();

					_archetypeFiles.put(artifactId, archetypeFile);

					archetypeFile.deleteOnExit();

					returnValue = archetypeFile;
				}
			}
		}

		return returnValue;
	}

	public static String getArchetypeJarName(
		String artifactId, String version) {

		String jarName = "/" + artifactId + "-" + version + ".jar";

		return jarName;
	}

	public static Collection<String> getArchetypeJarNames() {
		Properties archetypes = getArchetypes();
		Collection<String> archetypeJarNames = new ArrayList<>();
		Set<String> artifactIds = archetypes.stringPropertyNames();

		for (String artifactId : artifactIds) {
			String version = archetypes.getProperty(artifactId);

			String jarName = getArchetypeJarName(artifactId, version);

			archetypeJarNames.add(jarName);
		}

		return archetypeJarNames;
	}

	public static Properties getArchetypes() {
		if (_archetypes == null) {
			_archetypes = new Properties();

			InputStream archetypesList =
				ProjectTemplatesUtil.class.getResourceAsStream(
					"/project-template-jar-versions.properties");

			try {
				_archetypes.load(archetypesList);
			}
			catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		return _archetypes;
	}

	public static String getArchetypeVersion(String artifactId) {
		Properties archetypesListProperties = getArchetypes();

		return archetypesListProperties.getProperty(artifactId);
	}

	public static String getTemplateName(String name) {
		String projectTemplatesString = "project.templates.";

		int projectTemplatesEndIndex =
			name.indexOf(projectTemplatesString) +
				projectTemplatesString.length();

		String templateName = name.substring(
			projectTemplatesEndIndex, name.lastIndexOf('-'));

		templateName = templateName.replace('.', '-');

		return templateName;
	}

	private static final Map<String, File> _archetypeFiles = new HashMap<>();
	private static Properties _archetypes;

}
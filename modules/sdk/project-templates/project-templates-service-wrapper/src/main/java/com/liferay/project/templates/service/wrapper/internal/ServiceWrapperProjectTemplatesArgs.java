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

package com.liferay.project.templates.service.wrapper.internal;

import com.beust.jcommander.Parameter;

import com.liferay.project.templates.extensions.ProjectTemplatesArgsExt;

/**
 * @author Gregory Amerson
 */
public class ServiceWrapperProjectTemplatesArgs
	implements ProjectTemplatesArgsExt {

	public String getService() {
		return _service;
	}

	@Override
	public String getTemplateName() {
		return "service-wrapper";
	}

	public void setService(String service) {
		_service = service;
	}

	@Parameter(
		description = "Provide the name of the service to be implemented.",
		names = "--service", required = true
	)
	private String _service;

}
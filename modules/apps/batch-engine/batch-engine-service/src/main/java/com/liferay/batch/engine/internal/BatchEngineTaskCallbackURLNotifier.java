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

package com.liferay.batch.engine.internal;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.batch.engine.model.BatchEngineTask;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collections;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Ivica Cardic
 */
public class BatchEngineTaskCallbackURLNotifier {

	public void notify(BatchEngineTask batchEngineTask) {
		if (Validator.isBlank(batchEngineTask.getCallbackURL())) {
			return;
		}

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		try (CloseableHttpClient httpClient = httpClientBuilder.build()) {
			StringEntity requestEntity = new StringEntity(
				_OBJECT_MAPPER.writeValueAsString(
					Collections.singletonMap(
						batchEngineTask.getBatchEngineTaskId(),
						batchEngineTask.getExecuteStatus())),
				ContentType.APPLICATION_JSON);

			HttpPost postMethod = new HttpPost(
				batchEngineTask.getCallbackURL());

			postMethod.setEntity(requestEntity);

			httpClient.execute(postMethod);
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}

	private static final ObjectMapper _OBJECT_MAPPER = new ObjectMapper();

	private static final Log _log = LogFactoryUtil.getLog(
		BatchEngineTaskCallbackURLNotifier.class);

}
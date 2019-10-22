/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {cleanup, render, waitForElement} from '@testing-library/react';
import React from 'react';

import PerformanceByStepCard from '../../../../src/main/resources/META-INF/resources/js/components/process-metrics/performance-by-step/PerformanceByStepCard.es';
import {MockRouter} from '../../../mock/MockRouter.es';

const clientMock = {
	get: jest.fn().mockResolvedValue({data: {}})
};

describe('The performance by step body component should', () => {
	let getByTestId;

	afterEach(cleanup);

	beforeEach(() => {
		clientMock.get.mockRejectedValue(new Error('request-error'));

		const renderResult = render(
			<MockRouter client={clientMock}>
				<PerformanceByStepCard processId={123456} />
			</MockRouter>
		);

		getByTestId = renderResult.getByTestId;
	});

	test('Show a error view after return an error', async () => {
		const error = await waitForElement(() => getByTestId('error'));

		expect(error.innerHTML).toBe(
			'there-was-a-problem-retrieving-data-please-try-reloading-the-page'
		);
	});
});

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

import React from 'react';

import InstanceListItem from './InstanceListItem.es';

const InstanceListTable = ({items}) => {
	return (
		<div className="table-responsive">
			<table
				className="show-quick-actions-on-hover table table-fixed table-heading-nowrap table-hover table-list"
				style={{minWidth: '64rem'}}
			>
				<thead>
					<tr>
						<th style={{width: '4%'}} />

						<th className="table-head-title" style={{width: '8%'}}>
							{Liferay.Language.get('id')}
						</th>

						<th
							className="table-cell-expand table-head-title"
							style={{width: '22%'}}
						>
							{Liferay.Language.get('item-subject')}
						</th>

						<th className="table-head-title" style={{width: '20%'}}>
							{Liferay.Language.get('process-step')}
						</th>

						<th className="table-head-title" style={{width: '14%'}}>
							{Liferay.Language.get('assignee')}
						</th>

						<th className="table-head-title" style={{width: '14%'}}>
							{Liferay.Language.get('created-by')}
						</th>

						<th
							className="pr-4 table-head-title text-right"
							style={{width: '18%'}}
						>
							{Liferay.Language.get('creation-date')}
						</th>
					</tr>
				</thead>

				<tbody>
					{items.map((item, index) => (
						<InstanceListItem {...item} key={index} />
					))}
				</tbody>
			</table>
		</div>
	);
};

export default InstanceListTable;

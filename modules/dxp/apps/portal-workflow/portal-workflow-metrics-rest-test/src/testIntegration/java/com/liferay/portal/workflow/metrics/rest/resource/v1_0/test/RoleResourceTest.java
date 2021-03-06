/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.rest.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.search.document.DocumentBuilderFactory;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Process;
import com.liferay.portal.workflow.metrics.rest.client.dto.v1_0.Role;
import com.liferay.portal.workflow.metrics.rest.resource.v1_0.test.helper.WorkflowMetricsRESTTestHelper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * @author Rafael Praxedes
 */
@RunWith(Arquillian.class)
public class RoleResourceTest extends BaseRoleResourceTestCase {

	@BeforeClass
	public static void setUpClass() throws Exception {
		BaseRoleResourceTestCase.setUpClass();

		_workflowMetricsRESTTestHelper = new WorkflowMetricsRESTTestHelper(
			_documentBuilderFactory, _queries, _searchEngineAdapter);
	}

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_process = _workflowMetricsRESTTestHelper.addProcess(
			testGroup.getCompanyId());
	}

	@Override
	protected Role randomRole() throws Exception {
		com.liferay.portal.kernel.model.Role role1 = _addRole(
			RoleConstants.TYPE_SITE);

		return new Role() {
			{
				id = role1.getRoleId();
				name = role1.getName();
			}
		};
	}

	@Override
	protected Role testGetProcessRolesPage_addRole(Long processId, Role role)
		throws Exception {

		User user = UserTestUtil.addUser();

		com.liferay.portal.kernel.model.Role serviceBuilderRole =
			_roleLocalService.getRole(
				testCompany.getCompanyId(), RoleConstants.USER);

		_userLocalService.deleteRoleUser(
			serviceBuilderRole.getRoleId(), user.getUserId());

		_userLocalService.addRoleUser(role.getId(), user);

		_workflowMetricsRESTTestHelper.addTask(
			user.getUserId(), testGroup.getCompanyId(), processId);

		return role;
	}

	@Override
	protected Long testGetProcessRolesPage_getProcessId() throws Exception {
		return _process.getId();
	}

	private com.liferay.portal.kernel.model.Role _addRole(int roleType)
		throws Exception {

		com.liferay.portal.kernel.model.Role role = RoleTestUtil.addRole(
			roleType);

		_roles.add(role);

		return role;
	}

	@Inject
	private static DocumentBuilderFactory _documentBuilderFactory;

	@Inject
	private static Queries _queries;

	@Inject(blocking = false, filter = "search.engine.impl=Elasticsearch")
	private static SearchEngineAdapter _searchEngineAdapter;

	private static WorkflowMetricsRESTTestHelper _workflowMetricsRESTTestHelper;

	private Process _process;

	@Inject
	private RoleLocalService _roleLocalService;

	@DeleteAfterTestRun
	private final List<com.liferay.portal.kernel.model.Role> _roles =
		new ArrayList<>();

	@Inject
	private UserLocalService _userLocalService;

}
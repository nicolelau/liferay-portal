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

package com.liferay.account.service.base;

import com.liferay.account.model.AccountEntryUserRel;
import com.liferay.account.service.AccountEntryUserRelLocalService;
import com.liferay.account.service.persistence.AccountEntryUserRelPK;
import com.liferay.account.service.persistence.AccountEntryUserRelPersistence;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the account entry user rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.account.service.impl.AccountEntryUserRelLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.account.service.impl.AccountEntryUserRelLocalServiceImpl
 * @generated
 */
public abstract class AccountEntryUserRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AccountEntryUserRelLocalService, AopService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AccountEntryUserRelLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.account.service.AccountEntryUserRelLocalServiceUtil</code>.
	 */

	/**
	 * Adds the account entry user rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryUserRel the account entry user rel
	 * @return the account entry user rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AccountEntryUserRel addAccountEntryUserRel(
		AccountEntryUserRel accountEntryUserRel) {

		accountEntryUserRel.setNew(true);

		return accountEntryUserRelPersistence.update(accountEntryUserRel);
	}

	/**
	 * Creates a new account entry user rel with the primary key. Does not add the account entry user rel to the database.
	 *
	 * @param accountEntryUserRelPK the primary key for the new account entry user rel
	 * @return the new account entry user rel
	 */
	@Override
	@Transactional(enabled = false)
	public AccountEntryUserRel createAccountEntryUserRel(
		AccountEntryUserRelPK accountEntryUserRelPK) {

		return accountEntryUserRelPersistence.create(accountEntryUserRelPK);
	}

	/**
	 * Deletes the account entry user rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryUserRelPK the primary key of the account entry user rel
	 * @return the account entry user rel that was removed
	 * @throws PortalException if a account entry user rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AccountEntryUserRel deleteAccountEntryUserRel(
			AccountEntryUserRelPK accountEntryUserRelPK)
		throws PortalException {

		return accountEntryUserRelPersistence.remove(accountEntryUserRelPK);
	}

	/**
	 * Deletes the account entry user rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryUserRel the account entry user rel
	 * @return the account entry user rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AccountEntryUserRel deleteAccountEntryUserRel(
		AccountEntryUserRel accountEntryUserRel) {

		return accountEntryUserRelPersistence.remove(accountEntryUserRel);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AccountEntryUserRel.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return accountEntryUserRelPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.account.model.impl.AccountEntryUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return accountEntryUserRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.account.model.impl.AccountEntryUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return accountEntryUserRelPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return accountEntryUserRelPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return accountEntryUserRelPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AccountEntryUserRel fetchAccountEntryUserRel(
		AccountEntryUserRelPK accountEntryUserRelPK) {

		return accountEntryUserRelPersistence.fetchByPrimaryKey(
			accountEntryUserRelPK);
	}

	/**
	 * Returns the account entry user rel with the primary key.
	 *
	 * @param accountEntryUserRelPK the primary key of the account entry user rel
	 * @return the account entry user rel
	 * @throws PortalException if a account entry user rel with the primary key could not be found
	 */
	@Override
	public AccountEntryUserRel getAccountEntryUserRel(
			AccountEntryUserRelPK accountEntryUserRelPK)
		throws PortalException {

		return accountEntryUserRelPersistence.findByPrimaryKey(
			accountEntryUserRelPK);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			accountEntryUserRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AccountEntryUserRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.accountEntryUserRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			accountEntryUserRelLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			AccountEntryUserRel.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.accountEntryUserRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			accountEntryUserRelLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AccountEntryUserRel.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.accountEntryUserRelId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return accountEntryUserRelLocalService.deleteAccountEntryUserRel(
			(AccountEntryUserRel)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return accountEntryUserRelPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the account entry user rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.account.model.impl.AccountEntryUserRelModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entry user rels
	 * @param end the upper bound of the range of account entry user rels (not inclusive)
	 * @return the range of account entry user rels
	 */
	@Override
	public List<AccountEntryUserRel> getAccountEntryUserRels(
		int start, int end) {

		return accountEntryUserRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of account entry user rels.
	 *
	 * @return the number of account entry user rels
	 */
	@Override
	public int getAccountEntryUserRelsCount() {
		return accountEntryUserRelPersistence.countAll();
	}

	/**
	 * Updates the account entry user rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryUserRel the account entry user rel
	 * @return the account entry user rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AccountEntryUserRel updateAccountEntryUserRel(
		AccountEntryUserRel accountEntryUserRel) {

		return accountEntryUserRelPersistence.update(accountEntryUserRel);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AccountEntryUserRelLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		accountEntryUserRelLocalService =
			(AccountEntryUserRelLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AccountEntryUserRelLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AccountEntryUserRel.class;
	}

	protected String getModelClassName() {
		return AccountEntryUserRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				accountEntryUserRelPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected AccountEntryUserRelLocalService accountEntryUserRelLocalService;

	@Reference
	protected AccountEntryUserRelPersistence accountEntryUserRelPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}
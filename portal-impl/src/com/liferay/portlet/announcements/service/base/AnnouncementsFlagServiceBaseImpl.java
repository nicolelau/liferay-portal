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

package com.liferay.portlet.announcements.service.base;

import com.liferay.announcements.kernel.model.AnnouncementsFlag;
import com.liferay.announcements.kernel.service.AnnouncementsFlagService;
import com.liferay.announcements.kernel.service.persistence.AnnouncementsFlagPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the announcements flag remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.announcements.service.impl.AnnouncementsFlagServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.announcements.service.impl.AnnouncementsFlagServiceImpl
 * @generated
 */
public abstract class AnnouncementsFlagServiceBaseImpl
	extends BaseServiceImpl
	implements AnnouncementsFlagService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AnnouncementsFlagService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.announcements.kernel.service.AnnouncementsFlagServiceUtil</code>.
	 */

	/**
	 * Returns the announcements flag local service.
	 *
	 * @return the announcements flag local service
	 */
	public
		com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService
			getAnnouncementsFlagLocalService() {

		return announcementsFlagLocalService;
	}

	/**
	 * Sets the announcements flag local service.
	 *
	 * @param announcementsFlagLocalService the announcements flag local service
	 */
	public void setAnnouncementsFlagLocalService(
		com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService
			announcementsFlagLocalService) {

		this.announcementsFlagLocalService = announcementsFlagLocalService;
	}

	/**
	 * Returns the announcements flag remote service.
	 *
	 * @return the announcements flag remote service
	 */
	public AnnouncementsFlagService getAnnouncementsFlagService() {
		return announcementsFlagService;
	}

	/**
	 * Sets the announcements flag remote service.
	 *
	 * @param announcementsFlagService the announcements flag remote service
	 */
	public void setAnnouncementsFlagService(
		AnnouncementsFlagService announcementsFlagService) {

		this.announcementsFlagService = announcementsFlagService;
	}

	/**
	 * Returns the announcements flag persistence.
	 *
	 * @return the announcements flag persistence
	 */
	public AnnouncementsFlagPersistence getAnnouncementsFlagPersistence() {
		return announcementsFlagPersistence;
	}

	/**
	 * Sets the announcements flag persistence.
	 *
	 * @param announcementsFlagPersistence the announcements flag persistence
	 */
	public void setAnnouncementsFlagPersistence(
		AnnouncementsFlagPersistence announcementsFlagPersistence) {

		this.announcementsFlagPersistence = announcementsFlagPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AnnouncementsFlagService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AnnouncementsFlag.class;
	}

	protected String getModelClassName() {
		return AnnouncementsFlag.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				announcementsFlagPersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService.class
	)
	protected
		com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService
			announcementsFlagLocalService;

	@BeanReference(type = AnnouncementsFlagService.class)
	protected AnnouncementsFlagService announcementsFlagService;

	@BeanReference(type = AnnouncementsFlagPersistence.class)
	protected AnnouncementsFlagPersistence announcementsFlagPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}
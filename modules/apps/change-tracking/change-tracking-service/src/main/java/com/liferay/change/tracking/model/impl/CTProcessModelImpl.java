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

package com.liferay.change.tracking.model.impl;

import com.liferay.change.tracking.model.CTProcess;
import com.liferay.change.tracking.model.CTProcessModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CTProcess service. Represents a row in the &quot;CTProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CTProcessModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CTProcessImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CTProcessImpl
 * @generated
 */
public class CTProcessModelImpl
	extends BaseModelImpl<CTProcess> implements CTProcessModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ct process model instance should use the <code>CTProcess</code> interface instead.
	 */
	public static final String TABLE_NAME = "CTProcess";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"ctProcessId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"ctCollectionId", Types.BIGINT},
		{"backgroundTaskId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("ctProcessId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ctCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("backgroundTaskId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CTProcess (mvccVersion LONG default 0 not null,ctProcessId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,ctCollectionId LONG,backgroundTaskId LONG)";

	public static final String TABLE_SQL_DROP = "drop table CTProcess";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ctProcess.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CTProcess.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long CTCOLLECTIONID_COLUMN_BITMASK = 2L;

	public static final long CREATEDATE_COLUMN_BITMASK = 4L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public CTProcessModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ctProcessId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCtProcessId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ctProcessId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CTProcess.class;
	}

	@Override
	public String getModelClassName() {
		return CTProcess.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CTProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTProcess, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((CTProcess)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CTProcess, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CTProcess, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CTProcess)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CTProcess, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CTProcess, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CTProcess>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CTProcess.class.getClassLoader(), CTProcess.class,
			ModelWrapper.class);

		try {
			Constructor<CTProcess> constructor =
				(Constructor<CTProcess>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<CTProcess, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CTProcess, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CTProcess, Object>>();
		Map<String, BiConsumer<CTProcess, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CTProcess, ?>>();

		attributeGetterFunctions.put("mvccVersion", CTProcess::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<CTProcess, Long>)CTProcess::setMvccVersion);
		attributeGetterFunctions.put("ctProcessId", CTProcess::getCtProcessId);
		attributeSetterBiConsumers.put(
			"ctProcessId",
			(BiConsumer<CTProcess, Long>)CTProcess::setCtProcessId);
		attributeGetterFunctions.put("companyId", CTProcess::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId", (BiConsumer<CTProcess, Long>)CTProcess::setCompanyId);
		attributeGetterFunctions.put("userId", CTProcess::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<CTProcess, Long>)CTProcess::setUserId);
		attributeGetterFunctions.put("createDate", CTProcess::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<CTProcess, Date>)CTProcess::setCreateDate);
		attributeGetterFunctions.put(
			"ctCollectionId", CTProcess::getCtCollectionId);
		attributeSetterBiConsumers.put(
			"ctCollectionId",
			(BiConsumer<CTProcess, Long>)CTProcess::setCtCollectionId);
		attributeGetterFunctions.put(
			"backgroundTaskId", CTProcess::getBackgroundTaskId);
		attributeSetterBiConsumers.put(
			"backgroundTaskId",
			(BiConsumer<CTProcess, Long>)CTProcess::setBackgroundTaskId);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getCtProcessId() {
		return _ctProcessId;
	}

	@Override
	public void setCtProcessId(long ctProcessId) {
		_ctProcessId = ctProcessId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public long getCtCollectionId() {
		return _ctCollectionId;
	}

	@Override
	public void setCtCollectionId(long ctCollectionId) {
		_columnBitmask |= CTCOLLECTIONID_COLUMN_BITMASK;

		if (!_setOriginalCtCollectionId) {
			_setOriginalCtCollectionId = true;

			_originalCtCollectionId = _ctCollectionId;
		}

		_ctCollectionId = ctCollectionId;
	}

	public long getOriginalCtCollectionId() {
		return _originalCtCollectionId;
	}

	@Override
	public long getBackgroundTaskId() {
		return _backgroundTaskId;
	}

	@Override
	public void setBackgroundTaskId(long backgroundTaskId) {
		_backgroundTaskId = backgroundTaskId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CTProcess.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public CTProcess toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CTProcess>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CTProcessImpl ctProcessImpl = new CTProcessImpl();

		ctProcessImpl.setMvccVersion(getMvccVersion());
		ctProcessImpl.setCtProcessId(getCtProcessId());
		ctProcessImpl.setCompanyId(getCompanyId());
		ctProcessImpl.setUserId(getUserId());
		ctProcessImpl.setCreateDate(getCreateDate());
		ctProcessImpl.setCtCollectionId(getCtCollectionId());
		ctProcessImpl.setBackgroundTaskId(getBackgroundTaskId());

		ctProcessImpl.resetOriginalValues();

		return ctProcessImpl;
	}

	@Override
	public int compareTo(CTProcess ctProcess) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), ctProcess.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CTProcess)) {
			return false;
		}

		CTProcess ctProcess = (CTProcess)obj;

		long primaryKey = ctProcess.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		CTProcessModelImpl ctProcessModelImpl = this;

		ctProcessModelImpl._originalCompanyId = ctProcessModelImpl._companyId;

		ctProcessModelImpl._setOriginalCompanyId = false;

		ctProcessModelImpl._originalCtCollectionId =
			ctProcessModelImpl._ctCollectionId;

		ctProcessModelImpl._setOriginalCtCollectionId = false;

		ctProcessModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CTProcess> toCacheModel() {
		CTProcessCacheModel ctProcessCacheModel = new CTProcessCacheModel();

		ctProcessCacheModel.mvccVersion = getMvccVersion();

		ctProcessCacheModel.ctProcessId = getCtProcessId();

		ctProcessCacheModel.companyId = getCompanyId();

		ctProcessCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			ctProcessCacheModel.createDate = createDate.getTime();
		}
		else {
			ctProcessCacheModel.createDate = Long.MIN_VALUE;
		}

		ctProcessCacheModel.ctCollectionId = getCtCollectionId();

		ctProcessCacheModel.backgroundTaskId = getBackgroundTaskId();

		return ctProcessCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CTProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTProcess, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CTProcess)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CTProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CTProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CTProcess, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CTProcess)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CTProcess>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _ctProcessId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private Date _createDate;
	private long _ctCollectionId;
	private long _originalCtCollectionId;
	private boolean _setOriginalCtCollectionId;
	private long _backgroundTaskId;
	private long _columnBitmask;
	private CTProcess _escapedModel;

}
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

package com.liferay.friendly.url.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalizationModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the FriendlyURLEntryLocalization service. Represents a row in the &quot;FriendlyURLEntryLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>FriendlyURLEntryLocalizationModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link FriendlyURLEntryLocalizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryLocalizationImpl
 * @generated
 */
@ProviderType
public class FriendlyURLEntryLocalizationModelImpl
	extends BaseModelImpl<FriendlyURLEntryLocalization>
	implements FriendlyURLEntryLocalizationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a friendly url entry localization model instance should use the <code>FriendlyURLEntryLocalization</code> interface instead.
	 */
	public static final String TABLE_NAME = "FriendlyURLEntryLocalization";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT},
		{"friendlyURLEntryLocalizationId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"friendlyURLEntryId", Types.BIGINT},
		{"languageId", Types.VARCHAR}, {"urlTitle", Types.VARCHAR},
		{"groupId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryLocalizationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("friendlyURLEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("languageId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("urlTitle", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table FriendlyURLEntryLocalization (mvccVersion LONG default 0 not null,friendlyURLEntryLocalizationId LONG not null primary key,companyId LONG,friendlyURLEntryId LONG,languageId VARCHAR(75) null,urlTitle VARCHAR(255) null,groupId LONG,classNameId LONG,classPK LONG)";

	public static final String TABLE_SQL_DROP =
		"drop table FriendlyURLEntryLocalization";

	public static final String ORDER_BY_JPQL =
		" ORDER BY friendlyURLEntryLocalization.friendlyURLEntryLocalizationId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY FriendlyURLEntryLocalization.friendlyURLEntryLocalizationId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long FRIENDLYURLENTRYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long LANGUAGEID_COLUMN_BITMASK = 8L;

	public static final long URLTITLE_COLUMN_BITMASK = 16L;

	public static final long FRIENDLYURLENTRYLOCALIZATIONID_COLUMN_BITMASK =
		32L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	public FriendlyURLEntryLocalizationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _friendlyURLEntryLocalizationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setFriendlyURLEntryLocalizationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _friendlyURLEntryLocalizationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return FriendlyURLEntryLocalization.class;
	}

	@Override
	public String getModelClassName() {
		return FriendlyURLEntryLocalization.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<FriendlyURLEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply(
					(FriendlyURLEntryLocalization)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<FriendlyURLEntryLocalization, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<FriendlyURLEntryLocalization, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(FriendlyURLEntryLocalization)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<FriendlyURLEntryLocalization, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<FriendlyURLEntryLocalization, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, FriendlyURLEntryLocalization>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			FriendlyURLEntryLocalization.class.getClassLoader(),
			FriendlyURLEntryLocalization.class, ModelWrapper.class);

		try {
			Constructor<FriendlyURLEntryLocalization> constructor =
				(Constructor<FriendlyURLEntryLocalization>)
					proxyClass.getConstructor(InvocationHandler.class);

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

	private static final Map
		<String, Function<FriendlyURLEntryLocalization, Object>>
			_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<FriendlyURLEntryLocalization, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<FriendlyURLEntryLocalization, Object>>();
		Map<String, BiConsumer<FriendlyURLEntryLocalization, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<FriendlyURLEntryLocalization, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", FriendlyURLEntryLocalization::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setMvccVersion);
		attributeGetterFunctions.put(
			"friendlyURLEntryLocalizationId",
			FriendlyURLEntryLocalization::getFriendlyURLEntryLocalizationId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryLocalizationId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::
					setFriendlyURLEntryLocalizationId);
		attributeGetterFunctions.put(
			"companyId", FriendlyURLEntryLocalization::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setCompanyId);
		attributeGetterFunctions.put(
			"friendlyURLEntryId",
			FriendlyURLEntryLocalization::getFriendlyURLEntryId);
		attributeSetterBiConsumers.put(
			"friendlyURLEntryId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setFriendlyURLEntryId);
		attributeGetterFunctions.put(
			"languageId", FriendlyURLEntryLocalization::getLanguageId);
		attributeSetterBiConsumers.put(
			"languageId",
			(BiConsumer<FriendlyURLEntryLocalization, String>)
				FriendlyURLEntryLocalization::setLanguageId);
		attributeGetterFunctions.put(
			"urlTitle", FriendlyURLEntryLocalization::getUrlTitle);
		attributeSetterBiConsumers.put(
			"urlTitle",
			(BiConsumer<FriendlyURLEntryLocalization, String>)
				FriendlyURLEntryLocalization::setUrlTitle);
		attributeGetterFunctions.put(
			"groupId", FriendlyURLEntryLocalization::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setGroupId);
		attributeGetterFunctions.put(
			"classNameId", FriendlyURLEntryLocalization::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setClassNameId);
		attributeGetterFunctions.put(
			"classPK", FriendlyURLEntryLocalization::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<FriendlyURLEntryLocalization, Long>)
				FriendlyURLEntryLocalization::setClassPK);

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
	public long getFriendlyURLEntryLocalizationId() {
		return _friendlyURLEntryLocalizationId;
	}

	@Override
	public void setFriendlyURLEntryLocalizationId(
		long friendlyURLEntryLocalizationId) {

		_friendlyURLEntryLocalizationId = friendlyURLEntryLocalizationId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public long getFriendlyURLEntryId() {
		return _friendlyURLEntryId;
	}

	@Override
	public void setFriendlyURLEntryId(long friendlyURLEntryId) {
		_columnBitmask |= FRIENDLYURLENTRYID_COLUMN_BITMASK;

		if (!_setOriginalFriendlyURLEntryId) {
			_setOriginalFriendlyURLEntryId = true;

			_originalFriendlyURLEntryId = _friendlyURLEntryId;
		}

		_friendlyURLEntryId = friendlyURLEntryId;
	}

	public long getOriginalFriendlyURLEntryId() {
		return _originalFriendlyURLEntryId;
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return "";
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		_columnBitmask |= LANGUAGEID_COLUMN_BITMASK;

		if (_originalLanguageId == null) {
			_originalLanguageId = _languageId;
		}

		_languageId = languageId;
	}

	public String getOriginalLanguageId() {
		return GetterUtil.getString(_originalLanguageId);
	}

	@Override
	public String getUrlTitle() {
		if (_urlTitle == null) {
			return "";
		}
		else {
			return _urlTitle;
		}
	}

	@Override
	public void setUrlTitle(String urlTitle) {
		_columnBitmask |= URLTITLE_COLUMN_BITMASK;

		if (_originalUrlTitle == null) {
			_originalUrlTitle = _urlTitle;
		}

		_urlTitle = urlTitle;
	}

	public String getOriginalUrlTitle() {
		return GetterUtil.getString(_originalUrlTitle);
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), FriendlyURLEntryLocalization.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public FriendlyURLEntryLocalization toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		FriendlyURLEntryLocalizationImpl friendlyURLEntryLocalizationImpl =
			new FriendlyURLEntryLocalizationImpl();

		friendlyURLEntryLocalizationImpl.setMvccVersion(getMvccVersion());
		friendlyURLEntryLocalizationImpl.setFriendlyURLEntryLocalizationId(
			getFriendlyURLEntryLocalizationId());
		friendlyURLEntryLocalizationImpl.setCompanyId(getCompanyId());
		friendlyURLEntryLocalizationImpl.setFriendlyURLEntryId(
			getFriendlyURLEntryId());
		friendlyURLEntryLocalizationImpl.setLanguageId(getLanguageId());
		friendlyURLEntryLocalizationImpl.setUrlTitle(getUrlTitle());
		friendlyURLEntryLocalizationImpl.setGroupId(getGroupId());
		friendlyURLEntryLocalizationImpl.setClassNameId(getClassNameId());
		friendlyURLEntryLocalizationImpl.setClassPK(getClassPK());

		friendlyURLEntryLocalizationImpl.resetOriginalValues();

		return friendlyURLEntryLocalizationImpl;
	}

	@Override
	public int compareTo(
		FriendlyURLEntryLocalization friendlyURLEntryLocalization) {

		long primaryKey = friendlyURLEntryLocalization.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FriendlyURLEntryLocalization)) {
			return false;
		}

		FriendlyURLEntryLocalization friendlyURLEntryLocalization =
			(FriendlyURLEntryLocalization)obj;

		long primaryKey = friendlyURLEntryLocalization.getPrimaryKey();

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
		FriendlyURLEntryLocalizationModelImpl
			friendlyURLEntryLocalizationModelImpl = this;

		friendlyURLEntryLocalizationModelImpl._originalFriendlyURLEntryId =
			friendlyURLEntryLocalizationModelImpl._friendlyURLEntryId;

		friendlyURLEntryLocalizationModelImpl._setOriginalFriendlyURLEntryId =
			false;

		friendlyURLEntryLocalizationModelImpl._originalLanguageId =
			friendlyURLEntryLocalizationModelImpl._languageId;

		friendlyURLEntryLocalizationModelImpl._originalUrlTitle =
			friendlyURLEntryLocalizationModelImpl._urlTitle;

		friendlyURLEntryLocalizationModelImpl._originalGroupId =
			friendlyURLEntryLocalizationModelImpl._groupId;

		friendlyURLEntryLocalizationModelImpl._setOriginalGroupId = false;

		friendlyURLEntryLocalizationModelImpl._originalClassNameId =
			friendlyURLEntryLocalizationModelImpl._classNameId;

		friendlyURLEntryLocalizationModelImpl._setOriginalClassNameId = false;

		friendlyURLEntryLocalizationModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<FriendlyURLEntryLocalization> toCacheModel() {
		FriendlyURLEntryLocalizationCacheModel
			friendlyURLEntryLocalizationCacheModel =
				new FriendlyURLEntryLocalizationCacheModel();

		friendlyURLEntryLocalizationCacheModel.mvccVersion = getMvccVersion();

		friendlyURLEntryLocalizationCacheModel.friendlyURLEntryLocalizationId =
			getFriendlyURLEntryLocalizationId();

		friendlyURLEntryLocalizationCacheModel.companyId = getCompanyId();

		friendlyURLEntryLocalizationCacheModel.friendlyURLEntryId =
			getFriendlyURLEntryId();

		friendlyURLEntryLocalizationCacheModel.languageId = getLanguageId();

		String languageId = friendlyURLEntryLocalizationCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			friendlyURLEntryLocalizationCacheModel.languageId = null;
		}

		friendlyURLEntryLocalizationCacheModel.urlTitle = getUrlTitle();

		String urlTitle = friendlyURLEntryLocalizationCacheModel.urlTitle;

		if ((urlTitle != null) && (urlTitle.length() == 0)) {
			friendlyURLEntryLocalizationCacheModel.urlTitle = null;
		}

		friendlyURLEntryLocalizationCacheModel.groupId = getGroupId();

		friendlyURLEntryLocalizationCacheModel.classNameId = getClassNameId();

		friendlyURLEntryLocalizationCacheModel.classPK = getClassPK();

		return friendlyURLEntryLocalizationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<FriendlyURLEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply(
					(FriendlyURLEntryLocalization)this));
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
		Map<String, Function<FriendlyURLEntryLocalization, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<FriendlyURLEntryLocalization, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<FriendlyURLEntryLocalization, Object>
				attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply(
					(FriendlyURLEntryLocalization)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function
		<InvocationHandler, FriendlyURLEntryLocalization>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();
	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private long _mvccVersion;
	private long _friendlyURLEntryLocalizationId;
	private long _companyId;
	private long _friendlyURLEntryId;
	private long _originalFriendlyURLEntryId;
	private boolean _setOriginalFriendlyURLEntryId;
	private String _languageId;
	private String _originalLanguageId;
	private String _urlTitle;
	private String _originalUrlTitle;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _columnBitmask;
	private FriendlyURLEntryLocalization _escapedModel;

}
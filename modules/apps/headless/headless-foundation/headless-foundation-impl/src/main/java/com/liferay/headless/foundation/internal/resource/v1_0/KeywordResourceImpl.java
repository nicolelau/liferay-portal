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

package com.liferay.headless.foundation.internal.resource.v1_0;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagService;
import com.liferay.headless.foundation.dto.v1_0.Keyword;
import com.liferay.headless.foundation.internal.dto.v1_0.util.CreatorUtil;
import com.liferay.headless.foundation.internal.odata.entity.v1_0.KeywordEntityModel;
import com.liferay.headless.foundation.resource.v1_0.KeywordResource;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.SearchUtil;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Javier Gamarra
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/keyword.properties",
	scope = ServiceScope.PROTOTYPE, service = KeywordResource.class
)
public class KeywordResourceImpl
	extends BaseKeywordResourceImpl implements EntityModelResource {

	@Override
	public void deleteKeyword(Long keywordId) throws Exception {
		_assetTagService.deleteTag(keywordId);
	}

	@Override
	public Page<Keyword> getContentSpaceKeywordsPage(
			Long contentSpaceId, String search, Filter filter,
			Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			booleanQuery -> {
			},
			filter, AssetTag.class, search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.setCompanyId(contextCompany.getCompanyId());
				searchContext.setGroupIds(new long[] {contentSpaceId});
			},
			document -> _toKeyword(
				_assetTagService.getTag(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))),
			sorts);
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _entityModel;
	}

	@Override
	public Keyword getKeyword(Long keywordId) throws Exception {
		return _toKeyword(_assetTagService.getTag(keywordId));
	}

	@Override
	public Keyword postContentSpaceKeyword(Long contentSpaceId, Keyword keyword)
		throws Exception {

		return _toKeyword(
			_assetTagService.addTag(
				contentSpaceId, keyword.getName(), new ServiceContext()));
	}

	@Override
	public Keyword putKeyword(Long keywordId, Keyword keyword)
		throws Exception {

		return _toKeyword(
			_assetTagService.updateTag(keywordId, keyword.getName(), null));
	}

	private Keyword _toKeyword(AssetTag assetTag) {
		return new Keyword() {
			{
				contentSpaceId = assetTag.getGroupId();
				dateCreated = assetTag.getCreateDate();
				dateModified = assetTag.getModifiedDate();
				id = assetTag.getTagId();
				keywordUsageCount = assetTag.getAssetCount();
				name = assetTag.getName();

				setCreator(
					() -> {
						if (assetTag.getUserId() != 0) {
							return CreatorUtil.toCreator(
								_portal,
								_userLocalService.getUserById(
									assetTag.getUserId()));
						}

						return null;
					});
			}
		};
	}

	private static final EntityModel _entityModel = new KeywordEntityModel();

	@Reference
	private AssetTagService _assetTagService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}
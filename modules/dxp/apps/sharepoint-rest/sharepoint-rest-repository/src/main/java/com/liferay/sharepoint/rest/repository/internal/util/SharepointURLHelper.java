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

package com.liferay.sharepoint.rest.repository.internal.util;

import com.liferay.document.library.repository.external.ExtRepositoryFileEntry;
import com.liferay.document.library.repository.external.ExtRepositoryObject;
import com.liferay.document.library.repository.external.ExtRepositoryObjectType;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Arrays;

/**
 * @author Adolfo Pérez
 */
public class SharepointURLHelper {

	public SharepointURLHelper(String siteAbsoluteURL, String resultsSourceId) {
		_siteAbsoluteURL = siteAbsoluteURL;
		_resultsSourceId = resultsSourceId;
	}

	public String getAddFileURL(String extRepositoryFolderKey, String name) {
		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Files/Add" +
				"(overwrite=false,url='%s')",
			_siteAbsoluteURL, extRepositoryFolderKey, name);
	}

	public String getAddFolderURL(String extRepositoryFolderKey) {
		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Folders",
			_siteAbsoluteURL, extRepositoryFolderKey);
	}

	public String getCancelCheckedOutFileURL(String extRepositoryFileEntryKey) {
		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/UndoCheckOut",
			_siteAbsoluteURL, extRepositoryFileEntryKey);
	}

	public String getCheckInFileURL(
		String extRepositoryFileEntryKey, boolean createMajorVersion,
		String changeLog) {

		int checkInType = 0;

		if (createMajorVersion) {
			checkInType = 1;
		}

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/CheckIn(comment=" +
				"'%s',checkintype=%d)",
			_siteAbsoluteURL, extRepositoryFileEntryKey, changeLog,
			checkInType);
	}

	public String getCheckOutFileURL(String extRepositoryFileEntryKey) {
		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/CheckOut",
			_siteAbsoluteURL, extRepositoryFileEntryKey);
	}

	public String getCopyFileURL(
		String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
		String newTitle) {

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/CopyTo(strnewurl=" +
				"'%s',boverwrite=false)",
			_siteAbsoluteURL, extRepositoryFileEntryKey,
			newExtRepositoryFolderKey + StringPool.SLASH + newTitle);
	}

	public <T extends ExtRepositoryObject> String getDeleteObjectURL(
		ExtRepositoryObjectType<T> extRepositoryObjectType,
		String extRepositoryObjectKey) {

		if (extRepositoryObjectType == ExtRepositoryObjectType.FILE) {
			return String.format(
				"%s/_api/web/GetFileByServerRelativeUrl('%s')",
				_siteAbsoluteURL, extRepositoryObjectKey);
		}

		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')", _siteAbsoluteURL,
			extRepositoryObjectKey);
	}

	public String getFileEntryContentURL(
		ExtRepositoryFileEntry extRepositoryFileEntry) {

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/OpenBinaryStream",
			_siteAbsoluteURL,
			extRepositoryFileEntry.getExtRepositoryModelKey());
	}

	public String getFilesURL(String extRepositoryFolderKey) {
		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Files?$select=%s&" +
				"$expand=%s",
			_siteAbsoluteURL, extRepositoryFolderKey, _FIELDS_SELECTED_FILE,
			_FIELDS_EXPANDED_FILE);
	}

	public String getFileVersionContentURL(
		ExtRepositoryFileEntry extRepositoryFileEntry, String versionId) {

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/Versions(%s)",
			_siteAbsoluteURL, extRepositoryFileEntry.getExtRepositoryModelKey(),
			versionId);
	}

	public String getFileVersionsURL(
		ExtRepositoryFileEntry extRepositoryFileEntry) {

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/Versions",
			_siteAbsoluteURL,
			extRepositoryFileEntry.getExtRepositoryModelKey());
	}

	public String getFoldersURL(String extRepositoryFolderKey) {
		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Folders?$select=" +
				"%s&$expand=%s",
			_siteAbsoluteURL, extRepositoryFolderKey, _FIELDS_SELECTED_FOLDER,
			_FIELDS_EXPANDED_FOLDER);
	}

	public String getMoveFileURL(
		String extRepositoryFileEntryKey, String extRepositoryFolderKey,
		String title) {

		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/MoveTo(newurl='%s'," +
				"flags=1)",
			_siteAbsoluteURL, extRepositoryFileEntryKey,
			extRepositoryFolderKey + StringPool.SLASH + title);
	}

	public <T extends ExtRepositoryObject> String getObjectsCountURL(
		ExtRepositoryObjectType<T> extRepositoryObjectType,
		String extRepositoryFolderKey) {

		if (extRepositoryObjectType == ExtRepositoryObjectType.OBJECT) {
			return String.format(
				"%s/_api/web/GetFolderByServerRelativeUrl('%s')/ItemCount",
				_siteAbsoluteURL, extRepositoryFolderKey);
		}

		if (extRepositoryObjectType == ExtRepositoryObjectType.FOLDER) {
			return String.format(
				"%s/_api/web/GetFolderByServerRelativeUrl('%s')/Folders" +
					"?$select=ItemCount",
				_siteAbsoluteURL, extRepositoryFolderKey);
		}

		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')" +
				"/Files?$select=Level",
			_siteAbsoluteURL, extRepositoryFolderKey);
	}

	public <T extends ExtRepositoryObject> String getObjectURL(
		ExtRepositoryObjectType<T> extRepositoryObjectType,
		String extRepositoryObjectKey) {

		if (extRepositoryObjectType == ExtRepositoryObjectType.FILE) {
			return String.format(
				"%s/_api/web/GetFileByServerRelativeUrl('%s')?$select=%s&" +
					"$expand=%s",
				_siteAbsoluteURL, extRepositoryObjectKey, _FIELDS_SELECTED_FILE,
				_FIELDS_EXPANDED_FILE);
		}

		return String.format(
			"%s/_api/web/GetFolderByServerRelativeUrl('%s')?$select=%s&" +
				"$expand=%s",
			_siteAbsoluteURL, extRepositoryObjectKey, _FIELDS_SELECTED_FOLDER,
			_FIELDS_EXPANDED_FOLDER);
	}

	public String getSearchURL(String queryText, int start, int end) {
		return String.format(
			"%s/_api/search/query?QueryText='%s'&SourceID='%s'&StartRow=%d&" +
				"RowsPerPage=%d",
			_siteAbsoluteURL, HtmlUtil.escapeURL(queryText), _resultsSourceId,
			start, end - start);
	}

	public String getUpdateFileURL(String extRepositoryFileEntryKey) {
		return String.format(
			"%s/_api/web/GetFileByServerRelativeUrl('%s')/$value",
			_siteAbsoluteURL, extRepositoryFileEntryKey);
	}

	private static final String _FIELDS_EXPANDED_FILE = StringUtil.merge(
		Arrays.asList("Author", "CheckedOutByUser", "ListItemAllFields"));

	private static final String _FIELDS_EXPANDED_FOLDER = "ListItemAllFields";

	private static final String _FIELDS_SELECTED_FILE = StringUtil.merge(
		Arrays.asList(
			"Author/Title", "CheckedOutByUser/Title", "Length",
			"ListItemAllFields/EffectiveBasePermissions", "Name",
			"ServerRelativeUrl", "TimeCreated", "TimeLastModified", "Title",
			"UIVersion", "UIVersionLabel"));

	private static final String _FIELDS_SELECTED_FOLDER = StringUtil.merge(
		Arrays.asList(
			"Name", "ListItemAllFields/EffectiveBasePermissions",
			"ServerRelativeUrl", "TimeCreated", "TimeLastModified"));

	private final String _resultsSourceId;
	private final String _siteAbsoluteURL;

}
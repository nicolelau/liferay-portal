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

package com.liferay.portal.search.tuning.rankings.web.internal.index;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.document.GetDocumentRequest;
import com.liferay.portal.search.engine.adapter.document.GetDocumentResponse;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;

import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bryan Engler
 */
@Component(service = RankingIndexReader.class)
public class RankingIndexReaderImpl implements RankingIndexReader {

	@Override
	public Optional<Ranking> fetchByQueryStringOptional(String queryString) {
		if (Validator.isBlank(queryString)) {
			return Optional.empty();
		}

		SearchSearchRequest searchSearchRequest = new SearchSearchRequest();

		searchSearchRequest.setIndexNames(RankingIndexDefinition.INDEX_NAME);
		searchSearchRequest.setQuery(getQueryStringQuery(queryString));
		searchSearchRequest.setSize(1);

		SearchSearchResponse searchSearchResponse =
			_searchEngineAdapter.execute(searchSearchRequest);

		return getFirstRankingOptional(searchSearchResponse);
	}

	@Override
	public Optional<Ranking> fetchOptional(String id) {
		return Optional.ofNullable(
			_getDocument(id)
		).map(
			document -> translate(document, id)
		);
	}

	protected Optional<Ranking> getFirstRankingOptional(
		SearchSearchResponse searchSearchResponse) {

		if (searchSearchResponse.getCount() == 0) {
			return Optional.empty();
		}

		SearchHit searchHit = getFirstSearchHit(searchSearchResponse);

		return fetchOptional(searchHit.getId());
	}

	protected SearchHit getFirstSearchHit(
		SearchSearchResponse searchSearchResponse) {

		SearchHits searchHits = searchSearchResponse.getSearchHits();

		List<SearchHit> searchHitsList = searchHits.getSearchHits();

		return searchHitsList.get(0);
	}

	protected BooleanQuery getQueryStringQuery(String queryString) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addFilterQueryClauses(
			_queries.term(RankingFields.QUERY_STRINGS_KEYWORD, queryString));
		booleanQuery.addMustNotQueryClauses(
			_queries.term(RankingFields.INACTIVE, true));

		return booleanQuery;
	}

	@Reference(unbind = "-")
	protected void setQueries(Queries queries) {
		_queries = queries;
	}

	@Reference(unbind = "-")
	protected void setSearchEngineAdapter(
		SearchEngineAdapter searchEngineAdapter) {

		_searchEngineAdapter = searchEngineAdapter;
	}

	protected Ranking translate(Document document, String id) {
		return _documentToRankingTranslator.translate(document, id);
	}

	private Document _getDocument(String id) {
		GetDocumentRequest getDocumentRequest = new GetDocumentRequest(
			RankingIndexDefinition.INDEX_NAME, id);

		getDocumentRequest.setFetchSource(true);
		getDocumentRequest.setFetchSourceInclude(StringPool.STAR);

		GetDocumentResponse getDocumentResponse = _searchEngineAdapter.execute(
			getDocumentRequest);

		if (getDocumentResponse.isExists()) {
			return getDocumentResponse.getDocument();
		}

		return null;
	}

	@Reference
	private DocumentToRankingTranslator _documentToRankingTranslator;

	private Queries _queries;
	private SearchEngineAdapter _searchEngineAdapter;

}
package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.common.pojo.SearchResult;

public interface SearchDao {
	/**
	 * 在索引库中查询商品后返回对应的带有商品信息和分页条件的对象
	 * @param query 用来设置查询索引库需要的条件
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(SolrQuery query) throws Exception;
}

package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface SearchService {
	/**
	 * 根据条件查询出商品列表并分页
	 * @param queryString 查询条件
	 * @param page 当前页
	 * @param pageSize 每页显示数量
	 * @return
	 * @throws Exception
	 */
	public SearchResult search(String queryString, int page, int pageSize) throws Exception ;
}

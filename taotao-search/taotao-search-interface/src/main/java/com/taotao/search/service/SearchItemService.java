package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
	/**
	 * 想索引库中添加商品数据
	 * @return
	 */
	public TaotaoResult importItemsToIndex();
}

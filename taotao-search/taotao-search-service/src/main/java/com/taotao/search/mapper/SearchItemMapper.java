package com.taotao.search.mapper;

import java.util.List;

import com.taotao.common.pojo.SearchItem;

/**
 * 搜索商品的mapper
 */
public interface SearchItemMapper {
	/**
	 * 获取商品列表
	 * @return
	 */
	public List<SearchItem> getItemList();
	
	/**
	 * 根据商品id获取商品列表
	 * @param itemId
	 * @return
	 */
	public SearchItem getItemById(long itemId);
}

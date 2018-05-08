package com.taotao.service;

import com.taotao.pojo.TbItem;

public interface ItemService {

	/**
	 * 根据商品id获取商品
	 * @param itemId
	 * @return
	 */
	public TbItem getItemById(Long itemId);
}

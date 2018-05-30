package com.taotao.service;

import com.taotao.pojo.TbItemParamItem;

public interface ItemParamItemService {
	/**
	 * 根据商品id获取商品规格
	 * @param itemId 商品ID
	 * @return
	 */
	public TbItemParamItem getParamById(Long itemId);
	/**
	 * 根据商品id获取商品规格
	 * @param itemId 商品ID
	 * @return
	public String getParamById(Long itemId);
	 */
}

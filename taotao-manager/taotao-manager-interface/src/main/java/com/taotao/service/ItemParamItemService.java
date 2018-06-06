package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
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

	/**
	 * 根据商品规格id删除对应的商品规格
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteBatch(List<Long> ids);
}

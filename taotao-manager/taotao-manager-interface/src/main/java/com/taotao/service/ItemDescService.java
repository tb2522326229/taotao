package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;

public interface ItemDescService {

	/**
	 * 根据商品id获取商品详情
	 * @param id
	 * @return
	 */
	public TaotaoResult getItemDesc(Long id);

}

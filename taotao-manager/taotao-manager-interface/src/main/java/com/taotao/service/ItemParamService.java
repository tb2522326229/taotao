package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

public interface ItemParamService {

	/**
	 * 获取商品规格模板
	 * @param pageIndex 当前页码
	 * @param pageSize 每页显示数量
	 * @return
	 */
	public EasyUIDataGridResult getItemParamList(Integer pageIndex, Integer pageSize);

}

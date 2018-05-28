package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItemParamAndCat;

public interface TbItemParamAndCatMapper {

	/**
	 * 查询商品规格模板
	 */
	public List<TbItemParamAndCat> getItemParamList();
}

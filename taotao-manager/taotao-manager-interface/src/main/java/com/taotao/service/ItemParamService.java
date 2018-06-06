package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

	/**
	 * 获取商品规格模板
	 * @param pageIndex 当前页码
	 * @param pageSize 每页显示数量
	 * @return
	 */
	public EasyUIDataGridResult getItemParamList(Integer pageIndex, Integer pageSize);

	/**
	 * 新增商品规格模板
	 * @param itemParam
	 * @return
	 */
	public TaotaoResult saveItemParam(TbItemParam itemParam);

	/**
	 * 根据分类id查询商品模板
	 * @param catId 分类id
	 * @return
	 */
	public TaotaoResult queryItemParam(long catId);

	/**
	 * 根据模板id删除对应的模板信息
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteBatch(List<Long> ids);

}

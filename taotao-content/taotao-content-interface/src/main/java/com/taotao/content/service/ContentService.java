package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * 根据id查询出具体内容并分页
	 * @param id 内容id
	 * @param pageNum 当前页
	 * @param pageSize 每页显示数量
	 * @return
	 */
	public EasyUIDataGridResult getItemList(Long id,Integer pageNum, Integer pageSize);

	/**
	 * 编辑内容
	 * @param content
	 * @return
	 */
	public TaotaoResult editContent(TbContent content);

	/**
	 * 批量删除内容
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteBatchContent(List<Long> ids);

	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	public TaotaoResult addContent(TbContent content);
	
	/**
	 * 根据分类id返回内容集合
	 * @param categoryId 分类id
	 * @return
	 */
	public List<TbContent> getContentList(Long categoryId);

}

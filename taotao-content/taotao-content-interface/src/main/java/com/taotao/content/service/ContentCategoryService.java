package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * 内容分类的操作接口
 */
public interface ContentCategoryService {
	/**
	 * 根据父节点id查询出所有子节点
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategoryList(Long parentId);

	/**
	 * 新增叶子节点并返回该节点
	 * @param parentId 叶子节点的父id
	 * @param name 叶子节点的名字
	 * @return
	 */
	public TaotaoResult addContentCategory(Long parentId, String name);

	/**
	 * 修改节点的名字
	 * @param id 被修改节点id
	 * @param name 修改后的名称
	 * @return
	 */
	public TaotaoResult updateCategory(Long id, String name);

	/**
	 * 根据id删除该节点及其下面的所有子节点
	 * @param id
	 * @return
	 */
	public TaotaoResult deleteCategory(Long id);
}

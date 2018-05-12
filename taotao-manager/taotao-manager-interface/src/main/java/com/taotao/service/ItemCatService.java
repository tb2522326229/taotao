package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

/**
 * 商品分类接口
 */
public interface ItemCatService {
	/**
	 * 根据父节点id查询所有子节点
	 * @param parentId 父节点id
	 * @return 该节点对应的所有子节点
	 */
	public List<EasyUITreeNode> getItemCatList(Long parentId);
}

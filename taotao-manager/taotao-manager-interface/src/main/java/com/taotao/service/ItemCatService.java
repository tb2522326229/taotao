package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.pojo.CatResult;

/**
 * 商品分类接口
 */
public interface ItemCatService {
	/**
	 * 后台manager-web根据父节点id查询所有子节点
	 * @param parentId 父节点id
	 * @return 该节点对应的所有子节点
	 */
	public List<EasyUITreeNode> getItemCatList(Long parentId);

	/**
	 * 前台protal的首页：返回所有商品分类
	 * @return
	 */
	public CatResult getItemCatList2();
}

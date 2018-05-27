package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.CatNode;
import com.taotao.pojo.CatResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;
import com.taotao.utils.JsonUtils;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		//根据父节点id查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//设置parentid
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			//如果节点下有子节点“closed”，如果没有子节点“open”
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}
	
	@Override
	public CatResult getItemCatList2() {
		CatResult catResult = new CatResult();
		//查询分类列表
		catResult.setData(getCatList(0));
		return catResult;

	}
	
	/**
	 * 递归的查询出所有的商品分类列表
	 * @param parentId
	 * @return
	 */
	private List<?> getCatList(long parentId){
		//根据父节点id查询子节点列表
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		//设置parentid
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//返回值list
		List resultList = new ArrayList<>();
		//向list中添加节点
		for (TbItemCat tbItemCat : list) {
			//判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
			//如果是叶子节点
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
			}
		}
		return resultList;

	}
}

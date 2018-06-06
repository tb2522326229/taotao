package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.impl.calendar.CronCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
		// 1、取查询参数id，parentId
		// 2、根据parentId查询tb_content_category，查询子节点列表。
		TbContentCategoryExample example = new TbContentCategoryExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		// 3、得到List<TbContentCategory>
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 4、把列表转换成List<EasyUITreeNode>
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult addContentCategory(Long parentId, String name) {
		// 1、接收两个参数：parentId、name
		// 2、向tb_content_category表中插入数据。
		// a)创建一个TbContentCategory对象
		TbContentCategory tbContentCategory = new TbContentCategory();
		// b)补全TbContentCategory对象的属性
		tbContentCategory.setIsParent(false);
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		tbContentCategory.setSortOrder(1);
		//状态。可选值:1(正常),2(删除)
		tbContentCategory.setStatus(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		// c)向tb_content_category表中插入数据
		contentCategoryMapper.insert(tbContentCategory);
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		// 4、需要主键返回。
		// 5、返回TaotaoResult，其中包装TbContentCategory对象
		return TaotaoResult.ok(tbContentCategory);

	}

	@Override
	public TaotaoResult updateCategory(Long id, String name) {
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		tbContentCategory.setName(name);
		tbContentCategory.setUpdated(new Date());
		contentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
		return TaotaoResult.ok(tbContentCategory);
	}

	@Override
	public TaotaoResult deleteCategory(Long id) {
		TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		if(tbContentCategory.getIsParent()){
			// 获取下面所有子节点
			List<TbContentCategory> childrenNodeList = this.getChildrenNodeList(id);
			// 删除所有子节点
			for (TbContentCategory childrenNode : childrenNodeList) {
				deleteCategory(childrenNode.getId());
			}
		}
		
		// 判断被删除节点的父节点下是否还有子节点
		System.out.println(getChildrenNodeList(tbContentCategory.getParentId()).size());
		if(getChildrenNodeList(tbContentCategory.getParentId()).size() == 1){
			// 查询该节点对应的父节点
			TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
			// 将没有子节点的标记为叶子结点
			contentCategory.setIsParent(false);
			contentCategory.setUpdated(new Date());
			// 修改
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andIdEqualTo(contentCategory.getId());
			contentCategoryMapper.updateByExampleSelective(contentCategory, example);
		}
		contentCategoryMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContentCategory> getChildrenNodeList(Long id){
		TbContentCategoryExample example = new TbContentCategoryExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		//执行查询获取所有的子节点
		return contentCategoryMapper.selectByExample(example);
	}

	@Override
	public TbContentCategory getCategoryList(Long id) {
		return contentCategoryMapper.selectByPrimaryKey(id);
		
	}
}

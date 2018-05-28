package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemParamAndCatMapper;
import com.taotao.pojo.TbItemParamAndCat;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService{

	@Autowired
	private TbItemParamAndCatMapper itemParamMapper;

	@Override
	public EasyUIDataGridResult getItemParamList(Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<TbItemParamAndCat> itemList = itemParamMapper.getItemParamList();
		PageInfo<TbItemParamAndCat> pageInfo = new PageInfo<TbItemParamAndCat>(itemList);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(itemList);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
}

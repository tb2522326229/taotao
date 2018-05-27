package com.taotao.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.pojo.CatResult;
import com.taotao.service.ItemCatService;
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = itemCatService.getItemCatList(parentId);
		return list;
	}
	
	/**
	 * 前台页面展示商品分类
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/rest/item/cat/list")
	@ResponseBody
	public Object getItemCatList2(String callback) {
		CatResult catList = itemCatService.getItemCatList2();
		//判断是否为jsonp请求
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catList);
		//设置回调方法
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}

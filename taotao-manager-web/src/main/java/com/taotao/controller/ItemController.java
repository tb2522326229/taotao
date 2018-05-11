package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}
	
	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item,String desc) {
		return itemService.addItem(item, desc);
	}

	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult deleteItem(@RequestParam("ids")List<Long> ids){
		return itemService.batchDelete(ids);
	}
	
	
}

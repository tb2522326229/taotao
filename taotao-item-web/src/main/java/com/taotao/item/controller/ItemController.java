package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemDescService;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemService;
import com.taotao.utils.JsonUtils;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemDescService itemDescService;
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable long itemId ,Model model){
		TbItem tbItem = itemService.getItemById(itemId);
		Item item = new Item(tbItem);
		TbItemDesc itemDesc = itemDescService.getItemDescById(itemId);
		TbItemParamItem itemParam = itemParamItemService.getParamById(itemId);
		System.out.println("controller:" + itemParam.getParamData());
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		model.addAttribute("itemParam", JsonUtils.objectToJson(itemParam));
		return "item";
	}
}

package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@RequestMapping(value = "/item/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String showItem(@PathVariable long itemId, Model model) {
		TbItem tbItem = itemService.getItemById(itemId);
		Item item = new Item(tbItem);
		TbItemDesc itemDesc = itemDescService.getItemDescById(itemId);
		TbItemParamItem itemParam = itemParamItemService.getParamById(itemId);
		if (item != null) {
			model.addAttribute("item", item);
		} else {
			model.addAttribute("item", "该项暂未导入");
		}
		if (itemDesc != null) {
			model.addAttribute("itemDesc", itemDesc);
		} else {
			model.addAttribute("itemDesc", "该项暂未导入");
		}
		if (itemParam != null) {
			model.addAttribute("itemParam", JsonUtils.objectToJson(itemParam));
		} else {
			model.addAttribute("itemParam", "该项暂未导入");
		}
		return "item";
	}
	/**
	 * 	@RequestMapping(value="/item/{itemId}",produces=MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
	public String showItem(@PathVariable long itemId ,Model model){
		TbItem tbItem = itemService.getItemById(itemId);
		Item item = new Item(tbItem);
		TbItemDesc itemDesc = itemDescService.getItemDescById(itemId);
		String itemParam = itemParamItemService.getParamById(itemId);
		if(item != null){
			model.addAttribute("item", item);
		}
		if(itemDesc != null){
			model.addAttribute("itemDesc", itemDesc);
		}
		if(itemParam != null ){
			model.addAttribute("itemParam", itemParam);
		}
		return "item";
	}
	 */
}

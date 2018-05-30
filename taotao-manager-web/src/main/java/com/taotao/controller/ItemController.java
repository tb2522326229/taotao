package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public TbItem getItemById(@PathVariable Long itemId) {
		return itemService.getItemById(itemId);
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		return itemService.getItemList(page, rows);
	}

	@RequestMapping("/item/save")
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc, String itemParams) {
		return itemService.addItem(item, desc, itemParams);
	}

	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult deleteItem(@RequestParam("ids") List<Long> ids) {
		return itemService.deleteBatch(ids);
	}

	@RequestMapping("/rest/item/update")
	@ResponseBody
	public TaotaoResult deleteItem(TbItem item, String desc) {
		return itemService.updateItem(item, desc);
	}

	@RequestMapping("/item/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemById2(Long itemId) {
		return itemService.getItemById2(itemId);
	}

	@RequestMapping("/item/cat")
	public String getItemByCid(@RequestParam("catId") Long cid, @RequestParam(defaultValue = "1") Integer page,
			Model model) {
		TaotaoResult itemByCid = itemService.getItemByCid(cid);
		model.addAttribute("itemList", itemByCid.getData());
		return "search";
	}

}

package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemDescService;

@Controller
public class ItemDescController {
	
	@Autowired
	private ItemDescService itemDescService;
	
	@RequestMapping("/item/desc/{id}")
	@ResponseBody
	public TaotaoResult getDescById(@PathVariable Long id){
		return itemDescService.getItemDesc(id);
	}
}

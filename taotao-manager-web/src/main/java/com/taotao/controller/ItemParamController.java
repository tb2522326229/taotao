package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		return itemParamService.getItemParamList(page, rows);
	}

	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable long cid, String paramData) {
		// 创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		return itemParamService.saveItemParam(itemParam);
	}

	@RequestMapping("/item/param/query/itemcatid/{catId}")
	@ResponseBody
	public TaotaoResult queryItemParam(@PathVariable long catId) {
		return itemParamService.queryItemParam(catId);
	}

}

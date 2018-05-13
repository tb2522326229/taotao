package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(@RequestParam("categoryId")Long id,Integer page, Integer rows){
		return contentService.getItemList(id, page, rows);
	}
	
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public TaotaoResult editContent(TbContent content){
		return contentService.editContent(content);
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteBatchContent(@RequestParam("ids")List<Long> ids){
		return contentService.deleteBatchContent(ids);
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content) {
		TaotaoResult result = contentService.addContent(content);
		return result;
	}

}

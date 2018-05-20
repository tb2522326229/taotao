package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	@RequestMapping("/search")
	public String search(@RequestParam("q")String query,@RequestParam(defaultValue="1")Integer page,Model model) throws Exception{
		//int a = 1/0;
		//调用服务执行查询
		//把查询条件进行转码，解决get乱码问题
		query = new String(query.getBytes("iso8859-1"), "utf-8");
		SearchResult searchResult = searchService.search(query, page, SEARCH_RESULT_ROWS);
		//把结果传递给页面
		model.addAttribute("query", query);
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);

		//返回逻辑视图
		return "search";
	}
}

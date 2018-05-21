package com.taotao.item.controller;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
public class FreeController {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@RequestMapping("/genhtml")
	@ResponseBody
	public String genHtml(){
		try {
			Configuration config = freeMarkerConfigurer.getConfiguration();
			Template template = config.getTemplate("hello.html");
			Map<String ,Object> dataModel = new HashMap<>();
			dataModel.put("name", "阿加");
			dataModel.put("age", 25);
			// 把数据写进引用文件
			Writer out = new FileWriter("free.html");
			template.process(dataModel, out);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
}

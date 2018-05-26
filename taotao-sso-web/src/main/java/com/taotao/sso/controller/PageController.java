package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}
	@RequestMapping("/page/login")
	public String showLogin(String url, Model model) {
		// 用于跳转购物车：没登录就提交订单会重新调到登录页面，成功登录后悔根据登录url后面的?url来跳转到提交订单页面
		model.addAttribute("redirect", url);
		return "login";
	}
}

package com.taotao.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

@Controller
public class OrderCartController {
	@Value("${CART_KEY}")
	private String CART_KEY;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request){
		//用户必须是登录状态
		//取用户id
		TbUser user = (TbUser) request.getAttribute("user");
		//根据用户信息取收货地址列表，使用静态数据。
		//把收货地址列表取出传递给页面
		//从cookie中取购物车商品列表展示到页面
		List<TbItem> cartList = getCartItemList(request);
		request.setAttribute("cartList", cartList);
		return "order-cart";
	}
	/**
	 * 获取cookie中的购物车商品列表
	 * @param request
	 * @return
	 */
	private List<TbItem> getCartItemList(HttpServletRequest request){
		//从cookie中取购物车商品列表
		String json = CookieUtils.getCookieValue(request, CART_KEY, true);
		if(StringUtils.isBlank(json)){
			// 如果购物车中不存在该购物车列表，则返回空的集合
			return new ArrayList<TbItem>();
		}
		return JsonUtils.jsonToList(json, TbItem.class);
	}
	
	@RequestMapping("/order/create")
	public String createOrder(OrderInfo orderInfo,Model model){
		TaotaoResult result = orderService.createOrder(orderInfo);
		//返回逻辑视图
		model.addAttribute("orderId", result.getData().toString());
		model.addAttribute("payment", orderInfo.getPayment());
		//预计送达时间，预计三天后送达
		DateTime dateTime = new DateTime();
		dateTime = dateTime.plusDays(3);
		model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
		return "succcess";
	}
	
}

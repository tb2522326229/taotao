package com.taotao.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

@Controller
public class CartController {
	@Value("${CART_KEY}")
	private String CART_KEY;
	@Value("${CART_EXPIER}")
	private Integer CART_EXPIER;
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/cart/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId,@RequestParam(defaultValue="1")Integer num,HttpServletRequest request, HttpServletResponse response){
		//取购物车商品列表
		List<TbItem> cartItemList = getCartItemList(request);
		// 判断购物车中是否已经存在该商品
		boolean flag = false;
		for (TbItem item : cartItemList) {
			if(item.getId() == itemId.longValue()){// 如果商品已存在于购物车，则数量相加
				item.setNum(item.getNum() + num);
				flag = true;
				break;
			}
		}
		//如果不存在，添加一个新的商品
		if(!flag){
			TbItem item = itemService.getItemById(itemId);
			//设置购买的商品数量
			item.setNum(num);
			//取一张图片
			String image = item.getImage();
			if(StringUtils.isNotBlank(image)){
				String[] images = image.split(",");
				item.setImage(images[0]);
			}
			cartItemList.add(item);
			//把购物车列表写入cookie
			CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartItemList),CART_EXPIER, true);
		}
		return "cartSuccess";
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
	
	@RequestMapping("/cart/cart")
	public String showCartList(HttpServletRequest request){
		List<TbItem> cartList = getCartItemList(request);
		request.setAttribute("cartList", cartList);
		return "cart";
	}
	
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public TaotaoResult updateItemNum(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车列表
		List<TbItem> cartList = getCartItemList(request);
		//查询到对应的商品
		for (TbItem tbItem : cartList) {
			if (tbItem.getId() == itemId.longValue()) {
				//更新商品数量
				tbItem.setNum(num);
				break;
			}
		}
		//把购车列表写入 cookie
		CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartList),
				CART_EXPIER, true);
		//返回成功
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		//从cookie中取购物车列表
		List<TbItem> cartItemList = getCartItemList(request);
		for (TbItem tbItem : cartItemList) {
			if(tbItem.getId() == itemId.longValue()){
				cartItemList.remove(tbItem);
				break;
			}
		}
		//把购车列表写入cookie
		CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartItemList),
				CART_EXPIER, true);
		//重定向到购物车列表页面
		return "redirect:/cart/cart.html";
	}
}

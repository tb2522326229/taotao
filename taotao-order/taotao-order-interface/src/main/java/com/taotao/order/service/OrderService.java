package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

public interface OrderService {

	/**
	 * 创建订单
	 * @param orderInfo 提交订单时需要的属性的pojo
	 * @return
	 */
	TaotaoResult createOrder(OrderInfo orderInfo);
}

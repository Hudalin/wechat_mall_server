package com.jcg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcg.mapper.OrderMapper;
import com.jcg.model.Order;
import com.jcg.model.OrderGoods;
import com.jcg.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<Order> getOrderList(Map<String, Object> param) {
		List<Order> orderList = orderMapper.getOrderList(param);
		if(orderList != null && orderList.size() > 0){
			for (Order order : orderList) {
				List<OrderGoods> goods = orderMapper.getGoodsByOrderId(order.getOrder_id());
				order.setOrderGoodsList(goods);
			}
		}
		return orderList;
	}

}

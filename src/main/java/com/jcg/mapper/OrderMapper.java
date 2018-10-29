package com.jcg.mapper;

import java.util.List;
import java.util.Map;

import com.jcg.model.Order;
import com.jcg.model.OrderGoods;

public interface OrderMapper {

	List<Order> getOrderList(Map<String, Object> param);
	
	List<OrderGoods> getGoodsByOrderId(int order_id);

}

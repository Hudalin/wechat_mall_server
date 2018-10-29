package com.jcg.service;

import java.util.List;
import java.util.Map;

import com.jcg.model.Order;

public interface OrderService {

	List<Order> getOrderList(Map<String, Object> param);

}

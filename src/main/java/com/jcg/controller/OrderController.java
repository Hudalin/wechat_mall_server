package com.jcg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcg.model.Order;
import com.jcg.model.OrderGoods;
import com.jcg.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/**
	 * 获取所有订单列表
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String list(String account,@RequestParam(required=false)String status,HttpServletRequest request){
		JSONObject json = new JSONObject();
		
		if(StringUtils.isEmpty(account)){
			json.put("code", 4);
			json.put("msg", "account can not be empty.");
			return json.toJSONString();
		}
		Map<String, Object> param = new HashMap<>();
		param.put("account", account);
		if(StringUtils.isNoneEmpty(status) && !"-1".equals(status)){
			param.put("order_status", Integer.parseInt(status));
		}
		List<Order> list = orderService.getOrderList(param);
		if(list != null){
			JSONArray orderList = new JSONArray();
			for (Order order : list) {
				JSONObject orderJson = new JSONObject();
				orderJson.put("id", order.getOrder_id());
				orderJson.put("sn", order.getOrder_sn());
				orderJson.put("status", order.getOrder_status());
				orderJson.put("amount", order.getOrder_amount());
				List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
				JSONArray goodsList = new JSONArray();
				for (OrderGoods orderGoods : orderGoodsList) {
					JSONObject goodsJson = new JSONObject();
					goodsJson.put("goods_name", orderGoods.getGoods_name());
					goodsJson.put("goods_price", orderGoods.getGoods_price());
					goodsJson.put("goods_img", orderGoods.getGoods_img());
					goodsJson.put("goods_num", orderGoods.getGoods_num());
					goodsList.add(goodsJson);
				}
				orderJson.put("goodsList", goodsList);
				orderList.add(orderJson);
			}
			json.put("orderList", orderList);
		}
		json.put("code", 0);
		json.put("msg", "success");
		return json.toJSONString();
	}
	
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);
	}
}

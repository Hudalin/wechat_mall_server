package com.jcg.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcg.model.Product;
import com.jcg.model.ProductInfo;
import com.jcg.service.ProductService;
import com.jcg.utils.Constant;

@RestController
@RequestMapping(value="/product")
public class ProductContrller {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 获取热门商品列表
	 * @param request
	 * @param categoryid
	 * @return
	 */
	@RequestMapping(value="/hotList",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String getHotProductList(HttpServletRequest request,@RequestParam(defaultValue = "1")String categoryid){
		Map<String, Object> param = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		try {
			param.put("size", Constant.HOT_PRODUCT_SIZE);
			param.put("categoryid", categoryid);
			List<Product> products = productService.getHotList(param);
			for (Product product : products) {
				DecimalFormat df = new DecimalFormat("#.00");
				String price = df.format(product.getPrice());
				product.setPrice(new BigDecimal(price));
			}
			if(products != null && products.size() > 0){
				jsonObject.put("code", 0);
				jsonObject.put("msg", "success");
				jsonObject.put("data", products);
			}else{
				jsonObject.put("code", 5);
				jsonObject.put("msg", "result is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "system error");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 获取最新商品列表
	 * @param request
	 * @param categoryid
	 * @return
	 */
	@RequestMapping(value="/newList",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String getNewProductList(HttpServletRequest request,@RequestParam(defaultValue = "1")String categoryid){
		Map<String, Object> param = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		try {
			param.put("size", Constant.NEW_PRODUCT_SIZE);
			param.put("categoryid", categoryid);
			List<Product> products = productService.getNewList(param);
			for (Product product : products) {
				DecimalFormat df = new DecimalFormat("#.00");
				String price = df.format(product.getPrice());
				product.setPrice(new BigDecimal(price));
			}
			if(products != null && products.size() > 0){
				jsonObject.put("code", 0);
				jsonObject.put("msg", "success");
				jsonObject.put("data", products);
			}else{
				jsonObject.put("code", 5);
				jsonObject.put("msg", "result is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "system error");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 根据id获取商品详情
	 * @param request
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/productInfo",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String productInfo(HttpServletRequest request,@RequestParam(defaultValue = "1")String pid){
		JSONObject result = new JSONObject();
		try {
			Product product = productService.getProductById(pid);
			JSONObject productJson = new JSONObject();
			if(product != null){
				productJson.put("pid", product.getPid());
				productJson.put("pname", product.getPname());
				productJson.put("imageUrl", product.getImageUrl());
				productJson.put("price", product.getPrice());
				ProductInfo productInfo = product.getProductInfo();
				if(productInfo != null){
					JSONObject productInfoJson = new JSONObject();
					productInfoJson.put("specification", productInfo.getSpecification());
					productInfoJson.put("origin", productInfo.getOrigin());
					productInfoJson.put("sendPlace", productInfo.getSendPlace());
					JSONArray productIntroduce = JSONArray.parseArray(productInfo.getProductIntroduce());
					productInfoJson.put("productIntroduce", productIntroduce);
					productJson.put("productInfo", productInfoJson);
				}
				result.put("code", 0);
				result.put("msg", "success");
				result.put("product", productJson);
			}else{
				result.put("code", 5);
				result.put("msg", "result is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 1);
			result.put("msg", "system error");
		}
		return result.toJSONString();
	}
}

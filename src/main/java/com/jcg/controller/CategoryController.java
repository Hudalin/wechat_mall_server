package com.jcg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcg.model.Category;
import com.jcg.service.CategoryService;

@RestController
@RequestMapping(value="/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/getAllCategory",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String getAllCategoryAndProduct(){
		JSONObject result = new JSONObject();
		JSONArray cateItems = new JSONArray();
		try {
			List<Category> categories = categoryService.getAllCategory();
			if(categories != null && categories.size() > 0){
				for (Category category : categories) {
					JSONObject cate = new JSONObject();
					if(category.getProducts() != null && category.getProducts().size() > 0){
						cate.put("cid", category.getCid());
						cate.put("cname", category.getCname());
						cate.put("products", category.getProducts());
						cate.put("ishaveChild", true);
					}else{
						cate.put("cid", category.getCid());
						cate.put("ishaveChild", false);
						cate.put("cname", category.getCname());
					}
					cateItems.add(cate);
				}
				result.put("code", 0);
				result.put("msg", "success");
				result.put("cateItems", cateItems);
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
	
	@RequestMapping(value="/getAllCategory",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String getAllCategoryName(){
		JSONObject result = new JSONObject();
		try {
			List<Category> categories = categoryService.getAllCategoryName();
			if(categories != null && categories.size() > 0){
				JSONArray cateItems = new JSONArray();
				for (Category category : categories) {
					cateItems.add(category.getCname());
				}
				result.put("code", 0);
				result.put("msg", "success");
				result.put("cateItems", cateItems);
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

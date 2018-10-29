package com.jcg.service;

import java.util.List;
import java.util.Map;

import com.jcg.model.Product;

public interface ProductService {

	List<Product> getHotList(Map<String, Object> param);

	List<Product> getNewList(Map<String, Object> param);

	Product getProductById(String pid);

}

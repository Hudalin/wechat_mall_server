package com.jcg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcg.mapper.ProductMapper;
import com.jcg.model.Product;
import com.jcg.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> getHotList(Map<String, Object> param) {
		return productMapper.getHotList(param);
	}

	@Override
	public List<Product> getNewList(Map<String, Object> param) {
		return productMapper.getNewList(param);
	}

	@Override
	public Product getProductById(String pid) {
		return productMapper.getProductById(pid);
	}

}

package com.jcg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcg.mapper.CategoryMapper;
import com.jcg.model.Category;
import com.jcg.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getAllCategory() {
		return categoryMapper.getAllCategory();
	}

	@Override
	public List<Category> getAllCategoryName() {
		return categoryMapper.getAllCategoryName();
	}

}

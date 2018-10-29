package com.jcg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcg.mapper.AdverMapper;
import com.jcg.model.Adver;
import com.jcg.service.AdverService;

@Service
public class AdverServiceImpl implements AdverService{

	@Autowired
	private AdverMapper adverMapper;
	
	@Override
	public List<Adver> getAdvertByType(Map<String, Object> param) {
		return adverMapper.getAdvertByType(param);
	}

}

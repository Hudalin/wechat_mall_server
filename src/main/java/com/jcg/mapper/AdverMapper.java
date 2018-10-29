package com.jcg.mapper;

import java.util.List;
import java.util.Map;

import com.jcg.model.Adver;

public interface AdverMapper {

	List<Adver> getAdvertByType(Map<String, Object> param);

}

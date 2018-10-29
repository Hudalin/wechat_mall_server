package com.jcg.service;

import java.util.List;
import java.util.Map;

import com.jcg.model.Adver;

public interface AdverService {

	List<Adver> getAdvertByType(Map<String, Object> param);

}

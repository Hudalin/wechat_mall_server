package com.jcg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jcg.model.Adver;
import com.jcg.service.AdverService;
import com.jcg.utils.Constant;

@RestController
@RequestMapping(value="/advert")
public class AdvertController {
	
	@Autowired
	private AdverService adverService;
	
	@RequestMapping(value="/getAdvertByType",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String getAdvertByType(HttpServletRequest request){
		Map<String, Object> param = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		String type = request.getParameter("type");
		
		try {
			if(StringUtils.isEmpty(type)){
				jsonObject.put("code", 4);
				jsonObject.put("msg", "type is null or ''");
				return jsonObject.toJSONString();
			}
			param.put("maxSize", Constant.SOWING_MAP_MAX_SIZE);
			param.put("type", type);
			List<Adver> advers = adverService.getAdvertByType(param);
			if(advers != null && advers.size() > 0){
				jsonObject.put("code", 0);
				jsonObject.put("msg", "success");
				jsonObject.put("data", advers);
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
	
}

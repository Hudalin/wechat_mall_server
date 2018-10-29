package com.jcg.service;

import java.util.Map;

import com.jcg.model.User;

public interface UserService {

	User login(Map<String, Object> param);

	User wechatLogin(Map<String, Object> param);

	User updatePwd(Map<String, Object> param);

}

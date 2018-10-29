package com.jcg.mapper;

import java.util.Map;

import com.jcg.model.User;

public interface UserMapper {

	User findUser(Map<String, Object> param);

	int updateUser(Map<String, Object> param);

	int addUser(Map<String, Object> param);

	void updateUserLoginTime(Map<String, Object> param);

}

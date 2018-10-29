package com.jcg.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcg.mapper.UserMapper;
import com.jcg.model.User;
import com.jcg.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(Map<String, Object> param) {
		User user = userMapper.findUser(param);
		if(user != null){
			userMapper.updateUserLoginTime(param);
		}
		return user;
	}

	@Override
	public User wechatLogin(Map<String, Object> param) {
		User user = userMapper.findUser(param);
		if(user == null){
			userMapper.addUser(param);
		}
		userMapper.updateUserLoginTime(param);
		return user;
	}

	@Override
	public User updatePwd(Map<String, Object> param) {
		User user = userMapper.findUser(param);
		userMapper.updateUser(param);
		return user;
	}

}

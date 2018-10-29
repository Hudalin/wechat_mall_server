package com.jcg.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jcg.model.User;
import com.jcg.service.UserService;
import com.jcg.utils.Api;
import com.jcg.utils.EncryptUtil;
import com.jcg.utils.Util;

/**
 * 和用户相关的类
 * @author darli
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 普通账号密码登录的方法
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String login(String account,String password,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> param = new HashMap<>();
			if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
				jsonObject.put("code", 4);
				jsonObject.put("msg", "missing parameter");
				return jsonObject.toJSONString();
			}
			String type = "1"; // 0 openId,1 account
			param.put("account", account);
			param.put("password", EncryptUtil.MD5Encode(password, "UTF-8"));
			param.put("type", type);
			User user = userService.login(param);
			if(user != null){
				jsonObject.put("code", 0);
				jsonObject.put("msg", "success");
				jsonObject.put("userInfo", user);
				//request.getSession().setAttribute("account", account);
			}else{
				jsonObject.put("code", 408);
				jsonObject.put("msg", "failed");
			}
		} catch (Exception e) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value="/wechatLogin",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String wechatLogin(String code,String nickName,String avatarUrl,String gender,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> param = new HashMap<>();
			//通过code去查询出openid
			if(StringUtils.isEmpty(code)){
				jsonObject.put("code", 4);
				jsonObject.put("msg", "missing parameter");
				return jsonObject.toJSONString();
			}
			Util util = new Util();
			Properties properties = util.readRcErpURL("db.properties");
			String appid = properties.getProperty("appid");
			String secret = properties.getProperty("secret");
			String params = "appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
			String json = Api.sendGetTest("https://api.weixin.qq.com/sns/jscode2session", params);
			JSONObject data = JSONObject.parseObject(json);
			String openid = (String) data.get("openid");
			
			int type = 0; // 0 openId,1 account
			param.put("account", openid);
			param.put("openid", openid);
			param.put("nickName", nickName);
			param.put("avatarUrl", avatarUrl);
			param.put("type", type);
			if(StringUtils.isNotEmpty(gender)){
				param.put("sex", Integer.parseInt(gender));
			}
			User user = userService.wechatLogin(param);
			if(user == null){
				user = new User();
				user.setAccount(openid);
				user.setNickName(nickName);
				user.setAvatarUrl(avatarUrl);
				user.setType(type);
			}
			jsonObject.put("code", 0);
			jsonObject.put("msg", "success");
			jsonObject.put("userInfo", user);
			//request.getSession().setAttribute("account", openid);
		} catch (Exception e) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 修改手机注册用户的密码
	 * @param account
	 * @param oldPwd
	 * @param newPwd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update/Pwd",method=RequestMethod.PUT,produces="text/html;charset=UTF-8")
	public String updatePwd(String account,String oldPwd,String newPwd,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("account", account);
			param.put("password", EncryptUtil.MD5Encode(oldPwd, "UTF-8"));
			param.put("newPwd", EncryptUtil.MD5Encode(newPwd, "UTF-8"));
			User user = userService.updatePwd(param);
			if(user == null){
				jsonObject.put("code", 8);//密码错误
				jsonObject.put("msg", "密码错误");
			}else{
				jsonObject.put("code", 0);
				jsonObject.put("msg", "success");
			}
		} catch (Exception e) {
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 根据姓名创建图片
	 * @param name
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/big_stic",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public void getImageNew(String name,HttpServletRequest request,HttpServletResponse response){
		try {
			if(StringUtils.isEmpty(name)){
				name = "";
			}
			byte[] img = Util.generateImg(name);
			response.setContentType("image/png");
			OutputStream os = response.getOutputStream();
			os.write(img);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

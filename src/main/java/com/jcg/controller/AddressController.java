package com.jcg.controller;

import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jcg.model.Address;
import com.jcg.model.User;
import com.jcg.service.AddressService;
import com.jcg.utils.Util;

/**
 * 
 * 
 * File: AddressController.java
 *
 * Description: 收货地址相关类
 *
 * @author 胡大林 Notes: AddressController.java 2018年10月26日 下午2:30:42 darling
 */
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "/getAllAddressList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getAllOrderList(String account, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {
			List<Address> list = addressService.getAddressListByAcc(account);
			if (list != null && list.size() > 0) {
				for (Address address : list) {
					Util util = new Util();
					Properties properties = util.readRcErpURL("db.properties");
					String imageInterface = properties.getProperty("imageInterface");
					address.setAddress_user_img(imageInterface + "?name=" + address.getAddress_name());
				}
				jsonObject.put("list", list);
			}
			jsonObject.put("code", 0);
			jsonObject.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
		}
		return jsonObject.toJSONString();
	}

	@RequestMapping(value = "/getAddressById", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String getAddressById(int address_id, HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		try {
			Address address = addressService.getAddressById(address_id);
			if (address == null) {
				jsonObject.put("code", 5);
				jsonObject.put("msg", "address_id is error");
			} else {
				jsonObject.put("address", address);
				jsonObject.put("code", 0);
				jsonObject.put("msg", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
		}
		return jsonObject.toJSONString();
	}

	@RequestMapping(value = "/updateAddress", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
	public String updateAddress(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String address_city = request.getParameter("address_city");
		String address_detail = request.getParameter("address_detail");
		String address_district = request.getParameter("address_district");
		String address_id = request.getParameter("address_id");
		String address_name = request.getParameter("address_name");
		String address_phone = request.getParameter("address_phone");
		String address_province = request.getParameter("address_province");

		try {
			if (StringUtils.isEmpty(address_id)) {
				jsonObject.put("code", 4);
				jsonObject.put("msg", "id can not be empty");
				return jsonObject.toJSONString();
			}
			Address address = new Address();
			address.setAddress_city(address_city);
			address.setAddress_detail(address_detail);
			address.setAddress_district(address_district);
			address.setAddress_id(Integer.parseInt(address_id));
			address.setAddress_name(address_name);
			address.setAddress_phone(address_phone);
			address.setAddress_province(address_province);
			addressService.updateAddress(address);
			jsonObject.put("code", 0);
			jsonObject.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
		}
		return jsonObject.toJSONString();
	}

	@RequestMapping(value = "/deleteAddress", method = RequestMethod.DELETE, produces = "text/html;charset=UTF-8")
	public String deleteAddress(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String address_id = request.getParameter("address_id");

		try {
			if (StringUtils.isEmpty(address_id)) {
				jsonObject.put("code", 4);
				jsonObject.put("msg", "id can not be empty");
				return jsonObject.toJSONString();
			}
			addressService.deleteAddressById(Integer.parseInt(address_id));
			jsonObject.put("code", 0);
			jsonObject.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
		}
		return jsonObject.toJSONString();
	}
	
	@RequestMapping(value = "/addAddress", method = RequestMethod.PUT, produces = "text/html;charset=UTF-8")
	public String addAddress(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		String address_city = request.getParameter("address_city");
		String address_detail = request.getParameter("address_detail");
		String address_district = request.getParameter("address_district");
		String address_name = request.getParameter("address_name");
		String address_phone = request.getParameter("address_phone");
		String address_province = request.getParameter("address_province");
		String uid = request.getParameter("uid");
		
		try {
			//非空判断   ..省略
			Address address = new Address();
			address.setAddress_city(address_city);
			address.setAddress_detail(address_detail);
			address.setAddress_district(address_district);
			address.setAddress_name(address_name);
			address.setAddress_phone(address_phone);
			address.setAddress_province(address_province);
			User user = new User();
			user.setUid(Integer.parseInt(uid));
			address.setUser(user);
			addressService.addAddress(address);
			jsonObject.put("code", 0);
			jsonObject.put("msg", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", 1);
			jsonObject.put("msg", "error");
		}
		return jsonObject.toJSONString();
	}
	
}

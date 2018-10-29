package com.jcg.model;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -829184247876049496L;

	private Integer address_id;
	private String address_name;
	private String address_phone;
	private String address_province;
	private String address_city;
	private String address_district;
	private String address_detail;
	private Date create_time;
	private Date update_time;
	private User user;
	
	private String address_user_img;
	
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getAddress_phone() {
		return address_phone;
	}
	public void setAddress_phone(String address_phone) {
		this.address_phone = address_phone;
	}
	public String getAddress_province() {
		return address_province;
	}
	public void setAddress_province(String address_province) {
		this.address_province = address_province;
	}
	public String getAddress_city() {
		return address_city;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public String getAddress_district() {
		return address_district;
	}
	public void setAddress_district(String address_district) {
		this.address_district = address_district;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddress_user_img() {
		return address_user_img;
	}
	public void setAddress_user_img(String address_user_img) {
		this.address_user_img = address_user_img;
	}
	
}

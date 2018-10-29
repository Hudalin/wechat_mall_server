package com.jcg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -3839091987548528374L;

	private int order_id;
	private String order_sn;
	private User user;
	private int order_status;
	private BigDecimal order_amount;
	private Date create_time;
	private Date update_time;
	private Date consign_time;
	private Date end_time;
	private Date close_time;
	private String shipping_name;
	private String shipping_code;
	private List<OrderGoods> orderGoodsList;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderGoods> getOrderGoodsList() {
		return orderGoodsList;
	}
	public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
		this.orderGoodsList = orderGoodsList;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public BigDecimal getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(BigDecimal order_amount) {
		this.order_amount = order_amount;
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
	public Date getConsign_time() {
		return consign_time;
	}
	public void setConsign_time(Date consign_time) {
		this.consign_time = consign_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getClose_time() {
		return close_time;
	}
	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}
	public String getShipping_name() {
		return shipping_name;
	}
	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}
	public String getShipping_code() {
		return shipping_code;
	}
	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}
	
}

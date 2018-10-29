package com.jcg.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderGoods implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 6252234279928926993L;

	private Integer goods_id;
	private Integer order_id;
	private String goods_name;
	private String goods_img;
	private Integer goods_num;
	private BigDecimal goods_price;
	private BigDecimal goods_total_price;
	
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	public BigDecimal getGoods_total_price() {
		return goods_total_price;
	}
	public void setGoods_total_price(BigDecimal goods_total_price) {
		this.goods_total_price = goods_total_price;
	}
	
}

package com.jcg.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商品种类
 * @author darli
 *
 */
public class Category implements Serializable{
	
	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -3220529987299442906L;
	private Integer cid;
	private String cname;
	private List<Product> products;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}

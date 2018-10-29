package com.jcg.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品相关的类
 * @author darli
 *
 */
public class Product implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -156785019401663034L;
	private Integer pid;
	private String pname;//商品名称
	private BigDecimal price;//商品价格
	private String imageUrl;//商品图片路径
	private Date saleTime;//商品上架时间 
	private Integer isHot;//热卖商品    1:热卖 0:正常
	private ProductInfo productInfo;//商品详情
	private Category cagegory;//商品种类
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Date getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public Category getCagegory() {
		return cagegory;
	}
	public void setCagegory(Category cagegory) {
		this.cagegory = cagegory;
	}
	
}

package com.jcg.model;

import java.io.Serializable;

/**
 * 广告相关类
 * @author darli
 *
 */
public class Adver implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 5705641655787347920L;
	
	private Integer id;
	private String aname;//广告名称
	private String imageUrl;//广告图片
	private Integer type;//广告类型 1轮播图 2外接广告 3热门商品推荐
	private Integer pid;//所代表的商品id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	
}

package com.jcg.model;

import java.io.Serializable;

/**
 * 商品详情
 * @author darli
 *
 */
public class ProductInfo implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -1403111207570045343L;
	private Integer id;
	private String specification;//商品规格
	private String origin;//源产地
	private String sendPlace;//发送地
	private UserEvaluate userEvaluate;//用户评价
	private String productIntroduce;//商品图文详情(图片路径数组),json字符串
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getSendPlace() {
		return sendPlace;
	}
	public void setSendPlace(String sendPlace) {
		this.sendPlace = sendPlace;
	}
	public UserEvaluate getUserEvaluate() {
		return userEvaluate;
	}
	public void setUserEvaluate(UserEvaluate userEvaluate) {
		this.userEvaluate = userEvaluate;
	}
	public String getProductIntroduce() {
		return productIntroduce;
	}
	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}
	
}

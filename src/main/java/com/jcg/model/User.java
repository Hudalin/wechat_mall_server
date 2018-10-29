package com.jcg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户相关的类
 * @author darli
 *
 */
public class User implements Serializable{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = -4516966017937888930L;
	
	private Integer uid;
	private String account;//账号
	private String password;//密码
	private String nickName;//用户昵称
	private Integer isExist;//账号是否存在 1:存在 0:被删除       数据字段类型tinyint 只占1字节
	private Date loginTime;//登录时间
	private String openid;//微信登录id
	private String unionid;//微信登录id
	private String phone;//手机号
	private String email;//电子邮箱
	private String avatarUrl;//头像
	private Date birthday;//生日
	private Integer sex;//性别  0:默认 1:男 2:女
	private String address;//地点
	private String describe;//用户描述,级别
	private Integer type;// 账号类型 0:openId,1:account,2:phone
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getIsExist() {
		return isExist;
	}
	public void setIsExist(Integer isExist) {
		this.isExist = isExist;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}

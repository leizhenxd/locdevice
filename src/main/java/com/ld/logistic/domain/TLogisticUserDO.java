package com.ld.logistic.domain;

import java.util.Date;

import com.ld.model.BaseModel;

/**
* 
* @author haisheng.long Thu Mar 22 19:45:46 CST 2018
*/

public class TLogisticUserDO extends BaseModel {

/** 主键 */
private Integer id;

/** 1.普通用户 2.供应商用户 */
private Integer role;

/** uses' real name */
private String realName;

/** phone no */
private String phone;

/** password */
private String password;

/** address to live */
private String normalAddress;

/**  */
private String walletId;

/** register time */
private Date createTime;

private String lat;
private String lng;

/**
* 设置 主键
* @param id
*/
	public void setId(Integer id) {
	this.id = id;
}
/**
* 设置 1.普通用户 2.供应商用户
* @param role
*/
	public void setRole(Integer role) {
	this.role = role;
}
/**
* 设置 uses' real name
* @param realName
*/
	public void setRealName(String realName) {
	this.realName = realName;
}
/**
* 设置 phone no
* @param phone
*/
	public void setPhone(String phone) {
	this.phone = phone;
}
/**
* 设置 password
* @param password
*/
	public void setPassword(String password) {
	this.password = password;
}
/**
* 设置 address to live
* @param normalAddress
*/
	public void setNormalAddress(String normalAddress) {
	this.normalAddress = normalAddress;
}
/**
* 设置 
* @param walletId
*/
	public void setWalletId(String walletId) {
	this.walletId = walletId;
}
/**
* 设置 register time
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 获取 主键
* @return id
*/
	public Integer getId() {
	return id;
}
/**
* 获取 1.普通用户 2.供应商用户
* @return role
*/
	public Integer getRole() {
	return role;
}
/**
* 获取 uses' real name
* @return realName
*/
	public String getRealName() {
	return realName;
}
/**
* 获取 phone no
* @return phone
*/
	public String getPhone() {
	return phone;
}
/**
* 获取 password
* @return password
*/
	public String getPassword() {
	return password;
}
/**
* 获取 address to live
* @return normalAddress
*/
	public String getNormalAddress() {
	return normalAddress;
}
/**
* 获取 
* @return walletId
*/
	public String getWalletId() {
	return walletId;
}
/**
* 获取 register time
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}
public String getLat() {
	return lat;
}
public void setLat(String lat) {
	this.lat = lat;
}
public String getLng() {
	return lng;
}
public void setLng(String lng) {
	this.lng = lng;
}
}
package com.ld.logistic.domain;

import com.ld.logistic.domain.BaseDO;

/**
* 
* @author haisheng.long Thu Mar 22 19:45:46 CST 2018
*/

public class TLogisticUserExpendDO extends BaseDO {

/** 主键 */
private Integer id;

/** 用户ID */
private Long userId;

/** company's addess */
private String companyAddress;

/**  */
private String lat;

/**  */
private String lng;

/**
* 设置 主键
* @param id
*/
	public void setId(Integer id) {
	this.id = id;
}
/**
* 设置 用户ID
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 company's addess
* @param companyAddress
*/
	public void setCompanyAddress(String companyAddress) {
	this.companyAddress = companyAddress;
}
/**
* 设置 
* @param lat
*/
	public void setLat(String lat) {
	this.lat = lat;
}
/**
* 设置 
* @param lng
*/
	public void setLng(String lng) {
	this.lng = lng;
}
/**
* 获取 主键
* @return id
*/
	public Integer getId() {
	return id;
}
/**
* 获取 用户ID
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 company's addess
* @return companyAddress
*/
	public String getCompanyAddress() {
	return companyAddress;
}
/**
* 获取 
* @return lat
*/
	public String getLat() {
	return lat;
}
/**
* 获取 
* @return lng
*/
	public String getLng() {
	return lng;
}

}
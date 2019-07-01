package com.ld.logistic.domain;

import com.ld.logistic.domain.BaseDO;
import com.ld.model.BaseModel;

import java.util.Date;

/**
* 
* @author haisheng.long Thu Mar 22 19:45:44 CST 2018
*/

public class TLogisticScanRecordDO extends BaseModel {

/** 主键 */
private Long id;

/** user id who scan this parcel */
private Long userId;

/** parcel id */
private Long parcelId;

private Integer status;

/** scan address */
private String address;

/** hash code */
private String hashCode;

/** scan time */
private Date createTime;

/**  */
private String lat;

/**  */
private String lng;

private String code;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 user id who scan this parcel
* @param userId
*/
	public void setUserId(Long userId) {
	this.userId = userId;
}
/**
* 设置 parcel id
* @param parcelId
*/
	public void setParcelId(Long parcelId) {
	this.parcelId = parcelId;
}
/**
* 设置 scan address
* @param address
*/
	public void setAddress(String address) {
	this.address = address;
}
/**
* 设置 hash code
* @param hashCode
*/
	public void setHashCode(String hashCode) {
	this.hashCode = hashCode;
}
/**
* 设置 scan time
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
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
	public Long getId() {
	return id;
}
/**
* 获取 user id who scan this parcel
* @return userId
*/
	public Long getUserId() {
	return userId;
}
/**
* 获取 parcel id
* @return parcelId
*/
	public Long getParcelId() {
	return parcelId;
}
/**
* 获取 scan address
* @return address
*/
	public String getAddress() {
	return address;
}
/**
* 获取 hash code
* @return hashCode
*/
	public String getHashCode() {
	return hashCode;
}
/**
* 获取 scan time
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
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
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}

}
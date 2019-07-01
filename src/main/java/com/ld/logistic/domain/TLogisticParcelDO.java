package com.ld.logistic.domain;

import com.ld.logistic.domain.BaseDO;

import java.util.Date;

/**
* 
* @author haisheng.long Sat Mar 24 14:53:12 CST 2018
*/

public class TLogisticParcelDO extends BaseDO {

/** 主键 */
private Long id;

/** parcel name */
private String name;

/** send time */
private Date createTime;

private Date updateTime;

/**  */
private Integer userId;

/**  */
private String fromLat;

/**  */
private String fromLng;

/**  */
private String toLat;

/**  */
private String toLng;

/** 1.delivering 2.delivered */
private Integer status;

/**  */
private Integer node;

/**  */
private String beginCity;

/**  */
private String endCity;

private String code;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 parcel name
* @param name
*/
	public void setName(String name) {
	this.name = name;
}
/**
* 设置 send time
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 设置 
* @param userId
*/
	public void setUserId(Integer userId) {
	this.userId = userId;
}
/**
* 设置 
* @param fromLat
*/
	public void setFromLat(String fromLat) {
	this.fromLat = fromLat;
}
/**
* 设置 
* @param fromLng
*/
	public void setFromLng(String fromLng) {
	this.fromLng = fromLng;
}
/**
* 设置 
* @param toLat
*/
	public void setToLat(String toLat) {
	this.toLat = toLat;
}
/**
* 设置 
* @param toLng
*/
	public void setToLng(String toLng) {
	this.toLng = toLng;
}
/**
* 设置 1.delivering 2.delivered
* @param status
*/
	public void setStatus(Integer status) {
	this.status = status;
}
/**
* 设置 
* @param node
*/
	public void setNode(Integer node) {
	this.node = node;
}
/**
* 设置 
* @param beginCity
*/
	public void setBeginCity(String beginCity) {
	this.beginCity = beginCity;
}
/**
* 设置 
* @param endCity
*/
	public void setEndCity(String endCity) {
	this.endCity = endCity;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 parcel name
* @return name
*/
	public String getName() {
	return name;
}
/**
* 获取 send time
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}
/**
* 获取 
* @return userId
*/
	public Integer getUserId() {
	return userId;
}
/**
* 获取 
* @return fromLat
*/
	public String getFromLat() {
	return fromLat;
}
/**
* 获取 
* @return fromLng
*/
	public String getFromLng() {
	return fromLng;
}
/**
* 获取 
* @return toLat
*/
	public String getToLat() {
	return toLat;
}
/**
* 获取 
* @return toLng
*/
	public String getToLng() {
	return toLng;
}
/**
* 获取 1.delivering 2.delivered
* @return status
*/
	public Integer getStatus() {
	return status;
}
/**
* 获取 
* @return node
*/
	public Integer getNode() {
	return node;
}
/**
* 获取 
* @return beginCity
*/
	public String getBeginCity() {
	return beginCity;
}
/**
* 获取 
* @return endCity
*/
	public String getEndCity() {
	return endCity;
}
public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}

}
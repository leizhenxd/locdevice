package com.ld.model;

import java.util.Date;

/**
* 设备信息
* @author haisheng.long Thu Jun 15 19:53:22 CST 2017
*/

public class DeviceInfo extends BaseModel {

/**
	 * 
	 */
	private static final long serialVersionUID = -1589484546049630959L;

/** 主键 */
private Long id;

/** 主键 */
private String name;

/** IMEI编号 */
private String imei;

private String deviceOriginId;

/** 设备类型（1消防栓，2水泵接合器） */
private Integer deviceType;

/** 状态 */
private Integer status;

/** 区域ID */
private Long areaId;

/** 区域名称 */
private String areaName;

/** 经度 */
private String lat;

/** 纬度 */
private String lng;

/** 省 */
private String province;

/** 城市 */
private String city;

/** 创建时间 */
private Date createTime;

/** 更新时间 */
private Date updateTime;

/** 创建者 */
private String createUser;

/** 更新者 */
private String updateUser;

/**
* 设置 主键
* @param id
*/
	public void setId(Long id) {
	this.id = id;
}
/**
* 设置 主键
* @param name
*/
	public void setName(String name) {
	this.name = name;
}
/**
* 设置 IMEI编号
* @param imei
*/
	public void setImei(String imei) {
	this.imei = imei;
}
/**
* 设置 设备类型（1消防栓，2水泵接合器）
* @param deviceType
*/
	public void setDeviceType(Integer deviceType) {
	this.deviceType = deviceType;
}
/**
* 设置 状态
* @param status
*/
	public void setStatus(Integer status) {
	this.status = status;
}
/**
* 设置 区域ID
* @param areaId
*/
	public void setAreaId(Long areaId) {
	this.areaId = areaId;
}
/**
* 设置 区域名称
* @param areaName
*/
	public void setAreaName(String areaName) {
	this.areaName = areaName;
}
/**
* 设置 经度
* @param lat
*/
	public void setLat(String lat) {
	this.lat = lat;
}
/**
* 设置 纬度
* @param lng
*/
	public void setLng(String lng) {
	this.lng = lng;
}
/**
* 设置 省
* @param province
*/
	public void setProvince(String province) {
	this.province = province;
}
/**
* 设置 城市
* @param city
*/
	public void setCity(String city) {
	this.city = city;
}
/**
* 设置 创建时间
* @param createTime
*/
	public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
/**
* 设置 更新时间
* @param updateTime
*/
	public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
/**
* 设置 创建者
* @param createUser
*/
	public void setCreateUser(String createUser) {
	this.createUser = createUser;
}
/**
* 设置 更新者
* @param updateUser
*/
	public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
}
/**
* 获取 主键
* @return id
*/
	public Long getId() {
	return id;
}
/**
* 获取 主键
* @return name
*/
	public String getName() {
	return name;
}
/**
* 获取 IMEI编号
* @return imei
*/
	public String getImei() {
	return imei;
}
/**
* 获取 设备类型（1消防栓，2水泵接合器）
* @return deviceType
*/
	public Integer getDeviceType() {
	return deviceType;
}
/**
* 获取 状态
* @return status
*/
	public Integer getStatus() {
	return status;
}
/**
* 获取 区域ID
* @return areaId
*/
	public Long getAreaId() {
	return areaId;
}
/**
* 获取 区域名称
* @return areaName
*/
	public String getAreaName() {
	return areaName;
}
/**
* 获取 经度
* @return lat
*/
	public String getLat() {
	return lat;
}
/**
* 获取 纬度
* @return lng
*/
	public String getLng() {
	return lng;
}
/**
* 获取 省
* @return province
*/
	public String getProvince() {
	return province;
}
/**
* 获取 城市
* @return city
*/
	public String getCity() {
	return city;
}
/**
* 获取 创建时间
* @return createTime
*/
	public Date getCreateTime() {
	return createTime;
}
/**
* 获取 更新时间
* @return updateTime
*/
	public Date getUpdateTime() {
	return updateTime;
}
/**
* 获取 创建者
* @return createUser
*/
	public String getCreateUser() {
	return createUser;
}
/**
* 获取 更新者
* @return updateUser
*/
	public String getUpdateUser() {
	return updateUser;
}
public String getDeviceOriginId() {
	return deviceOriginId;
}
public void setDeviceOriginId(String deviceOriginId) {
	this.deviceOriginId = deviceOriginId;
}
}
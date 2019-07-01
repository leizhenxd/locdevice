package com.ld.response.logistic;

import java.util.Date;
import java.util.List;

import com.ld.logistic.domain.TLogisticScanRecordDO;

public class ParcelInfo {
	
	/** 主键 */
	private Integer id;
	
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
	
	private String code;
	
	/**  */
	private String beginCity;
	
	/**  */
	private String endCity;
	
	private List<TLogisticScanRecordDO> records;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFromLat() {
		return fromLat;
	}

	public void setFromLat(String fromLat) {
		this.fromLat = fromLat;
	}

	public String getFromLng() {
		return fromLng;
	}

	public void setFromLng(String fromLng) {
		this.fromLng = fromLng;
	}

	public String getToLat() {
		return toLat;
	}

	public void setToLat(String toLat) {
		this.toLat = toLat;
	}

	public String getToLng() {
		return toLng;
	}

	public void setToLng(String toLng) {
		this.toLng = toLng;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	public String getBeginCity() {
		return beginCity;
	}

	public void setBeginCity(String beginCity) {
		this.beginCity = beginCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public List<TLogisticScanRecordDO> getRecords() {
		return records;
	}

	public void setRecords(List<TLogisticScanRecordDO> records) {
		this.records = records;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

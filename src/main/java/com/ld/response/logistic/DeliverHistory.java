package com.ld.response.logistic;

import java.util.Date;

public class DeliverHistory {
	private Long id;
	private Integer status;
	private String name;
	private String hashCode;
	private String node;
	private String beginCity;
	private Long parcelId;
	private String endCity;
	private Date scanDate;
	private String code;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
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
	public Long getParcelId() {
		return parcelId;
	}
	public void setParcelId(Long parcelId) {
		this.parcelId = parcelId;
	}
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

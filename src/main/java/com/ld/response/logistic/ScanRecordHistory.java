package com.ld.response.logistic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScanRecordHistory {
	private Date createTime;
	private Long id;
	private String walletId;
	private String orgWalletId;
	private String userName;
	private int status;
	private String address;
	private String code;
	private String timeFormat;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public String getOrgWalletId() {
		return orgWalletId;
	}
	public void setOrgWalletId(String orgWalletId) {
		this.orgWalletId = orgWalletId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTimeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
	}
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

package com.ld.response.dto;

import com.ld.model.DeviceInfo;

public class DeviceInfoDto extends DeviceInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4972348764715162492L;
	private String statusDesc;

	public String getStatusDesc() {
		if(getStatus() == 0) return "未激活";
		else if(getStatus() == 1) return "离线";
		else if(getStatus() == 2) return "在线";
		return "未知";
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}

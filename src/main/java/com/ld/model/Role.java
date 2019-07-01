package com.ld.model;

import java.util.Date;

public class Role extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4601272057350903582L;
	private Long id;
	private String roleName;
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

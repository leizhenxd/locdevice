package com.ld.response.dto;

import com.ld.model.User;

public class UserDto extends User {
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

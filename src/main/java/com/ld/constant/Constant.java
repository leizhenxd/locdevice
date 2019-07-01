package com.ld.constant;

public interface Constant {

	public enum ROLE_TYPE {
		ADMIN(0),
		SELLER(1),
		USER(2);
		public int code;
		private ROLE_TYPE(int code) {
			this.code = code;
		}
	}

	public static final String LoginSessionKey = "loginUser";
	public static final String LoginRoleSessionKey = "loginUserRole";
}

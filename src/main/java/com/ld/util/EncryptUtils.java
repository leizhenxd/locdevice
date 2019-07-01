package com.ld.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {
	
	public static String md5(String password, String salt) {
		StringBuffer sb = new StringBuffer();
		sb.append(password);
		sb.append(salt);
		return DigestUtils.md5Hex(sb.toString()).toUpperCase();
	}
	
	public static void main(String[] args) {
		System.out.println(md5("123456", "abc"));
	}
}

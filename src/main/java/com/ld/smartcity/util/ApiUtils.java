package com.ld.smartcity.util;


import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class ApiUtils {
	public static String signIn(String email, String password) {
		String response = HttpUtils.get("https://api.4smartcity.com/signIn.php?username="+email+"&password="+password, null);
		int pos = response.indexOf("</style>");
		if(pos != -1) {
			String json = response.substring(pos+8);
			List<String> result = JSONObject.parseObject(json, List.class);
			return result.get(1);
		}
		return null;
	}
	public static String signUp(String name, String email, String password) {
		String response = HttpUtils.get("https://api.4smartcity.com/signUp.php?name="+name+"&email="+email+"&password="+password,
				null);
		int pos = response.indexOf("</style>");
		if(pos != -1) {
			String json = response.substring(pos+8);
			List<String> result = JSONObject.parseObject(json, List.class);
			if(result.get(1) != null && result.get(1).indexOf("BEGIN") != -1) {
				return "ok";
			}
			return result.get(1);
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(signIn("lilei01@email.com", "1234561"));
//		System.out.println(signUp("lilei","lilei02@email.com", "123456"));
	}
}

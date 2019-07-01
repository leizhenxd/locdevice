package com.ld.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class GPSUtils {
	private static Logger log = LoggerFactory.getLogger(GPSUtils.class);
	
	public static Map post(String url, Map<String, String> params) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			Set<String> keys = params.keySet();
			for(String key : keys) {
				NameValuePair p = new BasicNameValuePair(key, params.get(key));
				parameters.add(p);
			}
			
			post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));
			post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			post.addHeader("key", "A6D9FB7A-A3B2-4574-8360-BE91DC0B2361");
			HttpResponse response = client.execute(post);
			String resposnetText = IOUtils.toString(response.getEntity().getContent());
			log.info("response text:{}", resposnetText);
			return JSONObject.parseObject(resposnetText, Map.class);
			
		} catch (Exception e) {
			log.error("请求失败{}", e);
		}
		return null;
	}
	
	public static Map<String, String> getDeviceLocation(String deviceId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DeviceId", deviceId);
		params.put("MapType", "Baidu");
		params.put("TimeOffset", "8.0");
		params.put("Token", "09139642526D5D39524E3C2F70A93E4B5EC7F0248B88C94192DCC50435E89B9A23FF33259BDDFABE");
		Map<String, Object> lnglatMap = post("http://openapi.5gcity.com/api/Location/Tracking", params);
		Map<String, Object> item = (Map<String, Object>) lnglatMap.get("Item");
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://api.map.baidu.com/geocoder/v2/?location="+item.get("Latitude")+","+item.get("Longitude")+"&output=json&pois=1&ak=q6oYaI1yk8GHbigag4NQIs4M");
		try {
			HttpResponse response = client.execute(get);
			String responseText = IOUtils.toString(response.getEntity().getContent());
			Map<String, Object> resultMap = JSONObject.parseObject(responseText, Map.class);
			if((Integer)resultMap.get("status") == 0) {
				Map<String, Object> result = (Map<String, Object>) resultMap.get("result");
				Map<String, String> address = (Map<String, String>) result.get("addressComponent");
				address.put("lat", item.get("Latitude").toString());
				address.put("lng", item.get("Longitude").toString());
				return address;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, Object> getWarningList(String deviceId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DeviceId", deviceId);
		params.put("PageNo", "1");
		params.put("PageCount", "10");
		params.put("TypeID", "1");
		params.put("PageCount", "10");
		params.put("Token", "09139642526D5D39524E3C2F70A93E4B5EC7F0248B88C94192DCC50435E89B9A23FF33259BDDFABE");
		return post("http://openapi.5gcity.com/api/ExceptionMessage/ExcdeptionListWhitoutCode", params);
	}
	
	public static String getToken(String username, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Name", username);
		params.put("Pass", password);
		// userId=818522
		return (String) post("http://openapi.5gcity.com/api/User/Login", params).get("AccessToken");
	}
	
	
	public static void main(String[] args) {
//		System.out.println(getToken("宇岚科技", "123456"));
		System.out.println(getDeviceLocation("913806"));
//		System.out.println(getWarningList("913806"));
	}
}

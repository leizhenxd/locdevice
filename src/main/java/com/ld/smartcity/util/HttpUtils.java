package com.ld.smartcity.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static String get(String url, Map<String, String> params) {
		
		try {
			HttpClient client = HttpClients.createDefault();
			
			String queryStr = "";
			if(params != null && !params.isEmpty()) {
				Set<String> keys = params.keySet();
				for(String key : keys) {
					if(queryStr.length() == 0)
						queryStr = "?" + key + "="+params.get(key);
					else
						queryStr = queryStr + "&" + key+"="+params.get(key);
				}
			}
			System.out.println(queryStr);
			HttpGet get = new HttpGet(url+queryStr);
			HttpResponse response = client.execute(get);
			return EntityUtils.toString(response.getEntity());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String post(String url, Map<String, String> params) {
		
		try {
			HttpClient client = HttpClients.createDefault();
			
			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			if(params != null && !params.isEmpty()) {
				Set<String> keys = params.keySet();
				for(String key : keys) {
					BasicNameValuePair param = new BasicNameValuePair(key, params.get(key));
					parameters.add(param);
				}
			}
			HttpPost post = new HttpPost(url);
			HttpEntity entity = new UrlEncodedFormEntity(parameters);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			return EntityUtils.toString(response.getEntity());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String filePost(String url, Map<String, Object> params) {
		
		try {
			HttpClient client = HttpClients.createDefault();
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			
			if(params != null && !params.isEmpty()) {
				Set<String> keys = params.keySet();
				for(String key : keys) {
					if(params.get(key) instanceof File) {
						builder.addPart(key, new FileBody((File)params.get(key)));
					}
					else {
						builder.addPart(key, new StringBody((String)params.get(key), ContentType.MULTIPART_FORM_DATA));
					}
				}
			}
			HttpPost post = new HttpPost(url);
			HttpEntity entity = builder.build();
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			return EntityUtils.toString(response.getEntity());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectType", "test");
		params.put("file", new File("E:/Chrysanthemum.jpg"));
		System.out.println(filePost("http://www.show-lab.net/ic/upload", params));
	}
}

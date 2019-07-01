package com.ld.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class BaiDuMapUtils {
	
	public static Location toLocation(String cityName) {
		
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet get = new HttpGet("http://api.map.baidu.com/geocoder/v2/?address="+cityName+"&output=json&ak=q6oYaI1yk8GHbigag4NQIs4M");
			HttpResponse response = client.execute(get);
//			System.out.println(IOUtils.toString(response.getEntity().getContent()));
			CoderResponse result = JSONObject.parseObject(IOUtils.toString(response.getEntity().getContent()), CoderResponse.class);
			return null;//result.getResult().getLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Result toCity(String lat, String lng) {
		
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet get = new HttpGet("http://api.map.baidu.com/geocoder/v2/?location="+lat+","+lng+"&output=json&ak=q6oYaI1yk8GHbigag4NQIs4M");
			HttpResponse response = client.execute(get);
//			System.out.println(IOUtils.toString(response.getEntity().getContent()));
			CoderResponse result = new Gson().fromJson(IOUtils.toString(response.getEntity().getContent()), CoderResponse.class);
			return result.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		toLocation("上海市");
		System.out.println(JSONObject.toJSONString(toCity("22","114")));
	}
	
	class CoderResponse {
		public int status;
		public Result result;
		
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Result getResult() {
			return result;
		}
		public void setResult(Result result) {
			this.result = result;
		}
	}
	
	class Result {
//		public int precise;
//		public int confidence;
//		public String level;
		public Location location;
		public AddressComponent addressComponent;
		public String formatted_address;
//		public int getPrecise() {
//			return precise;
//		}
//		public void setPrecise(int precise) {
//			this.precise = precise;
//		}
//		public int getConfidence() {
//			return confidence;
//		}
//		public void setConfidence(int confidence) {
//			this.confidence = confidence;
//		}
//		public String getLevel() {
//			return level;
//		}
//		public void setLevel(String level) {
//			this.level = level;
//		}
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public AddressComponent getAddressComponent() {
			return addressComponent;
		}
		public void setAddressComponent(AddressComponent addressComponent) {
			this.addressComponent = addressComponent;
		}
		public String getFormatted_address() {
			return formatted_address;
		}
		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}
	}
	class Location {
		public String lng;
		public String lat;
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
	}
	class AddressComponent {
		public String country;
		public String province;
		public String district;
		public String street;
		public String adcode;
		public String city;
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getProvince() {
			return province;
		}
		public void setProvince(String province) {
			this.province = province;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getAdcode() {
			return adcode;
		}
		public void setAdcode(String adcode) {
			this.adcode = adcode;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	}
}

package com.ld.controller.smartcity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ld.dao.AreaDAO;
import com.ld.service.DeviceInfoService;
import com.ld.smartcity.util.ApiUtils;

@Controller
public class SmartCityController {
	Logger log = LoggerFactory.getLogger(SmartCityController.class);
	@Autowired
	DeviceInfoService deviceInfoService;
	@Autowired
	AreaDAO areaDAO;

	@RequestMapping(value = "/sc/login")
	public String login() {
		return "/sc/login";
	}
	@RequestMapping(value = "/sc/register")
	public String register() {
		return "/sc/register";
	}
	@RequestMapping(value = "/sc/form")
	public String form() {
		return "/sc/form";
	}
	@RequestMapping(value = "/sc/metadata")
	public String metadata() {
		return "/sc/metadata";
	}
	@RequestMapping(value = "/sc/history")
	public String history() {
		return "/sc/history";
	}
	@RequestMapping(value = "/sc/report")
	public String report() {
		return "/sc/report";
	}
	@RequestMapping(value = "/sc/signin")
	@ResponseBody
	public String signin(String email, String password) {
		String result = ApiUtils.signIn(email, password);
		return result;
	}
	@RequestMapping(value = "/sc/signup")
	@ResponseBody
	public String signup(String name, String email, String password) {
		String result = ApiUtils.signUp(name, email, password);
		return result;
	}
	
	@RequestMapping(value = "/sc/main")
	public String main() {
		return "/sc/main";
	}
}

package com.ld.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ld.constant.Constant;
import com.ld.dao.RoleDAO;
import com.ld.model.Area;
import com.ld.model.Function;
import com.ld.model.User;
import com.ld.response.BasePageResponse;
import com.ld.service.AreaService;
import com.ld.service.UserService;
import com.ld.util.CheckMobileUtils;
import com.ld.util.EncryptUtils;

@Controller
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	AreaService areaService;
	@Autowired
	RoleDAO roleDAO;

	@RequestMapping(value = "/index")
	public ModelAndView login(HttpServletResponse response, HttpServletRequest request) {
		
		
		Map<String, List<Function>> menus = new LinkedHashMap<String, List<Function>>();
		
		List<Function> functions = userService.selectAllFunctionByRole(0l);
		for(Function function : functions) {
			if(StringUtils.isEmpty(function.getParentId()) || function.getParentId() == 0) {
				if(menus.get("key"+function.getId()) == null) {
					List<Function> subTree = new ArrayList<Function>();
					subTree.add(function);
					menus.put("key"+function.getId(), subTree);
				}
				else {
					menus.get("key"+function.getId()).add(0, function);
				}
			}
			else {
				if(menus.get("key"+function.getParentId()) == null) {
					List<Function> subTree = new ArrayList<Function>();
					subTree.add(function);
					menus.put("key"+function.getParentId(), subTree);
				}
				else {
					menus.get("key"+function.getParentId()).add(function);
				}
			}
		}
		if(CheckMobileUtils.isPhone(request.getHeader( "USER-AGENT" ).toLowerCase())) {
			ModelAndView mv = new ModelAndView("/index_phone");
			mv.addObject("menus", menus);
			mv.addObject("areas", areaService.selectDynamic(new Area()));
			return mv;
		}
		else {
			ModelAndView mv = new ModelAndView("/index");
			mv.addObject("menus", menus);
			mv.addObject("areas", areaService.selectDynamic(new Area()));
			return mv;
		}
	}
	@RequestMapping(value = "/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest request, User u, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		log.info("用户登录请求:{}", JSONObject.toJSONString(u));
		if(u == null){
			return "清输入用户名和密码";
		}
		else if(StringUtils.isEmpty(u.getPhone())){
			return "请输入用户名";
		}
		else if(StringUtils.isEmpty(u.getPassword())){
			return "请输入密码";
		}
		if(u.getPassword().length() > 30 || u.getPhone().length() >99){
			return "用户名或密码太长";
		}
		User user = userService.selectByPhone(u.getPhone());
		if(user == null)
			return "用户名不存在";
		else if(!user.getPassword().equals(EncryptUtils.md5(u.getPassword(), user.getSalt()))){
			return "密码错误";
		}
		request.getSession().setAttribute(Constant.LoginSessionKey, user);
		request.getSession().setAttribute(Constant.LoginRoleSessionKey, roleDAO.selectById(user.getRoleId().intValue()));
		
		return "success";
	}
	@RequestMapping(value = "/user/userAdd")
	@ResponseBody
	public String addUser(HttpServletRequest request,User u) {
		
		log.info("添加用户请求:{}", JSONObject.toJSONString(u));
		if(StringUtils.isEmpty(u.getUserName())){
			return "请输入用户名";
		}
		else if(StringUtils.isEmpty(u.getPhone())){
			return "请输入手机号";
		}
		else if(StringUtils.isEmpty(u.getRoleId())){
			return "请选择角色";
		}
		else if(StringUtils.isEmpty(u.getPassword())){
			return "请输入密码";
		}
		if(u.getPassword().length() > 30 || u.getPhone().length() >99){
			return "用户名或密码太长";
		}
		User user = userService.selectByPhone(u.getPhone());
		if(user != null)
			return "用户已存在";
		userService.insert(u);
		
		return "success";
	}
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.LoginSessionKey);
		return "login";
	}
	@RequestMapping(value = "/home")
	public String home(HttpServletRequest request) {
		if(CheckMobileUtils.isPhone(request.getHeader( "USER-AGENT" ).toLowerCase())) {
			return "/m/home";
		}
		return "";
	}
	
	@RequestMapping(value = "/getAllUser")
	@ResponseBody
	public BasePageResponse<User> getAllUser(HttpServletRequest request, User user) {
		return userService.getPageList(user);
	}
}

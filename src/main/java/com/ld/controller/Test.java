package com.ld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {

	@RequestMapping(value="/test/list")
	public String test(){
		return "/test/hello";
	}
	@RequestMapping(value="/test/index")
	public String index(){
		return "/index";
	}
	
	@RequestMapping(value="/test/grid")
	public String grid(){
		return "/test/grid";
	}
	@RequestMapping(value="*")
	public String error404s(){
		return "/common/404";
	}
	@RequestMapping("favicon.ico")
    String favicon() {
        return "forward:/static/css/images/favicon.ico";
    }
}

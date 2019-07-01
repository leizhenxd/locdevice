package com.ld.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ld.constant.Constant;
import com.ld.model.User;
import com.ld.service.UserService;

/**
 * 验证登陆状态拦截器
 * 
 * @author lei
 * @version 2015年5月13日 下午4:39:38
 */
public class SmartCityInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(SmartCityInterceptor.class);
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("smartcity interceptor.");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// do nothing
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// do nothing
	}
	
	protected final User getMember(HttpServletRequest request) {
		if (request==null) {
			return null;
		}
		HttpSession session = request.getSession();
		if (session==null) {
			log.info("session=:{}", session);
			return null;
		}
//		User user = new User();
//		user.setId(682L);
//		session.setAttribute(Constant.LoginSessionKey, user);
		Object member = session.getAttribute(Constant.LoginSessionKey);
		
		if (null != member && member instanceof User) {
			return (User) member;
		}
//		log.info("can not find logined user. session.id=:{}, getRequestedSessionId={}", session.getId(), request.getRequestedSessionId());
		return null;
	}
	
	/**
	 * FIXME 应该通过filter方式来做好一点
	 * @param request
	 * @return
	 */
	protected final ModelAndView checkUser(HttpServletRequest request) {
		User member = getMember(request);
		if (member==null) {
			return new ModelAndView("/login");
		}
		return null;
	}
}

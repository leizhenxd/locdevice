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
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String visitUrl = request.getServletPath();
		if(visitUrl != null) visitUrl = visitUrl.replaceFirst("/", "");
		
		User user = getMember(request);
		
		if(visitUrl.startsWith("static") || visitUrl.contains("logistic")){
			return true;
		}
		if(visitUrl.startsWith("login") || visitUrl.startsWith("doLogin") || visitUrl.startsWith("logout") || visitUrl.equals("favicon.ico")){
			if(user != null){
				response.sendRedirect("index.htm");
				return true;
			}
			return true;
		}
		if (user==null) {
			log.info("未登陆请求url[{}]被拦截", visitUrl);
			response.sendRedirect("/login.htm");
			return false;
		}
		if(visitUrl.startsWith("index.htm")) {
			return true;
		}
		if(user != null && !userService.canVisit(user, visitUrl)){
			log.error("权限不够,请求url[{}]被拦截", visitUrl);
			if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
				response.getWriter().write("Permission Denied!");
			}
			else
				request.getRequestDispatcher("common/302.htm");
			return false;
		}
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

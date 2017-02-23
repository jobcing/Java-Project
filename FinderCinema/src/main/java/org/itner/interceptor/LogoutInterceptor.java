package org.itner.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 로그인한 사용자를 HttpSession에 보관처리하는 인터셉터
 */

public class LogoutInterceptor extends HandlerInterceptorAdapter{
						
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LogoutInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) == null){
			logger.info("user logout success........");
			
			response.sendRedirect("/");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null){
			logger.info("user logout..............");
			
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
}

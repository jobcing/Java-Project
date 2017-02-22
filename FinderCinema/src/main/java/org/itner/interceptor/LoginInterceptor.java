package org.itner.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 로그인한 사용자를 HttpSession에 보관처리하는 인터셉터
 */

public class LoginInterceptor extends HandlerInterceptorAdapter{

	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception{
		
		HttpSession session = request.getSession();
		// 컨트롤러에서 'memberVO'라는 이름으로 객체를 담아둔 상태, 이를 이용해서 세션에 저장
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberVO = modelMap.get("memberVO");
		
		if(memberVO != null){
			logger.info("new login success........");
			
			session.setAttribute(LOGIN, memberVO);
			Object dest = session.getAttribute("dest");
			
			response.sendRedirect(dest != null ? (String)dest : "/");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null){
			logger.info("clear login data before.........");
			
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
}

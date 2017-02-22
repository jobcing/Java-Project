package org.itner.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * 사용자가 특정 경로에 접근하는 경우 현재 사용자가 로그인한 상태의 사용자인지를 체크하는 인터셉터
 */

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null){
			logger.info("current user is not logined..........");
			
			saveURI(request);
			
			response.sendRedirect("/user/login");
			return false;
		}
		
		return true;
	}
	
	// 사용자가 원하던 URI를 보관했다가, 로그인에 성공하면 해당경로로 이동시켜주기 위한 메소드
	private void saveURI(HttpServletRequest request){
		
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if(query == null || query.equals("null")){
			query = "";
		} else{
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")){
			logger.info("destination : " + (uri + query));
			
			request.getSession().setAttribute("dest", uri + query);
		}
	}
}

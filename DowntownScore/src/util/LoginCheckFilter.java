package util;

/********* 로그인 체크 확인해주는 필터 클래스 *********/

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession(false);
		
		// 세션이 존재하지 않거나 세션에 "authUser" 속성이 없으면 login.jb로 리다이렉트한다.
		if(session == null || session.getAttribute("authUser") == null){
			HttpServletResponse response = (HttpServletResponse)res;
			response.sendRedirect(request.getContextPath() + "/login.jb");
		} else{ // 세션에 "authUser" 속성이 존재하면 로그인한 것으로 판단하고 기능을 실행한다.
			chain.doFilter(req, res);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

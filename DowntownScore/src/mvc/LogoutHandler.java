package mvc;

/********* 로그아웃 핸들러 *********/

import java.util.*;

import javax.servlet.http.*;

import service.*;

public class LogoutHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false);
		if(session != null){
			session.invalidate();
		}
		res.sendRedirect(req.getContextPath() + "/loginForm.jsp");
		return null;
	}
}

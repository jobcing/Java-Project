package mvc;

/********* 로그인 핸들러 *********/

import java.util.*;

import javax.servlet.http.*;

import service.*;

public class LoginHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "./loginForm.jsp";
	private LoginService loginService = new LoginService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(req, res);
		} else{
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res){
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		String member_id = trim(req.getParameter("member_id"));
		String member_pwd = trim(req.getParameter("member_pwd"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(member_id == null || member_id.isEmpty())
			errors.put("member_id", Boolean.TRUE);
		if(member_pwd == null || member_pwd.isEmpty())
			errors.put("member_pwd", Boolean.TRUE);
		
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		try{
			User user = loginService.login(member_id, member_pwd);
			req.getSession().setAttribute("authUser", user); // User객체를 세션의 authUser 속성에 저장한다.
			res.sendRedirect(req.getContextPath() + "/loginSuccess.jsp"); // loginSuccess.jsp로 리다이렉트한다.
			return null;
		} catch(LoginFailException e){
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}
}

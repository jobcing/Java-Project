package mvc;

/********* 회원가입을 할 때 사용되는 핸들러 *********/

import java.util.*;

import javax.servlet.http.*;

import service.*;

public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "./joinForm.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 만약 GET방식으로 오면 폼을 보여주는 페이지를 리턴한다.
		// POST방식으로 요청이 오면 회원 가입을 처리한다.
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
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res){
		JoinRequest joinReq = new JoinRequest();
		
		// 사용자가 폼에 입력한 정보를 가져와서 joinReq객체에 저장한다.
		joinReq.setId(req.getParameter("member_id"));
		joinReq.setPwd(req.getParameter("member_pwd"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		joinReq.setNickname(req.getParameter("member_nickname"));
		joinReq.setAge(req.getParameter("member_age"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		joinReq.vaildate(errors);
		
		// errors 맵 객체에 데이터가 존재하면 뷰를 다시 리턴한다.
		if(!errors.isEmpty()){
			return FORM_VIEW;
		}
		
		try{
			joinService.join(joinReq);
			return "./joinSuccess.jsp";
		} catch(DuplicatedException e){ // 아이디 중복 시 발생하는 Exception
			errors.put("duplicatedId", Boolean.TRUE);
			return FORM_VIEW;
		} catch(RuntimeException e){ // 닉네임 중복 시 발생하는 Exception
			errors.put("duplicatedNick", Boolean.TRUE);
			return FORM_VIEW;
		}
	}
	
}

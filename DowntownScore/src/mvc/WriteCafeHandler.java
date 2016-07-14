package mvc;

/********* 카페 평점 작성 핸들러 *********/

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import service.*;

public class WriteCafeHandler implements CommandHandler {
	private static final String FORM_VIEW = "./newCafeForm.jsp";
	private CafeService cafeService = new CafeService();
	
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
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res){
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		// 세션에서 사용자 정보를 구한다.
		User user = (User)req.getSession(false).getAttribute("authUser");
		CafeRequest cafeReq = createCafeReq(user, req);
		cafeReq.validate(errors);
		
		// 에러가 존재하면 다시 폼을 돌려준다.
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		int newCafeNo = cafeService.write(cafeReq);
		req.setAttribute("newCafeNo", newCafeNo);
		
		return "./newCafeSuccess.jsp";
	}
	
	private CafeRequest createCafeReq(User user, HttpServletRequest req){
		return new CafeRequest(req.getParameter("station"),
							   Integer.parseInt(req.getParameter("score")),
							   req.getParameter("name"),
							   req.getParameter("comment"),
							   user.getNickname());
	}

}

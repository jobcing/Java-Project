package mvc;

/********* 게시글 작성 핸들러 *********/

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import service.*;

public class WriteNoticeHandler implements CommandHandler{
	private static final String FORM_VIEW = "./newNoticeForm.jsp";
	private WriteNoticeService writeService = new WriteNoticeService();
	
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
		
		// 세션에서 로그인한 사용자 정보를 구한다.
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.vaildate(errors);
		
		// 에러가 존재하면 다시 폼을 돌려준다.
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		// WriteNoticeService를 이용해서 게시글을 등록하고 등록된 게시글 번호를 리턴받는다.
		int newNoticeNo = writeService.write(writeReq);
		// 전달받은 ID를 request newNoticeNo 속성에 저장한다.
		req.setAttribute("newNoticeNo", newNoticeNo);
		
		return "./newNoticeSuccess.jsp";
	}
	
	private WriteRequest createWriteRequest(User user, HttpServletRequest req){
		return new WriteRequest(new Writer(user.getId(), user.getNickname()),
								req.getParameter("title"),
								req.getParameter("content"));
	}
}

package mvc;

/********* 게시글 수정 시 사용되는 핸들러 *********/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;

public class ModifyNoticeHandler implements CommandHandler {

	private static final String FORM_VIEW = "./modifyForm.jsp";
	
	private ReadNoticeService readService = new ReadNoticeService();
	private ModifyNoticeService modifyService = new ModifyNoticeService();
	
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
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException{
		try{
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			NoticeData noticeData = readService.getNotice(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			
			if(!canModify(authUser, noticeData)){
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			// 폼에 데이터를 보여줄 때 사용할 객체를 생성하고, request 속성에 저장
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no, 
													noticeData.getNotice().getTitle(),
													noticeData.getContent());
			
			req.setAttribute("modReq", modReq);
			
			return FORM_VIEW;
		} catch(NoticeNotFoundException e){
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	// 게시글 수정을 요청한 사용자 정보를 구한다.
	private boolean canModify(User authUser, NoticeData noticeData){
		String writerId = noticeData.getNotice().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
												req.getParameter("title"),
												req.getParameter("content"));
		
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		try{
			modifyService.modify(modReq);
			return "./modifySuccess.jsp";
		} catch(NoticeNotFoundException e){
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch(PermissionDeniedException e){
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}

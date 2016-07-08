package mvc;

/********* 게시글을 삭제할 때 사용되는 핸들러 *********/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;

public class DeleteNoticeHandler implements CommandHandler {

	private static final String FORM_VIEW = "./deleteSuccess.jsp";
	
	private ReadNoticeService readService = new ReadNoticeService();
	private DeleteNoticeService deleteService = new DeleteNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		try{
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			NoticeData noticeData = readService.getNotice(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			
			if(!canModify(authUser, noticeData)){
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
		
			deleteService.remove(no);
			
			return FORM_VIEW;
		} catch(NoticeNotFoundException e){
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch(PermissionDeniedException e){
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
	
	// 게시글 삭제를 요청한 사용자 정보를 구한다.
	private boolean canModify(User authUser, NoticeData noticeData){
		String writerId = noticeData.getNotice().getWriter().getId();
		return authUser.getId().equals(writerId);
	}
}

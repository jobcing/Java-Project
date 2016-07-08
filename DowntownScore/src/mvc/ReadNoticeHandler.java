package mvc;

/********* 게시글 내용을 보여주는 핸들러 *********/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;

public class ReadNoticeHandler implements CommandHandler{

	private ReadNoticeService readService = new ReadNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int noticeNum = Integer.parseInt(noVal);
		
		try{
			NoticeData noticeData = readService.getNotice(noticeNum, true);
			req.setAttribute("noticeData", noticeData);
			
			return "./readNotice.jsp";
		} catch(NoticeNotFoundException | NoticeContentNotFoundException e){
			req.getServletContext().log("no notice", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}

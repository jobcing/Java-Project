package mvc;

/********* 게시글 리스트를 보여주는 핸들러 *********/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;

public class ListNoticeHandler implements CommandHandler{

	private ListNoticeService listService = new ListNoticeService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if(pageNoVal != null){
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		NoticePage noticePage = listService.getNoticePage(pageNo);
		req.setAttribute("noticePage", noticePage);
		
		return "./listNotice.jsp";
	}

}

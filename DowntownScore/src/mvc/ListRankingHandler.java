package mvc;

/********* 랭킹 리스트를 보여주는 핸들러 *********/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import service.*;

public class ListRankingHandler implements CommandHandler {
	private static final String FORM_VIEW = "./listRanking.jsp";
	private RankingService rankingService = new RankingService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if(pageNoVal != null){
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		// 세션에서 사용자 정보를 구한다.
		User user = (User)req.getSession(false).getAttribute("authUser");
		req.setAttribute("userNickname", user.getNickname());
		
		// 페이지번호에 맞는 페이지를 보여주기 위해
		RankingPage rankingPage = rankingService.getRankingPage(pageNo);
		req.setAttribute("rankingPage", rankingPage);
		
		return FORM_VIEW;
	}
}

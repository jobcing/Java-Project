package mvc;

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
		
		RankingPage rankingPage = rankingService.getRankingPage(pageNo);
		req.setAttribute("rankingPage", rankingPage);
		
		return FORM_VIEW;
	}
}

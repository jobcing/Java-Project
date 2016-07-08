package mvc;

/********* 식당 평점 작성 핸들러 *********/

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import service.*;

public class WriteResHandler implements CommandHandler{
	private static final String FORM_VIEW = "./newRestaurantForm.jsp";
	private	RestaurantService restaurantService = new RestaurantService();
	
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
		RestaurantRequest restaurantReq = createRestaurantReq(user, req);
		restaurantReq.vaildate(errors);
		
		// 에러가 존재하면 다시 폼을 돌려준다.
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		// WriteNoticeService를 이용해서 게시글을 등록하고 등록된 게시글 번호를 리턴받는다.
		int newRestaurantNo = restaurantService.write(restaurantReq);
		// 전달받은 ID를 request newNoticeNo 속성에 저장한다.
		req.setAttribute("newRestaurantNo", newRestaurantNo);
		
		return "./newResSuccess.jsp";
	}
	
	private RestaurantRequest createRestaurantReq(User user, HttpServletRequest req){
		return new RestaurantRequest(req.getParameter("station"),
								Integer.parseInt(req.getParameter("score")),
								req.getParameter("name"),
								req.getParameter("comment"),
								user.getNickname());
	}
}

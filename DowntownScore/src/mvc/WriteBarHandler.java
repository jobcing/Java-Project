package mvc;

/********* 술집 평점 작성 핸들러 *********/

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import service.BarRequest;
import service.BarService;
import service.User;

public class WriteBarHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "./newBarForm.jsp";
	private BarService barService = new BarService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")){
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")){
			return processSumbit(req, res);
		} else{
			res.setStatus(Response.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res){
		return FORM_VIEW;
	}
	
	private String processSumbit(HttpServletRequest req, HttpServletResponse res){
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		BarRequest barRequest = createBarReq(user, req);
		barRequest.validate(errors);
		
		if(!errors.isEmpty())
			return FORM_VIEW;
		
		int newBarNo = barService.write(barRequest);
		req.setAttribute("newBarNo", newBarNo);
		
		return "./newBarSuccess.jsp";
	}
	
	private BarRequest createBarReq(User user, HttpServletRequest req){
		return new BarRequest(req.getParameter("station"),
							  Integer.parseInt(req.getParameter("score")),
							  req.getParameter("name"),
							  req.getParameter("comment"),
							  user.getNickname());
	}
}

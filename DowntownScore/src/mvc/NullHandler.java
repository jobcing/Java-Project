package mvc;

/********* NULL 핸들러 *********/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception{
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}
}
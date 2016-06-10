package mvc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.*;

public class MainPageHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "./index.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	
}

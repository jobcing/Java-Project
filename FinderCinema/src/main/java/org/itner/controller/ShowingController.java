package org.itner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application now showing page.
 */
@Controller
@RequestMapping("/showing/*")
public class ShowingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ShowingController.class);
	
	/**
	 * Now Showing 메뉴 클릭시 /showing/list.jsp View를 되돌려주는 컨트롤러
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET(Model model) {
		logger.info("now showing page get..............");
		
		return "/showing/list";
	}
	
}

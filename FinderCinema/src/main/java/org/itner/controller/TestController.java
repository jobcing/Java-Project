package org.itner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application about page.
 */
@Controller
@RequestMapping("/test/*")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * Test 컨트롤러
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String aboutGET(Model model) {
		logger.info("test page get..............");
		
		return "test";
	}
	
}

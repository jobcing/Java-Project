package org.itner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application reservation page.
 */
@Controller
@RequestMapping("/reserve/*")
public class ReserveController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReserveController.class);
	
	/**
	 * Now Showing 메뉴 클릭시 /reserve/list.jsp View를 되돌려주는 컨트롤러
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET(Model model) throws Exception {
		logger.info("reservation page get..............");
		
		return "/reserve/list";
	}
	
	/*
	 * 첫번째 단계를 보여주는 View를 제공
	 */
	@RequestMapping(value = "/onestep", method = RequestMethod.GET)
	public String onestepGET(Model model) throws Exception{
		logger.info("reservation one step page get............");
		
		return "/reserve/onestep";
	}
	
	/*
	 * 두번째 단계를 보여주는 View를 제공
	 */
	@RequestMapping(value = "/twostep", method = RequestMethod.POST)
	public String twostepPOST(@RequestBody String cinemalist, RedirectAttributes rttr) throws Exception{
		logger.info("reservation two step page post............");
		
		rttr.addFlashAttribute("data", cinemalist);
		
		return "redirect:/reserve/twostep";
		// view를 리턴하는게아니고 json으로 리턴하는 듯....?
	}
	
}

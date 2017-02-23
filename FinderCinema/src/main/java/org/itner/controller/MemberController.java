package org.itner.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.itner.domain.MemberVO;
import org.itner.dto.LoginDTO;
import org.itner.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application User.
 */

@Controller
@RequestMapping("/user")
public class MemberController {

	@Inject
	private MemberService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(@ModelAttribute("dto") LoginDTO dto, HttpSession session, Model model)
		throws Exception{
		
		MemberVO vo = service.login(dto);
		
		if(vo == null){
			return;
		}
		
		model.addAttribute("memberVO", vo);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logoutGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET(@ModelAttribute("vo") MemberVO vo, Model model) throws Exception{

		logger.info("member join page get..............");
	}
}

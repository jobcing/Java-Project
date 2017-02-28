package org.itner.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.itner.domain.MemberVO;
import org.itner.domain.ReplyVO;
import org.itner.dto.LoginDTO;
import org.itner.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(@ModelAttribute("vo") MemberVO vo, RedirectAttributes rttr) throws Exception{
		logger.info("member join page post..............");
		
		service.join(vo);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		// 브라우저까지 전송되긴하지만, URI상에는 보이지 않는 데이터로 전송된다.
		
		return "redirect:/";
		// 회원가입에 성공하면 홈페이지로 이동하도록 설정
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<MemberVO> checkID(@RequestBody MemberVO vo) {

		ResponseEntity<MemberVO> entity = null;
		
		try {
			MemberVO member = service.checkRepetition(vo);
			entity = new ResponseEntity<MemberVO>(member, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<MemberVO>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
}

package org.itner.controller;

import javax.inject.Inject;

import org.itner.domain.BoardVO;
import org.itner.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application reviews page.
 */

@Controller
@RequestMapping("/review/*")
public class ReviewController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("review register get...........");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		// 모든 데이터를 BoardVO로 자동 수집
		logger.info("review register post..........");
		logger.info(board.toString());
		
		service.register(board);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		// 브라우저까지 전송되긴하지만, URI상에는 보이지 않는 데이터로 전송된다.
		
		return "redirect:/review/listAll";
		// 게시글 등록에 성공하면 게시글 목록을 보여주도록 설정
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("review list show.............");
		
		model.addAttribute("list", service.listAll());
		// Model을 이용해서 모든 게시글을 VIEW로 전송
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		logger.info("review read page..............");
		model.addAttribute(service.read(bno));
		// 변수명을 입력하지않고 데이터를 보내면 자동으로 클래스의 이름을 소문자로 시작해서 사용한다.
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
		logger.info("review remove.................");
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/review/listAll";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
		logger.info("review modify get..............");
		// 게시글 번호를 받아서 해당 데이터를 modify.jsp로 보내준다.
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		logger.info("review modify post.............");
		// 수정된 데이터를 자동으로 수집해서 BoardVO로 만든다.
		// 수집된 BoardVO를 이용해서 게시글을 수정한다.
		service.modify(board);
		
		rttr.addAttribute("msg", "SUCCESS");
		
		return "redirect:/review/listAll";
	}
}

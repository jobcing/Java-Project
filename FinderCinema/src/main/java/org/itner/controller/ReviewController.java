package org.itner.controller;

import javax.inject.Inject;

import org.itner.domain.BoardVO;
import org.itner.domain.Criteria;
import org.itner.domain.PageMaker;
import org.itner.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	// 게시글 작성 GET
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("review register get...........");
	}
	
	// 게시글 등록 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		// 모든 데이터를 BoardVO로 자동 수집
		logger.info("review register post..........");
		logger.info(board.toString());
		
		service.register(board);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		// 브라우저까지 전송되긴하지만, URI상에는 보이지 않는 데이터로 전송된다.
		
		return "redirect:/review/listPage";
		// 게시글 등록에 성공하면 게시글 목록을 보여주도록 설정
	}
	
	// 게시글 조회 GET
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno,
					 @ModelAttribute("cri") Criteria cri,
					 Model model) throws Exception{
		
		logger.info("review read page..............");

		model.addAttribute(service.read(bno)); 
		// 변수명을 입력하지않고 데이터를 보내면 자동으로 클래스의 이름을 소문자로 시작해서 사용한다.
	}
	
	// 게시글 삭제 POST
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
						 @ModelAttribute("cri") Criteria cri,
						 RedirectAttributes rttr) throws Exception{
		
		logger.info("review remove.................");
		service.remove(bno);
		
		// 삭제 후 페이지를 유지하기 위해 listPage에 데이터 전송
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS"); // msg는 임시로 사용된다.
		
		return "redirect:/review/listPage";
	}
	
	// 게시글 수정 GET
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno,
						  @ModelAttribute("cri") Criteria cri,
						  Model model) throws Exception{
		
		logger.info("review modify get..............");
		// 게시글 번호를 받아서 해당 데이터를 modifyPage.jsp로 보내준다.
		model.addAttribute(service.read(bno));
	}
	
	// 게시글 수정 POST
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(@ModelAttribute("board") BoardVO board,
							 @ModelAttribute("cri") Criteria cri,
							 RedirectAttributes rttr) throws Exception{
		
		logger.info("review modify post.............");
		// 수정된 데이터를 자동으로 수집해서 BoardVO로 만든다.
		// 수집된 BoardVO를 이용해서 게시글을 수정한다.
		service.modify(board);

		// 수정 후 페이지를 유지하기 위해 listPage에 데이터 전송
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/review/listPage";
	}
	
	// 게시글 목록 GET
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		// @ModelAttribute를 사용함으로써 Criteria 클래스가 자동으로 View로 전송
		// page, searchType, keyword가 View로 전달된다.
		logger.info("review show list page..........");
		
		model.addAttribute("list", service.listPage(cri)); // 페이지에 해당하는 게시글 목록 전송
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri); // 현재 페이지 설정
		pageMaker.setTotalCount(service.listCountPage(cri)); // 전체 게시글 개수 설정
		
		model.addAttribute("pageMaker", pageMaker); // 페이징 계산 결과 전송
	}
}

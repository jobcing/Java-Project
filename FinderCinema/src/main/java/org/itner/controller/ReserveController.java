package org.itner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.itner.domain.CinemaSiteVO;
import org.itner.domain.TimetableVO;
import org.itner.service.CinemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application reservation page.
 */
@Controller
@RequestMapping("/reserve/*")
public class ReserveController {
	
	@Inject
	private CinemaService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ReserveController.class);
	private List<String> cinemaList = new ArrayList<String>();
	
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
	 * ajax를 통해 보낸 JSON데이터를 받고 처리
	 */
	@RequestMapping(value = "/onestep", method = RequestMethod.POST)
	public ResponseEntity<String> onestepPOST(@RequestBody List<String> cinemaList) throws Exception{
		
		logger.info("reservation one step page post............");
		
		ResponseEntity<String> entity = null;
		
		/*
		try {
			// Ajax로 호출될 것이므로 Model을 사용하지 못한다.
			// 전달해야 하는 데이터들을 담기 위해서 Map 객체를 활용
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("cinemalist", cinemalist);
			map.put("url", "/reserve/twostep");
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		*/
		try {
			this.cinemaList = cinemaList;

			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
	
	/*
	 * 두번째 단계를 보여주는 View를 제공 
	 */
	@RequestMapping(value = "/twostep", method = RequestMethod.GET)
	public String twostepGET(Model model) throws Exception{
		logger.info("reservation two step page get.............");
		
		List<CinemaSiteVO> site = service.timetableList(cinemaList); // 테스트
		/*
		List<TimetableVO> timetable = new ArrayList<TimetableVO>();
		
		for(int i = 0; i < site.size(); i++){
			timetable.set(i, service.crawling(site.get(i)));
		} // 테스트
		*/
		
		TimetableVO timetable = new TimetableVO();
		
		timetable = service.crawling(site.get(0));
		
		model.addAttribute("test", timetable); // 테스트
		
		return "/reserve/twostep";
	}

}

package org.itner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.itner.domain.Criteria;
import org.itner.domain.PageMaker;
import org.itner.domain.ReplyVO;
import org.itner.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application reviews reply.
 */

@RestController
@RequestMapping("/finder")
public class FinderController {

	@Inject
	private ReplyService service;

	// 댓글 등록 POST
	@RequestMapping(value = "/twostep", method = RequestMethod.POST)
	public ResponseEntity<String> twostep(@RequestBody String cinemaList) {

		ResponseEntity<String> entity = null;

		try {
			service.registReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}
}

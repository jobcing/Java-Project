package org.itner.controller;

import java.util.List;

import javax.inject.Inject;

import org.itner.domain.BoardVO;
import org.itner.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardPageTest {

	@Inject
	private BoardDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardPageTest.class);
	
	@Test
	public void testListPage() throws Exception{
		int page = 3;
		
		List<BoardVO> list = dao.listPage(page);
		
		for(BoardVO boardVO : list){
			logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
		}
	}
}

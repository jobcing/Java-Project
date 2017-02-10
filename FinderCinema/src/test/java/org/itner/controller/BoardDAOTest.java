package org.itner.controller;

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
public class BoardDAOTest {
	
	@Inject
	private BoardDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Test
	public void testCreate() throws Exception{
		BoardVO board = new BoardVO();
		board.setTitle("테스트 제목");
		board.setContent("테스트 내용");
		board.setWriter("테스트 작성자");
		dao.create(board);
	}
	
	@Test
	public void testRead() throws Exception{
		logger.info(dao.read(1).toString());
	}

	@Test
	public void testUpdate() throws Exception{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("테스트 수정");
		board.setContent("테스트 수정 내용");
		dao.update(board);
	}
	
	@Test
	public void testDelete() throws Exception{
		dao.delete(1);
	}
}

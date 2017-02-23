package org.itner.controller;

import javax.inject.Inject;

import org.itner.domain.MemberVO;
import org.itner.persistence.MemberDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {
	
	@Inject
	private MemberDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	@Test
	public void testInsert() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setMember_id("user23");
		vo.setMember_pw("user23");
		vo.setNickname("user23");
		dao.insert(vo);
	}

}

package org.jpg.service;

import javax.inject.Inject;

import org.jpg.domain.MemberVO;
import org.jpg.dto.LoginDTO;
import org.jpg.persistence.MemberDAO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDAO dao;

	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void join(MemberVO vo) throws Exception {
		dao.insert(vo);
	}

	@Override
	public MemberVO checkRepetition(MemberVO vo) throws Exception {
		return dao.selectById(vo);
	}

}

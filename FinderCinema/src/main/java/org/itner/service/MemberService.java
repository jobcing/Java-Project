package org.itner.service;

import org.itner.domain.MemberVO;
import org.itner.dto.LoginDTO;

/*
 * 컨트롤러와 DAO를 연결해주는 MemberService (비즈니스 계층)
 */

public interface MemberService {

	public MemberVO login(LoginDTO dto) throws Exception;
	
	public void join(MemberVO vo) throws Exception;
	
	public MemberVO checkRepetition(MemberVO vo) throws Exception;
}

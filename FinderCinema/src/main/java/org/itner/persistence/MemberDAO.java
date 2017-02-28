package org.itner.persistence;

import org.itner.domain.MemberVO;
import org.itner.dto.LoginDTO;

public interface MemberDAO {

	public MemberVO login(LoginDTO dto) throws Exception;
	
	public void insert(MemberVO vo) throws Exception;
	
	public MemberVO selectById(MemberVO vo) throws Exception;
}

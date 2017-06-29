package org.jpg.persistence;

import org.jpg.domain.MemberVO;
import org.jpg.dto.LoginDTO;

public interface MemberDAO {

	public MemberVO login(LoginDTO dto) throws Exception;
	
	public void insert(MemberVO vo) throws Exception;
	
	public MemberVO selectById(MemberVO vo) throws Exception;
}

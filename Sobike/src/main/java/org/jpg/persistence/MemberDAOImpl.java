package org.jpg.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.jpg.domain.MemberVO;
import org.jpg.dto.LoginDTO;
import org.springframework.stereotype.Repository;

/*
 * MemberDAO를 구현한 클래스
 * SqlSessionTemplate을 이용해서 코드를 호출
 */

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.jpg.mapper.MemberMapper";
	
	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public void insert(MemberVO vo) throws Exception {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public MemberVO selectById(MemberVO vo) throws Exception {
		return session.selectOne(namespace + ".selectById", vo);
	}

}

package org.itner.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.itner.domain.CinemaSiteVO;
import org.springframework.stereotype.Repository;

/*
 * CinemaDAO를 구현
 */

@Repository
public class CinemaDAOImpl implements CinemaDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "org.itner.mapper.CinemaMapper";
	
	@Override
	public CinemaSiteVO selectByTitle(String title) throws Exception {
		return session.selectOne(namespace + ".selectByTitle", title);
	}

}

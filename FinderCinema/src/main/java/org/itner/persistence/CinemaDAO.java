package org.itner.persistence;

import org.itner.domain.CinemaSiteVO;

/*
 * MyBatis를 이용해서 데이터베이스 cinema_site에 직접적으로 접근하는 CinemaDAO
 */

public interface CinemaDAO {
	
	public CinemaSiteVO selectByTitle(String title) throws Exception;
	
}

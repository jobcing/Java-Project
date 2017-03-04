package org.itner.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.itner.domain.CinemaSiteVO;
import org.itner.persistence.CinemaDAO;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl implements CinemaService {
	
	@Inject
	private CinemaDAO dao;

	@Override
	public List<CinemaSiteVO> timetableList(List<String> cinemaList) throws Exception {
		
		List<CinemaSiteVO> result = new ArrayList<CinemaSiteVO>();
		
		for(int i = 0 ; i < cinemaList.size(); i++){
			result.add(dao.selectByTitle(cinemaList.get(i)));
		}
		
		return result;
	}

}

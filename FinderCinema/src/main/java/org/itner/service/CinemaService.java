package org.itner.service;

import java.util.List;

import org.itner.domain.CinemaSiteVO;

public interface CinemaService {
	
	public List<CinemaSiteVO> timetableList(List<String> cinemaList) throws Exception;
}

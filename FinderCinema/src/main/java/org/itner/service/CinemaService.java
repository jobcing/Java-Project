package org.itner.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.itner.domain.CinemaSiteVO;
import org.itner.domain.TimetableVO;
import org.itner.dto.MovieSearchDTO;

public interface CinemaService {
	
	public List<CinemaSiteVO> timetableList(List<String> cinemaList) throws Exception;
	
	public TimetableVO timeCrawling(CinemaSiteVO vo) throws ClientProtocolException, IOException;
	
	public String[] showingCrawling() throws ClientProtocolException, IOException;
	
	public TimetableVO search(TimetableVO vo, MovieSearchDTO dto) throws Exception;
}

package org.itner.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.itner.domain.CinemaSiteVO;
import org.itner.domain.TimetableVO;

public interface CinemaService {
	
	public List<CinemaSiteVO> timetableList(List<String> cinemaList) throws Exception;
	
	public TimetableVO timeCrawling(CinemaSiteVO vo) throws ClientProtocolException, IOException;
	
	public String[] showingCrawling() throws ClientProtocolException, IOException;
}

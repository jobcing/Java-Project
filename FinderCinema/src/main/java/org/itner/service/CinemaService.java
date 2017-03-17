package org.itner.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.itner.domain.CinemaSiteVO;
import org.itner.domain.TimetableVO;

public interface CinemaService {
	
	public List<CinemaSiteVO> timetableList(List<String> cinemaList) throws Exception;
	
	public TimetableVO crawling(CinemaSiteVO vo) throws ClientProtocolException, IOException; // 테스트
}

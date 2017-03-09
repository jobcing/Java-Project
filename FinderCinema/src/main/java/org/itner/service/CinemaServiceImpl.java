package org.itner.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.itner.domain.CinemaSiteVO;
import org.itner.domain.TimetableVO;
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

	// 테스트
	@Override
	public String crawling(CinemaSiteVO vo) throws ClientProtocolException, IOException {
		
		HttpPost http = new HttpPost(vo.getTimetable());
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(http);
		HttpEntity entity = response.getEntity();
		
		ContentType contentType = ContentType.getOrDefault(entity);
		Charset charset = contentType.getCharset();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		StringBuffer sb = new StringBuffer();
		
		String line = "";
		while((line = br.readLine()) != null){
			sb.append(line + "\n");
		}
		
		return sb.toString();
		
		return null;
	}

}

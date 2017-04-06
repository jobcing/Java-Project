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
import org.itner.domain.MovieTimeVO;
import org.itner.domain.TimetableVO;
import org.itner.dto.MovieSearchDTO;
import org.itner.persistence.CinemaDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

	// 영화 시간표 크롤링
	@Override
	public TimetableVO timeCrawling(CinemaSiteVO vo) throws ClientProtocolException, IOException {	
		
		// 영화관마다 웹크롤링 방식이 다르므로 영화관마다 다른 메소드 사용
		if(vo.getTitle().contains("CGV")){
			return cgvCrawling(vo);
		} else if(vo.getTitle().contains("롯데시네마")){
			return lotteCrawling(vo);
		}
		
		return null;
	}
	
	// cgv 영화시간표 웹크롤링
	private TimetableVO cgvCrawling(CinemaSiteVO vo) throws ClientProtocolException, IOException{
		Document doc = getDoc(vo);
		
		// 영화관 목록 Elements로 가져오기
		Elements titles = doc.select("div.info-movie strong");
		
		// 그냥 String[]으로 할지 List<String> 으로 할지 ..?
		String[] movieTitles = new String[titles.size()];
		
		TimetableVO result = new TimetableVO();
		
		// 상영 영화 제목 배열에 저장
		for(int i = 0; i < titles.size(); i++){
			movieTitles[i] = titles.get(i).text();
		}

		// 상영 영화 시간표를 저장하기 위한 클래스
		MovieTimeVO[] movietimeVO = new MovieTimeVO[titles.size()];
		
		// 영화 시간은 select를 두번써서 분류되도록 ????
		Elements times = doc.select("div.col-times");
		
		for(int i = 0; i < titles.size(); i++){
			Elements time = times.get(i).select("div.info-timetable li em");
			String[] movieTimes = new String[time.size()];
			
			for(int j = 0; j < time.size(); j++){
				movieTimes[j] = time.get(j).text();
			}
			
			movietimeVO[i] = new MovieTimeVO();
			movietimeVO[i].setMovie(movieTitles[i]); // 영화 제목 설정
			movietimeVO[i].setTime(movieTimes); // 영화에 해당하는 시간표 저장
		}
		
		result.setCinemaTitle(vo.getTitle()); // 영화관 이름 저장
		result.setMovie(movieTitles);
		result.setMovieTimeVO(movietimeVO);
		
		return result;
	}
	
	// 롯데시네마 영화시간표 웹크롤링
	private TimetableVO lotteCrawling(CinemaSiteVO vo) throws ClientProtocolException, IOException{
		Document doc = getDoc(vo);
		
		// 영화관 목록 Elements로 가져오기
		Elements titles = doc.select("div.box_story_8 table tbody th a");
		
		// 그냥 String[]으로 할지 List<String> 으로 할지 ..?
		String[] movieTitles = new String[titles.size()];
		
		TimetableVO result = new TimetableVO();
		
		// 상영 영화 제목 배열에 저장
		for(int i = 0; i < titles.size(); i++){
			movieTitles[i] = titles.get(i).text();
		}

		// 상영 영화 시간표를 저장하기 위한 클래스
		MovieTimeVO[] movietimeVO = new MovieTimeVO[titles.size()];
		
		Elements times = doc.select("div.box_story_8 table tbody td");
		
		for(int i = 0; i < titles.size(); i++){
			String[] movieTimes = times.get(i).text().split("\\s*[|]\\s*");
			
			movietimeVO[i] = new MovieTimeVO();
			movietimeVO[i].setMovie(movieTitles[i]); // 영화 제목 설정
			movietimeVO[i].setTime(movieTimes); // 영화에 해당하는 시간표 저장
		}
		
		result.setCinemaTitle(vo.getTitle()); // 영화관 이름 저장
		result.setMovie(movieTitles);
		result.setMovieTimeVO(movietimeVO);
		
		return result;
	}
	
	private Document getDoc(CinemaSiteVO vo) throws ClientProtocolException, IOException{
		HttpPost http = new HttpPost(vo.getTimetable()); // 가져올 HTTP 주소 셋팅
		HttpClient httpClient = HttpClientBuilder.create().build(); // 가져오기를 실행할 클라이언트 객체 생성
		HttpResponse response = httpClient.execute(http); // 실행 및 실행 데이터를 Response 객체에 담음
		HttpEntity entity = response.getEntity(); // Response 받은 데이터 중, DOM 데이터를 가져와 Entity에 담음
		
		ContentType contentType = ContentType.getOrDefault(entity); // Charset을 알아내기 위해 DOM의 컨텐트 타입을 가져와 담고
		Charset charset = contentType.getCharset(); // Charset을 가져옴
		
		// DOM 데이터를 한 줄씩 읽기 위해 Reader에 담음
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		StringBuffer sb = new StringBuffer(); // 가져온 DOM 데이터를 담기위한 그릇
		
		// DOM 데이터 가져오기
		String line = "";
		while((line = br.readLine()) != null){
			sb.append(line + "\n");
		}
		
		// Jsoup으로 파싱
		return Jsoup.parse(sb.toString());
	}

	// 현재 상영작 정보를 가져오는 웹크롤링
	@Override
	public String[] showingCrawling() throws ClientProtocolException, IOException {
		// 가져올 HTTP 주소 셋팅
		HttpPost http = new HttpPost("http://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=reserve");
		HttpClient httpClient = HttpClientBuilder.create().build(); // 가져오기를 실행할 클라이언트 객체 생성
		HttpResponse response = httpClient.execute(http); // 실행 및 실행 데이터를 Response 객체에 담음
		HttpEntity entity = response.getEntity(); // Response 받은 데이터 중, DOM 데이터를 가져와 Entity에 담음
		
		ContentType contentType = ContentType.getOrDefault(entity); // Charset을 알아내기 위해 DOM의 컨텐트 타입을 가져와 담고
		Charset charset = contentType.getCharset(); // Charset을 가져옴
		
		// DOM 데이터를 한 줄씩 읽기 위해 Reader에 담음
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
		StringBuffer sb = new StringBuffer(); // 가져온 DOM 데이터를 담기위한 그릇
		
		// DOM 데이터 가져오기
		String line = "";
		while((line = br.readLine()) != null){
			sb.append(line + "\n");
		}
		
		Document doc = Jsoup.parse(sb.toString());
		
		// 상영 영화 목록 Elements로 가져오기
		Elements titles = doc.select("div.lst_wrap dt.tit a");
		
		// 크롤링하는 사이트는 예매 순으로 나열되있는 사이트이다.
		// 위에서부터 10개의 상영 영화 목록만 가져오도록 한다.
		String[] movieTitles = new String[10];
				
		// 상영 영화 제목 배열에 저장
		for(int i = 0; i < movieTitles.length; i++){
			movieTitles[i] = titles.get(i).text();
		}
				
		return movieTitles;
	}

	// 사용자가 원하는 시간표만 보여주기 위한 메소드
	@Override
	public TimetableVO search(TimetableVO vo, MovieSearchDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

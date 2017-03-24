package org.itner.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.itner.domain.MovieTimeVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class lotteCrawlingTest {

	private static final Logger logger = LoggerFactory.getLogger(lotteCrawlingTest.class);
	
	@Test
	public void lotteCrawlingTest() throws ClientProtocolException, IOException{
		String[] outprint = lotteCrawling("http://movie.naver.com/movie/bi/ti/running.nhn?code=435");
		for(int i = 0; i < outprint.length; i++){
			System.out.println(outprint[i]);
		}
	}
	
	private String[] lotteCrawling(String site) throws ClientProtocolException, IOException{
		HttpPost http = new HttpPost(site); // 가져올 HTTP 주소 셋팅
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
		Document doc = Jsoup.parse(sb.toString());
		
		// 영화관 목록 Elements로 가져오기
		Elements titles = doc.select("div.box_story_8 table tbody th a");
		
		// 그냥 String[]으로 할지 List<String> 으로 할지 ..?
		String[] movieTitles = new String[titles.size()];
		
		// 상영 영화 제목 배열에 저장
		for(int i = 0; i < titles.size(); i++){
			movieTitles[i] = titles.get(i).text();
		}
		
		// 상영 영화 시간표를 저장하기 위한 클래스
		String[] movietime = new String[titles.size()];

		// 상영 영화 시간표를 저장하기 위한 클래스
		MovieTimeVO[] movietimeVO = new MovieTimeVO[titles.size()];
		
		// 영화 시간은 select를 두번써서 분류되도록 ????
		Elements times = doc.select("div.box_story_8 table tbody td");
		
		String[] movieTimes = null;
		
		for(int i = 0; i < titles.size() - 2; i++){
			String[] imsi = times.get(i).text().split("[|]");
			movieTimes = new String[imsi.length];
			for(int j = 0; j < imsi.length; j++){
				movieTimes[j] = imsi[j].trim();
			}
		}
		/*
		result.setCinemaTitle(vo.getTitle()); // 영화관 이름 저장
		result.setMovie(movieTitles);
		result.setMovieTimeVO(movietimeVO);
		*/
		return movieTimes;
	}
}

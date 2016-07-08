package service;

/********* 식당 관련 데이터를 담기 위한 클래스 *********/

import java.util.Date;
import java.util.Map;

import model.*;

public class RestaurantRequest {
	
	private String station; // 주변 역
	private Integer score; // 평점
	private String name; // 상호명
	private String comment; // 한줄평
	private String member_nickname; // 닉네임
	
	public RestaurantRequest(String station,
						Integer score,
						String name,
						String comment,
						String member_nickname){
		this.station = station;
		this.score = score;
		this.name = name;
		this.comment = comment;
		this.member_nickname = member_nickname;
	}
	
	public String getStation(){
		return station;
	}
	
	public Integer getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	public String getComment(){
		return comment;
	}
	
	public String getNickname(){
		return member_nickname;
	}
	
	public void vaildate(Map<String, Boolean> errors){
		checkEmpty(errors, station, "station");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, comment, "comment");
		checkEmpty(errors, member_nickname, "member_nickname");
	}
	
	// value가 값이 없는 경우 errors 맵 객체의 fieldName 키에 TRUE를 값으로 추가한다.
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName){
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}

package service;

/********* 술집 관련 데이터를 담기 위한 클래스 *********/

import java.util.Map;

public class BarRequest {
	
	private String station;
	private Integer score;
	private String name;
	private String comment;
	private String member_nickname;
	
	public BarRequest(String station, Integer score, String name, String comment, String member_nickname){
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
	
	public void validate(Map<String, Boolean> errors){
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

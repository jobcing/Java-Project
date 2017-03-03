package org.itner.domain;

/*
 * cinema_site 테이블의 구조를 객체화 시킬 때 사용하는 CinemaSiteVO 클래스
 * 전체 영역에서 파라미터와 리턴 타입으로 사용됨
 */

public class CinemaSiteVO {
	private String name;
	private String timetable;
	private String reserve;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setTimetable(String timetable){
		this.timetable = timetable;
	}
	
	public void setReserve(String reserve){
		this.reserve = reserve;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getTimetable(){
		return this.timetable;
	}
	
	public String getReserve(){
		return this.reserve;
	}
}

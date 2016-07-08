package model;

/********* DB BAR 테이블의 데이터를 담을 때 사용할 클래스 *********/

import java.util.Date;

public class Bar {
	
	private Integer number;
	private String station;
	private Integer score;
	private String name;
	private String comment;
	private String member_nickname;
	private Date regDate;
	
	public Bar(Integer number,
			   String station,
			   Integer score,
			   String name,
			   String comment,
			   String member_nickname,
			   Date regDate){
		this.number = number;
		this.station = station;
		this.score = score;
		this.name = name;
		this.comment = comment;
		this.member_nickname = member_nickname;
		this.regDate = regDate;
	}
	
	public Integer getNumber(){
		return number;
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
	
	public Date getRegDate(){
		return regDate;
	}
}

package model;

/********* DB CAFE 테이블의 데이터를 담을 때 사용할 클래스 *********/

import java.util.Date;

public class Cafe {

	private Integer number; // 게시글 번호
	private String station; // 주변 역
	private Integer score; // 평점
	private String name; // 상호명
	private String comment; // 한줄평
	private String member_nickname; // 닉네임
	private Date regDate; // 게시글 올린 날짜

	public Cafe(Integer number,
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
	
	public String getStation() {
		return station;
	}

	public Integer getScore() {
		return score;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}
	
	public String getNickname(){
		return member_nickname;
	}
	
	public Date getRegDate(){
		return regDate;
	}
}

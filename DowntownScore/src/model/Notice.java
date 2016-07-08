package model;

/********* DB Notice 테이블의 데이터를 담을 때 사용할 클래스 *********/

import java.util.Date;

public class Notice {
	
	private Integer number; // 게시글 번호
	private Writer writer; // 작성자 클래스
	private String title; // 게시글 제목
	private Date regDate; // 작성일
	private Date modDate; // 수정일
	private int readCount; // 조회수
	
	public Notice(Integer number, Writer writer, String title, Date regDate, Date modDate, int readCount){
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modDate = modDate;
		this.readCount = readCount;
	}
	
	public Integer getNumber(){
		return number;
	}
	
	public Writer getWriter(){
		return writer;
	}
	
	public String getTitle(){
		return title;
	}
	
	public Date getRegDate(){
		return regDate;
	}
	
	public Date getModDate(){
		return modDate;
	}
	
	public int getReadCount(){
		return readCount;
	}
}

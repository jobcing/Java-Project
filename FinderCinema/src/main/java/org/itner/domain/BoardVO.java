package org.itner.domain;

import java.util.Date;

/*
 * review_board 테이블의 구조를 객체화 시킬 때 사용하는 BoardVO 클래스
 * 전체 영역에서 파라미터와 리턴 타입으로 사용됨
 */

public class BoardVO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
	
	public void setBno(Integer bno){
		this.bno = bno;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public void setWriter(String writer){
		this.writer = writer;
	}
	
	public void setRegdate(Date regdate){
		this.regdate = regdate;
	}
	
	public void setViewcnt(int viewcnt){
		this.viewcnt = viewcnt;
	}
	
	public void setReplycnt(int replycnt){
		this.replycnt = replycnt;
	}
	
	public Integer getBno(){
		return this.bno;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public String getWriter(){
		return this.writer;
	}
	
	public Date getRegdate(){
		return this.regdate;
	}
	
	public int getViewcnt(){
		return this.viewcnt;
	}
	
	public int getReplycnt(){
		return this.replycnt;
	}
}

package org.itner.domain;

import java.util.Date;

/*
 * review_reply 댓글 테이블 구조를 객체화 시킬 때 사용하는 BoardVO 클래스
 */

public class ReplyVO {

	private Integer rno;
	private Integer bno;
	private String replytext;
	private String replyer;
	
	private Date regdate;
	private Date updatedate;
	
	public void setRno(Integer rno){
		this.rno = rno;
	}
	
	public void setBno(Integer bno){
		this.bno = bno;
	}
	
	public void setReplytext(String replytext){
		this.replytext = replytext;
	}
	
	public void setReplyer(String replyer){
		this.replyer = replyer;
	}
	
	public void setRegdate(Date regdate){
		this.regdate = regdate;
	}
	
	public void setUpdatedate(Date updatedate){
		this.updatedate = updatedate;
	}
	
	public Integer getRno(){
		return this.rno;
	}
	
	public Integer getBno(){
		return this.bno;
	}
	
	public String getReplytext(){
		return this.replytext;
	}
	
	public String getReplyer(){
		return this.replyer;
	}
	
	public Date getRegdate(){
		return this.regdate;
	}
	
	public Date getUpdatedate(){
		return this.updatedate;
	}
}

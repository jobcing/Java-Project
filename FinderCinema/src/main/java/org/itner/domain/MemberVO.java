package org.itner.domain;

import java.util.Date;

/*
 * fc_member 테이블의 구조를 객체화 시킬 때 사용하는 MemberVO 클래스
 * 전체 영역에서 파라미터와 리턴 타입으로 사용됨
 */

public class MemberVO {

	private String member_id;
	private String member_pw;
	private String nickname;
	private Date regdate;
	
	public void setMember_id(String member_id){
		this.member_id = member_id;
	}
	
	public void setMember_pw(String member_pw){
		this.member_pw = member_pw;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public void setRegdate(Date regdate){
		this.regdate = regdate;
	}
	
	public String getMember_id(){
		return this.member_id;
	}
	
	public String getMember_pw(){
		return this.member_pw;
	}
	
	public String getNickname(){
		return this.nickname;
	}
	
	public Date getRegdate(){
		return this.regdate;
	}
}

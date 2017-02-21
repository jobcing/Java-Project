package org.itner.dto;

/*
 * 화면에서 전달되는 데이터를 수집하는 용도
 */

public class LoginDTO {

	private String member_id;
	private String member_pw;
	private boolean useCookie;
	
	public void setMember_id(String member_id){
		this.member_id = member_id;
	}
	
	public void setMember_pw(String member_pw){
		this.member_pw = member_pw;
	}
	
	public void setUseCookie(boolean useCookie){
		this.useCookie = useCookie;
	}
	
	public String getMember_id(){
		return this.member_id;
	}
	
	public String getMember_pw(){
		return this.member_pw;
	}
	
	public boolean isUseCookie(){
		return this.useCookie;
	}
}

package org.jpg.dto;

/*
 * 화면에서 전달되는 데이터를 수집하는 용도
 */

public class LoginDTO {

	private String classnum;
	private String regnum;
	private boolean useCookie;
	
	public void setClassnum(String classnum){
		this.classnum = classnum;
	}
	
	public void setRegnum(String regnum){
		this.regnum = regnum;
	}
	
	public void setUseCookie(boolean useCookie){
		this.useCookie = useCookie;
	}
	
	public String getClassnum(){
		return this.classnum;
	}
	
	public String getRegnum(){
		return this.regnum;
	}
	
	public boolean isUseCookie(){
		return this.useCookie;
	}
}

package service;

/********* 사용자가 작성한 회원가입 데이터를 담기 위한 클래스 *********/

import java.util.Map;

public class JoinRequest {

	private String member_id;
	private String member_pwd;
	private String member_nickname;
	private String member_age;
	private String confirmPassword;
	
	public String getId(){
		return member_id;
	}
	
	public void setId(String member_id){
		this.member_id = member_id;
	}
	
	public String getPwd(){
		return member_pwd;
	}
	
	public void setPwd(String member_pwd){
		this.member_pwd = member_pwd;
	}
	
	public String getConfirmPassword(){
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword){
		this.confirmPassword = confirmPassword;
	}
	
	public boolean isPasswordEqualToConfirm(){
		return member_pwd != null && member_pwd.equals(confirmPassword);
	}
	
	public String getNickname(){
		return member_nickname;
	}
	
	public void setNickname(String member_nickname){
		this.member_nickname = member_nickname;
	}
	
	public String getAge(){
		return member_age;
	}

	public void setAge(String member_age){
		this.member_age = member_age;
	}
	
	// 각 필드의 데이터가 유효한지 검사한다.
	public void vaildate(Map<String, Boolean> errors){
		checkEmpty(errors, member_id, "member_id");
		checkEmpty(errors, member_pwd, "member_pwd");
		checkEmpty(errors, confirmPassword, "confirmPassword");
		checkEmpty(errors, member_nickname, "member_nickname");
		
		if(!errors.containsKey("confirmPassword")){
			if(!isPasswordEqualToConfirm()){
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	
	// value가 값이 없는 경우 errors 맵 객체의 fieldName 키에 TRUE를 값으로 추가한다.
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName){
		if(value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}
}

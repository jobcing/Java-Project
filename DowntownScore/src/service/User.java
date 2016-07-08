package service;

/********* 패스워드 등 개인정보까지 가져올 필요 없으므로 Member와 별개로 User라는 클래스 따로 생성 *********/

public class User {
	
	private String member_id;
	private String member_nickname;
	private String member_age;
	
	public User(String member_id, String member_nickname, String member_age){
		this.member_id = member_id;
		this.member_nickname = member_nickname;
		this.member_age = member_age;
	}
	
	public String getId(){
		return member_id;
	}
	
	public String getNickname(){
		return member_nickname;
	}
	
	public String getAge(){
		return member_age;
	}
}

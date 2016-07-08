package model;

/********* 작성자의 정보를 담고있는 클래스 *********/

public class Writer {
	
	private String member_id; // 회원 아이디
	private String member_nickname; // 회원 닉네임
	
	public Writer(String member_id, String member_nickname){
		this.member_id = member_id;
		this.member_nickname = member_nickname;
	}
	
	public String getId(){
		return member_id;
	}
	
	public String getNickname(){
		return member_nickname;
	}
}

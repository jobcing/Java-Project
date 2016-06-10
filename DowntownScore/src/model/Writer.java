package model;

public class Writer {
	
	private String member_id;
	private String member_nickname;
	
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

package model;

/********* DB Member_Admin 테이블의 데이터를 담을 때 사용할 클래스 *********/

import java.util.Date;

public class Member {

	private String member_id; // 회원아이디 (primary key)
	private String member_pwd; // 비밀번호
	private String member_nickname; // 닉네임
	private String member_age; // 나이
	private Integer member_cnt; // 사용자가 작성한 게시글 수
	private Date regDate; // 가입날짜

	public Member(String member_id,
				  String member_pwd,
				  String member_nickname,
				  String member_age,
				  Integer member_cnt,
				  Date regDate){
		this.member_id = member_id;
		this.member_pwd = member_pwd;
		this.member_nickname = member_nickname;
		this.member_age = member_age;
		this.member_cnt = member_cnt;
		this.regDate = regDate;
	}

	public String getId() {
		return member_id;
	}

	public String getPwd() {
		return member_pwd;
	}

	public String getNickname() {
		return member_nickname;
	}

	public String getAge() {
		return member_age;
	}
	
	public Integer getCnt(){
		return member_cnt;
	}
	
	public Date getRegDate(){
		return regDate;
	}
	
	public boolean matchPassword(String pwd){
		return member_pwd.equals(pwd);
	}
}

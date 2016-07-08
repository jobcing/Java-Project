package service;

/********* 로그인 Service *********/

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.*;
import dao.*;
import model.*;
import util.*;

public class LoginService {

	private MemberDao memberDao = new MemberDao();
	
	public User login(String member_id, String member_pwd){
		try(Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(conn, member_id); // 해당 아이디가 테이블에 있는지 검사
			
			if(member == null){ // 해당 아이디가 테이블에 존재하지 않는다면
				throw new LoginFailException();
			}
			
			if(!member.matchPassword(member_pwd)){ // 비밀번호가 일치하지 않는다면
				throw new LoginFailException();
			}
			
			// User 객체 반환
			// Member는 비밀번호, 가입날짜등을 포함하고 있으므로 따로 User객체를 이용해서
			// 사용자가 사이트에서 필요한 정보만을 제공한다.
			return new User(member.getId(), member.getNickname(), member.getAge());
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

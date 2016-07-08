package service;

/********* 회원가입 Service *********/

import java.sql.*;
import java.util.Date;

import jdbc.*;
import dao.*;
import model.*;
import util.*;

public class JoinService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq){
		Connection conn = null;
		try{
			// DB 커넥션을 구하고 트랜잭션을 시작한다.
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// selectById() 메서드를 이용해서 joinReq.getId()에 해당하는 회원 데이터를 구한다.
			Member member = memberDao.selectById(conn, joinReq.getId());
			
			// 가입하려는 ID가 이미 존재하면 트랜잭션을 롤백하고 DuplicatedException을 발생시킨다.
			if(member != null){
				JdbcUtil.rollback(conn);
				throw new DuplicatedException();
			}
			
			// selectByNickname() 메서드를 이용해서 joinReq.getNickname()에 해당하는 회원 데이터를 구한다.
			Member member1 = memberDao.selectByNickname(conn, joinReq.getNickname());
			
			// 가입하려는 Nickname이 이미 존재하면 트랜잭션을 롤백하고 RuntimeException을 발생시킨다.
			if(member1 != null){
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			}
			
			memberDao.insert(conn, new Member(joinReq.getId(),
											  joinReq.getPwd(),
											  joinReq.getNickname(),
											  joinReq.getAge(),
											  0, // 처음 회원가입하면 작성한 게시글 수는 0으로 초기화.
											  new Date()));

			// 트랜잭션 커밋
			conn.commit();
		} catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally{
			JdbcUtil.close(conn);
		}
	}
}

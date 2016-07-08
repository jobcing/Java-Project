package dao;

/********* NoticeContent DB 테이블에 직접 접근하는 클래스 *********/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;
import util.*;

public class NoticeContentDao {
	// 테이블 삽입 메서드
	public NoticeContent insert(Connection conn, NoticeContent content) throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
			pstmt = conn.prepareStatement("insert into notice_content " 
					+ "(notice_no, content) values (?, ?)");
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount > 0){
				return content;
			} else{
				return null;
			}
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	// 선택한 번호에 해당된 게시글 내용을 리턴해주는 메서드
	public NoticeContent selectByNo(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Notice_content where notice_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			NoticeContent content = null;
			if(rs.next())
				content = new NoticeContent(rs.getInt("notice_no"), rs.getString("content"));
			
			return content;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 게시글 수정하고 DB 내용을 업데이트하는 메서드
	public int update(Connection conn, int no, String content) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("update Notice_content set content = ? " +
															"where notice_no = ?")){
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			
			return pstmt.executeUpdate();
		}
	}
	
	// 파라미터로 전달받은 게시글 번호를 이용해서 데이터를 삭제한다.
	public void delete(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			// 해당 번호 게시글 삭제
			pstmt = conn.prepareStatement("delete from Notice_content where notice_no = ?");
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			stmt = conn.createStatement();
			
			// mysql 쿼리 변수 설정 후 게시글 번호(auto_increment) 리넘버링
			stmt.executeUpdate("SET @notice_no := 0");
			stmt.executeUpdate("update Notice_content set notice_no = @notice_no := @notice_no + 1 order by notice_no");
			
			// 리넘버링되고 마지막 게시글 번호를 얻어온다.
			rs = stmt.executeQuery("select @notice_no");
			
			int auto_increment = 0;
			
			// if(rs.next()) 안하면 ERROR 발생
			if(rs.next()){
				auto_increment = rs.getInt(1);
			}

			// 다음 insert 게시글을 위해 auto_increment 초기화
			stmt.executeUpdate("alter table Notice_content auto_increment = " + auto_increment);
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
		}
	}
}

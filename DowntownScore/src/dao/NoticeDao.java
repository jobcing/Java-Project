package dao;

/********* NOTICE DB 테이블에 직접 접근하는 클래스 *********/

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import model.*;
import util.*;

public class NoticeDao {
	// 테이블 삽입 메서드
	public Notice insert(Connection conn, Notice notice) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("insert into Notice " +
					"(writer_id, writer_nickname, title, regdate, moddate, read_cnt) " +
					"values (?, ?, ?, ?, ?, 0)");
			pstmt.setString(1, notice.getWriter().getId());
			pstmt.setString(2, notice.getWriter().getNickname());
			pstmt.setString(3, notice.getTitle());
			pstmt.setTimestamp(4, toTimestamp(notice.getRegDate()));
			pstmt.setTimestamp(5, toTimestamp(notice.getModDate()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0){
				stmt = conn.createStatement();
				// notice 테이블에 추가된 최근 레코드의 주요키(primary key)를 구한다.
				rs = stmt.executeQuery("select last_insert_id() from notice");
				if(rs.next()){
					Integer newNum = rs.getInt(1); // 최신에 등록된 게시물 번호를 가져온다.
					// 데이터 저장에 성공하면 Notice를 반환
					return new Notice(newNum,
							notice.getWriter(),
							notice.getTitle(),
							notice.getRegDate(),
							notice.getModDate(),
							0);
				}
			}
			
			return null; // 데이터 저장 실패시 null값 리턴
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Timestamp toTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	// Notice 테이블의 전체 레코드 수를 반환하는 메서드
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from Notice");
			
			if(rs.next()){
				return rs.getInt(1);
			}
			
			return 0;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	// 원하는 범위의 게시글 목록을 읽어오는 메서드
	public List<Notice> select(Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Notice order by notice_no desc limit ?, ?");
			// limit 쿼리는 실행 결과 중 일부 레코드만 조회할 때 사용한다.
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			
			List<Notice> result = new ArrayList<>();
			
			while(rs.next()){
				result.add(convertNotice(rs));
			}
			
			return result;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 데이터 베이스에 저장되있는 데이터를 Notice객체로 변환시켜주는 메서드
	private Notice convertNotice(ResultSet rs) throws SQLException{
		return new Notice(rs.getInt("notice_no"),
				new Writer(rs.getString("writer_id"), rs.getString("writer_nickname")),
						rs.getString("title"),
						toDate(rs.getTimestamp("regdate")),
						toDate(rs.getTimestamp("moddate")),
						rs.getInt("read_cnt"));
	}
	
	private Date toDate(Timestamp timestamp){
		return new Date(timestamp.getTime());
	}
	
	// 특정 번호에 해당하는 게시글 데이터를 읽어오는 메서드
	public Notice selectByNo(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Notice where notice_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			Notice notice = null;
			
			if(rs.next())
				notice = convertNotice(rs);
				
			return notice;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 특전 번호 게시글 조회수를 증가시켜주는 메서드
	public void increaseReadCount(Connection conn, int no) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("update Notice set read_cnt = read_cnt + 1 " + 
															"where notice_no = ?")){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	// 파라미터로 전달받은 게시글 번호와 제목을 이용해서 데이터를 수정한다.
	public int update(Connection conn, int no, String title) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("update Notice set title = ?, moddate = now() " +
															"where notice_no = ?")){
			pstmt.setString(1, title);
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
			pstmt = conn.prepareStatement("DELETE from Notice where notice_no = ?");
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			stmt = conn.createStatement();
			
			// mysql 쿼리 변수 설정 후 게시글 번호(auto_increment) 리넘버링 
			stmt.executeUpdate("SET @notice_no := 0");
			stmt.executeUpdate("UPDATE Notice SET notice_no = @notice_no := @notice_no + 1 ORDER BY notice_no");
			
			// 리넘버링되고 마지막 게시글 번호를 얻어온다.
			rs = stmt.executeQuery("SELECT @notice_no");
			
			int auto_increment = 0;
			
			// if(rs.next()) 안하면 ERROR 발생
			if(rs.next()){
				auto_increment = rs.getInt(1);
			}
			
			// 다음 insert 게시글을 위해 auto_increment 초기화
			stmt.executeUpdate("ALTER TABLE Notice auto_increment = " + auto_increment);
			
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
}

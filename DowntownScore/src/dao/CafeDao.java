package dao;

/********* CAFE DB 테이블에 직접 접근하는 클래스 *********/

import java.sql.*;
import java.util.*;
import java.util.Date;

import jdbc.*;
import util.*;
import model.*;

public class CafeDao {
	// DAO 메서드를 실행할 때 Connection을 전달받는 방식
	// Cafe 테이블에 데이터 추가
	public Cafe insert(Connection conn, Cafe cafe) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("insert into Cafe " +
					"(station, score, name, comment, writer_nickname, regdate) " +
					"values (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, cafe.getStation());
			pstmt.setInt(2, cafe.getScore());
			pstmt.setString(3, cafe.getName());
			pstmt.setString(4, cafe.getComment());
			pstmt.setString(5, cafe.getNickname());
			pstmt.setTimestamp(6, toTimestamp(cafe.getRegDate()));
			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0){
				stmt = conn.createStatement();
				// Cafe 테이블에 추가된 최근 레코드의 주요키(primary key)를 구한다.
				rs = stmt.executeQuery("select last_insert_id() from Cafe");
				if(rs.next()){
					Integer newNum = rs.getInt(1); // 최신에 등록된 게시물 번호를 가져온다.
					// 데이터 저장에 성공하면 Cafe를 반환
					return new Cafe(newNum,
							cafe.getStation(),
							cafe.getScore(),
							cafe.getName(),
							cafe.getComment(),
							cafe.getNickname(),
							cafe.getRegDate());
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
	
	// Member 테이블 member_cnt 칼럼 update ( 작성자 게시글 수 + 1 )
	public void increaseCnt(Connection conn, String member_nickname) throws SQLException{
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("update Member set member_cnt = member_cnt + 1 where member_nickname = ?");
			pstmt.setString(1, member_nickname);
			
			pstmt.executeUpdate();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	// Cafe 테이블의 사용자가 입력한 station과 일치하는 레코드 수를 반환하는 메서드
	public int selectCount(Connection conn, String station) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select count(*) from Cafe where station = ?");
			pstmt.setString(1, station);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// Cafe 테이블의 사용자가 입력한 station, name과 일치하는 레코드 수를 반환하는 메서드 ( 오버로딩 )
	public int selectCount(Connection conn, String station, String name) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select count(*) from Cafe where station = ?"
							+ " and name like ?");
			pstmt.setString(1, station);
			pstmt.setString(2, "%" + name + "%");
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// Cafe 테이블에서 상호명이 같은 데이터가 있는지 조회한다.
	// 데이터가 존재하면 Cafe객체 ArrayList를 리턴한다.
	// 데이터가 존재하지 않는다면 null을 리턴한다.
	public List<Cafe> select(Connection conn, String station, String name, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Cafe where station = ?"
					+ " and name like ? order by cafe_no desc limit ?, ?");
			// 사용자가 입력한 station과 일치하는 레코드만 조회한다.
			// like % name % --> name이 포함되어있는 필드를 조회한다. 
			// limit 쿼리는 실행 결과 중 일부 레코드만 조회할 때 사용한다.
			pstmt.setString(1, station);
			pstmt.setString(2, "%" + name + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();
			
			List<Cafe> result = new ArrayList<>();
			
			while(rs.next()){
				result.add(convertCafe(rs));
			}
			
			return result;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 사용자가 상호명을 입력하지 않았을 때 실행되는 메서드 ( 오버로딩 )
	public List<Cafe> select(Connection conn, String station, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Cafe where station = ?"
					+ " order by cafe_no desc limit ?, ?");
			// 사용자가 입력한 station과 일치하는 레코드만 조회한다.
			// limit 쿼리는 실행 결과 중 일부 레코드만 조회할 때 사용한다.
			pstmt.setString(1, station);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			
			List<Cafe> result = new ArrayList<>();
			
			while(rs.next()){
				result.add(convertCafe(rs));
			}
			
			return result;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 데이터 베이스에 저장되있는 데이터를 Cafe객체로 변환시켜주는 메서드
	private Cafe convertCafe(ResultSet rs) throws SQLException {
		return new Cafe(rs.getInt("cafe_no"), rs.getString("station"), rs.getInt("score"),
							  rs.getString("name"), rs.getString("comment"), rs.getString("writer_nickname"),
							  toDate(rs.getTimestamp("regdate")));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	// 사용자가 입력한 주변역과 상호명에 해당하는 쿼리의 평점 평균을 구해주는 메서드
	public double average(Connection conn, String station, String name) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Cafe where station = ?"
					+ " and name like ?");
			// 사용자가 입력한 station, name과 일치하는 레코드만 조회한다.
			pstmt.setString(1, station);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			double scoreSum = 0;
			double count = 0;
			
			while(rs.next()){
				scoreSum = scoreSum + rs.getInt("score");
				count++;
			}
			
			return scoreSum / count;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	// 사용자가 입력한 주변역에 해당하는 쿼리의 평점 평균을 구해주는 메서드 ( 오버로딩 )
	public double average(Connection conn, String station) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Cafe where station = ?");
			// 사용자가 입력한 station과 일치하는 레코드만 조회한다.
			pstmt.setString(1, station);
			rs = pstmt.executeQuery();
			
			double scoreSum = 0;
			double count = 0;
			
			while(rs.next()){
				scoreSum = scoreSum + rs.getInt("score");
				count++;
			}
			
			return scoreSum / count;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}

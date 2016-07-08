package dao;

/********* BAR DB 테이블에 직접 접근하는 클래스 *********/

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;
import util.JdbcUtil;

public class BarDao {
	// Dao 메서드를 실행할 때 Connection을 전달받는 형식
	// 테이블 삽입 메서드
	public Bar insert(Connection conn, Bar bar) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("insert into Bar "
					+ "(station, score, name, comment, writer_nickname, regdate) " + "values (?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, bar.getStation());
			pstmt.setInt(2, bar.getScore());
			pstmt.setString(3, bar.getName());
			pstmt.setString(4, bar.getComment());
			pstmt.setString(5, bar.getNickname());
			pstmt.setTimestamp(6, toTimestamp(bar.getRegDate()));

			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from Bar");

				if (rs.next()) {
					Integer newNum = rs.getInt(1); // 최신에 등록된 게시물 번호를 가져온다.
					// 데이터 저장에 성공하면 Bar를 반환
					return new Bar(newNum, bar.getStation(), bar.getScore(), bar.getName(), bar.getComment(),
							bar.getNickname(), bar.getRegDate());
				}
			}

			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	// Member 테이블 member_cnt 칼럼 update ( 작성자 게시글 수 + 1 )
	public void increaseCnt(Connection conn, String member_nickname) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("update Member set member_cnt = member_cnt + 1 where member_nickname = ?");
			pstmt.setString(1, member_nickname);

			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	// Bar 테이블의 사용자가 입력한 station과 일치하는 레코드 수를 반환하는 메서드
	public int selectCount(Connection conn, String station) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select count(*) from Bar where station = ?");
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
	
	// Bar 테이블의 사용자가 입력한 station, name과 일치하는 레코드 수를 반환하는 메서드 ( 오버로딩 )
	public int selectCount(Connection conn, String station, String name) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select count(*) from Bar where station = ?"
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

	// 테이블에서 전달받은 상호명과 같은 레코드가 있는지 검색한다.
	public List<Bar> select(Connection conn, String station, String name, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"select * from Bar where station = ?" + " and name like ? order by bar_no limit ?, ?");

			pstmt.setString(1, station);
			pstmt.setString(2, "%" + name + "%");
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, size);
			rs = pstmt.executeQuery();

			List<Bar> result = new ArrayList<>();

			while (rs.next()) {
				result.add(convertBar(rs));
			}

			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 사용자가 상호명을 입력하지 않았을 때 실행되는 메서드 ( 오버로딩 )
	public List<Bar> select(Connection conn, String station, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from Bar where station = ?" + " order by bar_no desc limit ?, ?");
			// 사용자가 입력한 station과 일치하는 레코드만 조회한다.
			// limit 쿼리는 실행 결과 중 일부 레코드만 조회할 때 사용한다.
			pstmt.setString(1, station);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();

			List<Bar> result = new ArrayList<>();

			while (rs.next()) {
				result.add(convertBar(rs));
			}

			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 데이터 베이스에 저장되있는 데이터를 Bar객체로 변환시켜주는 메서드
	private Bar convertBar(ResultSet rs) throws SQLException {
		return new Bar(rs.getInt("bar_no"), rs.getString("station"), rs.getInt("score"), rs.getString("name"),
				rs.getString("comment"), rs.getString("writer_nickname"), toDate(rs.getTimestamp("regdate")));
	}

	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	// 사용자가 입력한 주변역과 상호명에 해당하는 쿼리의 평점 평균을 구해주는 메서드
	public double average(Connection conn, String station, String name) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from Bar where station = ?" + " and name like ?");
			// 사용자가 입력한 station, name과 일치하는 레코드만 조회한다.
			pstmt.setString(1, station);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();

			double scoreSum = 0;
			double count = 0;

			while (rs.next()) {
				scoreSum = scoreSum + rs.getInt("score");
				count++;
			}

			return scoreSum / count;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 사용자가 입력한 주변역에 해당하는 쿼리의 평점 평균을 구해주는 메서드 ( 오버로딩 )
	public double average(Connection conn, String station) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from Bar where station = ?");
			// 사용자가 입력한 station과 일치하는 레코드만 조회한다.
			pstmt.setString(1, station);
			rs = pstmt.executeQuery();

			double scoreSum = 0;
			double count = 0;

			while (rs.next()) {
				scoreSum = scoreSum + rs.getInt("score");
				count++;
			}

			return scoreSum / count;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}

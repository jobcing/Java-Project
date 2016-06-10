// DB member_admin에 접근하는 클래스

package dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import jdbc.*;
import util.*;
import model.*;

public class MemberDao {
	// DAO 메서드를 실행할 때 Connection을 전달받는 방식
	// 테이블에 동일한 데이터가 있는지 조회한다.
	// 데이터가 존재한다면 Member 객체를 생성하고 존재하지 않으면 null을 리턴한다.
	public Member selectById(Connection conn, String member_id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from Member where member_id = ?");
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("member_id"), rs.getString("member_pwd"),
						rs.getString("member_nickname"), rs.getString("member_age"), rs.getInt("member_cnt"),
						toDate(rs.getTimestamp("regdate")));

			}

			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// DAO 메서드를 실행할 때 Connection을 전달받는 방식
	// 테이블에 동일한 데이터가 있는지 조회한다.
	// 데이터가 존재한다면 Member 객체를 생성하고 존재하지 않으면 null을 리턴한다.
	public Member selectByNickname(Connection conn, String member_nickname) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from Member where member_nickname = ?");
			pstmt.setString(1, member_nickname);
			rs = pstmt.executeQuery();

			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("member_id"), rs.getString("member_pwd"),
						rs.getString("member_nickname"), rs.getString("member_age"), rs.getInt("member_cnt"),
						toDate(rs.getTimestamp("regdate")));

			}

			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}

	// 작성수가 높은 순서대로 조회한다. ( 랭킹 구하기 )
	public List<Member> selectByCnt(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from Member order by member_cnt DESC limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();

			List<Member> result = new ArrayList<>();

			while (rs.next()) {
				result.add(convertMember(rs));
			}

			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 데이터 베이스에 저장되있는 데이터를 Member객체로 변환시켜주는 메서드
	private Member convertMember(ResultSet rs) throws SQLException {
		return new Member(rs.getString("member_id"), rs.getString("member_pwd"), rs.getString("member_nickname"),
				rs.getString("member_age"), rs.getInt("member_cnt"), toDate(rs.getTimestamp("regdate")));
	}

	// Member 테이블의 전체 레코드 수를 반환하는 메서드
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from Member");

			if (rs.next()) {
				return rs.getInt(1);
			}

			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// Member 테이블에 데이터 추가
	public void insert(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into Member values(?, ?, ?, ?, ?, ?)")) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getNickname());
			pstmt.setString(4, member.getAge());
			pstmt.setInt(5, member.getCnt());
			pstmt.setTimestamp(6, new Timestamp(member.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}
}

package service;

/********* Ranking Service *********/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.MemberDao;
import jdbc.ConnectionProvider;
import model.Member;
import util.JdbcUtil;

public class RankingService {

	private MemberDao memberDao = new MemberDao();

	private int size = 30;

	// 등록된 게시글을 페이지 형식으로 출력하는 메서드
	public RankingPage getRankingPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = memberDao.selectCount(conn);
			// (pageNum - 1) * size 는 페이지의 시작 행 번호이다.
			// 예를 들어, 3페이지를 요청하면 시작 행 번호는 20이 된다.
			List<Member> content = memberDao.selectByCnt(conn, (pageNum - 1) * size, size);

			return new RankingPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

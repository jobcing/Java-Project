package service;

/********* 술집 데이터에 관련된 메서드를 담고있는 클래스 *********/

import java.sql.*;
import java.util.Date;
import java.util.List;

import dao.*;
import jdbc.*;
import util.*;
import model.*;

public class BarService {

	private BarDao barDao = new BarDao();

	public int write(BarRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Bar bar = toBar(req);
			Bar savedBar = barDao.insert(conn, bar);
			barDao.increaseCnt(conn, bar.getNickname()); // 작성자 게시글 수 증가

			if (savedBar == null)
				throw new RuntimeException("fali to insert Cafe");

			conn.commit();

			return savedBar.getNumber();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private Bar toBar(BarRequest barRequest) {
		Date now = new Date();

		return new Bar(null, barRequest.getStation(), barRequest.getScore(), barRequest.getName(),
				barRequest.getComment(), barRequest.getNickname(), now);
	}

	private int size = 10;

	// 등록된 게시글을 페이지 형식으로 출력하는 메서드
	public BarPage getBarPage(int pageNum, String station, String name) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 입력한 station, name에 해당하는 총 레코드를 구한다.
			int total = barDao.selectCount(conn, station, name);
			// (pageNum - 1) * size 는 페이지의 시작 행 번호이다.
			// 예를 들어, 3페이지를 요청하면 시작 행 번호는 20이 된다.
			List<Bar> content = barDao.select(conn, station, name, (pageNum - 1) * size, size);

			return new BarPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 사용자가 상호명을 입력하지 않았을때 실행되는 메서드 ( 오버로딩 )
	public BarPage getBarPage(int pageNum, String station) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			// 입력한 station에 해당하는 총 레코드수를 구한다.
			int total = barDao.selectCount(conn, station);
			// (pageNum - 1) * size 는 페이지의 시작 행 번호이다.
			// 예를 들어, 3페이지를 요청하면 시작 행 번호는 20이 된다.
			List<Bar> content = barDao.select(conn, station, (pageNum - 1) * size, size);

			return new BarPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 사용자가 입력한 주변역과 상호명에 해당하는 쿼리의 평점 평균을 구하는 메서드
	public double getAvg(String station, String name) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return barDao.average(conn, station, name);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 사용자가 입력한 주변역에 해당하는 쿼리의 평점 평균을 구하는 메서드
	public double getAvg(String station) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			return barDao.average(conn, station);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

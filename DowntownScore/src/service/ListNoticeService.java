package service;

/********* 게시글 리스트 Service *********/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.*;
import jdbc.ConnectionProvider;
import model.*;
import jdbc.*;

public class ListNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private int size = 10;
	
	public NoticePage getNoticePage(int pageNum){
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = noticeDao.selectCount(conn);
			// (pageNum - 1) * size 는 페이지의 시작 행 번호이다.
			// 예를 들어, 3페이지를 요청하면 시작 행 번호는 20이 된다.
			List<Notice> content = noticeDao.select(conn, (pageNum - 1) * size, size);
			
			return new NoticePage(total, pageNum, size, content);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

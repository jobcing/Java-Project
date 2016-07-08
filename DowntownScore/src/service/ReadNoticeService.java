package service;

/********* 게시글 읽을 때 사용되는 메서드를 가지고 있는 클래스 *********/

import java.sql.Connection;
import java.sql.SQLException;

import dao.*;
import model.*;
import jdbc.*;

public class ReadNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	// 두번째 파라미터 increaseReadCount는 조회수를 증가시킬지 여부를 지정한다.
	public NoticeData getNotice(int noticeNum, boolean increaseReadCount){
		try(Connection conn = ConnectionProvider.getConnection()){
			Notice notice = noticeDao.selectByNo(conn, noticeNum);
			
			if(notice == null)
				throw new NoticeNotFoundException();
			
			NoticeContent content = contentDao.selectByNo(conn, noticeNum);
			
			if(content == null)
				throw new NoticeContentNotFoundException();
			
			if(increaseReadCount)
				noticeDao.increaseReadCount(conn, noticeNum);
			
			return new NoticeData(notice, content);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

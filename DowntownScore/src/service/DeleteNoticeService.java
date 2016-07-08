package service;

/********* 게시글 삭제 Service **********/

import java.sql.Connection;
import java.sql.SQLException;

import dao.*;
import model.*;
import util.JdbcUtil;
import jdbc.*;

public class DeleteNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public void remove(int no){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// 게시글 번호에 해당하는 Notice객체를 구한다.
			Notice notice = noticeDao.selectByNo(conn, no);
			
			// 해당 번호를 가진 게시글이 존재하지 않으면 익셉션을 발생시킨다.
			if(notice == null)
				throw new NoticeNotFoundException();
		
			// delete() 메서드를 해당 번호를 가진 게시글을 삭제한다.
			noticeDao.delete(conn, no);
			contentDao.delete(conn, no);
			
			conn.commit();
		} catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(PermissionDeniedException e){
			JdbcUtil.rollback(conn);
			throw e;
		} finally{
			JdbcUtil.close(conn);
		}
	}
}

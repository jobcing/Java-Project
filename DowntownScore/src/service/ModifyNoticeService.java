package service;

/********* 게시글 수정 Service *********/

import java.sql.Connection;
import java.sql.SQLException;

import dao.*;
import model.*;
import util.JdbcUtil;
import jdbc.*;

public class ModifyNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	public void modify(ModifyRequest modReq){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// 게시글 번호에 해당하는 Notice객체를 구한다.
			Notice notice = noticeDao.selectByNo(conn, modReq.getNoticeNumber());
			
			// 해당 번호를 가진 게시글이 존재하지 않으면 익셉션을 발생시킨다.
			if(notice == null)
				throw new NoticeNotFoundException();
			
			// 수정하려는 사용자가 게시글을 수정할 수 있는지 검사한다.
			if(!canModify(modReq.getId(), notice))
				throw new PermissionDeniedException();
			
			// update() 메서드를 이용해서 제목과 내용을 수정한다.
			noticeDao.update(conn, modReq.getNoticeNumber(), modReq.getTitle());
			contentDao.update(conn, modReq.getNoticeNumber(), modReq.getContent());
			
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
	
	// 게시글을 수정할 수 있는지 검사하는 기능을 하는 메서드
	// 수정하려는 사용자 ID가 게시글 작성자 ID와 동일하면 true 리턴
	private boolean canModify(String member_id, Notice notice){
		return notice.getWriter().getId().equals(member_id);
	}
}

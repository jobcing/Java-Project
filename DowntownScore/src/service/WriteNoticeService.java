package service;

/********* 게시글 작성 Service *********/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import dao.*;
import jdbc.ConnectionProvider;
import model.*;
import util.*;

public class WriteNoticeService {
	
	private NoticeDao noticeDao = new NoticeDao();
	private NoticeContentDao contentDao = new NoticeContentDao();
	
	// write 메서드는 WriteRequest를 이용해서 게시글을 등록하고 결과로 게시글 번호를 리턴한다.
	public Integer write(WriteRequest req){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작
			
			Notice notice = toNotice(req);
			Notice savedNotice = noticeDao.insert(conn, notice);
			if(savedNotice == null){
				throw new RuntimeException("fail to insert notice");
			}
			
			// Notice 번호는 Auto_increase 로 되있으므로 값을 NoticeDao를 통해 얻어와야한다.
			// getNumber()가 주요 키를 얻어오는 것.
			NoticeContent content = new NoticeContent(savedNotice.getNumber(), req.getContent());
			NoticeContent savedContent = contentDao.insert(conn, content);
			if(savedContent == null){
				throw new RuntimeException("fail to insert notice_content");
			}
			
			conn.commit();
			
			// 새로 추가한 게시글 번호를 리턴한다.
			return savedNotice.getNumber();
		} catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		} finally{
			JdbcUtil.close(conn);
		}
	}
	
	// 현재 시각 구하려고 따로 Constructor 클래스를 만든건가???
	private Notice toNotice(WriteRequest req){
		Date now = new Date();
		return new Notice(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;
import util.*;

public class NoticeContentDao {
	
	public NoticeContent insert(Connection conn, NoticeContent content) throws SQLException{
		PreparedStatement pstmt = null;
		
		try{
			pstmt = conn.prepareStatement("insert into notice_content " 
					+ "(notice_no, content) values (?, ?)");
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount > 0){
				return content;
			} else{
				return null;
			}
		} finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public NoticeContent selectByNo(Connection conn, int no) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from Notice_content where notice_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			NoticeContent content = null;
			if(rs.next())
				content = new NoticeContent(rs.getInt("notice_no"), rs.getString("content"));
			
			return content;
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int update(Connection conn, int no, String content) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement("update Notice_content set content = ? " +
															"where notice_no = ?")){
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			
			return pstmt.executeUpdate();
		}
	}
}

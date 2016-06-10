package util;

import java.sql.*;

public class JdbcUtil {
	
	public static void close(ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			} catch(SQLException ee){}
		}
	}
	
	public static void close(Statement stmt){
		if(stmt != null){
			try{
				stmt.close();
			} catch(SQLException ee){}
		}
	}
	
	public static void close(Connection conn){
		if(conn != null){
			try{
				conn.close();
			} catch(SQLException ee){}
		}
	}
	
	public static void rollback(Connection conn){
		if(conn != null){
			try{
				conn.rollback();
			} catch(SQLException ee){}
		}
	}
}

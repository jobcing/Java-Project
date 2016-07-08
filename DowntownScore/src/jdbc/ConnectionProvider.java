package jdbc;

/********** 커넥션을 구할 때 사용되는 클래스 **********/
// 커넥션풀에서 커넥션을 제공

import java.sql.*;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:downtown_score");
	}
}

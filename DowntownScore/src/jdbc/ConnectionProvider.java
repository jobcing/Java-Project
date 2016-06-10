// 커넥션을 구할 때 사용할 클래스

package jdbc;

import java.sql.*;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:downtown_score");
	}
}

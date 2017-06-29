package org.jpg.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MariaDBConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/sobike";
	private static final String USER = "jobang";
	private static final String PW = "wh18020";
	
	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection conn = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(conn);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

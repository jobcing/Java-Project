package jdbc;

/********** 커넥션풀을 초기화해주는 클래스 **********/

import java.io.*;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.*;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.*;

// ServletContextListener : 컨텍스트 초기화 파라미터를 읽어들여 파라미터를 인자로 하여 객체를 생성한다.
// 서블릿이 객체를 공유할 수 있다.
public class DBCPInitListener implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent sce){
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig"); // 컨텍스트 파라미터 불러오기
		Properties prop = new Properties(); 
		
		try{
			prop.load(new StringReader(poolConfig)); // 프로퍼티에 로드
		} catch(IOException e){
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}
	
	private void loadJDBCDriver(Properties prop){ // 드라이버 로딩
		String driverClass = prop.getProperty("jdbcdriver"); // 드라이버 불러오기
		try{
			Class.forName(driverClass);
		} catch(ClassNotFoundException e){
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	
	private void initConnectionPool(Properties prop){
		try{
			// 프로퍼티 각각 키에서 값 얻어오기
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");
			
			// 커넥션 풀이 새로운 커넥션을 생성할 때 사용할 커넥션 팩토리를 생성한다.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			// PoolableConnection을 생성하는 팩토리를 생성한다. DBCP는 커넥션 풀에 커넥션을 보관할 때 PoolabelConnection을
			// 사용한다. 이 클래스는 실제 커넥션을 담고있으며 close()메소드를 실행하면 실제 커넥션을 종료하지 않고
			// 풀에 반환한다.
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			String validationQuery = prop.getProperty("validationQuery");
			// 커넥션이 유효한지 여부를 검사할 때 사용할 쿼리를 지정한다.
			if(validationQuery != null && !validationQuery.isEmpty()){
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			
			// 커넥션 풀의 설정 정보를 설정한다.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L); // 유휴 커넥션 검사주기
			poolConfig.setTestWhileIdle(true); // 풀에 보관중인 커넥션이 유효한지 검사할지 여부
			poolConfig.setMinIdle(4); // 커넥션 최소 개수
			poolConfig.setMaxTotal(50); // 커넥션 최대 개수
			
			// 커넥션 풀을 생성한다. 커넥션풀을 생성할 때 사용할 팩토리와 커넥션 풀 설정을 파라미터로 전달받는다.
			GenericObjectPool<PoolableConnection> connectionPool =
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool); // 팩토리에도 생성한 커넥션 풀을 연결한다.
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool); // 커넥션풀을 등록한다.
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	private int getIntProperty(Properties prop, String propName, int defaultValue){
		String value = prop.getProperty(propName);
		if(value == null) return defaultValue;
		
		return Integer.parseInt(value);
	}
	
	public void contextDestroyed(ServletContextEvent sce){
		
	}
}

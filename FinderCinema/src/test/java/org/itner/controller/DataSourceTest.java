package org.itner.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* 2017.02.03 DataSource 테스트 성공 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
	
	@Inject
	DataSource ds;
	
	@Test
	public void testConnection() throws Exception{
		try(Connection conn = ds.getConnection()){
			System.out.println(conn);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}

package service;

/********* 식당 데이터에 관련된 메서드를 담고있는 클래스 *********/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.*;
import jdbc.ConnectionProvider;
import model.*;
import util.*;

public class RestaurantService {
	
	private RestaurantDao restaurantDao = new RestaurantDao();
	
	// write 메서드는 RestaurantRequest를 이용해서 게시글을 등록하고 결과로 게시글 번호를 리턴한다.
	public int write(RestaurantRequest req){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작
			
			Restaurant restaurant = toRestaurant(req);
			Restaurant savedRes = restaurantDao.insert(conn, restaurant);
			restaurantDao.increaseCnt(conn, restaurant.getNickname()); // 작성자 게시글 수 증가
			
			if(savedRes == null){
				throw new RuntimeException("fail to insert restaurant");
			}
			
			conn.commit();
			
			return savedRes.getNumber();
			// 새로 추가한 게시글 번호를 리턴한다.
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
	
	// 현재 시각 구하려고 따로 메서드를 만든건가???
	private Restaurant toRestaurant(RestaurantRequest req){
		Date now = new Date();
		return new Restaurant(null, req.getStation(), req.getScore(), req.getName(),
							req.getComment(), req.getNickname(), now);
	}
	

	private int size = 10;
	
	// 등록된 게시글을 페이지 형식으로 출력하는 메서드
	public RestaurantPage getRestaurantPage(int pageNum, String station, String name){
		try(Connection conn = ConnectionProvider.getConnection()){
			// 입력한 station, name에 해당하는 총 레코드를 구한다.
			int total = restaurantDao.selectCount(conn, station, name);
			// (pageNum - 1) * size 는 페이지의 시작 행 번호이다.
			// 예를 들어, 3페이지를 요청하면 시작 행 번호는 20이 된다.
			List<Restaurant> content = restaurantDao.select(conn, station, name, (pageNum - 1) * size, size);
			
			return new RestaurantPage(total, pageNum, size, content);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	// 사용자가 상호명을 입력하지 않았을때 실행되는 메서드 ( 오버로딩 )
	public RestaurantPage getRestaurantPage(int pageNum, String station){
		try(Connection conn = ConnectionProvider.getConnection()){
			// 입력한 station에 해당하는 총 레코드수를 구한다.
			int total = restaurantDao.selectCount(conn, station);
			// (pageNum - 1) * size 는 페이지의 시작 행 번호이다.
			// 예를 들어, 3페이지를 요청하면 시작 행 번호는 20이 된다.
			List<Restaurant> content = restaurantDao.select(conn, station, (pageNum - 1) * size, size);
			
			return new RestaurantPage(total, pageNum, size, content);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	// 사용자가 입력한 주변역과 상호명에 해당하는 쿼리의 평점 평균을 구하는 메서드
	public double getAvg(String station, String name) throws SQLException{
		try(Connection conn = ConnectionProvider.getConnection()){
			return restaurantDao.average(conn, station, name);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	// 사용자가 입력한 주변역에 해당하는 쿼리의 평점 평균을 구하는 메서드
	public double getAvg(String station) throws SQLException{
		try(Connection conn = ConnectionProvider.getConnection()){
			return restaurantDao.average(conn, station);
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

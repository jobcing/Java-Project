package org.itner.persistence;

import java.util.List;

import org.itner.domain.BoardVO;

/*
 * MyBatis를 이용해서 데이터베이스 reivew_board에 직접적으로 접근하는 BoardDAO
 */

public interface BoardDAO {
	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
}

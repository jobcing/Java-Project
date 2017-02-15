package org.itner.service;

import java.util.List;

import org.itner.domain.BoardVO;
import org.itner.domain.Criteria;

/*
 * 컨트롤러와 DAO를 연결해주는 BoardService (비즈니스 계층)
 */

public interface BoardService {
	public void register(BoardVO board) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void modify(BoardVO board) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<BoardVO> listPage(Criteria cri) throws Exception;
	
	public int listCountPage(Criteria cri) throws Exception;
}

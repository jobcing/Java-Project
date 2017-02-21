package org.itner.service;

import java.util.List;

import javax.inject.Inject;

import org.itner.domain.BoardVO;
import org.itner.domain.Criteria;
import org.itner.persistence.BoardDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/*
 * BoardService 인터페이스를 구현한 클래스
 */

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;

	@Override
	public void register(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardVO read(Integer bno) throws Exception {
		dao.updateViewCnt(bno);
		
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}
	
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		return dao.listPage(cri);
	}

	@Override
	public int listCountPage(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

}

package org.itner.service;

import java.util.List;

import javax.inject.Inject;

import org.itner.domain.Criteria;
import org.itner.domain.ReplyVO;
import org.itner.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

/*
 * ReplyService 인터페이스를 구현한 클래스
 */

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Override
	public void registReply(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		dao.delete(rno);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return dao.count(bno);
	}

}

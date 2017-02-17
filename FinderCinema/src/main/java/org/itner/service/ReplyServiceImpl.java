package org.itner.service;

import java.util.List;

import javax.inject.Inject;

import org.itner.domain.Criteria;
import org.itner.domain.ReplyVO;
import org.itner.persistence.BoardDAO;
import org.itner.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * ReplyService 인터페이스를 구현한 클래스
 */

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO replyDao;
	
	@Inject
	private BoardDAO boardDao;
	
	@Transactional
	@Override
	public void registReply(ReplyVO vo) throws Exception {
		replyDao.create(vo);
		boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return replyDao.listPage(bno, cri);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		replyDao.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		int bno = replyDao.getBno(rno);
		
		replyDao.delete(rno);
		boardDao.updateReplyCnt(bno, -1);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return replyDao.count(bno);
	}

}

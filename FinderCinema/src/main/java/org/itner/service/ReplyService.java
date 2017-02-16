package org.itner.service;

import java.util.List;

import org.itner.domain.Criteria;
import org.itner.domain.ReplyVO;

/*
 * 컨트롤러와 DAO를 연결해주는 ReplyService
 */

public interface ReplyService {

	public void registReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(Integer rno) throws Exception;
	
	public int count(Integer bno) throws Exception;
}

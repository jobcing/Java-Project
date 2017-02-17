package org.itner.persistence;

import java.util.List;

import org.itner.domain.Criteria;
import org.itner.domain.ReplyVO;

/*
 * MyBatis를 이용해서 데이터베이스 reivew_reply에 직접적으로 접근하는 BoardDAO
 */

public interface ReplyDAO {
	
	public void create(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
	public int getBno(Integer rno) throws Exception;
}

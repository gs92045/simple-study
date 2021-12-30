package kr.co.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring.domain.Reply;
import kr.co.spring.domain.ReplyVO;
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.mybatis.mapper.ReplyMapper;

@Repository
@Transactional
public class ReplyRepository {
	@Autowired
	private ReplyMapper mapper;
	
	public void save(ReplySaveForm parameter) {
		mapper.save(parameter);
	}
	
	public List<ReplyVO> getList(int boardSeq) {
		return mapper.getList(boardSeq);
	}
	
	public void updateOrder(int boardSeq, int order) {
		mapper.updateOrder(boardSeq, order);
	}
	
	public int countReply(int boardSeq){
		return mapper.countReply(boardSeq);
	}
}

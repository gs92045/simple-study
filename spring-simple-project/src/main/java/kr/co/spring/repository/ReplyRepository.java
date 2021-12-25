package kr.co.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.spring.domain.Reply;
import kr.co.spring.domain.ReplySaveForm;
import kr.co.spring.mybatis.mapper.ReplyMapper;

@Repository
public class ReplyRepository {
	@Autowired
	private ReplyMapper mapper;
	
	public void save(ReplySaveForm parameter) {
		mapper.save(parameter);
	}
	
	public Reply getReply(int replySeq) {
		return mapper.getReply(replySeq);
	}
	
	public List<Reply> list(int parameter) {
		return mapper.list(parameter);
	}
}

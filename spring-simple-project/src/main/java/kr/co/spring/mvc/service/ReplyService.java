package kr.co.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.domain.Reply;
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.http.Form.ReplyVO;
import kr.co.spring.repository.ReplyRepository;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository repository;

	public void save(ReplySaveForm parameter) {		
		repository.save(parameter);
	}
	
	public ReplyVO getReply(int replySeq) {
		return repository.getReply(replySeq);
	}
	
	public List<ReplyVO> getList(int boardSeq){
		return repository.getList(boardSeq);
	}
}

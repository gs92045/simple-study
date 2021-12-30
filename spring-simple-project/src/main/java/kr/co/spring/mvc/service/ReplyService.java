package kr.co.spring.mvc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.domain.ReplyVO;
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.repository.ReplyRepository;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository repository;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//parent reply save
	//댓글 등록
	public void prSave(ReplySaveForm parameter) {
		int boardSeq = parameter.getBoardSeq();
		int order = repository.countReply(boardSeq);
		
		//댓글 추가 - 게시판에 등록된 댓굴 수 + 1 
		parameter.setOrder(order+1);
		repository.save(parameter);
	}
	
	//child reply save
	//대댓글 등록
	public void crSave(ReplySaveForm parameter) {
		/*
		 * 부모 댓글 다음 순서로 온다
		 * 최신 대댓글이 이전 대댓글보다 먼저 오도록한다
		 */
		int cOrder = parameter.getOrder();
		
		//대댓글이 들어갈 순서에서부터 +1
		repository.updateOrder(parameter.getBoardSeq(),cOrder);	
		repository.save(parameter);
	}
	
		
	public List<ReplyVO> getList(int boardSeq){
		return repository.getList(boardSeq);
	}
}

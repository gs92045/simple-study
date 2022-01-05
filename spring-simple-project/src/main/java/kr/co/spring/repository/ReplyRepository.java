package kr.co.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring.domain.ReplyDto;
import kr.co.spring.mybatis.mapper.ReplyMapper;


/**
 * 댓글 Repository
 * Controller, Service와 기능 내용이 비슷
 * @author kodin
 *
 */

@Repository
@Transactional
public class ReplyRepository {
	@Autowired
	private ReplyMapper mapper;
	
	
	public void save(ReplyDto parameter) {
		mapper.save(parameter);
	}
	
	public List<ReplyDto> getList(int boardSeq,int offset,int limit) {
		return mapper.getList(boardSeq,offset,limit);
	}
	
	
	/**
	 * 답글의 순서를 맞춰 리스트 조정하기위한 메소드
	 * @param boardSeq
	 * @param order
	 */
	public void updateOrder(int boardSeq, int order) {
		mapper.updateOrder(boardSeq, order);
	}
	
	public int countReply(int boardSeq){
		return mapper.countReply(boardSeq);
	}
	
	/**
	 * 특정 댓글 조회
	 *   - 필요로 하는 기능이 있었는데 안쓰는 기능
	 *   - 추후에 회원이 작성한 특정 댓글을 보기 위한? 기능으로 사용예정?
	 * @param replySeq
	 * @return
	 */
	public ReplyDto getReply(int replySeq) {
		return mapper.getReply(replySeq);
	}
}

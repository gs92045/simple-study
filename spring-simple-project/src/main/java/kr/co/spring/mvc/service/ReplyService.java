package kr.co.spring.mvc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.domain.ReplyDto;
import kr.co.spring.repository.BoardRepository;
import kr.co.spring.repository.ReplyRepository;
import kr.co.spring.repository.UserRepository;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository repository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	

	
	/**
	 * 특정 게시글의 댓글 리스트 조회
	 *   - 추후 페이징 처리를 위해 offset 과 limit를 추가해둠
	 * 
	 * @param boardSeq
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<ReplyDto> getList(int boardSeq,int offset,int limit){
		return repository.getList(boardSeq,offset,limit);
	}
	
	
	/**
	 * 
	 * 댓글 등록 
	 * @param parameter
	 */
	public void save(ReplyDto parameter) {
		ReplyDto reply = parameter; 
		int boardSeq = reply.boardSeq;
		int userSeq = userRepository.userGetById(parameter.getUserId());
			
		parameter.setUserSeq(userSeq);
		/**
		* 댓글이면 0, 답글이면 0이 아닌 수를 반환
		*	답글은 order와 parent,depth가 함께 넘어오기때문에 1값을 더해준다
		*	댓글은 최신 것이 오름차순
		*	답글은 최신 것은 내림차순 
		*/
		if(reply.getParent() != 0) {
			reply.setOrder(reply.getOrder()+1);
			reply.setDepth(reply.getDepth()+1);
			repository.updateOrder(boardSeq, reply.getOrder());
		}
		else {
			int order = repository.countReply(reply.boardSeq);
			reply.setOrder(order);
		}
		
		//해당 게시글의 댓글 수 증가
		boardRepository.addComment(boardSeq);
		repository.save(reply);
	}
	
	
	/**
	 * 특정 게시글의 댓글 수를 반환
	 * @param boardSeq
	 * @return
	 */
	public int countReply(int boardSeq) {
		return repository.countReply(boardSeq);
	}
}

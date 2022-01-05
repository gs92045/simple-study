package kr.co.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring.domain.ReplyDto;
import kr.co.spring.mybatis.mapper.ReplyMapper;


/**
 * ��� Repository
 * Controller, Service�� ��� ������ ���
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
	 * ����� ������ ���� ����Ʈ �����ϱ����� �޼ҵ�
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
	 * Ư�� ��� ��ȸ
	 *   - �ʿ�� �ϴ� ����� �־��µ� �Ⱦ��� ���
	 *   - ���Ŀ� ȸ���� �ۼ��� Ư�� ����� ���� ����? ������� ��뿹��?
	 * @param replySeq
	 * @return
	 */
	public ReplyDto getReply(int replySeq) {
		return mapper.getReply(replySeq);
	}
}

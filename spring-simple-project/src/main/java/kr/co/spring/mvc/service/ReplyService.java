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
	//��� ���
	public void prSave(ReplySaveForm parameter) {
		int boardSeq = parameter.getBoardSeq();
		int order = repository.countReply(boardSeq);
		
		//��� �߰� - �Խ��ǿ� ��ϵ� �� �� + 1 
		parameter.setOrder(order+1);
		repository.save(parameter);
	}
	
	//child reply save
	//���� ���
	public void crSave(ReplySaveForm parameter) {
		/*
		 * �θ� ��� ���� ������ �´�
		 * �ֽ� ������ ���� ���ۺ��� ���� �������Ѵ�
		 */
		int cOrder = parameter.getOrder();
		
		//������ �� ������������ +1
		repository.updateOrder(parameter.getBoardSeq(),cOrder);	
		repository.save(parameter);
	}
	
		
	public List<ReplyVO> getList(int boardSeq){
		return repository.getList(boardSeq);
	}
}

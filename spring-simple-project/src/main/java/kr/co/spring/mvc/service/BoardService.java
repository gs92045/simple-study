package kr.co.spring.mvc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.domain.BoardVO;
import kr.co.spring.http.Form.BoardRegistryForm;
import kr.co.spring.http.Form.BoardUpdateForm;
import kr.co.spring.repository.BoardRepository;


/**
 * �Խñ� ���� Service
 * @author kodin
 *
 */

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepository repository;
	
	/**
	 * Ư�� �Խñ� ��ȸ
	 *   - �ش� �Խñ��� ��ȸ���� �߰�
	 * @param boardSeq
	 * @return
	 */
	public BoardVO get(int boardSeq) {
		this.addView(boardSeq);
		return repository.get(boardSeq);
	}
	
	/**
	 * �Խñ� ����Ʈ ��ȸ
	 *   - ����¡ ó���� �Ͽ� ����ڰ� ������ �������� �Խñ� ����Ʈ�� ��ȸ
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<BoardVO> getList(int offset,int limit){
		return repository.getList(offset,limit);
	}
	
	
	/**
	 * �Խñ� ���
	 * @param board
	 */
	public void save(BoardRegistryForm board) {
		repository.save(board);
	}
	
	/**
	 * �Խñ� ����
	 * @param board
	 */
	public void update(BoardUpdateForm board) {
		repository.update(board);
	}
	
	/**
	 * �Խñ� ����
	 * @param board
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
	
	/**
	 * �Խñ� ��� �� �����ϱ� ���� �޼ҵ�
	 * @param board
	 */
	public void addComment(int boardSeq) {
		repository.addComment(boardSeq);
	}
	
	/**
	 * �Խñ� ��ȸ���� �����ϱ� ���� �޼ҵ�
	 * @param board
	 */
	public void addView(int boardSeq) {
		repository.addView(boardSeq);
	}
	
	
	/**
	 * �˻�� ���� �ش� �Խñ۵��� �˻�
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<BoardVO> getSearch(String keyword,int offset,int limit) {
		return repository.getSearch(keyword,offset,limit);
	}

}

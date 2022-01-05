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
 * 게시글 관련 Service
 * @author kodin
 *
 */

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepository repository;
	
	/**
	 * 특정 게시글 조회
	 *   - 해당 게시글의 조회수를 추가
	 * @param boardSeq
	 * @return
	 */
	public BoardVO get(int boardSeq) {
		this.addView(boardSeq);
		return repository.get(boardSeq);
	}
	
	/**
	 * 게시글 리스트 조회
	 *   - 페이징 처리를 하여 사용자가 지정한 페이지의 게시글 리스트를 조회
	 * 
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<BoardVO> getList(int offset,int limit){
		return repository.getList(offset,limit);
	}
	
	
	/**
	 * 게시글 등록
	 * @param board
	 */
	public void save(BoardRegistryForm board) {
		repository.save(board);
	}
	
	/**
	 * 게시글 수정
	 * @param board
	 */
	public void update(BoardUpdateForm board) {
		repository.update(board);
	}
	
	/**
	 * 게시글 삭제
	 * @param board
	 */
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
	
	/**
	 * 게시글 댓글 수 증가하기 위한 메소드
	 * @param board
	 */
	public void addComment(int boardSeq) {
		repository.addComment(boardSeq);
	}
	
	/**
	 * 게시글 조회수를 증가하기 위한 메소드
	 * @param board
	 */
	public void addView(int boardSeq) {
		repository.addView(boardSeq);
	}
	
	
	/**
	 * 검색어를 통해 해당 게시글들을 검색
	 * @param keyword
	 * @param offset
	 * @param limit
	 * @return
	 */
	public List<BoardVO> getSearch(String keyword,int offset,int limit) {
		return repository.getSearch(keyword,offset,limit);
	}

}

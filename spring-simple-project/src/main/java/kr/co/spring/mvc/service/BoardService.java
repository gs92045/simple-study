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

@Service
public class BoardService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BoardRepository repository;
	
	public BoardVO get(int boardSeq) {
		this.addView(boardSeq);
		return repository.get(boardSeq);
	}
	
	public List<BoardVO> getList(int offset,int limit){
		return repository.getList(offset,limit);
	}
	
	public void save(BoardRegistryForm board) {
		logger.info("repo-save : {} ",board);
		repository.save(board);
	}
	
	public void update(BoardUpdateForm board) {
		repository.update(board);
	}
	
	public void delete(int boardSeq) {
		repository.delete(boardSeq);
	}
	
	public void addComment(int boardSeq) {
		repository.addComment(boardSeq);
	}
	
	public void addView(int boardSeq) {
		repository.addView(boardSeq);
	}
	
	public List<BoardVO> getSearch(String keyword) {
		return repository.getSearch(keyword);
	}

}

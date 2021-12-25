package kr.co.spring.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.spring.domain.Board;
import kr.co.spring.domain.BoardSaveForm;
import kr.co.spring.domain.BoardUpdateForm;
import kr.co.spring.repository.BoardRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository repository;
	
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}
	
	public List<Board> getList(){
		return repository.getList();
	}
	
	public void save(BoardSaveForm board) {
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
}

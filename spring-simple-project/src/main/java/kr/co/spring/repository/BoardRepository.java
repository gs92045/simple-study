package kr.co.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.spring.domain.Board;
import kr.co.spring.domain.BoardSaveForm;
import kr.co.spring.domain.BoardUpdateForm;
import kr.co.spring.mybatis.mapper.BoardMapper;

@Repository
public class BoardRepository {
	
	@Autowired
	private BoardMapper mapper;
	
	public Board get(int boardSeq) {
		return mapper.get(boardSeq);
	}
	
	public Board getById(String userId) {
		return mapper.getById(userId);
	}
	
	public List<Board> getList(){
		return mapper.getList();
	}
	
	public void save(BoardSaveForm board) {
		mapper.save(board);
	}
	
	public void update(BoardUpdateForm board) {
		mapper.update(board);
	}
	
	public void delete(int boardSeq) {
		mapper.delete(boardSeq);
	}
	
	public void addComment(int boardSeq) {
		mapper.addComment(boardSeq);
	}
}

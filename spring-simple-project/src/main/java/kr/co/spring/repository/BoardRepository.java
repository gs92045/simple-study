package kr.co.spring.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.spring.domain.Board;
import kr.co.spring.domain.BoardSaveForm;
import kr.co.spring.domain.BoardUpdateForm;
import kr.co.spring.http.Form.BoardVO;
import kr.co.spring.mvc.controller.BoardRegistryForm;
import kr.co.spring.mybatis.mapper.BoardMapper;

@Repository
public class BoardRepository {
	
	@Autowired
	private BoardMapper mapper;
	
	public BoardVO get(int boardSeq) {
		
		return mapper.get(boardSeq);
	}
	
	public List<BoardVO> getSearch(String keyword) {
		return mapper.getSearch(keyword);
	}
	
	public BoardVO getById(String userId) {
		return mapper.getById(userId);
	}
	
	public List<BoardVO> getList(int offset,int limit){
		return mapper.getList(offset,limit);
	}
	
	public void save(BoardRegistryForm board) {
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
	
	public void add(int boardSeq) {
		mapper.addComment(boardSeq);
	}
	
	
	public int getCount() {
		return mapper.getCount();
	}
	
	
	
}

package kr.co.spring.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;

import kr.co.spring.domain.Reply;
import kr.co.spring.domain.ReplyVO;
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.mvc.service.BoardService;
import kr.co.spring.mvc.service.ReplyService;
import kr.co.spring.mvc.service.UserService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Gson gson;
	@Autowired
	private ReplyService service;
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;
	
	/*
	 * 상세 조회에서 댓글 등록 기능
	 * 회원관련된 기능은 아직 미구현
	 * 
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("commentRegForm") ReplySaveForm parameter,Model model) {
		//댓글 등록을 위한 객체전달
		logger.info("comentRegForm : {}",parameter);
		//임시 설정-회원관리 기능구현 후 삭제
		if(parameter.getUserId() == null) {
			parameter.setUserId("test1");
		}
			
		//외래키 저장을 위해 userId가 아닌 userSeq를 저장한다.
		// -> 외래키와 함께 userId를 가지고 있는것이 테이블을 불러오지 않고 더 좋지않을까?
		int userSeq = userService.userGetById(parameter.getUserId());
		int boardSeq = parameter.getBoardSeq();
		
		parameter.setUserSeq(userSeq);
		
		//댓글과 대댓글에 따라 저장
		//댓글 리스트의 순서를 변경해야하기때문 
		if(parameter.parent == 0) {
			service.prSave(parameter);
		}else {
			service.crSave(parameter);
		}
		
		//게시판의 댓글 수 추가
		boardService.addComment(boardSeq);
	
		//댓글 등록 후 새로 등록된 댓글 리스트를 반환
		List<ReplyVO> recent = service.getList(boardSeq);
		model.addAttribute("commentList",recent);
		return "/board/detailPage :: #commentSpace";
	}
}

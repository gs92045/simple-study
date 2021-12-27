package kr.co.spring.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.Gson;

import kr.co.spring.domain.Reply;
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.http.Form.ReplyVO;
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
	
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/save")
	@ResponseBody
	public String save(@ModelAttribute("commentRegForm") ReplySaveForm parameter) {
		if(parameter.getUserId() == null) {
			parameter.setUserId("test1");
		}
		int userSeq = userService.userGetById(parameter.getUserId());
		parameter.setUserSeq(userSeq);
		
		//댓글을 저장 후 해당 댓글 번호 반환(저장)
		service.save(parameter);
		//댓글을 단 게시판의 번호
		logger.info("reply : {}",parameter);
		
		int boardSeq = parameter.getBoardSeq();
		//게시판의 댓글 수 추가
		boardService.addComment(boardSeq);
		
		//댓글 등록 후 새로 등록된 댓글 리스트를 반환
		List<ReplyVO> recent = service.getList(boardSeq);
		
		return gson.toJson(recent);
	}
}

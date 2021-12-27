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
		
		//����� ���� �� �ش� ��� ��ȣ ��ȯ(����)
		service.save(parameter);
		//����� �� �Խ����� ��ȣ
		logger.info("reply : {}",parameter);
		
		int boardSeq = parameter.getBoardSeq();
		//�Խ����� ��� �� �߰�
		boardService.addComment(boardSeq);
		
		//��� ��� �� ���� ��ϵ� ��� ����Ʈ�� ��ȯ
		List<ReplyVO> recent = service.getList(boardSeq);
		
		return gson.toJson(recent);
	}
}

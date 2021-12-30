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
	 * �� ��ȸ���� ��� ��� ���
	 * ȸ�����õ� ����� ���� �̱���
	 * 
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("commentRegForm") ReplySaveForm parameter,Model model) {
		//��� ����� ���� ��ü����
		logger.info("comentRegForm : {}",parameter);
		//�ӽ� ����-ȸ������ ��ɱ��� �� ����
		if(parameter.getUserId() == null) {
			parameter.setUserId("test1");
		}
			
		//�ܷ�Ű ������ ���� userId�� �ƴ� userSeq�� �����Ѵ�.
		// -> �ܷ�Ű�� �Բ� userId�� ������ �ִ°��� ���̺��� �ҷ����� �ʰ� �� ����������?
		int userSeq = userService.userGetById(parameter.getUserId());
		int boardSeq = parameter.getBoardSeq();
		
		parameter.setUserSeq(userSeq);
		
		//��۰� ���ۿ� ���� ����
		//��� ����Ʈ�� ������ �����ؾ��ϱ⶧�� 
		if(parameter.parent == 0) {
			service.prSave(parameter);
		}else {
			service.crSave(parameter);
		}
		
		//�Խ����� ��� �� �߰�
		boardService.addComment(boardSeq);
	
		//��� ��� �� ���� ��ϵ� ��� ����Ʈ�� ��ȯ
		List<ReplyVO> recent = service.getList(boardSeq);
		model.addAttribute("commentList",recent);
		return "/board/detailPage :: #commentSpace";
	}
}

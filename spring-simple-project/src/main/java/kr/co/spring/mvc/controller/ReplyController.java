package kr.co.spring.mvc.controller;


import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.google.gson.Gson;

import kr.co.spring.domain.ReplyDto;

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
	public String save(@ModelAttribute("replySave") ReplyDto reply ,
			HttpServletRequest req,
			Model model) {
		logger.info("parent : {} ", reply);
		
		Enumeration<String> test = req.getParameterNames();
		while(true) {
			if(!test.hasMoreElements()) break;
			logger.info("string : {}",test.nextElement());
		}
		
		ReplyDto parameter = reply;
		int boardSeq = parameter.getBoardSeq();
		
		//��� �Ǵ� ��� ����
		service.save(parameter);
		
		//��� ��� �� ���� ��ϵ� ��� ����Ʈ�� ��ȯ
		List<ReplyDto> recent = service.getList(boardSeq);
		for (ReplyDto replyDto : recent) {
			logger.info("list : {} ",replyDto);
		}
		model.addAttribute("commentList",recent);
		return "/board/detailPage :: #commentSpace";
	}
	
	@GetMapping("/test")
	public String test() {
		return "redirect:/board/list/1";
	}
	
}

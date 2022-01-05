package kr.co.spring.mvc.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.spring.domain.ReplyDto;
import kr.co.spring.mvc.service.ReplyService;

/**
 * ��� ���� ��Ʈ�ѷ�
 * 
 *  ����� ������� �񵿱� ��ſ��� �ٷ� ���ŵ����ʴ�? ���װ� �־��µ�
 *  �ش� �̽��� ó���ϱ� ���ؼ� ����¡ ó���� �ʿ�
 * 
 * @author kodin
 *
 */

@Controller
@RequestMapping("/reply")
public class ReplyController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ReplyService service;
	
	@PostMapping("/save")
	public String save(@ModelAttribute("replySave") ReplyDto reply,Model model) {
	
		ReplyDto parameter = reply;
		int boardSeq = parameter.getBoardSeq();
		int offset = 0;
		int limit = service.countReply(boardSeq);
		
		//��� �Ǵ� ��� ����
		service.save(parameter);
		
		//��� ��� �� ���� ��ϵ� ��� ����Ʈ�� ��ȯ
		List<ReplyDto> recent = service.getList(boardSeq,offset,limit);
		
		model.addAttribute("commentList",recent);
		return "/board/detailPage :: #commentSpace";
	}	
		
}

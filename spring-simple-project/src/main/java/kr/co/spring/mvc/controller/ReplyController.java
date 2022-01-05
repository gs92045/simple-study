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
 * 댓글 관련 컨트롤러
 * 
 *  댓글이 많은경우 비동기 통신에서 바로 갱신되지않는? 버그가 있었는데
 *  해당 이슈를 처리하기 위해서 페이징 처리가 필요
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
		
		//댓글 또는 답글 저장
		service.save(parameter);
		
		//댓글 등록 후 새로 등록된 댓글 리스트를 반환
		List<ReplyDto> recent = service.getList(boardSeq,offset,limit);
		
		model.addAttribute("commentList",recent);
		return "/board/detailPage :: #commentSpace";
	}	
		
}

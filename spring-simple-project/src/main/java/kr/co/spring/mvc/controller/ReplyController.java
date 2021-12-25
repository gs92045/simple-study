package kr.co.spring.mvc.controller;

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
import kr.co.spring.domain.ReplySaveForm;
import kr.co.spring.mvc.service.BoardService;
import kr.co.spring.mvc.service.ReplyService;

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
		
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/save")
	@ResponseBody
	public String save(@ModelAttribute("formData") ReplySaveForm parameter) {
		logger.info("reply : {}",parameter);

		service.save(parameter);
		
		int boardSeq = parameter.getBoardSeq();
		boardService.addComment(boardSeq);
		
		Reply recent = service.getReply(parameter.getReplySeq());
		
		return gson.toJson(recent);
	}
}

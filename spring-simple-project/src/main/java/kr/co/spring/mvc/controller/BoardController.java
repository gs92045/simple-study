package kr.co.spring.mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.spring.domain.Board;
import kr.co.spring.domain.BoardSaveForm;
import kr.co.spring.domain.BoardUpdateForm;
import kr.co.spring.domain.Reply;
import kr.co.spring.domain.ReplySaveForm;
import kr.co.spring.mvc.service.BoardService;
import kr.co.spring.mybatis.mapper.ReplyMapper;
import kr.co.spring.repository.ReplyRepository;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private ReplyRepository replyRepository;
	
	private final Logger logger =LoggerFactory.getLogger(getClass());
	
	//ui 테스트용
	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("type","Test String");
		return "/board/test";
	}
	
	@GetMapping("/get/{boardSeq}")
	public String get(@PathVariable int boardSeq, Model model) {
		Board board = service.get(boardSeq);
		ReplySaveForm reply = new ReplySaveForm();
		reply.setBoardSeq(board.getBoardSeq());
				
		if(board.getComments()!=0) {
			List<Reply> replyList = replyRepository.list(boardSeq);
			model.addAttribute("replyList",replyList);
		}
		
		model.addAttribute(board);
		model.addAttribute("reply",reply);
		return "/board/get";
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		List<Board> board = service.getList();
		for(Board t : board) {
			logger.info("t : {} {} {} {}",t.getBoardSeq(),t.getTitle(),t.getContents(),t.getUserId());
			
		}
		model.addAttribute("boards",board);
		return "/board/list";
	}
	
	@GetMapping("/form/save")
	public String registryBoard(Model model) {
		model.addAttribute("board",new Board());
		return "/board/form/save";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("board") BoardSaveForm board, Model model) {
		logger.info("board : {}",board);
		service.save(board);
		return "redirect:/board/list";
	}
	
	@GetMapping("/form/update/{boardSeq}")
	public String updateBoard(@PathVariable int boardSeq,Model model) {
		model.addAttribute("board",service.get(boardSeq));
		return "/board/form/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute("board") BoardUpdateForm board) {
		int seq = board.getBoardSeq();
		service.update(board);
		String url = String.format("redirect:/board/get/%d", seq);
		return url;
	}
	
	@GetMapping("/delete/{boardSeq}")
	public String delete(@PathVariable int boardSeq) {
		service.delete(boardSeq);
		return "redirect:/board/list";
	}
}	

package kr.co.spring.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import kr.co.spring.domain.BoardUpdateForm;
import kr.co.spring.http.Form.BoardPage;
import kr.co.spring.http.Form.BoardVO;
import kr.co.spring.http.Form.ReplySaveForm;
import kr.co.spring.http.Form.ReplyVO;
import kr.co.spring.mvc.service.BoardService;
import kr.co.spring.repository.BoardRepository;
import kr.co.spring.repository.ReplyRepository;
import kr.co.spring.repository.UserRepository;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired 
	private BoardRepository repository;
	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private UserRepository userRepository;
	private final Logger logger =LoggerFactory.getLogger(getClass());
	
	//�Խù� ����ϱ� - �Է��� ���� �����۰�ü ����
	@GetMapping("/registry")
	public String boardRegistry(Model model) {
		model.addAttribute("boardRegistryForm",new BoardRegistryForm());
		return "/board/boardRegistryPage";
	}
	
	//�Խù� ����ϱ� - db�� ����
	@PostMapping("/save")
	public String save(@ModelAttribute("boardRegistryForm") BoardRegistryForm parameter , Model model) {
		int userSeq = userRepository.userGetById(parameter.getUserId());
		parameter.setUserSeq(userSeq);
		logger.info("boardController-save : {} {}",parameter,userSeq);
		service.save(parameter);
		
		return "redirect:/board/list/1";
	}
	
	
	//(�ӽ�)���� ������, �Խù� ����Ʈ ��ȸ 
	@GetMapping("/list/{page}")
	public String getList(@PathVariable int page, Model model) {
		List<BoardVO> lists = new ArrayList<BoardVO>();
		
		//����¡ ó��-�⺻ 3��
		int offset = (page-1) * 3;
		int limit = 3;
		int count = repository.getCount();
		BoardPage boardPage = new BoardPage(page,count,offset,limit);
		
		logger.info("boardController-list : {} .. {}",boardPage,lists);
		lists.addAll(service.getList(offset,limit));
		
		model.addAttribute("lists",lists);
		model.addAttribute("page",boardPage);
		return "/board/MainPage";
	}

	//�� ��ȸ ������
	/*
	 *	- ��� ����Ʈ
	 *  - ��� �ۼ�
	 *  - (�̱���) ȸ�� �α���
	 */
	@GetMapping("/get/{boardSeq}")
	public String get(@PathVariable int boardSeq, Model model) throws ParseException {
		BoardVO board = service.get(boardSeq);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toReg = transFormat.format(transFormat.parse(board.getRegDate()));
		board.setRegDate(toReg);
		
		if(board.getUpDate() != null) {
			String toUp = transFormat.format(transFormat.parse(board.getUpDate()));
			board.setUpDate(toUp);
		}
		
		ReplySaveForm reply = new ReplySaveForm();
		reply.setBoardSeq(board.getBoardSeq());
		logger.info("test board : {}",board);		
		
		if(board.getComments() != 0) {
			List<ReplyVO> replyList = replyRepository.getList(boardSeq);
			model.addAttribute("commentList",replyList);
			logger.info("list : {} ",replyList);
		}
		
		model.addAttribute("post",board);
		model.addAttribute("commentRegForm",reply);
		return "/board/detailPage";
	}
	
	
	//�����ʿ�
	@GetMapping("/updateForm/{boardSeq}")
	public String updateForm(@PathVariable int boardSeq, Model model) {
		BoardVO board = service.get(boardSeq);
		BoardUpdateForm boardUpdateForm = new BoardUpdateForm();
		
		boardUpdateForm.setBoardSeq(boardSeq);
		boardUpdateForm.setContents(board.getContents());
		boardUpdateForm.setTitle(board.getTitle());
		boardUpdateForm.setUserId(board.getUserId());
		
		model.addAttribute("boardUpdateForm",boardUpdateForm);
		
		return "/board/boardUpdatePage";
	}
	
	
	//�����ʿ�
	@PostMapping("/update")
	public String update(@ModelAttribute("boardUpdateForm") BoardUpdateForm boardUpdateForm) {
		int seq = boardUpdateForm.getBoardSeq();
		
		service.update(boardUpdateForm);
		
		String url = String.format("redirect:/board/get/%d", seq);
		return url;
	}
	
	//�����ʿ�
	@GetMapping("/delete/{boardSeq}")
	public String delete(@PathVariable int boardSeq) {
		service.delete(boardSeq);
		return "redirect:/board/list/1";
	}
}	

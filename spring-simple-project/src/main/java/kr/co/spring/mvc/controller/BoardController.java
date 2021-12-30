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

import kr.co.spring.domain.BoardVO;
import kr.co.spring.domain.ReplyVO;
import kr.co.spring.http.Form.BoardPage;
import kr.co.spring.http.Form.BoardRegistryForm;
import kr.co.spring.http.Form.BoardUpdateForm;
import kr.co.spring.http.Form.ReplySaveForm;
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
	
	//게시물 등록하기 - 입력을 위한 폼전송객체 전달
	@GetMapping("/registry")
	public String boardRegistry(Model model) {
		model.addAttribute("boardRegistryForm",new BoardRegistryForm());
		return "/board/boardRegistryPage";
	}
	
	//게시물 등록하기 - db에 저장
	//등록후 최신 리스트로 redirect
	@PostMapping("/save")
	public String save(@ModelAttribute("boardRegistryForm") BoardRegistryForm parameter , Model model) {
		int userSeq = userRepository.userGetById(parameter.getUserId());
		parameter.setUserSeq(userSeq);
		service.save(parameter);
		return "redirect:/board/list/1";
	}
	
	
	//(임시)메인 페이지, 게시물 리스트 조회 
	@GetMapping("/list/{page}")
	public String getList(@PathVariable int page, Model model) {
		List<BoardVO> lists = new ArrayList<BoardVO>();
		
		//페이징 처리-기본 3개
		//컨트롤러단이 아닌 서비스단에서 만들어주거나
		//공통 페이징 처리를 만들도록하자
		int offset = (page-1) * 3;
		int limit = 3;
		int count = repository.getCount();
		BoardPage boardPage = new BoardPage(page,count,offset,limit);
		
		lists.addAll(service.getList(offset,limit));
		
		//게시글 리스트객체 전달
		model.addAttribute("lists",lists);
		//페이징 갯수 전달
		model.addAttribute("page",boardPage);
		return "/board/MainPage";
	}

	//상세 조회 페이지
	/*
	 *	- 댓글 리스트
	 *  - 댓글 작성
	 *  - (미구현) 회원 로그인
	 *  리팩토링 필요
	 *  기능이 여러개가 있으며 이는 분리를 해줘야한다.
	 */
	@GetMapping("/get/{boardSeq}")
	public String get(@PathVariable int boardSeq, Model model) throws ParseException {
		BoardVO board = service.get(boardSeq);
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String toReg = transFormat.format(transFormat.parse(board.getRegDate()));
		board.setRegDate(toReg);
		
		//수정된 게시판인지 체크
		if(board.getUpDate() != null) {
			String toUp = transFormat.format(transFormat.parse(board.getUpDate()));
			board.setUpDate(toUp);
		}
		
		//해당 게시글에 댓글을 등록하기 위한 객체
		ReplySaveForm reply = new ReplySaveForm();
		reply.setBoardSeq(board.getBoardSeq());
		
		//해당 게시글에 등록돼있는 댓글 리스트 불러오기
		if(board.getComments() != 0) {
			List<ReplyVO> replyList = replyRepository.getList(boardSeq);
			model.addAttribute("commentList",replyList);
		}
		
		model.addAttribute("post",board);
		model.addAttribute("commentRegForm",reply);
		return "/board/detailPage";
	}
	
	
	//게시글 수정 폼
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
	
	
	//수정된 게시글 등록
	@PostMapping("/update")
	public String update(@ModelAttribute("boardUpdateForm") BoardUpdateForm boardUpdateForm) {
		int seq = boardUpdateForm.getBoardSeq();
		
		service.update(boardUpdateForm);
		
		String url = String.format("redirect:/board/get/%d", seq);
		return url;
	}
	
	//게시글 삭제
	@GetMapping("/delete/{boardSeq}")
	public String delete(@PathVariable int boardSeq) {
		service.delete(boardSeq);
		return "redirect:/board/list/1";
	}
}	

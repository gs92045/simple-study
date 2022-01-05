package kr.co.spring.http.Form;


import lombok.Data;
/**
 * 페이징 처리를 위한 클래스
 * 
 *  게시판, 댓글 페이징 처리를 위해 공통 클래스로 변경필요
 *  페이징 객체를 보낸뒤 프론트 단에서 newer 와 older 를 처리하도록 하였는데 더 좋은 폼을 찾아봐야할듯
 * 
 * @author kodin
 *
 */

@Data
public class BoardPage {
	public int count;
	public int offset;
	public int limit;
	
	
	//요청받은 페이지에서 이전 페이지
	public int prePage;
	//현재 요청받은 페이지
	public int current;
	//마지막 페이지보다 이전 페이지
	public int overPage;
	//마지막 페이지
	public int endPage;
	
	public BoardPage(int current, int count, int offset,int limit) {
		this.current = current;
		this.count = count;
		this.offset = offset;
		this.limit = limit;
		calRange();
	}
	
	private void calRange() {
		
		endPage = count % 5 == 0 ? count/5 : count/5 +1;
		prePage = (1 < current-5) ? (current-5) : 1; 
		overPage = (current + 5 < endPage ) ? current + 5 : endPage;
	}	
}

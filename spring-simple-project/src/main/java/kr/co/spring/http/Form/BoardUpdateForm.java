package kr.co.spring.http.Form;

import lombok.Data;
/**
 * 게시글 수정 폼전송객체
 * @author kodin
 *
 */
@Data
public class BoardUpdateForm {
	int boardSeq;
	String userId;
	String title;
	String contents;
}

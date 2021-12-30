package kr.co.spring.http.Form;

import lombok.Data;

@Data
public class BoardUpdateForm {
	int boardSeq;
	String userId;
	String title;
	String contents;
}

package kr.co.spring.domain;

import lombok.Data;

@Data
public class BoardUpdateForm {
	int boardSeq;
	String title;
	String contents;
}

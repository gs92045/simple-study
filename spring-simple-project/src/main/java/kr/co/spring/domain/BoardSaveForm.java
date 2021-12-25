package kr.co.spring.domain;

import lombok.Data;

@Data
public class BoardSaveForm {
	String userId;
	String title;
	String contents;
}

package kr.co.spring.domain;


import lombok.Data;
@Data
public class BoardVO {
	/*
	 * 기본 전송 폼
	 */
	
	int boardSeq;
	String title;
	String contents;
	String userId;
	
	int comments;
	
	String regDate;
	String upDate;
}

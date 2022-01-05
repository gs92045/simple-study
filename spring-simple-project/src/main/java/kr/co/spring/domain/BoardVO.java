package kr.co.spring.domain;


import lombok.Data;
@Data
public class BoardVO {
	
	int boardSeq;
	String title;
	String contents;
	String userId;
	
	int comments;
	
	String regDate;
	String upDate;
}

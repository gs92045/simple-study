package kr.co.spring.http.Form;


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

package kr.co.spring.domain;

import lombok.Data;

@Data
public class ReplyDto {
	public int replySeq;
	public int boardSeq;
	
	public int userSeq;
	
	public String userId;
	
	
	public String contents;
	
	
	public String regDate;
	
	public String upDate;

	public int parent;
	
	public int depth;
	
	public int order;
	
}

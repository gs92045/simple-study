package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	public int replySeq;
	public int boardSeq;
	
	public String userSeq;
	public String userId;
	public String contents;
	
	public Date regDate;
	public Date upDate;

	//´ë´ñ±Û ´Ù´Â ´ñ±Û¹øÈ£
	public int parent;
	public int depth;
	public int order;
}

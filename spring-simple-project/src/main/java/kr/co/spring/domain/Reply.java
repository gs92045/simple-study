package kr.co.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	public int replySeq;
	public int userSeq;
	public int boardSeq;
	
	public String contents;
	
	public Date regDate;
	public Date upDate;
	public Date delDate;
}

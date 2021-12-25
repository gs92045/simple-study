package kr.co.spring.domain;

import lombok.Data;

@Data
public class Reply {
	public int replySeq;
	public String title;
	public String contents;
	public int userSeq;
	public int boardSeq;
}

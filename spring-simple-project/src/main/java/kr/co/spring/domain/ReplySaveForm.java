package kr.co.spring.domain;

import lombok.Data;

@Data
public class ReplySaveForm {
	public int replySeq;
	public String title;
	public String contents;
	//public String userId;
	//public String userSeq;
	public int boardSeq;
}

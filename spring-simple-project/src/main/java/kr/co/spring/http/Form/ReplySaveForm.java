package kr.co.spring.http.Form;

import lombok.Data;

@Data
public class ReplySaveForm {
	public int replySeq;
	public int userSeq;
	public int boardSeq;
	
	public String userId;
	public String contents;
	
	public int parent;
	public int depth;
	public int order;	
}


